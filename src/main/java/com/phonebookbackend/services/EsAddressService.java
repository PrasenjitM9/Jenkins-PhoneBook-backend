package com.phonebookbackend.services;

import com.phonebookbackend.model.EsAddress;

import java.util.Iterator;
import java.util.Optional;

public interface EsAddressService {
   // void createIndex();
    void deleteIndex(String index);
    void delete(String id);
    void saveAll(Iterable<EsAddress> list);
    EsAddress save(EsAddress esAddress);
    Iterator<EsAddress> findAll();
    EsAddress create(EsAddress esAddress);
    Optional<EsAddress> findById(String id);
}
