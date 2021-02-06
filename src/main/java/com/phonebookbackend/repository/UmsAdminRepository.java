package com.phonebookbackend.repository;

import com.phonebookbackend.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UmsAdminRepository extends MongoRepository<User,String> {
    void deleteById(String id);
    Optional<User> findById(String id);
    List<User> findByUsername(String username);
}
