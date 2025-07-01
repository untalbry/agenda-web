package com.binarybrains.core.buisness.implementation;

import com.binarybrains.core.buisness.input.ContactoService;
import com.binarybrains.core.buisness.output.ContactoRepository;
import com.binarybrains.core.entity.Contacto;
import com.binarybrains.utils.ErrorCode;
import io.vavr.control.Either;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class ContactoBs implements ContactoService {
    private final ContactoRepository contactoRepository;
    @Inject
    public ContactoBs(ContactoRepository contactoRepository){
        this.contactoRepository = contactoRepository;
    }
    @Override
    public Either<ErrorCode, Contacto> save(Contacto contacto) {
        Either<ErrorCode, Contacto> result;
        var contactExists = contactoRepository.getContactByNickname(contacto.getNickname());
        if(contactExists.isEmpty()){
            result = Either.left(ErrorCode.RN003);
        }else{
            var contactCreated = contactoRepository.create(contacto);
            result = contactCreated.<Either<ErrorCode, Contacto>>map(Either::right).orElseGet(() -> Either.left(ErrorCode.RN006));
        }
        return result;
    }

    @Override
    public Either<ErrorCode, Contacto> getByNickname(String nickname) {
        Either<ErrorCode, Contacto> result;
        var contactExists = contactoRepository.getContactByNickname(nickname);
        result = contactExists.<Either<ErrorCode, Contacto>>map(contactos -> Either.right(contactos.getFirst())).orElseGet(() -> Either.left(ErrorCode.RN004));
        return result;
    }

    @Override
    public Either<ErrorCode, Contacto> update(Contacto contacto) {
        Either<ErrorCode, Contacto> result;
        var contactExists = contactoRepository.getContactByUserId(contacto.getIdUser());
        if(contactExists.isEmpty() || contactExists.get().isEmpty()){
            result = Either.left(ErrorCode.RN004);
        }else{
            Contacto contactUpdate = contactExists.get().getFirst();
            contactUpdate.setNickname(contacto.getNickname());
            var contactUpdated = contactoRepository.update(contactUpdate);
            result = contactUpdated.<Either<ErrorCode, Contacto>>map(Either::right).orElseGet(() -> Either.left(ErrorCode.RN006));
        }
        return result;
    }

    @Override
    public Either<ErrorCode, Boolean> deleteContactById(Integer id) {
        Either<ErrorCode, Boolean> result;
        var contactExists = contactoRepository.getContactById(id);
        if(contactExists.isEmpty()){
            result = Either.left(ErrorCode.RN004);
        }else {
            contactoRepository.delete(contactExists.get());
            result = Either.right(true);
        }
        return result;
    }

    @Override
    public Either<ErrorCode, List<Contacto>> getAllByIdUser(Integer idUser) {
        Either<ErrorCode, List<Contacto>> result;
        var contacts = contactoRepository.getContactByUserId(idUser);
        result = contacts.<Either<ErrorCode, List<Contacto>>>map(Either::right).orElseGet(() -> Either.left(ErrorCode.RN004));
        return result;
    }
}
