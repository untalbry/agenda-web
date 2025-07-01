package com.binarybrains.external.rest.dto;

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
    @NotNull
    @Schema(name = "id contacto")
    private Integer ContactId;
    @NotNull
    @Schema(name = "id tipo contacto")
    private Integer TypeContactId;
    @NotEmpty
    @Schema(name = "value of type contact")
    private String value;
}
