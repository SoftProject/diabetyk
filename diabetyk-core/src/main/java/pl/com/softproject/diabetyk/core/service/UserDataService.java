package pl.com.softproject.diabetyk.core.service;

import pl.com.softproject.diabetyk.core.enums.Role;
import pl.com.softproject.diabetyk.core.model.UserData;

/**
 * Interface UserDataService
 *
 * @author Marcin Jasinski {@literal <mkjasinski@gmail.com>}
 */
public interface UserDataService extends CrudService<UserData, Long> {

    UserData findByLogin(String login);

    String getLoggedUserLogin();

    UserData getLoggedUserData();

    void add(UserData element, Role... roles);

    void update(UserData element, String password, String ipAddress);

    void disableUser(long id);

    void enableUser(long id);

    UserData findByEmail(String email);

    UserData findByToken(String token);

    boolean isUserInRole(Role role);

    boolean isUserInAnyRole(Role... roles);

    boolean isUserInRole(UserData userData, Role role);

    boolean isUserInAnyRole(UserData userData, Role... roles);

    boolean hasLoggedUser();

    void updateBadLoginValue(String userName);

    void clearBadLoginValue(String userName);

    void createUser(UserData userData);

    void createUser(UserData userData, boolean enabled, Role... roles);

    UserData findByResetPasswordKey(String token);
}
