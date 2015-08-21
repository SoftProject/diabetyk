package pl.com.softproject.diabetyk.web.util;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

/**
 * Created by admin-vm on 21.08.15.
 */
public class DateTimeValidatorTest {

    @Test
    public void testValidate() throws Exception {
        DateTimeValidator timeValidator = new DateTimeValidator();
        String time1 = "2015-08-20-10-11-12";
        String time2 = "2015-08-20-23-59-56";
        String time3 = "2015-08-23";

        assertThat(timeValidator.validate(time1));
        assertThat(timeValidator.validate(time2));
        assertThat(timeValidator.validate(time3));
    }
}