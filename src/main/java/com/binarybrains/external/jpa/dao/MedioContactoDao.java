package com.binarybrains.external.jpa.dao;

import com.binarybrains.core.buisness.output.MedioContactoRepository;
import com.binarybrains.core.entity.MedioContacto;
import com.binarybrains.external.jpa.entity.MedioContactoJpa;
import com.binarybrains.external.jpa.repository.MedioContactoJpaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceUnit;

import java.util.Optional;

@ApplicationScoped
public class MedioContactoDao implements MedioContactoRepository {
    @PersistenceUnit
    EntityManager entityManager;
    private final MedioContactoJpaRepository medioContactoJpaRepository;

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

}
