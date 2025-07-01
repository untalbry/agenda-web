package com.binarybrains.core.entity;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Contacto {
    private Integer id;
    private Integer idUser;
    private String name;
    private String lastName;
    private String secondLastName;
    private String nickname;

}
