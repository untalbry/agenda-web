package com.binarybrains.external.jpa.entity;

import com.binarybrains.core.entity.MedioContacto;
import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cac01_tipo_medio_contacto")
public class MedioContactoJpa {
    @Id
    @SequenceGenerator(name = "cac01_tipo_medio_contacto_id_tipo_seq", sequenceName = "cac01_tipo_medio_contacto_id_tipo_seq", allocationSize = 1)
    @GeneratedValue(generator = "cac01_tipo_medio_contacto_id_tipo_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id_tipo")
    private Integer id;
    @Column(name = "tx_nombre")
    private String name;
    @Column(name = "tx_descripcion")
    private String description;
    @Column(name = "st_activo")
    private Boolean active;

    public MedioContacto toEntity(){
        return MedioContacto.builder()
                .id(id)
                .name(name)
                .description(description)
                .active(active)
                .build();
    }
    public static MedioContactoJpa fromEntity(MedioContacto medioContacto){
        return MedioContactoJpa.builder()
                .id(medioContacto.getId())
                .name(medioContacto.getName())
                .description(medioContacto.getDescription())
                .active(medioContacto.getActive())
                .build();
    }
}
