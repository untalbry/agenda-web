package com.binarybrains.core.buisness.input;

import com.binarybrains.core.entity.TipoMedioContacto;
import com.binarybrains.utils.ErrorCode;
import io.vavr.control.Either;

import java.util.List;

public interface TipoMedioContactoService {
    Either<ErrorCode, TipoMedioContacto> save(TipoMedioContacto tipoMedioContacto);
    Either<ErrorCode, List<TipoMedioContacto>> readMedioContactoByName(String name);
}
