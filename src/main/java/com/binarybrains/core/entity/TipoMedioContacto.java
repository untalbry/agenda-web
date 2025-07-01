package com.binarybrains.core.entity;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TipoMedioContacto {
    private Integer id;
    private String name;
    private String description;
    private Boolean active;
}
