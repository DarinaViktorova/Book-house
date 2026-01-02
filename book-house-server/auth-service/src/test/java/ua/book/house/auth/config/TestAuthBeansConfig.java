package ua.book.house.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua.book.house.auth.domain.entity.Account;

import java.util.Set;

@Configuration
public class TestAuthBeansConfig {
//    @Bean
//    public Account userAccount() {
//        return Account.builder()
//                .id(12L)
//                .email("dimon@gmail.com")
//                .password("qwerty")
//                .nickname("dimas")
//                .roles(Set.of(Role.builder().nameRole("USER").build()))
//                .user(User.builder().build())
//                .build();
//    }

//    @Bean
//    public Card card(){
//        return Card.builder()
//                   .numberCard("xxxx-xxxx-xxxx-xxxx")
//                   .cardEndDataMonth((short)5)
//                   .cardEndDataYear((short)2026)
//                   .cvc2("859")
//                   .moneyCards(moneyCard())
//                   .account(userAccount())
//                   .build();
//    }
//
//    @Bean
//    public MoneyCards moneyCard(){
//        return MoneyCards.builder()
//                .spendLimit(1000)
//                .money(Money.builder()
//                        .id(null)
//                        .amount(2200)
//                        .currency(Currency.UAH)
//                        .build())
//                .build();
//    }


//    @Bean
//    public Account adminAccount() {
//        return Account.builder()
//                .id(null)
//                .email("admin@gmail.com")
//                .password("admin")
//                .nickname("admin")
//                .roles(Set.of(Role.builder().nameRole("ADMIN").build()))
//                .admin(Admin.builder().build())
//                .build();
//    }

//    @Bean
//    public RegistrationDTO userAccountRegistrationDtoRequest() {
//        return RegistrationDTO.builder()
//                .email("dimon@gmail.com")
//                .password("qwerty")
//                .username("dimas")
//                .build();
//    }
//
//    @Bean
//    public RegistrationDTO adminAccountRegistrationDtoRequest() {
//        return RegistrationDTO.builder()
//                .email("admin@gmail.com")
//                .password("admin")
//                .username("admin")
//                .build();
//    }
//
//    @Bean
//    public AuthorizationDTO userAuthorizationDtoRequest() {
//        return AuthorizationDTO
//                .builder()
//                .email("dimon@gmail.com")
//                .password("qwerty")
//                .build();
//    }
//
//    @Bean
//    public AuthorizationDTO adminAuthorizationDtoRequest() {
//        return AuthorizationDTO
//                .builder()
//                .email("admin@gmail.com")
//                .password("admin")
//                .build();
//    }
//
//    @Bean
//    public AuthResponseDTO authResponseDTO(){
//        return AuthResponseDTO.builder()
//                .token("token")
//                .build();
//    }
}
