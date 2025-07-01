package com.binarybrains.external.rest.dto;

import com.binarybrains.core.entity.Contacto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(name = "Contact", description = "DTO for contact information.")
public class ContactoDto {

    @NotNull(message = "User ID cannot be null.")
    @Schema(description = "Identifier of the user associated with this contact.")
    private Integer idUser;

    @NotEmpty(message = "Nickname cannot be empty.")
    @Size(max = 100, message = "Nickname cannot exceed 100 characters.")
    @Schema(description = "Nickname or alias for the contact.")
    private String nickname;

    public Contacto toEntity(){
        return Contacto.builder()
                .idUser(idUser)
                .nickname(nickname)
                .build();
    }
}