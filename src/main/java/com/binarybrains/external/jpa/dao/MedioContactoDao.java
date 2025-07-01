package com.binarybrains.external.jpa.dao;

import com.binarybrains.core.buisness.input.MedioContactoService;
import com.binarybrains.core.buisness.output.MedioContactoRepository;
import com.binarybrains.core.entity.MedioContacto;
import com.binarybrains.external.jpa.entity.MedioContactoJpa;
import com.binarybrains.external.jpa.repository.MedioContactoJpaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;

@ApplicationScoped

public class MedioContactoDao implements MedioContactoRepository {
    @PersistenceContext
    EntityManager entityManager;
    private final MedioContactoJpaRepository medioContactoJpaRepository;

    private static String QUERY_GET_MEDIO_CONTACTO_BY_NAME = "SELECT * FROM cac01_tipo_medio_contacto WHERE tx_nombre=:name";
    @Inject
    public MedioContactoDao(MedioContactoJpaRepository medioContactoJpaRepository){
        this.medioContactoJpaRepository = medioContactoJpaRepository;
    }
    @Override
    public Optional<MedioContacto> create(MedioContacto medioContacto) {
        return Optional.of(medioContactoJpaRepository.save(MedioContactoJpa.fromEntity(medioContacto)).toEntity());
    }

    @Override
    @SuppressWarnings("unchecked")
    public Optional<List<MedioContacto>> getMedioContactoByName(String name) {
        List<MedioContacto> medioContactos = entityManager
                .createNativeQuery(QUERY_GET_MEDIO_CONTACTO_BY_NAME)
                .setParameter("name", name)
                .getResultList()
                .stream()
                .map(row -> {
                    Object[] r = (Object[]) row;
                    return MedioContactoJpa.builder()
                            .id((Integer) r[0])
                            .name((String) r[1])
                            .description((String) r[2])
                            .active((Boolean) r[3])
                            .build()
                            .toEntity();
                })
                .toList();
        return Optional.of(medioContactos);
    }
}
