package pl.com.softproject.diabetyk.web.controller.app.reset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pl.com.softproject.diabetyk.web.controller.BaseController;
import pl.com.softproject.diabetyk.web.form.app.reset.ResetPasswordForm;
import pl.com.softproject.diabetyk.web.helper.app.reset.ResetPasswordHelper;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Class ResetPasswordController
 *
 * @author Marcin Jasinski {@literal <mkjasinski@gmail.com>}
 */
@Controller
@RequestMapping("/app/resetPassword")
public class ResetPasswordController extends BaseController {

    @Autowired
    private ResetPasswordHelper resetPasswordHelper;

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String form() {

        logCalledMethod();

        return "/app/resetPassword/form";
    }

    @RequestMapping(value = "/formAction", method = RequestMethod.POST)
    public String formAction(@RequestParam("email") String email,
                             RedirectAttributes redirectAttributes) {

        logCalledMethod();

        boolean isSend = resetPasswordHelper.sendUrl(email);

        String message = "Wiadomośc wysłana";
        String type = "message";
        if (!isSend) {
            type = "error";
            message = "Użytkownik nie istnieje";
        }

        redirectAttributes.addFlashAttribute(type, message);

        return isSend ? "redirect:/app/auth/login" : "redirect:/app/resetPassword/form";
    }

    @RequestMapping(value = "/{token}/confirm", method = RequestMethod.GET)
    public String confirm(Model model, @PathVariable("token") String token,
                          RedirectAttributes redirectAttributes) {

        logCalledMethod();

        boolean hasToken = resetPasswordHelper.checkToken(token);

        if (!hasToken) {

            redirectAttributes.addFlashAttribute("error", "Nie znaleziono użytkownika");

            return "redirect:/app/auth/login";
        }

        resetPasswordHelper.prepareResetForm(model, token);

        return "/app/resetPassword/confirm";
    }

    @RequestMapping(value = "/confirmAction", method = RequestMethod.POST)
    public String confirmAction(
            @Valid @ModelAttribute("resetPasswordForm") ResetPasswordForm resetPasswordForm,
            BindingResult bindingResult, RedirectAttributes redirectAttributes,
            HttpServletRequest request) {

        logCalledMethod();

        resetPasswordHelper.checkErrors(resetPasswordForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "/app/resetPassword/confirm";
        }

        resetPasswordHelper.resetPassword(resetPasswordForm, request.getRemoteAddr());

        redirectAttributes.addFlashAttribute("message", "Hasło zmienione poprawnie");

        return "redirect:/app/auth/login";
    }
}
