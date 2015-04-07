package pl.com.softproject.diabetyk.web.component.listener;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.session.SessionDestroyedEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import pl.com.softproject.diabetyk.core.enums.GiodoOperationType;
import pl.com.softproject.diabetyk.core.model.UserData;
import pl.com.softproject.diabetyk.core.service.GiodoLogService;
import pl.com.softproject.diabetyk.core.service.UserDataService;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * Class LogoutListener
 *
 * @author Marcin Jasinski <mkjasinski@gmail.com>
 */
@Component
public class LogoutListener implements ApplicationListener<SessionDestroyedEvent> {

    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private GiodoLogService giodoLogService;

    @Autowired
    private UserDataService userDataService;

    @Autowired
    private HttpServletRequest request;

    @Override
    public void onApplicationEvent(SessionDestroyedEvent event) {

        List<SecurityContext> securityContextList = event.getSecurityContexts();
        UserDetails userDetails;

        for (SecurityContext securityContext : securityContextList) {
            userDetails = (UserDetails) securityContext.getAuthentication().getPrincipal();

            UserData userData = userDataService.findByLogin(userDetails.getUsername());

            String message = String.format("ip: %s", request.getRemoteAddr());

            giodoLogService.saveLog(GiodoOperationType.LOGOUT, message, userData);

            logger.debug("loggedOut " + userDetails);
        }
    }
}
