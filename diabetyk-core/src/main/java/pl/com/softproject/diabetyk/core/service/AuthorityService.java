package pl.com.softproject.diabetyk.core.service;

import pl.com.softproject.diabetyk.core.model.Authority;
import pl.com.softproject.diabetyk.core.model.AuthorityId;

import java.util.List;

/**
 * Interface AuthorityService
 *
 * @author Marcin Jasinski {@literal <mkjasinski@gmail.com>}
 */
public interface AuthorityService extends CrudService<Authority, AuthorityId> {

    List<Authority> findByUserName(String userName);

    List<Authority> findByAuthority(String authority);
}
