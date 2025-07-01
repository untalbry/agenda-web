package com.binarybrains.external.jpa.entity;

import com.binarybrains.core.entity.Contacto;
import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tac02_contacto")
public class ContactoJpa {
    @Id
    @SequenceGenerator(name = "tac02_contacto_id_contacto_seq", sequenceName = "tac02_contacto_id_contacto_seq", allocationSize = 1)
    @GeneratedValue(generator = "tac02_contacto_id_contacto_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id_contacto")
    private Integer id;
    @Column(name = "id_usuario")
    private Integer idUser;
    @Column(name = "tx_nombre")
    private String name;
    @Column(name = "tx_primer_apellido")
    private String lastName;
    @Column(name = "tx_segundo_apellido")
    private String secondLastName;
    @Column(name = "tx_apodo")
    private String nickname;

    public Contacto toEntity(){
        return Contacto.builder()
                .id(id)
                .idUser(idUser)
                .name(name)
                .lastName(lastName)
                .secondLastName(secondLastName)
                .nickname(nickname)
                .build();
    }
    public static ContactoJpa fromEntity(Contacto contacto){
        return ContactoJpa.builder()
                .id(contacto.getId())
                .idUser(contacto.getIdUser())
                .name(contacto.getName())
                .lastName(contacto.getLastName())
                .secondLastName(contacto.getSecondLastName())
                .nickname(contacto.getNickname())
                .build();
    }
}

