package br.com.erudio.restwithspringbootandjavaerudio.services;

import br.com.erudio.restwithspringbootandjavaerudio.controllers.PersonController;
import br.com.erudio.restwithspringbootandjavaerudio.data.vo.v1.PersonVO;
import br.com.erudio.restwithspringbootandjavaerudio.data.vo.v2.PersonVOV2;
import br.com.erudio.restwithspringbootandjavaerudio.exceptions.RequiredObjectsNullException;
import br.com.erudio.restwithspringbootandjavaerudio.exceptions.ResourceNotFoundException;
import br.com.erudio.restwithspringbootandjavaerudio.mapper.DozerMapper;
import br.com.erudio.restwithspringbootandjavaerudio.mapper.custom.PersonMapper;
import br.com.erudio.restwithspringbootandjavaerudio.model.PersonModel;
import br.com.erudio.restwithspringbootandjavaerudio.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.logging.Logger;



@Service
public class PersonServices {
    private final Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    private PersonRepository repository;
    @Autowired
    private PersonMapper personMapper;

    public List<PersonVO> findAll() {

        logger.info("Finding all people!");

        List<PersonVO> people = DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
        people.stream()
                .forEach(p ->
                        p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
        return people;
    }

    public PersonVO findById(Long id) {

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResolutionException("No records found for this ID"));

        PersonVO vo =  DozerMapper.parseObject(entity,PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return vo;
    }

    public PersonVO create(PersonVO personVO) {

        if (personVO == null) throw  new RequiredObjectsNullException();
        logger.info("Creating one person");
        var entity = DozerMapper.parseObject(personVO, PersonModel.class);
        var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public PersonVOV2 createV2(PersonVOV2 personVOV2) {
        logger.info("Creating one person with V2");
        var entity = personMapper.convertVOToEntity(personVOV2);
        var vo = personMapper.convertEntityToVO(repository.save(entity));
        return vo;
    }

    public PersonVO update(PersonVO personVO) {

        if (personVO == null) throw  new RequiredObjectsNullException();
        logger.info("Updating one person");
        var entity = repository.findById(personVO.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        entity.setFirstName(personVO.getFirstName());
        entity.setLastName(personVO.getLastName());
        entity.setAddress(personVO.getAddress());
        entity.setGender(personVO.getGender());
        var vo = DozerMapper.parseObject(repository.save(entity),PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public void delete(Long id) {
        logger.info("Deleting one person");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResolutionException("No records found for this ID!"));

        repository.delete(entity);


    }







}
