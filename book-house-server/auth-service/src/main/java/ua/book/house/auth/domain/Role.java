package ua.book.house.auth.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role implements GrantedAuthority {
    private String nameRole;

    @Override
    public String getAuthority() {
        return nameRole;
    }
}
