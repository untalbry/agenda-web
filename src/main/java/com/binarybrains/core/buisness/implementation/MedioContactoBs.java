package com.binarybrains.core.buisness.implementation;

import com.binarybrains.core.buisness.input.MedioContactoService;
import com.binarybrains.core.buisness.output.MedioContactoRepository;
import com.binarybrains.core.entity.MedioContacto;
import com.binarybrains.utils.ErrorCode;
import io.vavr.control.Either;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class MedioContactoBs implements MedioContactoService {
    MedioContactoRepository medioContactoRepository;
    @Inject
    public MedioContactoBs(MedioContactoRepository medioContactoRepository){
        this.medioContactoRepository = medioContactoRepository;
    }
    @Override
    public Either<ErrorCode, MedioContacto> save(MedioContacto medioContacto) {
        Either<ErrorCode, MedioContacto> result;
        var existsMedioContacto = medioContactoRepository.getMedioContactoByName(medioContacto.getName());
        if(existsMedioContacto.isEmpty()){
            result = Either.left(ErrorCode.RN004);
        }else{
            result = medioContactoRepository.create(medioContacto)
                    .map(medioContactoRegister -> Either.<ErrorCode, MedioContacto>right(medioContactoRegister))
                    .orElseGet(() -> Either.left(ErrorCode.RN001));
        }
        return result;
    }

    @Override
    public Either<ErrorCode, List<MedioContacto>> readMedioContactoByName(String name) {
        Either<ErrorCode, List<MedioContacto>> result;
        var existsMedioContacto = medioContactoRepository.getMedioContactoByName(name);
        result = existsMedioContacto.<Either<ErrorCode, List<MedioContacto>>>map(Either::right).orElseGet(() -> Either.left(ErrorCode.RN004));
        return result;
    }
}
