package ua.book.house.auth.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.book.house.auth.domain.dto.response.AccountDto;
import ua.book.house.auth.domain.dto.response.AuthResponseDTO;
import ua.book.house.auth.service.AuthService;
import ua.book.house.auth.domain.dto.request.AuthorizationDTO;
import ua.book.house.auth.domain.dto.request.RegistrationDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/users/registration")
    public ResponseEntity<AuthResponseDTO> ordinalRegistration(@Valid @RequestBody RegistrationDTO registrationDTORequest) {
        var authResponseDTO = authService.ordinalRegistration(registrationDTORequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(authResponseDTO);
    }

    @PostMapping("/admins/registration")
    public ResponseEntity<AuthResponseDTO> adminRegistration(@Valid @RequestBody RegistrationDTO registrationDTORequest) {
        var authResponseDTO = authService.adminRegistration(registrationDTORequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(authResponseDTO);
    }

    @PostMapping("/authorization")
    public ResponseEntity<AuthResponseDTO> authorization(@Valid @RequestBody AuthorizationDTO authorizationDTORequest) {
        var authResponseDTO = authService.authorization(authorizationDTORequest);
        return ResponseEntity
                .ok()
                .body(authResponseDTO);
    }

    @GetMapping("/account/{email}")
    public ResponseEntity<AccountDto> getAccountByEmail(@PathVariable(name = "email") String accountEmail) {
        return ResponseEntity
               .ok()
               .body(authService.getAccountByEmail(accountEmail));
    }
}

