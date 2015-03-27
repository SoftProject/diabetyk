package pl.com.softproject.diabetyk.web.controller;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * Class BaseController
 *
 * @author Marcin Jasinski {@literal <mkjasinski@gmail.com>}
 */
public class BaseController {

    protected final Logger logger = Logger.getLogger(getClass());

    public static String getBaseUrl() {

        return getBaseUrl("/");
    }

    public static String getBaseUrl(String path) {

        return ServletUriComponentsBuilder.fromCurrentContextPath().path(path).build()
                .toUriString();
    }

    protected void logCalledMethod() {

        if (logger.isDebugEnabled()) {
            logger.debug(String.format("%s.%s()", getClass().getSimpleName(),
                                       Thread.currentThread().getStackTrace()[2].getMethodName()));
        }
    }
}
