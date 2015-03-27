package pl.com.softproject.diabetyk.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.com.softproject.diabetyk.core.model.GiodoLog;

/**
 * Interface GiodoLogDAO
 *
 * @author Marcin Jasinski {@literal <mkjasinski@gmail.com>}
 */
public interface GiodoLogDAO extends JpaRepository<GiodoLog, Long> {

}
