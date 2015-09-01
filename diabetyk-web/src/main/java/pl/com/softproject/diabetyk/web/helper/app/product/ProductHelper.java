package pl.com.softproject.diabetyk.web.helper.app.product;

import org.apache.commons.validator.routines.DateValidator;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
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


import java.util.*;

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
            map.put("isModerated", product.isModerated() ? "yes" : "no");
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

    public List<Map> updateProductList(String lastCheckDate) {

        List<Product> productList = new ArrayList<>();
        String pattern = "yyyy-MM-dd-HH-mm-ss";

        if(DateValidator.getInstance().isValid(lastCheckDate, pattern)) {

            DateTime dateTime = DateTime.parse(lastCheckDate, DateTimeFormat.forPattern(pattern));

            if (userDataService
                    .isUserInAnyRole(Role.ROLE_MODERATOR, Role.ROLE_ADMIN, Role.ROLE_SYS_ADMIN)) {
                productList = productService.findByAddDateGreaterThan(dateTime);
            }
        }

        List<Map> list = new ArrayList<>(productList.size());

        for (Product product : productList) {

            Map<String, Object> productCategories = new HashMap<>(2);
            for (ProductCategory productCategory : product.getCategories()){
                productCategories.put("id", productCategory.getId());
                productCategories.put("name", productCategory.getName());
            }

            Map<String, Object> map = new HashMap<>(40);
            map.put("id", product.getId());
            map.put("name", product.getName());
            map.put("isModerated", product.isModerated() ? "yes" : "no");
            map.put("editable",
                    product.getAuthor().getId().equals(cacheService.getLoggedUserData().getId())
                            ? "yes" : "no");
            map.put("description", replaceNull(product.getDescription()));
            map.put("fat", replaceNull(product.getFat()));
            map.put("protein", replaceNull(product.getProtein()));
            map.put("carbohydrates", replaceNull(product.getCarbohydrates()));
            map.put("weightForOneWw", replaceNull(product.getWeightForOneWw()));
            map.put("homeMeasure", replaceNull(product.getHomeMeasure()));
            map.put("wwInPortion", replaceNull(product.getWwInPortion()));
            map.put("author", product.getAuthor().getId());
            map.put("productNormalizedName", replaceNull(product.getProductNormalizedName()));
            map.put("allergen", product.isAllergen() ? "yes" : "no");
            map.put("glutenFree", product.isGlutenFree() ? "yes" : "no");
            map.put("saturatedFattyAcids", replaceNull(product.getSaturatedFattyAcids()));
            map.put("oneSaturatedFattyAcids", replaceNull(product.getOneSaturatedFattyAcids()));
            map.put("multiSaturatedFattyAcids", replaceNull(product.getMultiSaturatedFattyAcids()));
            map.put("cholesterol", replaceNull(product.getCholesterol()));
            map.put("saccharose", replaceNull(product.getSaccharose()));
            map.put("cellulose", replaceNull(product.getCellulose()));
            map.put("calcium", replaceNull(product.getCalcium()));
            map.put("magnesium", replaceNull(product.getMagnesium()));
            map.put("iron", replaceNull(product.getIron()));
            map.put("zinc", replaceNull(product.getZinc()));
            map.put("vitaminD", replaceNull(product.getVitaminD()));
            map.put("vitaminB1", replaceNull(product.getVitaminB1()));
            map.put("vitaminB2",  replaceNull(product.getVitaminB2()));
            map.put("vitaminPp", replaceNull(product.getVitaminPp()));
            map.put("vitaminB6", replaceNull(product.getVitaminB6()));
            map.put("folicAcid", replaceNull(product.getFolicAcid()));
            map.put("vitaminB12", replaceNull(product.getVitaminB12()));
            map.put("vitaminC", replaceNull(product.getVitaminC()));
            map.put("categories",  replaceNull(productCategories));
            map.put("likesPlus", replaceNull(product.getLikesPlus()));
            map.put("likesMinus", replaceNull(product.getLikesMinus()));
            map.put("energy", replaceNull(product.getEnergy()));
            map.put("amountOfCarbohydrateExchangers", replaceNull(product.getAmountOfCarbohydrateExchangers()));
            map.put("amountOfProteinExchangers", replaceNull(product.getAmountOfProteinExchangers()));
            map.put("addDate", product.getAddDate());

            list.add(map);
        }

        return list;

    }

    private String replaceNull(Object input) {
        return input == null ? "" : input.toString();
    }
}
