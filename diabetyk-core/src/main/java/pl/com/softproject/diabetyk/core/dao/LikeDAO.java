package pl.com.softproject.diabetyk.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.com.softproject.diabetyk.core.enums.LikeType;
import pl.com.softproject.diabetyk.core.model.Like;
import pl.com.softproject.diabetyk.core.model.Product;
import pl.com.softproject.diabetyk.core.model.UserData;

/**
 * Interface LikeDAO
 *
 * @author Marcin Jasinski {@literal <mkjasinski@gmail.com>}
 */
public interface LikeDAO extends JpaRepository<Like, Long> {

    Like findByProductAndAuthorAndType(Product product, UserData userData, LikeType type);
}
