package pl.com.softproject.diabetyk.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.com.softproject.diabetyk.core.dao.LikeDAO;
import pl.com.softproject.diabetyk.core.enums.LikeType;
import pl.com.softproject.diabetyk.core.model.Like;
import pl.com.softproject.diabetyk.core.model.Product;
import pl.com.softproject.diabetyk.core.model.UserData;

import java.util.List;

/**
 * Class LikeServiceImpl
 *
 * @author Marcin Jasinski {@literal <mkjasinski@gmail.com>}
 */
@Service
@Transactional
public class LikeServiceImpl implements LikeService {

    @Autowired
    private LikeDAO likeDAO;

    @Autowired
    private ProductService productService;

    @Value("${web.app.product.isModeratedFromLike}")
    private int isModeratedFromLike;

    @Value("${web.app.product.isNotModeratedFromDislike}")
    private int isNotModeratedFromDislike;

    @Override
    @Transactional(readOnly = true)
    public Like getElement(Long id) {

        return likeDAO.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Like> getElements() {

        return likeDAO.findAll();
    }

    @Override
    public void add(Like element) {

        if (element.getId() != null) {
            throw new RuntimeException("Brak mozliwosci edycji");
        }

        setLike(element);

        likeDAO.save(element);
    }

    @Override
    public void update(Like element) {

        throw new RuntimeException("Brak mozliwosci edycji");
    }

    @Override
    public void delete(Like element) {

        setLike(element, true);

        likeDAO.delete(element);
    }

    @Override
    @Transactional(readOnly = true)
    public Like findLikeForProductAndUser(Product product, UserData userData) {

        return likeDAO.findByProductAndAuthorAndType(product, userData, LikeType.LIKE);
    }

    @Override
    @Transactional(readOnly = true)
    public Like findDislikeForProductAndUser(Product product, UserData userData) {

        return likeDAO.findByProductAndAuthorAndType(product, userData, LikeType.DISLIKE);
    }

    private void setLike(Like element) {

        setLike(element, false);
    }

    private void setLike(Like element, boolean minus) {

        Product product = element.getProduct();

        switch (element.getType()) {
            case LIKE:
                product.setLikesPlus(
                        minus ? product.getLikesPlus() - 1 : product.getLikesPlus() + 1);
                break;
            case DISLIKE:
                product.setLikesMinus(
                        minus ? product.getLikesMinus() - 1 : product.getLikesMinus() + 1);
                break;
            default:
                throw new RuntimeException("Nieokreslony typ");
        }

        if (product.getLikesPlus() - product.getLikesMinus() >= isModeratedFromLike) {
            product.setModerated(true);
        } else if (product.getLikesPlus() - product.getLikesMinus() <= isNotModeratedFromDislike) {
            product.setModerated(false);
        }

        productService.update(product);
    }
}
