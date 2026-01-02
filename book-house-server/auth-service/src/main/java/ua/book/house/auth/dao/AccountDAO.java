package ua.book.house.auth.dao;

import ua.book.house.auth.domain.entity.Account;

import java.util.Optional;

public interface AccountDAO {
    void saveAccount(final Account account);
    Optional<Account> findUserAccountByEmailAndPassword(String email, String password);
    Optional<Account> findAdminAccountByEmailAndPassword(String email, String password);
    Optional<Account> findAccountByEmail(String email);
    Optional<Account> findAccountById(Long accountId);
}
