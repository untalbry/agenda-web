package com.binarybrains.core.buisness.implementation;

import com.binarybrains.core.buisness.input.ContactoService;
import com.binarybrains.core.buisness.output.ContactoRepository;
import com.binarybrains.core.buisness.output.UserRepository;
import com.binarybrains.core.entity.Contacto;
import com.binarybrains.core.entity.User;
import com.binarybrains.utils.ErrorCode;
import io.vavr.control.Either;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ContactoBs implements ContactoService {
    private final ContactoRepository contactoRepository;
    private final UserRepository userRepository;
    @Inject
    public ContactoBs(ContactoRepository contactoRepository, UserRepository userRepository){
        this.contactoRepository = contactoRepository;
        this.userRepository = userRepository;
    }
    @Override
    public Either<ErrorCode, Contacto> save(Contacto contacto) {
        Either<ErrorCode, Contacto> result;
        var contactExists = contactoRepository.getContactByNickname(contacto.getNickname());
        if(contactExists.isEmpty()){
            result = Either.left(ErrorCode.RN003);
        }else{
            var userExists = userRepository.getUserById(contacto.getIdUser());
            if(userExists.isEmpty()){
                result = Either.left(ErrorCode.RN004);
            }else{
                User user = userExists.get().getFirst();
                contacto.setIdUser(user.getId());
                contacto.setName(user.getName());
                contacto.setLastName(user.getLastName());
                contacto.setSecondLastName(user.getSecondLastName());
                var contactCreated = contactoRepository.create(contacto);
                result = contactCreated.<Either<ErrorCode, Contacto>>map(Either::right).orElseGet(() -> Either.left(ErrorCode.RN006));
            }
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
            contactoRepository.deleteContact(contactExists.get());
            result = Either.right(true);
        }
        return result;
    }
}
