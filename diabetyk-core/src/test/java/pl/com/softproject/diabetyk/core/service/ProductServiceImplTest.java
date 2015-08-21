package pl.com.softproject.diabetyk.core.service;

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
//@Ignore
public class ProductServiceImplTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private ProductService productService;

    @Test
    public void testFindFromCurrentDate() throws Exception {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

        // daty produktów - przed ostatnim sprawdzeniem
        String dateB1 = "2014-10-10-00-00-00";
        String dateB2 = "2014-08-01-00-00-00";
        String dateB3 = "2015-02-01-23-59-00";

        Date dateBeforeLastCheck_1 = format.parse(dateB1);
        Date dateBeforeLastCheck_2 = format.parse(dateB2);
        Date dateBeforeLastCheck_3 = format.parse(dateB3);

        // data ostatniej weryfikacji
        String dateLC = "2015-02-02-00-00-01";

        Date lastCheckDate =  format.parse(dateLC);

        // daty produktow - po ostatnim sprawdzeniu
        String dateA1 = "2015-02-10-00-00-00";
        String dateA2 = "2015-03-01-00-00-00";
        String dateA3 = "2015-04-19-00-00-00";

        Date dateAfterLastCheck_1 = format.parse(dateA1);
        Date dateAfterLastCheck_2 = format.parse(dateA2);
        Date dateAfterLastCheck_3 = format.parse(dateA3);

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