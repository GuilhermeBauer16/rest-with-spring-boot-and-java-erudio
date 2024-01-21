package br.com.erudio.restwithspringbootandjavaerudio.controllers;

import br.com.erudio.restwithspringbootandjavaerudio.model.PersonModel;
import br.com.erudio.restwithspringbootandjavaerudio.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private PersonServices personServices;
    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonModel findById(@PathVariable(value = "id") String id) {

        return personServices.findById(id);
    }

    @RequestMapping(method = RequestMethod.GET,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PersonModel> findAll(){
        return personServices.findAll();
    }

    @PostMapping(
            value = "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonModel create(@RequestBody PersonModel personModel) {

        return personServices.create(personModel);
    }

    @PutMapping(
            value = "/update",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonModel update(@RequestBody PersonModel personModel) {

        return personServices.update(personModel);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable(value = "id") String id) {
        personServices.delete(id);
    }



}
