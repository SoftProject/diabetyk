package pl.com.softproject.diabetyk.web.component.listener;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import pl.com.softproject.diabetyk.core.enums.GiodoOperationType;
import pl.com.softproject.diabetyk.core.model.UserData;
import pl.com.softproject.diabetyk.core.service.GiodoLogService;
import pl.com.softproject.diabetyk.core.service.UserDataService;

import javax.servlet.http.HttpServletRequest;

/**
 * Class LoginListener
 *
 * @author Marcin Jasinski <mkjasinski@gmail.com>
 */
@Component
public class LoginListener implements ApplicationListener<AuthenticationSuccessEvent> {

    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private GiodoLogService giodoLogService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserDataService userDataService;

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent e) {

        UserDetails userDetails = (UserDetails) e.getAuthentication().getPrincipal();

        String message = String.format("ip: %s", request.getRemoteAddr());

        String userName = userDetails.getUsername();

        userDataService.clearBadLoginValue(userName);
        UserData userData = userDataService.findByLogin(userName);

        giodoLogService.saveLog(GiodoOperationType.LOGIN, message, userData);

        logger.debug("loggedIn " + userDetails);
    }
}
