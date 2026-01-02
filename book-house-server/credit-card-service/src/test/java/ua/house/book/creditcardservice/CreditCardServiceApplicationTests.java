package ua.house.book.creditcardservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import ua.house.book.creditcardservice.config.TestCreditCardConfig;
import ua.house.book.creditcardservice.config.TestHibernateConfig;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ContextConfiguration(classes = {TestHibernateConfig.class, TestCreditCardConfig.class})
class CreditCardServiceApplicationTests {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void contextLoads() {
        assertThat(applicationContext).isNotNull();
    }

}
