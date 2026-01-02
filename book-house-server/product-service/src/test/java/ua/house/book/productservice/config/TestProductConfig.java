package ua.house.book.productservice.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "ua.house.book.productservice.dao"
})
public class TestProductConfig {
}
