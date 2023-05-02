package com.ttrides.turntablRides.repositories;

import com.ttrides.turntablRides.models.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface UserRepository extends MongoRepository<UserModel, Integer> {

    Optional<UserModel> findUserByEmail(String email);
}
