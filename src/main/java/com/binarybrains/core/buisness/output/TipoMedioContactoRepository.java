package com.binarybrains.core.buisness.output;

import com.binarybrains.core.entity.TipoMedioContacto;

import java.util.List;
import java.util.Optional;

public interface TipoMedioContactoRepository {
    Optional<TipoMedioContacto> create(TipoMedioContacto tipoMedioContacto);
    Optional<List<TipoMedioContacto>> getMedioContactoByName(String name);
}
