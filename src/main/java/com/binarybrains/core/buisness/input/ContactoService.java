package com.binarybrains.core.buisness.input;

import com.binarybrains.core.entity.Contacto;
import com.binarybrains.utils.ErrorCode;
import io.vavr.control.Either;


public interface ContactoService {
    Either<ErrorCode, Contacto> save(Contacto contacto);
    Either<ErrorCode, Contacto> getByNickname(String nickname);
    Either<ErrorCode, Contacto> update(Contacto contacto);
    Either<ErrorCode, Boolean> deleteContactById(Integer id);

}
