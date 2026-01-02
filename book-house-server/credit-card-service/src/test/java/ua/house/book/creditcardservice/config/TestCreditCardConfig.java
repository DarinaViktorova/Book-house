package ua.house.book.creditcardservice.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "ua.house.book.creditcardservice.dao"
})
public class TestCreditCardConfig {
}
