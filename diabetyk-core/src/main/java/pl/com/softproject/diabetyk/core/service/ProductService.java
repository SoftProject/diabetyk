package pl.com.softproject.diabetyk.core.service;

import org.joda.time.DateTime;
import pl.com.softproject.diabetyk.core.model.Product;
import pl.com.softproject.diabetyk.core.model.UserData;

import java.util.Date;
import java.util.List;

/**
 * Interface ProductService
 *
 * @author Marcin Jasinski {@literal <mkjasinski@gmail.com>}
 */
public interface ProductService extends CrudService<Product, Long> {

    List<Product> findAllMyAndModerated(UserData author);

    List<Product> findAllOrderedByName();

    List<Product> findByAddDateGreaterThan(DateTime date);
}
