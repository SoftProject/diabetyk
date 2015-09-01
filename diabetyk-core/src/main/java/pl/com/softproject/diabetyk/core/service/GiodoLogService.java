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

    void saveLogIn(String userName);

    void saveLogOut(String userName);

    void saveLog(GiodoOperationType giodoOperationType, String message, UserData userData);

    void saveLog(GiodoOperationType giodoOperationType, String message);
}
