package com.binarybrains.core.buisness.implementation;

import com.binarybrains.core.buisness.input.MedioContactoService;
import com.binarybrains.core.buisness.output.ContactoRepository;
import com.binarybrains.core.buisness.output.MedioContactoRepository;
import com.binarybrains.core.buisness.output.TipoMedioContactoRepository;
import com.binarybrains.core.entity.MedioContacto;
import com.binarybrains.utils.ErrorCode;
import io.vavr.control.Either;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class MedioContactoBs implements MedioContactoService {
    private final MedioContactoRepository medioContactoRepository;
    private final TipoMedioContactoRepository tipoMedioContactoRepository;
    private final ContactoRepository contactoRepository;
    @Inject
    public MedioContactoBs(MedioContactoRepository medioContactoRepository, TipoMedioContactoRepository tipoMedioContactoRepository, ContactoRepository contactoRepository){
        this.medioContactoRepository = medioContactoRepository;
        this.tipoMedioContactoRepository = tipoMedioContactoRepository;
        this.contactoRepository = contactoRepository;
    }
    @Override
    public Either<ErrorCode, MedioContacto> save(MedioContacto medioContacto) {
        Either<ErrorCode, MedioContacto> result;
        var contactoExits = contactoRepository.getContactById(medioContacto.getIdContacto());
        if(contactoExits.isEmpty()){
            result = Either.left(ErrorCode.RN004);
        }else{
            var tipoExits = tipoMedioContactoRepository.findById(medioContacto.getIdTipo());
            if(tipoExits.isEmpty()){
                result = Either.left(ErrorCode.RN004);
            }else{
                var medioContactoResult = medioContactoRepository.create(medioContacto);
                result = medioContactoResult.<Either<ErrorCode, MedioContacto>>map(Either::right).orElseGet(() -> Either.left(ErrorCode.RN006));
            }

        }
        return result;
    }

    @Override
    public Either<ErrorCode, MedioContacto> getById(Integer id) {
        Either<ErrorCode, MedioContacto> result;
        var medioContactoExist = medioContactoRepository.findById(id);
        result = medioContactoExist.<Either<ErrorCode, MedioContacto>>map(Either::right).orElseGet(() -> Either.left(ErrorCode.RN004));
        return result;
    }

    @Override
    public Either<ErrorCode, Boolean> delete(Integer id) {
        Either<ErrorCode, Boolean> result;
        var contactoExists = medioContactoRepository.findById(id);
        if(contactoExists.isEmpty()){
            result = Either.left(ErrorCode.RN004);
        }else{
            medioContactoRepository.remove(contactoExists.get());
            result = Either.right(true);
        }
        return result;
    }
}
