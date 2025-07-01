package com.binarybrains.core.buisness.output;

import com.binarybrains.core.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<List<User>> getUserById(Integer id);

}
