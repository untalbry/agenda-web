package com.binarybrains.external.rest.dto;

import com.binarybrains.core.entity.TipoMedioContacto;
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
@Schema(name = "Type contact medium", description = "DTO for contact medium type information.")
public class TipoMedioContactoDto {

    @NotEmpty(message = "The name cannot be empty.")
    @Size(max = 100, message = "The name cannot exceed 100 characters.")
    @Schema(description = "Name of the contact medium.")
    private String name;

    @NotEmpty(message = "The description cannot be empty.")
    @Size(max = 255, message = "The description cannot exceed 255 characters.")
    @Schema(description = "Description of the contact medium.")
    private String description;

    @NotNull(message = "The 'active' status cannot be null.")
    @Schema(description = "Indicates whether the contact medium is active.")
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