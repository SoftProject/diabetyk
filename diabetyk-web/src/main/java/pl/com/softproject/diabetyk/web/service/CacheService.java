package pl.com.softproject.diabetyk.web.service;

import pl.com.softproject.diabetyk.core.model.UserData;

/**
 * Class CacheService
 *
 * @author Marcin Jasinski {@literal <mkjasinski@gmail.com>}
 */
public interface CacheService {

    String getLoggedUserLogin();

    UserData getLoggedUserData();

    boolean hasLoggedUser();
}
