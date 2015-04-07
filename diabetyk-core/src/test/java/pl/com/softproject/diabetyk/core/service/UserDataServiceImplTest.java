package pl.com.softproject.diabetyk.core.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

import pl.com.softproject.diabetyk.core.model.UserData;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Class UserDataServiceImplTest
 *
 * @author Marcin Jasinski {@literal <mkjasinski@gmail.com>}
 */
@ContextConfiguration({"/test-diabetyk-core-persistence-embedded.xml",
                       "/test-diabetyk-core-mail.xml", "/test-diabetyk-core-security.xml"})
@TransactionConfiguration
public class UserDataServiceImplTest extends AbstractTransactionalJUnit4SpringContextTests {

    private static final String LOGIN = "admin";

    @Autowired
    private UserDataService userDataService;

    @Test
    public void testFindByLogin() throws Exception {
        UserData userData = userDataService.findByLogin(LOGIN);

        assertThat(userData).isNotNull().isInstanceOf(UserData.class);
        assertThat(userData.getUser().getUsername()).isEqualTo(LOGIN);
    }
}
