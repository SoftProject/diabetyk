package pl.com.softproject.diabetyk.core.service;

import pl.com.softproject.diabetyk.core.enums.Role;
import pl.com.softproject.diabetyk.core.model.UserData;

/**
 * Interface UserDataService
 *
 * @author Marcin Jasinski {@literal <mkjasinski@gmail.com>}
 */
public interface UserDataService extends CrudService<UserData, Long> {

    public UserData findByLogin(String login);

    public String getLoggedUserLogin();

    public UserData getLoggedUserData();

    public void add(UserData element, Role... roles);

    public void update(UserData element, String password, String ipAddress);

    public void disableUser(long id);

    public void enableUser(long id);

    public UserData findByEmail(String email);

    public UserData findByToken(String token);

    public boolean isUserInRole(Role role);

    public boolean isUserInAnyRole(Role... roles);

    public boolean isUserInRole(UserData userData, Role role);

    public boolean isUserInAnyRole(UserData userData, Role... roles);

    public boolean hasLoggedUser();

    public void updateBadLoginValue(String userName);

    public void clearBadLoginValue(String userName);

    void createUser(UserData userData);

    void createUser(UserData userData, boolean enabled, Role... roles);

    public UserData findByResetPasswordKey(String token);
}
