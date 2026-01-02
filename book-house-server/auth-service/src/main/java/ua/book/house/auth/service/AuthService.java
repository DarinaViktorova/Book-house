package ua.book.house.auth.service;

import ua.book.house.auth.domain.dto.request.AuthorizationDTO;
import ua.book.house.auth.domain.dto.request.RegistrationDTO;
import ua.book.house.auth.domain.dto.response.AccountDto;
import ua.book.house.auth.domain.dto.response.AuthResponseDTO;

public interface AuthService {
    AuthResponseDTO ordinalRegistration(RegistrationDTO accountDTORequest);
    AuthResponseDTO adminRegistration(RegistrationDTO accountDTORequest);
    AuthResponseDTO authorization(AuthorizationDTO authorizationDTORequest);
    AccountDto getAccountByEmail(String email);
}
