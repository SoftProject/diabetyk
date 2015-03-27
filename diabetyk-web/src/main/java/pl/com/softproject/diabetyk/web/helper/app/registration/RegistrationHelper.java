package pl.com.softproject.diabetyk.web.helper.app.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import pl.com.softproject.diabetyk.core.enums.Role;
import pl.com.softproject.diabetyk.core.model.User;
import pl.com.softproject.diabetyk.core.model.UserData;
import pl.com.softproject.diabetyk.core.service.MailService;
import pl.com.softproject.diabetyk.core.service.UserDataService;
import pl.com.softproject.diabetyk.web.form.app.registration.RegistrationForm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Class RegistrationHelper
 *
 * @author Marcin Jasinski {@literal <mkjasinski@gmail.com>}
 */
@Component
@Transactional
public class RegistrationHelper {

    @Value("${web.app.registration.minPasswordLength}")
    private int minPasswordLength;

    @Value("${serverUrl}")
    private String applicationUrl;

    @Value("${web.app.registration.defaultRegistrationSubject}")
    private String defaultRegistrationSubject;

    @Autowired
    private UserDataService userDataService;

    @Autowired
    private MailService mailService;

    public void prepareForm(Model model) {

        RegistrationForm registrationForm = new RegistrationForm();

        model.addAttribute("registrationForm", registrationForm);
    }

    public void checkErrors(RegistrationForm registrationForm, BindingResult bindingResult) {

        if (userDataService.findByEmail(registrationForm.getEmail()) != null) {
            bindingResult
                    .rejectValue("email", "error.registrationForm", "Adres email jest już zajęty");
        }

        if (registrationForm.getPassword().length() < minPasswordLength) {
            bindingResult.rejectValue("password", "error.registrationForm",
                                      "Hasła musi składać się z co najmniej 6 znaków");
        }

        if (!registrationForm.getPassword().equals(registrationForm.getPassword2())) {
            bindingResult.rejectValue("password", "error.registrationForm",
                                      "Hasła muszą być identyczne");
        }
    }

    public void registerUser(RegistrationForm registrationForm) {

        UserData userData = new UserData();

        User user = new User();
        user.setEnabled(false);
        user.setUsername(registrationForm.getEmail());
        user.setPassword(registrationForm.getPassword());

        userData.setUser(user);
        userData.setCreationDate(new Date());
        userData.setEmail(registrationForm.getEmail());
        userData.setLogin(registrationForm.getEmail());

        String token = UUID.randomUUID().toString();
        userData.setRegistrationToken(token);

        userDataService.add(userData, Role.ROLE_USER);

        String url = String.format("app/registration/%s/confirm", token);

        Map<String, Object> params = new HashMap<>(1);
        params.put("register_url", applicationUrl + url);

        mailService.sendMail(userData.getEmail(), defaultRegistrationSubject, "register", params,
                             null);
    }

    public boolean confirmUser(String token) {

        UserData userData = userDataService.findByToken(token);

        if (userData == null) {
            return false;
        }

        userDataService.enableUser(userData.getId());

        userData.setRegistrationToken(null);

        userDataService.update(userData);

        return true;
    }
}
