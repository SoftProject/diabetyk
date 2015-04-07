package pl.com.softproject.diabetyk.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.com.softproject.diabetyk.core.dao.ProductCategoryDAO;
import pl.com.softproject.diabetyk.core.model.ProductCategory;

import java.util.List;

/**
 * Class ProductCategoryServiceImpl
 *
 * @author Marcin Jasinski {@literal <mkjasinski@gmail.com>}
 */
@Service
@Transactional
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryDAO productCategoryDAO;

    @Override
    @Transactional(readOnly = true)
    public ProductCategory getElement(Long id) {

        return productCategoryDAO.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductCategory> getElements() {

        return productCategoryDAO.findAll();
    }

    @Override
    public void add(ProductCategory element) {

        productCategoryDAO.save(element);
    }

    @Override
    public void update(ProductCategory element) {

        productCategoryDAO.save(element);
    }

    @Override
    public void delete(ProductCategory element) {

        productCategoryDAO.delete(element);
    }
}
