package br.com.erudio.restwithspringbootandjavaerudio.controllers;

import br.com.erudio.restwithspringbootandjavaerudio.data.vo.v1.PersonVO;
import br.com.erudio.restwithspringbootandjavaerudio.data.vo.v2.PersonVOV2;
import br.com.erudio.restwithspringbootandjavaerudio.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private PersonServices personServices;
    @GetMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonVO findById(@PathVariable(value = "id") Long id) {

        return personServices.findById(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PersonVO> findAll(){
        return personServices.findAll();
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonVO create(@RequestBody PersonVO personVO) {

        return personServices.create(personVO);
    }

    @PostMapping(value = "/v2",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonVOV2 createV2(@RequestBody PersonVOV2 personVOV2) {

        return personServices.createV2(personVOV2);
    }

    @PutMapping(value = "/update",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonVO update(@RequestBody PersonVO personVO) {

        return personServices.update(personVO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {

        personServices.delete(id);
        return ResponseEntity.noContent().build();
    }



}
