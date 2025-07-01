package com.binarybrains.external.rest.dto;

import com.binarybrains.core.entity.TipoMedioContacto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(name = "Tipo medio contacto", description = "Type of medio contacto information to register")
public class TipoMedioContactoDto {
    @NotEmpty(message = "RN001")
    @Schema(description = "Medio contacto name")
    private String name;
    @NotEmpty(message = "RN001")
    @Schema(description = "Medio contacto description")
    private String description;
    @NotNull(message = "RN001")
    @Schema(description = "active flag")
    private Boolean active;
    public TipoMedioContacto toEntity(){
        return TipoMedioContacto.builder()
                .name(name)
                .description(description)
                .active(active)
                .build();
    }
    public static TipoMedioContactoDto fromEntity(TipoMedioContacto tipoMedioContacto){
        return TipoMedioContactoDto.builder()
                .name(tipoMedioContacto.getName())
                .description(tipoMedioContacto.getDescription())
                .active(tipoMedioContacto.getActive())
                .build();
    }
}
