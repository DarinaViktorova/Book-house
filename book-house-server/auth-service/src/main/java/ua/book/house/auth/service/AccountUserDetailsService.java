package ua.book.house.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.book.house.auth.exception.UserNotFoundException;
import ua.book.house.auth.dao.AccountDAO;

@Service
@RequiredArgsConstructor
public class AccountUserDetailsService implements UserDetailsService {
    private final AccountDAO accountDAO;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountDAO.findAccountByEmail(username)
                .orElseThrow(() -> new UserNotFoundException("Account with email: " + username + " was not found!"));
    }
}
