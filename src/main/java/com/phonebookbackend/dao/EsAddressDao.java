package com.phonebookbackend.dao;

import com.phonebookbackend.model.EsAddress;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EsAddressDao {
    List<EsAddress> getAllEsAddressList(@Param("id") Long id);
}
