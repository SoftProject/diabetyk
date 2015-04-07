package pl.com.softproject.diabetyk.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.com.softproject.diabetyk.core.model.UserData;

/**
 * Interface UserDataDAO
 *
 * @author Marcin Jasinski {@literal <mkjasinski@gmail.com>}
 */
public interface UserDataDAO extends JpaRepository<UserData, Long> {

    public UserData findByLogin(String login);

    public UserData findByEmail(String email);

    public UserData findByRegistrationToken(String token);

    public UserData findByResetPasswordKey(String token);
}
