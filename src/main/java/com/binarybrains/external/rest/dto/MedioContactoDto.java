package com.binarybrains.external.rest.dto;

import com.binarybrains.core.entity.MedioContacto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(name = "Medio contacto", description = "Medio contacto information to register")
public class MedioContactoDto {
    @NotEmpty(message = "RN001")
    @Schema(description = "Medio contacto name")
    private String name;
    @NotEmpty(message = "RN001")
    @Schema(description = "Medio contacto description")
    private String description;
    @NotNull(message = "RN001")
    @Schema(description = "active flag")
    private Boolean active;
    public MedioContacto toEntity(){
        return MedioContacto.builder()
                .name(name)
                .description(description)
                .active(active)
                .build();
    }
    public static MedioContactoDto fromEntity(MedioContacto medioContacto){
        return MedioContactoDto.builder()
                .name(medioContacto.getName())
                .description(medioContacto.getDescription())
                .active(medioContacto.getActive())
                .build();
    }
}
