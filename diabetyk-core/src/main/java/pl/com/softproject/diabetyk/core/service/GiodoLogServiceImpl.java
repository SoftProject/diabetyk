package pl.com.softproject.diabetyk.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.com.softproject.diabetyk.core.dao.GiodoLogDAO;
import pl.com.softproject.diabetyk.core.enums.GiodoOperationType;
import pl.com.softproject.diabetyk.core.model.GiodoLog;
import pl.com.softproject.diabetyk.core.model.UserData;

import java.util.Calendar;
import java.util.List;

/**
 * Class GiodoLogServiceImpl
 *
 * @author Marcin Jasinski {@literal <mkjasinski@gmail.com>}
 */
@Service
@Transactional
public class GiodoLogServiceImpl implements GiodoLogService {

    @Autowired
    private GiodoLogDAO giodoLogDAO;

    @Autowired
    private UserDataService userDataService;

    @Override
    @Transactional(readOnly = true)
    public GiodoLog getElement(Long id) {

        return giodoLogDAO.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<GiodoLog> getElements() {

        return giodoLogDAO.findAll();
    }

    @Override
    public void add(GiodoLog element) {

        giodoLogDAO.save(element);
    }

    @Override
    public void update(GiodoLog element) {

        giodoLogDAO.save(element);
    }

    @Override
    public void delete(GiodoLog element) {

        giodoLogDAO.delete(element);
    }

    @Override
    public void saveLog(GiodoOperationType giodoOperationType, String message, UserData userData) {

        GiodoLog giodoLog = new GiodoLog();
        giodoLog.setOperation(giodoOperationType);
        giodoLog.setUser(userData);
        giodoLog.setMessage(message);
        giodoLog.setLogTime(Calendar.getInstance().getTime());

        add(giodoLog);
    }

    @Override
    public void saveLog(GiodoOperationType giodoOperationType, String message) {

        saveLog(giodoOperationType, message, null);
    }

    @Override
    public void saveLogIn(String userName) {

        UserData user = userDataService.findByLogin(userName);

        saveLog(GiodoOperationType.LOGIN, null, user);
    }

    @Override
    public void saveLogOut(String userName) {

        UserData user = userDataService.findByLogin(userName);

        saveLog(GiodoOperationType.LOGOUT, null, user);
    }
}
