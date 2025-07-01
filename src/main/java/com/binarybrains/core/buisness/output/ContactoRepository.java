package com.binarybrains.core.buisness.output;

import com.binarybrains.core.entity.Contacto;
import java.util.List;
import java.util.Optional;

public interface ContactoRepository {
    Optional<Contacto> create(Contacto contacto);
    Optional<List<Contacto>> getContactByNickname(String nickname);

}
