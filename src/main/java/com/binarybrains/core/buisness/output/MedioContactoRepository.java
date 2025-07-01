package com.binarybrains.core.buisness.output;

import com.binarybrains.core.entity.MedioContacto;

import java.util.List;
import java.util.Optional;

public interface MedioContactoRepository {
    Optional<MedioContacto> create(MedioContacto medioContacto);
    Optional<List<MedioContacto>> getMedioContactoByName(String name);
}
