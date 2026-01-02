package ua.book.house.auth.controller;

import org.springframework.boot.test.context.SpringBootTest;
import ua.book.house.auth.AuthApplication;
import ua.book.house.auth.config.TestAuthBeansConfig;
import ua.book.house.auth.config.TestAuthConfig;
import ua.book.house.auth.config.TestHibernateConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ua.book.house.auth.domain.dto.request.AuthorizationDTO;
import ua.book.house.auth.domain.dto.request.RegistrationDTO;
import ua.book.house.auth.domain.dto.response.AuthResponseDTO;
import ua.book.house.auth.service.AuthService;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ContextConfiguration(classes = {TestHibernateConfig.class, TestAuthConfig.class, TestAuthBeansConfig.class})
class AuthControllerTest {
    @InjectMocks
    private AuthController authController;
    @Mock
    private AuthService authService;
    private MockMvc mockMvc;
    private AuthResponseDTO authResponseDTO;
    private RegistrationDTO userAccountRegistrationDtoRequest;
    private RegistrationDTO adminAccountRegistrationDtoRequest;
    private AuthorizationDTO userAuthorizationDtoRequest;
    private AuthorizationDTO adminAuthorizationDtoRequest;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(authController)
                .build();

        authResponseDTO = AuthResponseDTO.builder()
                .token("token")
                .build();

        userAccountRegistrationDtoRequest = RegistrationDTO.builder()
                .email("dimon@gmail.com")
                .password("qwerty")
                .username("dimas")
                .build();

        adminAccountRegistrationDtoRequest = RegistrationDTO.builder()
                .email("admin@gmail.com")
                .password("admin1")
                .username("admin")
                .build();

        userAuthorizationDtoRequest = AuthorizationDTO
                .builder()
                .email("dimon@gmail.com")
                .password("qwerty")
                .build();

        adminAuthorizationDtoRequest = AuthorizationDTO
                .builder()
                .email("admin@gmail.com")
                .password("admin1")
                .build();
    }

    @Test
    void testOrdinalRegistrationHttp() throws Exception {
        // Prepare the request data
        // Perform the HTTP POST request to the endpoint
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/auth/users/registration")
                        .content(asJsonString(userAccountRegistrationDtoRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    @SneakyThrows
    void testAdminRegistrationHttp() {
        // Prepare the request data
        // Perform the HTTP POST request to the endpoint
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/auth/admins/registration")
                        .content(asJsonString(adminAccountRegistrationDtoRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void testAdminAuthorizationHttp() throws Exception {
        given(authService.authorization(ArgumentMatchers.any()))
                .willReturn(authResponseDTO);
        // Perform the HTTP POST request to the endpoint
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/auth/authorization")
                        .content(asJsonString(adminAuthorizationDtoRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testOrdinalAuthorizationHttp() throws Exception {
        given(authService.authorization(ArgumentMatchers.any()))
                .willReturn(authResponseDTO);
        // Perform the HTTP POST request to the endpoint
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/auth/authorization")
                        .content(asJsonString(userAuthorizationDtoRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    // Helper method to convert an object to JSON
    private String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}