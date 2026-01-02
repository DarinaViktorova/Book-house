package ua.book.house.auth.dao;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.book.house.auth.domain.entity.Account;
import ua.book.house.auth.dao.hql.AccountHQL;

import javax.annotation.PostConstruct;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
@Transactional(readOnly = true)
public class AccountDAOImpl implements AccountDAO {
    private final SessionFactory sessionFactory;

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Transactional
    @Override
    public void saveAccount(Account account) {
        currentSession().save(account);
    }

    @Override
    public Optional<Account> findUserAccountByEmailAndPassword(String email, String password) {
        var resultSearch = currentSession()
                .createQuery(AccountHQL.FIND_USER_BY_EMAIL_AND_PASSWORD, Account.class)
                .setParameter("email", email)
                .setParameter("password", password)
                .uniqueResult();
        return Optional.ofNullable(resultSearch);
    }

    @Override
    public Optional<Account> findAdminAccountByEmailAndPassword(String email, String password) {
        var resultSearch = currentSession()
                .createQuery(AccountHQL.FIND_ADMIN_BY_EMAIL_AND_PASSWORD, Account.class)
                .setParameter("email", email)
                .setParameter("password", password)
                .uniqueResult();
        return Optional.ofNullable(resultSearch);
    }

    @Override
    public Optional<Account> findAccountByEmail(String email) {
        var resultSearch = currentSession()
                .createQuery(AccountHQL.FIND_USER_BY_EMAIL, Account.class)
                .setParameter("email", email)
                .uniqueResult();
        return Optional.ofNullable(resultSearch);
    }

    @Override
    public Optional<Account> findAccountById(Long accountId) {
        var resultSearch = Optional.ofNullable(currentSession().find(Account.class, accountId));
        return Optional.ofNullable(currentSession().find(Account.class, accountId));
    }
}
