package com.phonebookbackend.services.impl;

import com.phonebookbackend.model.EsAddress;
import com.phonebookbackend.repository.EsAddressRepository;
import com.phonebookbackend.services.EsAddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Optional;

@Service
public class EsAddressServiceImpl implements EsAddressService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EsAddressServiceImpl.class);
//    @Autowired
//    private ElasticsearchRestTemplate elasticsearchRestTemplate;
    @Autowired
    EsAddressRepository addressRepository;

//    @Override
//    public void createIndex() {
//        elasticsearchRestTemplate.createIndex(EsAddress.class);
//    }

    @Override
    public void deleteIndex(String index) {

    }


    @Override
    public void delete(String id) {
        addressRepository.deleteById(id);
    }

    @Override
    public void saveAll(Iterable<EsAddress> list) {
        //elasticsearchRestTemplate.indexOps(EsAddress.class).create();
        addressRepository.saveAll(list);
    }

    @Override
    public EsAddress save(EsAddress esAddress) {
        return addressRepository.save(esAddress);
    }

    @Override
    public Iterator<EsAddress> findAll() {
        return addressRepository.findAll().iterator();
    }

    @Override
    public EsAddress create(EsAddress esAddress) {
        return addressRepository.save(esAddress);
    }

    @Override
    public Optional<EsAddress> findById(String id) {
        return addressRepository.findById(id);
    }

}
