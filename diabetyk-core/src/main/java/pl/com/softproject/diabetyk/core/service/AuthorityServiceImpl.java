package pl.com.softproject.diabetyk.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.com.softproject.diabetyk.core.dao.AuthorityDAO;
import pl.com.softproject.diabetyk.core.model.Authority;
import pl.com.softproject.diabetyk.core.model.AuthorityId;

import java.util.List;

/**
 * Class AuthorityServiceImpl
 *
 * @author Marcin Jasinski {@literal <mkjasinski@gmail.com>}
 */
@Service
@Transactional(readOnly = true)
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    private AuthorityDAO authorityDAO;

    @Override
    public List<Authority> findByUserName(String userName) {

        return authorityDAO.findByUserName(userName);
    }

    @Override
    public List<Authority> findByAuthority(String authority) {

        return authorityDAO.findByAuthority(authority);
    }

    @Override
    public Authority getElement(AuthorityId id) {

        return authorityDAO.findOne(id);
    }

    @Override
    public List<Authority> getElements() {

        return authorityDAO.findAll();
    }

    @Override
    public void add(Authority element) {

        throw new RuntimeException("nie obslugujemy zapisu");
    }

    @Override
    public void update(Authority element) {

        throw new RuntimeException("nie obslugujemy zapisu");
    }

    @Override
    public void delete(Authority element) {

        throw new RuntimeException("nie obslugujemy zapisu");
    }
}
