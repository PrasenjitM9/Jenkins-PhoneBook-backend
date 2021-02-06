package com.phonebookbackend.repository;

import com.phonebookbackend.model.ContactPerson;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactPersonRepository extends MongoRepository<ContactPerson,String> {
    Optional<ContactPerson> findById(String id);
    List<ContactPerson> findByName(String name);
    List<ContactPerson> findByPhoneNumber(String phoneNumber);
    void deleteById(String id);
}
