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
@Table(name = "tac03_medio_contacto")
public class MedioContactoJpa {
    @Id
    @SequenceGenerator(name = "tac03_medio_contacto_id_medio_contacto_seq", sequenceName = "tac03_medio_contacto_id_medio_contacto_seq", allocationSize = 1)
    @GeneratedValue(generator = "tac03_medio_contacto_id_medio_contacto_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id_medio_contacto")
    private Integer id;
    @Column(name = "id_contacto")
    private Integer idContacto;
    @Column(name = "id_tipo")
    private Integer idTipo;
    @Column(name = "valor")
    private String valor;

    public MedioContacto toEntity(){
        return MedioContacto.builder()
                .id(id)
                .idContacto(idContacto)
                .idTipo(idTipo)
                .valor(valor)
                .build();
    }
    public static MedioContactoJpa fromEntity(MedioContacto medio){
        return MedioContactoJpa.builder()
                .id(medio.getId())
                .idContacto(medio.getIdContacto())
                .idTipo(medio.getIdTipo())
                .valor(medio.getValor())
                .build();
    }
}
