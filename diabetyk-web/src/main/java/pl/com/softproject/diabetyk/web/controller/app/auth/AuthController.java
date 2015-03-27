package pl.com.softproject.diabetyk.web.controller.app.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.com.softproject.diabetyk.core.enums.Role;
import pl.com.softproject.diabetyk.core.model.ProductCategory;
import pl.com.softproject.diabetyk.core.model.User;
import pl.com.softproject.diabetyk.core.model.UserData;
import pl.com.softproject.diabetyk.core.service.ProductCategoryService;
import pl.com.softproject.diabetyk.core.service.UserDataService;
import pl.com.softproject.diabetyk.web.controller.BaseController;

/**
 * Class AuthController
 *
 * @author Marcin Jasinski {@literal <mkjasinski@gmail.com>}
 */
@Controller
@RequestMapping("/app/auth")
public class AuthController extends BaseController {

    @Autowired
    private UserDataService userDataService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {

        logCalledMethod();

        return "/app/auth/login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {

        logCalledMethod();

        return "/app/auth/logout";
    }

    @RequestMapping(value = "/nimda", method = RequestMethod.GET)
    @ResponseBody
    public String addSysAdmin() {

        logCalledMethod();

        UserData admin = new UserData();
        admin.setEmail("admin@example.com");
        admin.setLogin("admin");

        User user = new User();
        user.setEnabled(true);
        user.setUsername("admin");
        user.setPassword("admin123");

        admin.setUser(user);

        userDataService
                .createUser(admin, true, Role.ROLE_USER, Role.ROLE_MODERATOR, Role.ROLE_ADMIN,
                            Role.ROLE_SYS_ADMIN);

        ProductCategory productCategory = new ProductCategory();
        productCategory.setName("Kat1");

        productCategoryService.add(productCategory);

        ProductCategory productCategory2 = new ProductCategory();
        productCategory2.setName("Kat2");

        productCategoryService.add(productCategory2);

        return "OK";
    }
}
