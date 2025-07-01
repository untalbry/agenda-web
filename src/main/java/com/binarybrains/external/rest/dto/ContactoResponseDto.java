package com.binarybrains.external.rest.dto;

import com.binarybrains.core.entity.Contacto;
import lombok.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(name = "Contact", description = "Contact information for reading")
public class ContactoResponseDto {
    @Schema(description = "Contact id")
    private Integer id;
    @Schema(description = "Contact id")
    private Integer idUser;
    @Schema(description = "Contact name")
    private String name;
    @Schema(description = "Contact last name")
    private String lastName;
    @Schema(description = "Contact second last name")
    private String secondLastName;
    @Schema(description = "Contact nickname")
    private String nickname;

    public static ContactoResponseDto fromEntity(Contacto contacto){
        return  ContactoResponseDto.builder()
                .id(contacto.getId())
                .idUser(contacto.getIdUser())
                .name(contacto.getName())
                .lastName(contacto.getLastName())
                .secondLastName(contacto.getSecondLastName())
                .nickname(contacto.getNickname())
                .build();
    }
}
