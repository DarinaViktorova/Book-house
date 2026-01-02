package ua.book.house.auth.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import ua.book.house.auth.config.filter.JwtAuthenticationFilter;

@Configuration
@ComponentScan(basePackages = {
        "ua.book.house.auth.dao"
})
@ContextConfiguration(classes = {SecurityConfig.class, JwtAuthenticationFilter.class})
public class TestAuthConfig {
}
