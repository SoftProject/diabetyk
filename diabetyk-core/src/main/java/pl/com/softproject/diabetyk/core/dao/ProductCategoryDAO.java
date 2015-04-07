package pl.com.softproject.diabetyk.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.com.softproject.diabetyk.core.model.ProductCategory;

/**
 * Interface ProductCategoryDAO
 *
 * @author Marcin Jasinski {@literal <mkjasinski@gmail.com>}
 */
public interface ProductCategoryDAO extends JpaRepository<ProductCategory, Long> {

}
