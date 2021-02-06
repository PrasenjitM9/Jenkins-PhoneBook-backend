package com.phonebookbackend.repository;

import com.phonebookbackend.model.EsAddress;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface EsAddressRepository extends ElasticsearchRepository<EsAddress, String> {
    List<EsAddress> findEsAddressByCityName(String name);

}
