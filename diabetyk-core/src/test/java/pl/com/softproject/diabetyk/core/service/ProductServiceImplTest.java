package pl.com.softproject.diabetyk.core.service;

import org.joda.time.DateTime;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

import pl.com.softproject.diabetyk.core.model.Product;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Piotr Szwed on 20.08.15.
 */
@ContextConfiguration({"/test-diabetyk-core-persistence-embedded.xml",
                       "/test-diabetyk-core-mail.xml", "/test-diabetyk-core-security.xml"})
@TransactionConfiguration
@Ignore
public class ProductServiceImplTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private ProductService productService;

    @Test
    public void testFindFromCurrentDate() throws Exception {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

        // daty produktów - przed ostatnim sprawdzeniem
        DateTime dateBeforeLastCheck_1 = new DateTime(2014, 10, 10, 0, 0, 0);
        DateTime dateBeforeLastCheck_2 = new DateTime(2014, 10, 10, 0, 0, 0);
        DateTime dateBeforeLastCheck_3 = new DateTime(2014, 10, 10, 0, 0, 0);

        // data ostatniej weryfikacji
        DateTime lastCheckDate = new DateTime(2015, 02, 02, 0, 0, 1);

        // daty produktow - po ostatnim sprawdzeniu

        DateTime dateAfterLastCheck_1 = new DateTime(2015, 02, 10, 0, 0, 0);;
        DateTime dateAfterLastCheck_2 = new DateTime(2015, 03, 01, 0, 0, 0);;
        DateTime dateAfterLastCheck_3 = new DateTime(2015, 04, 19, 0, 0, 0);;

        // produkty
        Product product1 = new Product();
        product1.setAddDate(dateBeforeLastCheck_1);
        product1.setName("Test before 1");

        Product product2 = new Product();
        product2.setAddDate(dateBeforeLastCheck_2);
        product2.setName("Test before 2");

        Product product3 = new Product();
        product3.setAddDate(dateBeforeLastCheck_3);
        product3.setName("Test before 3");

        Product product4 = new Product();
        product4.setAddDate(dateAfterLastCheck_1);
        product4.setName("Test after 1");

        Product product5 = new Product();
        product5.setAddDate(dateAfterLastCheck_2);
        product5.setName("Test after 2");

        Product product6 = new Product();
        product6.setAddDate(dateAfterLastCheck_3);
        product6.setName("Test after 3");

        productService.add(product1);
        productService.add(product2);
        productService.add(product3);
        productService.add(product4);
        productService.add(product5);
        productService.add(product6);

        assertThat(productService.findByAddDateGreaterThan(lastCheckDate).size()).isEqualTo(3);
    }
}