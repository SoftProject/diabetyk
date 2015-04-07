package pl.com.softproject.diabetyk.web.form.app.reset;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Class ResetPasswordForm
 *
 * @author Marcin Jasinski {@literal <mkjasinski@gmail.com>}
 */
public class ResetPasswordForm {

    @NotEmpty(message = "Pole jest wymagane")
    private String password;

    @NotEmpty(message = "Pole jest wymagane")
    private String password2;

    private String token;

    public ResetPasswordForm() {

    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public String getPassword2() {

        return password2;
    }

    public void setPassword2(String password2) {

        this.password2 = password2;
    }

    public String getToken() {

        return token;
    }

    public void setToken(String token) {

        this.token = token;
    }
}
