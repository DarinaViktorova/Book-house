package ua.book.house.auth.dao;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.book.house.auth.dao.hql.TokenHQL;
import ua.book.house.auth.domain.entity.Token;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
@Transactional(readOnly = true)
public class TokenDAOImpl implements TokenDAO{
    private final SessionFactory sessionFactory;

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }
    @Override
    public Optional<Token> findByToken(String token) {
        var accountToken = currentSession()
                .createQuery(TokenHQL.FIND_BY_TOKEN, Token.class)
                .setParameter("token", token)
                .uniqueResult();
        return Optional.of(accountToken);
    }

    @Override
    @Transactional
    public void saveToken(Token token) {
        currentSession().merge(token);
    }

    @Override
    public List<Token> findAllValidTokenByAccount(Long accountId) {
        return currentSession()
                .createQuery(TokenHQL.FIND_ALL_VALID_TOKENS_BY_ID_ACCOUNT, Token.class)
                .setParameter("accountId", accountId)
                .list();
    }

    @Override
    @Transactional
    public void saveAllTokens(List<Token> validUserTokens) {
        for (var token : validUserTokens) {
            currentSession().merge(token);
        }
    }

}
