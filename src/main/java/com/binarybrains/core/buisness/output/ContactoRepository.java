package com.binarybrains.core.buisness.output;

import com.binarybrains.core.entity.Contacto;
import java.util.List;
import java.util.Optional;

public interface ContactoRepository {
    Optional<Contacto> create(Contacto contacto);
    Optional<List<Contacto>> getContactByNickname(String nickname);

    Optional<Contacto> update(Contacto contacto);
    Optional<List<Contacto>> getContactByUserId(Integer id);

    Optional<Contacto> getContactById(Integer id);
    void deleteContact(Contacto contacto);

}
