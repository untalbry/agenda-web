package external.rest.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.List;

@Schema(name = "AuthenticationResponse", description = "User authentication entity")
public class AuthenticationResponseDto {
    @Schema(description = "Has the user information")
    private UserDto userDto;
    @Schema(description = "users rols list")
    private List<RolDto> rols;
    @Schema(description = "user authentication")
    private AuthenticationDto authenticationDto;

    @Schema(name="AuthenticationResponse.User", description = "has the information from the user that whats to authenticate")
    private static class UserDto {
        private Integer id;
        private String name;
        private String lastName;
        private String secondLastName;
        private String email;
        private String password;

    }
    @Schema(name="AuthenticationResponse.Rol", description = "")
    private static class RolDto{
        private Integer id;
        private String name;
    }
    private static class AuthenticationDto{
        private String token;
        private String refreshToken;
        private LocalDateTime date;
        private LocalDateTime expires;
    }
}
