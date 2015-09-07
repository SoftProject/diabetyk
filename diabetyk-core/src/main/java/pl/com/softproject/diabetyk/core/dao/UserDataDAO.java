package pl.com.softproject.diabetyk.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.com.softproject.diabetyk.core.model.UserData;

/**
 * Interface UserDataDAO
 *
 * @author Marcin Jasinski {@literal <mkjasinski@gmail.com>}
 */
public interface UserDataDAO extends JpaRepository<UserData, Long> {

    UserData findByLogin(String login);

    UserData findByEmail(String email);

    UserData findByRegistrationToken(String token);

    UserData findByResetPasswordKey(String token);
}
