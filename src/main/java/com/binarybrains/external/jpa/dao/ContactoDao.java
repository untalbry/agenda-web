package com.binarybrains.external.jpa.dao;

import com.binarybrains.core.buisness.output.ContactoRepository;
import com.binarybrains.core.entity.Contacto;
import com.binarybrains.external.jpa.entity.ContactoJpa;
import com.binarybrains.external.jpa.repository.ContactoJpaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ContactoDao implements ContactoRepository {
    @PersistenceContext
    EntityManager entityManager;
    private static final  String QUERY_GET_CONTACT_BY_NICKNAME = "SELECT * FROM tac02_contacto WHERE tx_apodo=:nickname";
    private static final String QUERY_GET_CONTACT_BY_ID_USER = "SELECT * FROM tac02_contacto WHERE id_usuario=:idUsuario";
    private final ContactoJpaRepository contactoJpaRepository;
    @Inject
    public ContactoDao(ContactoJpaRepository contactoJpaRepository){
        this.contactoJpaRepository = contactoJpaRepository;
    }
    @Override
    public Optional<Contacto> create(Contacto contacto) {
        return Optional.of(contactoJpaRepository.save(ContactoJpa.fromEntity(contacto)).toEntity());

    }


    @Override
    @SuppressWarnings("unchecked")
    public Optional<List<Contacto>> getContactByNickname(String nickname) {
        List<Contacto> contacts = entityManager
                .createNativeQuery(QUERY_GET_CONTACT_BY_NICKNAME)
                .setParameter("nickname", nickname)
                .getResultList()
                .stream()
                .map(row -> {
                    Object[] r = (Object[]) row;
                    return ContactoJpa.builder()
                            .id((Integer) r[0])
                            .idUser((Integer) r[1])
                            .name((String) r[2])
                            .lastName((String) r[3])
                            .secondLastName((String) r[4])
                            .nickname((String) r[5])
                            .build()
                            .toEntity();
                })
                .toList();
        return Optional.of(contacts);
    }

    @Override
    public Optional<Contacto> update(Contacto contacto) {
        return Optional.of(contactoJpaRepository.saveAndFlush(ContactoJpa.fromEntity(contacto)).toEntity());
    }

    @Override
    @SuppressWarnings("unchecked")
    public Optional<List<Contacto>> getContactByUserId(Integer id) {
        List<Contacto> contacts = entityManager.createNativeQuery(QUERY_GET_CONTACT_BY_ID_USER, ContactoJpa.class)
                .setParameter("idUsuario", id)
                .getResultList()
                .stream().map(contactoJpa -> ((ContactoJpa) contactoJpa).toEntity()).toList();
        return Optional.of(contacts);
    }

    @Override
    public Optional<Contacto> getContactById(Integer id) {
        return contactoJpaRepository.findById(id)
                .map(ContactoJpa::toEntity);
    }

    @Override
    public void delete(Contacto contacto) {
        contactoJpaRepository.delete(ContactoJpa.fromEntity(contacto));
    }
}
