package pl.com.softproject.diabetyk.web;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * Class PersistenceContextTest
 *
 * @author Marcin Jasinski {@literal <mkjasinski@gmail.com>}
 */
@ContextConfiguration({"/test-diabetyk-web-persistence-embedded.xml", "/test-diabetyk-web-mail.xml",
                       "/test-diabetyk-web-security.xml"})
@TransactionConfiguration
public class PersistenceContextTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Test
    public void testContextUp() {

    }
}
