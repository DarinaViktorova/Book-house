package ua.book.house.auth;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import ua.book.house.auth.config.TestAuthBeansConfig;
import ua.book.house.auth.config.TestAuthConfig;
import ua.book.house.auth.config.TestHibernateConfig;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ContextConfiguration(classes = {TestHibernateConfig.class, TestAuthConfig.class, TestAuthBeansConfig.class})
class AuthApplicationTests {

	@Autowired
	private ApplicationContext applicationContext;

	@Test
	void contextLoads() {
		assertThat(applicationContext).isNotNull();
	}
}
