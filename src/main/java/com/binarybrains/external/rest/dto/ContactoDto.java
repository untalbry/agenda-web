package com.binarybrains.external.rest.dto;

import com.binarybrains.core.entity.Contacto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(name = "Contacto", description = "Contacto information to register")
public class ContactoDto {
    @NotNull(message = "RN001")
    @Schema(description = "User id")
    private Integer idUser;
    @NotEmpty(message = "RN001")
    @Schema(description = "Contact nickname")
    private String nickname;
    public Contacto toEntity(){
        return Contacto.builder()
                .idUser(idUser)
                .nickname(nickname)
                .build();
    }

}
