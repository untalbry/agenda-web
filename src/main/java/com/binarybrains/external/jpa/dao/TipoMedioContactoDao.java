package com.binarybrains.external.jpa.dao;

import com.binarybrains.core.buisness.output.TipoMedioContactoRepository;
import com.binarybrains.core.entity.TipoMedioContacto;
import com.binarybrains.external.jpa.entity.TipoMedioContactoJpa;
import com.binarybrains.external.jpa.repository.TipoMedioContactoJpaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;

@ApplicationScoped

public class TipoMedioContactoDao implements TipoMedioContactoRepository {
    @PersistenceContext
    EntityManager entityManager;
    private final TipoMedioContactoJpaRepository tipoMedioContactoJpaRepository;

    private static String QUERY_GET_MEDIO_CONTACTO_BY_NAME = "SELECT * FROM cac01_tipo_medio_contacto WHERE tx_nombre=:name";
    @Inject
    public TipoMedioContactoDao(TipoMedioContactoJpaRepository tipoMedioContactoJpaRepository){
        this.tipoMedioContactoJpaRepository = tipoMedioContactoJpaRepository;
    }
    @Override
    public Optional<TipoMedioContacto> create(TipoMedioContacto tipoMedioContacto) {
        return Optional.of(tipoMedioContactoJpaRepository.save(TipoMedioContactoJpa.fromEntity(tipoMedioContacto)).toEntity());
    }

    @Override
    @SuppressWarnings("unchecked")
    public Optional<List<TipoMedioContacto>> getMedioContactoByName(String name) {
        List<TipoMedioContacto> tipoMedioContactos = entityManager
                .createNativeQuery(QUERY_GET_MEDIO_CONTACTO_BY_NAME)
                .setParameter("name", name)
                .getResultList()
                .stream()
                .map(row -> {
                    Object[] r = (Object[]) row;
                    return TipoMedioContactoJpa.builder()
                            .id((Integer) r[0])
                            .name((String) r[1])
                            .description((String) r[2])
                            .active((Boolean) r[3])
                            .build()
                            .toEntity();
                })
                .toList();
        return Optional.of(tipoMedioContactos);
    }

    @Override
    public Optional<TipoMedioContacto> findById(Integer idTipo) {
        return tipoMedioContactoJpaRepository.findById(idTipo).map(TipoMedioContactoJpa::toEntity);
    }
}
