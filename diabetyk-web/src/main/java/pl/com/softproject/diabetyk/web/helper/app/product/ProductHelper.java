package pl.com.softproject.diabetyk.web.helper.app.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;

import pl.com.softproject.diabetyk.core.enums.LikeType;
import pl.com.softproject.diabetyk.core.enums.Role;
import pl.com.softproject.diabetyk.core.model.Like;
import pl.com.softproject.diabetyk.core.model.Product;
import pl.com.softproject.diabetyk.core.model.ProductCategory;
import pl.com.softproject.diabetyk.core.model.UserData;
import pl.com.softproject.diabetyk.core.service.LikeService;
import pl.com.softproject.diabetyk.core.service.ProductCategoryService;
import pl.com.softproject.diabetyk.core.service.ProductService;
import pl.com.softproject.diabetyk.core.service.UserDataService;
import pl.com.softproject.diabetyk.web.service.CacheService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Class ProductHelper
 *
 * @author Marcin Jasinski {@literal <mkjasinski@gmail.com>}
 */
@Component
@Transactional
public class ProductHelper {

    @Autowired
    private CacheService cacheService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private UserDataService userDataService;

    @Autowired
    private LikeService likeService;

    private void checkPermissionsForEdit(Product product) {

        if (!product.getAuthor().getId().equals(cacheService.getLoggedUserData().getId())) {
            throw new AccessDeniedException("Access denied");
        }
    }

    private void checkPermissionsForDetails(Product product) {

        if (userDataService
                .isUserInAnyRole(Role.ROLE_MODERATOR, Role.ROLE_ADMIN, Role.ROLE_SYS_ADMIN)) {
            return;
        }

        if (!product.isModerated() && !product.getAuthor().getId()
                .equals(cacheService.getLoggedUserData().getId())) {
            throw new AccessDeniedException("Access denied");
        }
    }

    public void prepareAddForm(Model model) {

        Product product = new Product();
        product.setAuthor(cacheService.getLoggedUserData());

        model.addAttribute("product", product);
        model.addAttribute("categories", productCategoryService.getElements());
    }

    public void saveProduct(Product product) {

        productService.add(product);
    }

    public void prepareAddFormAfterErrors(Model model) {

        model.addAttribute("categories", productCategoryService.getElements());
    }

    public void bindCategories(ServletRequestDataBinder binder) {

        binder.registerCustomEditor(Set.class, "categories", new CustomCollectionEditor(Set.class) {

            @Override
            protected Object convertElement(Object element) {

                ProductCategory productCategory;

                if (element != null) {
                    Long id = Long.valueOf(element.toString());
                    productCategory = productCategoryService.getElement(id);

                    return productCategory;
                }

                return null;
            }
        });
    }

    public void prepareEditForm(Model model, Long productId) {

        Product product = productService.getElement(productId);

        checkPermissionsForEdit(product);

        model.addAttribute("product", product);
        model.addAttribute("categories", productCategoryService.getElements());
    }

    public void updateProduct(Product product) {

        checkPermissionsForEdit(product);

        product.setModerated(false);

        productService.update(product);
    }

    public List<Map> getProductsList() {

        List<Product> productList;
        if (userDataService
                .isUserInAnyRole(Role.ROLE_MODERATOR, Role.ROLE_ADMIN, Role.ROLE_SYS_ADMIN)) {
            productList = productService.findAllOrderedByName();
        } else {
            productList = productService.findAllMyAndModerated(cacheService.getLoggedUserData());
        }

        List<Map> list = new ArrayList<>(productList.size());

        for (Product product : productList) {
            Map<String, Object> map = new HashMap<>(4);
            map.put("id", product.getId());
            map.put("name", product.getName());
            map.put("isModerated", product.isModerated() ? "Tak" : "Nie");
            map.put("editable",
                    product.getAuthor().getId().equals(cacheService.getLoggedUserData().getId())
                    ? "yes" : "no");

            list.add(map);
        }

        return list;
    }

    public void prepareDetailsForm(Model model, Long productId) {

        Product product = productService.getElement(productId);

        checkPermissionsForDetails(product);

        UserData userData = cacheService.getLoggedUserData();

        Like like = likeService.findLikeForProductAndUser(product, userData);
        Like dislike = likeService.findDislikeForProductAndUser(product, userData);

        model.addAttribute("product", product);
        model.addAttribute("like", like);
        model.addAttribute("dislike", dislike);
    }

    public void moderate(Long productId) {

        Product product = productService.getElement(productId);
        product.setModerated(!product.isModerated());

        productService.update(product);
    }

    public void like(Long productId) {

        Product product = productService.getElement(productId);

        UserData user = cacheService.getLoggedUserData();

        Like like = likeService.findLikeForProductAndUser(product, user);

        if (like != null) {

            likeService.delete(like);

            return;
        }

        Like newLike = new Like();
        newLike.setAuthor(user);
        newLike.setProduct(product);
        newLike.setType(LikeType.LIKE);

        likeService.add(newLike);
    }

    public void dislike(Long productId) {

        Product product = productService.getElement(productId);

        UserData user = cacheService.getLoggedUserData();

        Like dislike = likeService.findDislikeForProductAndUser(product, user);

        if (dislike != null) {

            likeService.delete(dislike);

            return;
        }

        Like newLike = new Like();
        newLike.setAuthor(user);
        newLike.setProduct(product);
        newLike.setType(LikeType.DISLIKE);

        likeService.add(newLike);
    }
}
