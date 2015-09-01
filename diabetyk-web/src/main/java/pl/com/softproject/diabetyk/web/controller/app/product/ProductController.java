package pl.com.softproject.diabetyk.web.controller.app.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pl.com.softproject.diabetyk.core.model.Product;
import pl.com.softproject.diabetyk.web.controller.BaseController;
import pl.com.softproject.diabetyk.web.helper.app.product.ProductHelper;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

/**
 * Class ProductController
 *
 * @author Marcin Jasinski {@literal <mkjasinski@gmail.com>}
 */
@Controller
@RequestMapping("/app/product")
public class ProductController extends BaseController {

    @Autowired
    private ProductHelper productHelper;

    @InitBinder
    protected void initBinder(ServletRequestDataBinder binder) throws Exception {

        productHelper.bindCategories(binder);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list() {

        logCalledMethod();

        return "/app/product/list";
    }

    @RequestMapping(value = "/list/json", method = RequestMethod.GET, produces = "application/json;charset=utf8")
    @ResponseBody
    public List<Map> listJson() {

        logCalledMethod();

        return productHelper.getProductsList();
    }

    @RequestMapping(value = "/{productId}/details", method = RequestMethod.GET)
    public String details(Model model, @PathVariable("productId") Long productId) {

        logCalledMethod();

        productHelper.prepareDetailsForm(model, productId);

        return "/app/product/details";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model) {

        logCalledMethod();

        productHelper.prepareAddForm(model);

        return "/app/product/add";
    }

    @RequestMapping(value = "/addAction", method = RequestMethod.POST)
    public String addAction(Model model, @Valid @ModelAttribute("product") Product product,
                            BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        logCalledMethod();

        if (bindingResult.hasErrors()) {
            productHelper.prepareAddFormAfterErrors(model);

            return "/app/product/add";
        }

        productHelper.saveProduct(product);

        redirectAttributes.addFlashAttribute("message", "Produkt zapisany poprawnie");

        return "redirect:/app/product/list";
    }

    @RequestMapping(value = "/{productId}/edit", method = RequestMethod.GET)
    public String edit(Model model, @PathVariable("productId") Long productId) {

        logCalledMethod();

        productHelper.prepareEditForm(model, productId);

        return "/app/product/add";
    }

    @RequestMapping(value = "/editAction", method = RequestMethod.POST)
    public String editAction(Model model, @Valid @ModelAttribute("product") Product product,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        logCalledMethod();

        if (bindingResult.hasErrors()) {

            productHelper.prepareEditForm(model, product.getId());

            return "/app/product/add";
        }

        productHelper.updateProduct(product);

        redirectAttributes.addFlashAttribute("message", "Produkt zapisany poprawnie");

        return "redirect:/app/product/list";
    }

    @RequestMapping(value = "/{productId}/like", method = RequestMethod.GET)
    public String like(@PathVariable("productId") Long productId,
                       RedirectAttributes redirectAttributes) {

        logCalledMethod();

        productHelper.like(productId);

        redirectAttributes.addFlashAttribute("message", "Zmiany zapisane");

        return String.format("redirect:/app/product/%s/details", productId);
    }

    @RequestMapping(value = "/{productId}/dislike", method = RequestMethod.GET)
    public String dislike(@PathVariable("productId") Long productId,
                          RedirectAttributes redirectAttributes) {

        logCalledMethod();

        productHelper.dislike(productId);

        redirectAttributes.addFlashAttribute("message", "Zmiany zapisane");

        return String.format("redirect:/app/product/%s/details", productId);
    }

    @RequestMapping(value = "/{productId}/moderate", method = RequestMethod.GET)
    @Secured({"ROLE_MODERATOR", "ROLE_ADMIN", "ROLE_SYS_ADMIN"})
    public String moderate(@PathVariable("productId") Long productId,
                           RedirectAttributes redirectAttributes) {

        logCalledMethod();

        productHelper.moderate(productId);

        redirectAttributes.addFlashAttribute("message", "Zmiany zapisane");

        return String.format("redirect:/app/product/%s/details", productId);
    }
}
