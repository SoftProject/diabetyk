package pl.com.softproject.diabetyk.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.com.softproject.diabetyk.core.model.Authority;
import pl.com.softproject.diabetyk.core.model.AuthorityId;

import java.util.List;

/**
 * Interface AuthorityDAO
 *
 * @author Marcin Jasinski {@literal <mkjasinski@gmail.com>}
 */
public interface AuthorityDAO extends JpaRepository<Authority, AuthorityId> {

    public List<Authority> findByUserName(String userName);

    public List<Authority> findByAuthority(String authority);
}
