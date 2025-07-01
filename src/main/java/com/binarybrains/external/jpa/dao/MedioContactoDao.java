package com.binarybrains.external.jpa.dao;

import com.binarybrains.core.buisness.output.MedioContactoRepository;
import com.binarybrains.core.entity.MedioContacto;
import com.binarybrains.external.jpa.entity.MedioContactoJpa;
import com.binarybrains.external.jpa.repository.MedioContactoJpaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceUnit;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class MedioContactoDao implements MedioContactoRepository {
    @PersistenceUnit
    EntityManager entityManager;
    private final MedioContactoJpaRepository medioContactoJpaRepository;

    private final static String QUERY_GET_ALL_BY_CONTACT_ID = "SELECT * FROM tac03_medio_contacto WHERE id_contacto=:idContacto";

    @Inject
    public MedioContactoDao(MedioContactoJpaRepository medioContactoJpaRepository){
        this.medioContactoJpaRepository = medioContactoJpaRepository;
    }


    @Override
    public Optional<MedioContacto> create(MedioContacto medioContacto) {
        return Optional.of(medioContactoJpaRepository.save(MedioContactoJpa.fromEntity(medioContacto)).toEntity());

    }

    @Override
    public Optional<MedioContacto> findById(Integer id) {
        return medioContactoJpaRepository.findById(id).map(MedioContactoJpa::toEntity);
    }

    @Override
    public void remove(MedioContacto medioContacto) {
        medioContactoJpaRepository.delete(MedioContactoJpa.fromEntity(medioContacto));
    }

    @Override
    public Optional<MedioContacto> update(MedioContacto medioContacto) {
        return Optional.of( medioContactoJpaRepository.saveAndFlush(MedioContactoJpa.fromEntity(medioContacto)).toEntity());
    }

    @Override
    @SuppressWarnings("unchecked")
    public Optional<List<MedioContacto>> findAllByIdContact(Integer id) {
        return Optional.of(entityManager
                .createNativeQuery(QUERY_GET_ALL_BY_CONTACT_ID, MedioContactoJpa.class)
                .setParameter("idContacto", id)
                .getResultList()
                .stream().map(object -> ((MedioContactoJpa) object).toEntity()).toList());
    }

}
