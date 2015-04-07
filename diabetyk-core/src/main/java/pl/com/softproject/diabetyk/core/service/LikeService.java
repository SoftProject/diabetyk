package pl.com.softproject.diabetyk.core.service;

import pl.com.softproject.diabetyk.core.model.Like;
import pl.com.softproject.diabetyk.core.model.Product;
import pl.com.softproject.diabetyk.core.model.UserData;

/**
 * Interface LikeService
 *
 * @author Marcin Jasinski {@literal <mkjasinski@gmail.com>}
 */
public interface LikeService extends CrudService<Like, Long> {

    public Like findLikeForProductAndUser(Product product, UserData userData);

    public Like findDislikeForProductAndUser(Product product, UserData userData);
}
