package com.binarybrains.core.buisness.input;

import com.binarybrains.core.entity.MedioContacto;
import com.binarybrains.utils.ErrorCode;
import io.vavr.control.Either;

public interface MedioContactoService {
    Either<ErrorCode, MedioContacto> save(MedioContacto medioContacto);
    Either<ErrorCode, MedioContacto> getById(Integer id);
    Either<ErrorCode, Boolean> delete(Integer id);

}
