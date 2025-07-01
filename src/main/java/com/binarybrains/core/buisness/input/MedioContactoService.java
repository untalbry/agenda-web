package com.binarybrains.core.buisness.input;

import com.binarybrains.core.entity.MedioContacto;
import com.binarybrains.utils.ErrorCode;
import io.vavr.control.Either;

import java.util.List;

public interface MedioContactoService {
    Either<ErrorCode, MedioContacto> save(MedioContacto medioContacto);
    Either<ErrorCode, List<MedioContacto>> readMedioContactoByName(String name);
}
