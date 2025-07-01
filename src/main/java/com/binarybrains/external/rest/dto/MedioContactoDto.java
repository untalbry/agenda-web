package com.binarybrains.external.rest.dto;

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
@Schema(name = "MedioContactoDto", description = "DTO for contact medium information.")
public class MedioContactoDto {

    @NotNull(message = "Contact ID cannot be null.")
    @Schema(description = "Identifier of the contact.")
    private Integer contactId;

    @NotNull(message = "Contact Type ID cannot be null.")
    @Schema(description = "Identifier of the contact type.")
    private Integer typeContactId;

    @NotEmpty(message = "The value cannot be empty.")
    @Size(max = 255, message = "The value cannot exceed 255 characters.")
    @Schema(description = "The actual value of the contact medium (e.g., email address, phone number).")
    private String value;
}