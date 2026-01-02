package ua.book.house.auth.dao;

import jakarta.inject.Inject;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ua.book.house.auth.AuthApplication;
import ua.book.house.auth.config.TestAuthBeansConfig;
import ua.book.house.auth.config.TestAuthConfig;
import ua.book.house.auth.config.TestHibernateConfig;
import ua.book.house.auth.domain.Role;
import ua.book.house.auth.domain.entity.Account;
import ua.book.house.auth.domain.entity.Admin;
import ua.book.house.auth.domain.entity.User;
import ua.book.house.auth.exception.UserNotFoundException;

import java.util.Set;


@SpringBootTest
@ContextConfiguration(classes = {TestHibernateConfig.class, TestAuthConfig.class, TestAuthBeansConfig.class})
class AccountDAOImplTest {
    @Autowired
    private AccountDAO accountDAO;
    private Account userAccount;
    private Account adminAccount;
    @BeforeEach
    public void init(){
        userAccount = Account.builder()
                             .id(12L)
                             .email("dimon123@gmail.com")
                             .password("qwerty")
                             .nickname("dimas")
                             .roles(Set.of(Role.builder()
                                               .nameRole("USER")
                                               .build()))
                             .user(User.builder()
                                       .build())
                             .build();
        adminAccount = Account.builder()
                              .id(null)
                              .email("admin1@gmail.com")
                              .password("admin")
                              .nickname("admin")
                              .roles(Set.of(Role.builder()
                                                .nameRole("ADMIN")
                                                .build()))
                              .admin(Admin.builder()
                                          .build())
                              .build();
    }
    @Test
    void createAdminShouldReturnTrue() {
        accountDAO.saveAccount(adminAccount);
        Account foundAccount = accountDAO.findAdminAccountByEmailAndPassword(adminAccount.getEmail(), adminAccount.getPassword())
                .orElseThrow(() -> new UserNotFoundException("Not found account with this args: "
                        + adminAccount.getEmail() + " " + adminAccount.getPassword()));
        Assertions.assertEquals(adminAccount, foundAccount);
    }
    @Test
    void createUserShouldReturnTrue() {
        accountDAO.saveAccount(userAccount);
        Account foundAccount = accountDAO.findUserAccountByEmailAndPassword(userAccount.getEmail(), userAccount.getPassword())
                .orElseThrow(() -> new UserNotFoundException("Not found account with this args: "
                        + userAccount.getEmail() + " " + userAccount.getPassword()));
        System.out.println(foundAccount);
        Assertions.assertEquals(userAccount, foundAccount);
    }
}