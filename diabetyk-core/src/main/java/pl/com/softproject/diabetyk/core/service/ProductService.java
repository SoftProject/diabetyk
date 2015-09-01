package pl.com.softproject.diabetyk.core.service;

import pl.com.softproject.diabetyk.core.model.Product;
import pl.com.softproject.diabetyk.core.model.UserData;

import java.util.List;

/**
 * Interface ProductService
 *
 * @author Marcin Jasinski {@literal <mkjasinski@gmail.com>}
 */
public interface ProductService extends CrudService<Product, Long> {

    public List<Product> findAllMyAndModerated(UserData author);

    public List<Product> findAllOrderedByName();
}
