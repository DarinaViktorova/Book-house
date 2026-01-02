package ua.book.house.auth.domain.dto.response;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class AuthResponseDTO {
    private String token;
}
