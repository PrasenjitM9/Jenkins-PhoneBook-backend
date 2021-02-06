package com.phonebookbackend.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
@Data
@NoArgsConstructor
@Document(indexName = "address")
public class EsAddress implements Serializable {
    @Id
    private String id;

    @Field(searchAnalyzer = "ik_max_word", analyzer = "ik_smart", type = FieldType.Text)
    private String cityName;

    @Field(searchAnalyzer = "ik_max_word", analyzer = "ik_smart",type = FieldType.Text)
    private String countryName;

    public EsAddress(String id, String cityName, String countryName) {
        this.id = id;
        this.cityName = cityName;
        this.countryName = countryName;
    }
}
