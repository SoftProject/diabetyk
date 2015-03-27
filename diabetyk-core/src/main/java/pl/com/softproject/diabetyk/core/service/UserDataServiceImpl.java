package pl.com.softproject.diabetyk.core.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.com.softproject.diabetyk.core.dao.UserDataDAO;
import pl.com.softproject.diabetyk.core.enums.GiodoOperationType;
import pl.com.softproject.diabetyk.core.enums.Role;
import pl.com.softproject.diabetyk.core.exception.NoAuthInfoAvailableException;
import pl.com.softproject.diabetyk.core.model.Authority;
import pl.com.softproject.diabetyk.core.model.UserData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Class UserDataServiceImpl
 *
 * @author Marcin Jasinski {@literal <mkjasinski@gmail.com>}
 */
@Service
@Transactional
public class UserDataServiceImpl implements UserDataService {

    private static final Logger logger = Logger.getLogger(UserDataServiceImpl.class);

    @Autowired
    private UserDataDAO userDataDAO;

    @Autowired
    private UserDetailsManager userDetailsManager;

    @Autowired(required = false)
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthorityService authorityService;

    @Value("${core.maxBadLogin}")
    private int maxBadLogin;

    @Autowired
    private GiodoLogService giodoService;

    @Override
    @Transactional(readOnly = true)
    public UserData getElement(Long id) {

        return userDataDAO.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserData> getElements() {

        return userDataDAO.findAll();
    }

    @Override
    public void add(UserData element) {

        element.setCreationDate(new Date());

        userDataDAO.save(element);
    }

    @Override
    public void update(UserData element) {

        element.setLastModifiedDate(new Date());

        userDataDAO.save(element);
    }

    @Override
    public void delete(UserData element) {

        userDataDAO.delete(element);
    }

    @Override
    @Transactional(readOnly = true)
    public UserData findByLogin(String login) {

        return userDataDAO.findByLogin(login);
    }

    @Override
    @Transactional(readOnly = true)
    public String getLoggedUserLogin() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {
            Object principal = auth.getPrincipal();
            if (principal instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) principal;
                return userDetails.getUsername();

            }
        }

        logger.warn("brak SecurityContext");

        throw new NoAuthInfoAvailableException("Brak informacji o zalogowanym użytkowniku");
    }

    @Override
    @Transactional(readOnly = true)
    public UserData getLoggedUserData() {

        String login = getLoggedUserLogin();

        UserData userData = findByLogin(login);

        if (userData == null) {
            throw new NoAuthInfoAvailableException("Brak informacji o zalogowanym użytkowniku");
        }

        return userData;
    }

    @Override
    public void add(UserData element, Role... roles) {

        GrantedAuthority[] grantedAuthorities = new GrantedAuthority[roles.length];
        int i = 0;
        for (Role role : roles) {
            grantedAuthorities[i++] = new SimpleGrantedAuthority(role.toString());
        }

        List<GrantedAuthority> auth = Arrays.asList(grantedAuthorities);
        User user = new User(element.getUser().getUsername(),
                             encodePassword(element.getUser().getPassword()), false, true, true,
                             true, auth);
        userDetailsManager.createUser(user);

        element.setCreationDate(new Date());

        userDataDAO.saveAndFlush(element);
    }

    @Override
    public void update(UserData element, String password, String ipAddress) {

        element.getUser().setPassword(encodePassword(password));
        element.setLastPasswordChangeDate(new Date());
        element.setResetPasswordKey(null);

        giodoService.saveLog(GiodoOperationType.PASSWORD_CHANGE, "ip: " + ipAddress, element);

        update(element);
    }

    @Override
    public void disableUser(long id) {

        UserData user = userDataDAO.findOne(id);
        user.getUser().setEnabled(false);

        userDataDAO.save(user);
    }

    @Override
    public void enableUser(long id) {

        UserData user = userDataDAO.findOne(id);
        user.getUser().setEnabled(true);

        userDataDAO.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserData findByEmail(String email) {

        return userDataDAO.findByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public UserData findByToken(String token) {

        return userDataDAO.findByRegistrationToken(token);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isUserInRole(Role role) {

        return isUserInAnyRole(role);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isUserInAnyRole(Role... roles) {

        SecurityContext context = SecurityContextHolder.getContext();
        if (context == null) {
            return false;
        }

        Authentication authentication = context.getAuthentication();
        if (authentication == null) {
            return false;
        }

        for (Role role : roles) {
            for (GrantedAuthority auth : authentication.getAuthorities()) {
                if (role.toString().equals(auth.getAuthority())) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isUserInRole(UserData userData, Role role) {

        return isUserInAnyRole(userData, role);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isUserInAnyRole(UserData userData, Role... roles) {

        if (userData == null) {
            throw new IllegalArgumentException("userData can not be null");
        }

        Iterable<Authority> authorities =
                authorityService.findByUserName(userData.getUser().getUsername());

        List<String> authorityList = new ArrayList<>();

        for (Authority authority : authorities) {
            authorityList.add(authority.getAuthority().toString());
        }

        for (Role role : roles) {
            if (authorityList.contains(role.toString())) {
                return true;
            }
        }
        return false;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean hasLoggedUser() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {
            Object principal = auth.getPrincipal();
            if (principal instanceof UserDetails) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void updateBadLoginValue(String userName) {

        UserData userData = findByLogin(userName);
        if (userData == null) {
            return;
        }

        userData.setBadLoginCount(userData.getBadLoginCount() + 1);

        userDataDAO.save(userData);

        if (userData.getBadLoginCount() >= maxBadLogin) {
            disableUser(userData.getId());
        }
    }

    @Override
    public void clearBadLoginValue(String userName) {

        UserData userData = findByLogin(userName);

        if (userData == null) {
            return;
        }

        userData.setBadLoginCount(0);

        userDataDAO.save(userData);
    }

    @Override
    @Transactional
    public void createUser(UserData userData) {

        createUser(userData, true, Role.ROLE_USER);
    }

    @Override
    @Transactional
    public void createUser(UserData userData, boolean enabled, Role... roles) {

        GrantedAuthority[] grantedAuthorities = new GrantedAuthority[roles.length];
        int i = 0;
        for (Role role : roles) {
            grantedAuthorities[i++] = new SimpleGrantedAuthority(role.toString());
        }

        List<GrantedAuthority> auth = Arrays.asList(grantedAuthorities);
        User user = new User(userData.getLogin(),
                             passwordEncoder.encode(userData.getUser().getPassword()), enabled,
                             true, true, true, auth);
        userDetailsManager.createUser(user);

        userDataDAO.save(userData);
    }

    @Override
    @Transactional(readOnly = true)
    public UserData findByResetPasswordKey(String token) {

        return userDataDAO.findByResetPasswordKey(token);
    }

    private String encodePassword(String passwordPlainText) {

        if (passwordEncoder != null) {
            return passwordEncoder.encode(passwordPlainText);
        }
        return passwordPlainText;
    }
}
