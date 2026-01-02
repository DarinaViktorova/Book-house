package ua.house.book.creditcardservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableAspectJAutoProxy
@SpringBootApplication
public class CreditCardServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CreditCardServiceApplication.class, args);
    }

}
