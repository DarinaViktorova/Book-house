package ua.book.house.auth.dao;

import ua.book.house.auth.domain.entity.Token;

import java.util.List;
import java.util.Optional;

public interface TokenDAO {
    Optional<Token> findByToken(String token);
    void saveToken(Token token);

    List<Token> findAllValidTokenByAccount(Long id);

    void saveAllTokens(List<Token> validUserTokens);
}
