package ua.book.house.auth.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ua.book.house.auth.config.TestAuthBeansConfig;
import ua.book.house.auth.config.TestAuthConfig;
import ua.book.house.auth.config.TestHibernateConfig;
import ua.book.house.auth.dao.AccountDAO;
import ua.book.house.auth.dao.TokenDAO;
import ua.book.house.auth.domain.Role;
import ua.book.house.auth.domain.dto.request.AuthorizationDTO;
import ua.book.house.auth.domain.dto.request.RegistrationDTO;
import ua.book.house.auth.domain.dto.response.AuthResponseDTO;
import ua.book.house.auth.domain.entity.Account;
import ua.book.house.auth.domain.entity.Admin;
import ua.book.house.auth.domain.entity.User;
import ua.book.house.auth.service.jwt.JwtService;

import java.util.Optional;
import java.util.Set;

@SpringBootTest
@ContextConfiguration(classes = {TestHibernateConfig.class, TestAuthConfig.class, TestAuthBeansConfig.class})
class AuthServiceImplTest {
    @InjectMocks
    private AuthServiceImpl authService;
    @Mock
    private AccountDAO accountDAO;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private JwtService jwtService;
    @Mock
    private TokenDAO tokenDAO;
    @Mock
    private AuthenticationManager authenticationManager;
    private Account userAccount;
    private Account adminAccount;
    private AuthResponseDTO authResponseDTO;
    private RegistrationDTO userAccountRegistrationDtoRequest;
    private RegistrationDTO adminAccountRegistrationDtoRequest;
    private AuthorizationDTO userAuthorizationDtoRequest;
    private AuthorizationDTO adminAuthorizationDtoRequest;

    @BeforeEach
    void setUp() {
        userAccount = Account.builder()
                             .id(12L)
                             .email("dimon@gmail.com")
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
                              .email("admin@gmail.com")
                              .password("admin")
                              .nickname("admin")
                              .roles(Set.of(Role.builder()
                                                .nameRole("ADMIN")
                                                .build()))
                              .admin(Admin.builder()
                                          .build())
                              .build();
        authResponseDTO = AuthResponseDTO.builder()
                                         .token("token")
                                         .build();

        userAccountRegistrationDtoRequest = RegistrationDTO.builder()
                                                           .email("dimon@gmail.com")
                                                           .password("qwerty")
                                                           .username("dimas")
                                                           .build();

        adminAccountRegistrationDtoRequest = RegistrationDTO.builder()
                                                            .email("admin@gmail.com")
                                                            .password("admin")
                                                            .username("admin")
                                                            .build();

        userAuthorizationDtoRequest = AuthorizationDTO
                .builder()
                .email("dimon@gmail.com")
                .password("qwerty")
                .build();

        adminAuthorizationDtoRequest = AuthorizationDTO
                .builder()
                .email("admin@gmail.com")
                .password("admin")
                .build();

    }

    @Test
    @Order(1)
    void registrationUserShouldBeSuccessful() {
        Mockito.doNothing()
                .when(accountDAO)
                .saveAccount(Mockito.any());
        Mockito.when(passwordEncoder.encode(Mockito.any()))
                .thenReturn("qwerty");
        Mockito.when(jwtService.generateToken(Mockito.any()))
                .thenReturn("token");
        Assertions.assertEquals(authResponseDTO, authService.ordinalRegistration(userAccountRegistrationDtoRequest));
    }

    @Test
    @Order(2)
    void authorizationUserShouldReturnAccount() {
        Mockito.when(accountDAO.
                        findAccountByEmail(userAccount.getEmail()))
                .thenReturn(Optional.ofNullable(userAccount));
        Mockito.when(jwtService.generateToken(Mockito.any()))
                .thenReturn("token");
        Authentication mockAuthentication = Mockito.mock(Authentication.class);
        Mockito.when(authenticationManager.authenticate(Mockito.any()))
                .thenReturn(mockAuthentication);
        Assertions.assertEquals(authResponseDTO, authService.authorization(userAuthorizationDtoRequest));
    }

    @Test
    @Order(3)
    void registrationAdminShouldBeSuccessful() {
        Mockito.doNothing()
                .when(accountDAO)
                .saveAccount(Mockito.any());
        Mockito.when(passwordEncoder.encode(Mockito.any()))
                .thenReturn("admin");
        Mockito.when(jwtService.generateToken(Mockito.any()))
                .thenReturn("token");
        Assertions.assertEquals(authResponseDTO, authService.adminRegistration(adminAccountRegistrationDtoRequest));
    }

    @Test
    @Order(4)
    void authorizationAdminShouldReturnAccount() {
        Mockito.when(accountDAO.
                       findAccountByEmail(adminAccount.getEmail()))
               .thenReturn(Optional.of(adminAccount));
        Mockito.when(jwtService.generateToken(Mockito.any()))
               .thenReturn("token");
        Authentication mockAuthentication = Mockito.mock(Authentication.class);
        Mockito.when(authenticationManager.authenticate(Mockito.any()))
               .thenReturn(mockAuthentication);
        Assertions.assertEquals(authResponseDTO, authService.authorization(adminAuthorizationDtoRequest));
    }

}