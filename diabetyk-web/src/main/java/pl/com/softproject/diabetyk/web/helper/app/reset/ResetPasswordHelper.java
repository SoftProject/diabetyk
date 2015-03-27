package pl.com.softproject.diabetyk.web.helper.app.reset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import pl.com.softproject.diabetyk.core.model.UserData;
import pl.com.softproject.diabetyk.core.service.MailService;
import pl.com.softproject.diabetyk.core.service.UserDataService;
import pl.com.softproject.diabetyk.web.form.app.reset.ResetPasswordForm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Class ResetPasswordHelper
 *
 * @author Marcin Jasinski {@literal <mkjasinski@gmail.com>}
 */
@Component
@Transactional
public class ResetPasswordHelper {

    @Value("${serverUrl}")
    private String applicationUrl;

    @Value("${web.app.registration.defaultResetPasswordSubject}")
    private String defaultResetPasswordSubject;

    @Value("${web.app.registration.minPasswordLength}")
    private int minPasswordLength;

    @Autowired
    private UserDataService userDataService;

    @Autowired
    private MailService mailService;

    public boolean sendUrl(String email) {

        UserData userData = userDataService.findByEmail(email);

        if (userData == null) {
            return false;
        }

        String token = UUID.randomUUID().toString();

        userData.setResetPasswordKey(token);
        userData.setResetPasswordKeyGenerationDate(new Date());

        userDataService.update(userData);

        String url = String.format("app/resetPassword/%s/confirm", token);

        Map<String, Object> params = new HashMap<>(1);
        params.put("reset_url", applicationUrl + url);

        mailService
                .sendMail(userData.getEmail(), defaultResetPasswordSubject, "resetPassword", params,
                          null);

        return true;
    }

    public boolean checkToken(String token) {

        return userDataService.findByResetPasswordKey(token) != null;
    }

    public void prepareResetForm(Model model, String token) {

        ResetPasswordForm resetPasswordForm = new ResetPasswordForm();
        resetPasswordForm.setToken(token);

        model.addAttribute("resetPasswordForm", resetPasswordForm);
    }

    public void checkErrors(ResetPasswordForm resetPasswordForm, BindingResult bindingResult) {

        if (resetPasswordForm.getPassword().length() < minPasswordLength) {
            bindingResult.rejectValue("password", "error.resetPasswordForm",
                                      "Hasła musi składać się z co najmniej 6 znaków");
        }

        if (!resetPasswordForm.getPassword().equals(resetPasswordForm.getPassword2())) {
            bindingResult.rejectValue("password", "error.resetPasswordForm",
                                      "Hasła muszą być identyczne");
        }
    }

    public void resetPassword(ResetPasswordForm resetPasswordForm, String ipAddress) {

        UserData userData = userDataService.findByResetPasswordKey(resetPasswordForm.getToken());

        if (userData == null) {
            return;
        }

        userDataService.update(userData, resetPasswordForm.getPassword(), ipAddress);
    }
}
