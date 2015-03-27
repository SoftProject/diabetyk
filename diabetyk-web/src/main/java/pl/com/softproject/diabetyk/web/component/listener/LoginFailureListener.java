package pl.com.softproject.diabetyk.web.component.listener;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

import pl.com.softproject.diabetyk.core.enums.GiodoOperationType;
import pl.com.softproject.diabetyk.core.service.GiodoLogService;
import pl.com.softproject.diabetyk.core.service.UserDataService;

import javax.servlet.http.HttpServletRequest;

/**
 * Class LoginFailureListener
 *
 * @author Marcin Jasinski <mkjasinski@gmail.com>
 */
@Component
public class LoginFailureListener
        implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private GiodoLogService giodoLogService;

    @Autowired
    private UserDataService userDataService;

    @Override
    public void onApplicationEvent(
            AuthenticationFailureBadCredentialsEvent authenticationFailureBadCredentialsEvent) {

        String userName = request.getParameter("username");

        String message = String.format("userName: %s, ip: %s", userName, request.getRemoteAddr());

        userDataService.updateBadLoginValue(userName);

        giodoLogService.saveLog(GiodoOperationType.BAD_CREDENTIALS, message);

        logger.debug("bad credentials for: " + userName + ", from: " + request.getRemoteAddr());
    }
}
