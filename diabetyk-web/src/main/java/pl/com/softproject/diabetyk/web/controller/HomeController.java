package pl.com.softproject.diabetyk.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Class HomeController
 *
 * @author Marcin Jasinski {@literal <mkjasinski@gmail.com>}
 */
@Controller
public class HomeController extends BaseController {

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String home() {

        logCalledMethod();

        return "home";
    }
}
