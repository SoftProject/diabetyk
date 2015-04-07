package pl.com.softproject.diabetyk.web.service;

import org.springframework.beans.factory.annotation.Autowired;

import pl.com.softproject.diabetyk.core.model.UserData;
import pl.com.softproject.diabetyk.core.service.UserDataService;

import javax.annotation.PostConstruct;

/**
 * Class CacheServiceImpl
 *
 * @author Marcin Jasinski {@literal <mkjasinski@gmail.com>}
 */
public class CacheServiceImpl implements CacheService {

    @Autowired
    private UserDataService userDataService;

    @PostConstruct
    public void init() {

    }

    @Override
    public String getLoggedUserLogin() {

        return userDataService.getLoggedUserLogin();
    }

    @Override
    public UserData getLoggedUserData() {

        return userDataService.getLoggedUserData();
    }

    @Override
    public boolean hasLoggedUser() {

        return userDataService.hasLoggedUser();
    }
}
