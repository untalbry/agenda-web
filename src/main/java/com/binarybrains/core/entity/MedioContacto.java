package com.binarybrains.core.entity;

import jakarta.persistence.Entity;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MedioContacto {
    private Integer id;
    private Integer idContacto;
    private Integer idTipo;
    private String valor;
}
