package pl.com.softproject.diabetyk.core.service;

import pl.com.softproject.diabetyk.core.enums.GiodoOperationType;
import pl.com.softproject.diabetyk.core.model.GiodoLog;
import pl.com.softproject.diabetyk.core.model.UserData;

/**
 * Interface GiodoLogService
 *
 * @author Marcin Jasinski {@literal <mkjasinski@gmail.com>}
 */
public interface GiodoLogService extends CrudService<GiodoLog, Long> {

    public void saveLogIn(String userName);

    public void saveLogOut(String userName);

    public void saveLog(GiodoOperationType giodoOperationType, String message, UserData userData);

    public void saveLog(GiodoOperationType giodoOperationType, String message);
}
