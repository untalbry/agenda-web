package com.binarybrains.core.buisness.output;

import com.binarybrains.core.entity.MedioContacto;

import java.util.Optional;

public interface MedioContactoRepository {
    Optional<MedioContacto> create(MedioContacto medioContacto);
    Optional<MedioContacto> findById(Integer id);
    void remove(MedioContacto medioContacto);
    Optional<MedioContacto> update(MedioContacto medioContacto);

}
