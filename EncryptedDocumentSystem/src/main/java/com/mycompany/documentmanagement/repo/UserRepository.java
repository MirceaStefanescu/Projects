package com.mycompany.documentmanagement.repo;

import com.mycompany.documentmanagement.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);
}
