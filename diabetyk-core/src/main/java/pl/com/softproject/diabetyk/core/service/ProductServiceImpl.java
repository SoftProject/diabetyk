package pl.com.softproject.diabetyk.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.com.softproject.diabetyk.core.dao.ProductDAO;
import pl.com.softproject.diabetyk.core.model.Product;
import pl.com.softproject.diabetyk.core.model.UserData;

import java.util.List;

/**
 * Class ProductServiceImpl
 *
 * @author Marcin Jasinski {@literal <mkjasinski@gmail.com>}
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDAO productDAO;

    @Override
    @Transactional(readOnly = true)
    public Product getElement(Long id) {

        return productDAO.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getElements() {

        return productDAO.findAll();
    }

    @Override
    public void add(Product element) {

        productDAO.save(element);
    }

    @Override
    public void update(Product element) {

        productDAO.save(element);
    }

    @Override
    public void delete(Product element) {

        productDAO.delete(element);
    }

    @Override
    @Transactional(readOnly = false)
    public List<Product> findAllMyAndModerated(UserData author) {

        return productDAO.findAllMyAndModerated(author);
    }

    @Override
    @Transactional(readOnly = false)
    public List<Product> findAllOrderedByName() {

        return productDAO.findAllOrderedByName();
    }
}
