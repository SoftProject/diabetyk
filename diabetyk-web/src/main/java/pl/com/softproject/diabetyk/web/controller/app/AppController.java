package pl.com.softproject.diabetyk.web.controller.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.com.softproject.diabetyk.web.controller.BaseController;

/**
 * Class AppController
 *
 * @author Marcin Jasinski {@literal <mkjasinski@gmail.com>}
 */
@Controller
@RequestMapping("/app")
public class AppController extends BaseController {

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public String home() {

        logCalledMethod();

        return "/app/home";
    }
}
