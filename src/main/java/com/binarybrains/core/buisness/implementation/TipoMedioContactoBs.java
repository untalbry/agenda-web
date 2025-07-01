package com.binarybrains.core.buisness.implementation;

import com.binarybrains.core.buisness.input.TipoMedioContactoService;
import com.binarybrains.core.buisness.output.TipoMedioContactoRepository;
import com.binarybrains.core.entity.TipoMedioContacto;
import com.binarybrains.utils.ErrorCode;
import io.vavr.control.Either;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class TipoMedioContactoBs implements TipoMedioContactoService {
    TipoMedioContactoRepository tipoMedioContactoRepository;
    @Inject
    public TipoMedioContactoBs(TipoMedioContactoRepository tipoMedioContactoRepository){
        this.tipoMedioContactoRepository = tipoMedioContactoRepository;
    }
    @Override
    public Either<ErrorCode, TipoMedioContacto> save(TipoMedioContacto tipoMedioContacto) {
        Either<ErrorCode, TipoMedioContacto> result;
        var existsMedioContacto = tipoMedioContactoRepository.getMedioContactoByName(tipoMedioContacto.getName());
        if(existsMedioContacto.isEmpty()){
            result = Either.left(ErrorCode.RN004);
        }else{
            result = tipoMedioContactoRepository.create(tipoMedioContacto)
                    .map(Either::<ErrorCode, TipoMedioContacto>right)
                    .orElseGet(() -> Either.left(ErrorCode.RN001));
        }
        return result;
    }

    @Override
    public Either<ErrorCode, List<TipoMedioContacto>> readMedioContactoByName(String name) {
        Either<ErrorCode, List<TipoMedioContacto>> result;
        var existsMedioContacto = tipoMedioContactoRepository.getMedioContactoByName(name);
        result = existsMedioContacto.<Either<ErrorCode, List<TipoMedioContacto>>>map(Either::right).orElseGet(() -> Either.left(ErrorCode.RN004));
        return result;
    }
}
