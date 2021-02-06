package com.phonebookbackend.controller;

import com.phonebookbackend.model.EsAddress;
import com.phonebookbackend.services.EsAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;
import java.util.*;

@RestController
@Api(tags = "EsAddressController")
@RequestMapping("/api/esAddress")
public class EsAddressController {
    @Autowired
    private EsAddressService esAddressService;
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;
    @ApiOperation(value = "initial all the address to ES")
    @GetMapping("/init")
    public String init(){
       // esAddressService.createIndex();
//        List<EsAddress> list = new ArrayList<>();
//        list.add(new EsAddress(1L, "Leuven", "Belgium"));
//        list.add(new EsAddress(2L, "Brussels", "Belgium"));
//        list.add(new EsAddress(3L, "KunMing", "China"));
//        list.add(new EsAddress(4L, "Miami", "USA"));
        //esAddressService.saveAll(list);
        esAddressService.saveAll(Arrays.asList(
                new EsAddress("4", "Miami", "USA"),
                new EsAddress("5", "Gent", "Belgium"),
                new EsAddress("6", "YuXi", "China")
        ));
        return "Initialized!";
    }



    @GetMapping("/getAll")
    public List<EsAddress> getAll(){
        List<EsAddress> addresses = new ArrayList<>();
        for (Iterator<EsAddress> it = this.esAddressService.findAll(); it.hasNext(); ) {
            EsAddress address = it.next();
            addresses.add(address);
        }
        return addresses;
    }

    @ApiOperation("Delete document by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<EsAddress> deleteById(@PathVariable("id") String id) {
        esAddressService.delete(id);
        return new ResponseEntity<EsAddress>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<EsAddress>> getById(@PathVariable String id){
        return new ResponseEntity<Optional<EsAddress>>(esAddressService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EsAddress> createAddress(@RequestBody EsAddress esAddress){
        return new ResponseEntity<EsAddress>(esAddressService.save(esAddress), HttpStatus.CREATED);
    }

}
