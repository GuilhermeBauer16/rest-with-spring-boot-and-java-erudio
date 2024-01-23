package br.com.erudio.restwithspringbootandjavaerudio.services;

import br.com.erudio.restwithspringbootandjavaerudio.model.PersonModel;
import br.com.erudio.restwithspringbootandjavaerudio.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.lang.module.ResolutionException;
import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonServices {
    private final Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    private PersonRepository repository;

    public List<PersonModel> findAll() {

        return repository.findAll();
    }

    public PersonModel create(PersonModel personModel) {
        logger.info("Creating one person");
        return repository.save(personModel);
    }

    public void delete(Long id) {
        logger.info("Deleting one person");

        PersonModel entity = repository.findById(id)
                .orElseThrow(() -> new ResolutionException("No records found for this ID!"));

        repository.delete(entity);


    }

    public PersonModel update(PersonModel personModel) {

        logger.info("Updating one person");
        PersonModel entity = repository.findById(personModel.getId())
                .orElseThrow(() -> new ResolutionException("No records found for this ID!"));
        entity.setFirstName(personModel.getFirstName());
        entity.setLastName(personModel.getLastName());
        entity.setAddress(personModel.getAddress());
        entity.setGender(personModel.getGender());

        return repository.save(personModel);
    }


    public PersonModel findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResolutionException("No records found for this ID"));
    }


}
