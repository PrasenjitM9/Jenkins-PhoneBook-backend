package com.phonebookbackend.services.impl;

import com.phonebookbackend.model.ContactPerson;
import com.phonebookbackend.repository.ContactPersonRepository;
import com.phonebookbackend.services.ContactPersonService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactPersonServiceImpl implements ContactPersonService {

    private final ContactPersonRepository contactPersonRepository;

    public ContactPersonServiceImpl(ContactPersonRepository contactPersonRepository) {
        this.contactPersonRepository = contactPersonRepository;
    }

    @Override
    public List<ContactPerson> getAll() {
        return contactPersonRepository.findAll();
    }

    @Override
    public Optional<ContactPerson> getById(String id) {
        return contactPersonRepository.findById(id);
    }

    @Override
    public List<ContactPerson> getByName(String name) {
        return contactPersonRepository.findByName(name);
    }

    @Override
    public ContactPerson createContactPerson(ContactPerson contactPerson) {
        return contactPersonRepository.save(contactPerson);
    }

    @Override
    public ContactPerson update(ContactPerson contactPerson) {
        return contactPersonRepository.save(contactPerson);
    }

    @Override
    public void delete(String id) {
        contactPersonRepository.deleteById(id);
    }
}
