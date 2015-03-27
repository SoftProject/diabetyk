package pl.com.softproject.diabetyk.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pl.com.softproject.diabetyk.core.model.Product;
import pl.com.softproject.diabetyk.core.model.UserData;

import java.util.List;

/**
 * Interface ProductDAO
 *
 * @author Marcin Jasinski {@literal <mkjasinski@gmail.com>}
 */
public interface ProductDAO extends JpaRepository<Product, Long> {

    @Query("select distinct p from Product p where p.author = :author or p.moderated = true order by p.name")
    public List<Product> findAllMyAndModerated(@Param("author") UserData author);

    @Query("select p from Product p order by p.name")
    public List<Product> findAllOrderedByName();
}
