package com.binarybrains.external.jpa.dao;

import com.binarybrains.core.buisness.output.UserRepository;
import com.binarybrains.core.entity.User;
import com.binarybrains.external.jpa.entity.UserJpa;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class UserDao implements UserRepository {
    @PersistenceContext
    EntityManager entityManager;
    private static final  String QUERY_GET_USER_BY_ID = "SELECT id_usuario, tx_nombre, tx_primer_apellido, tx_segundo_apellido FROM tac01_usuario WHERE id_usuario=:id";

    @Override
    @SuppressWarnings("unchecked")
    public Optional<List<User>> getUserById(Integer id) {
        List<User> users = entityManager
                .createNativeQuery(QUERY_GET_USER_BY_ID)
                .setParameter("id", id)
                .getResultList()
                .stream()
                .map(row -> {
                    Object[] r = (Object[]) row;
                    return UserJpa.builder()
                            .id((Integer) r[0])
                            .name((String) r[1])
                            .lastName((String) r[2])
                            .secondLastName((String) r[3])
                            .build()
                            .toEntity();
                })
                .toList();
        return Optional.of(users);
    }
}
