package br.com.erudio.restwithspringbootandjavaerudio.services;

import br.com.erudio.restwithspringbootandjavaerudio.model.PersonModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    public List<PersonModel> findAll() {
        List<PersonModel> people = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            PersonModel person = mockPerson(i);
            people.add(person);

        }
        return people;
    }

    public PersonModel create(PersonModel personModel){
        logger.info("Creating one person");
        return personModel;
    }

    public void delete(String id){
        logger.info("Deleting one person");

    }

    public PersonModel update(PersonModel personModel){
        logger.info("Updating one person");
        return personModel;
    }



    public PersonModel findById(String id) {
        logger.info("Finding one person");
        PersonModel person = new PersonModel();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Guilherme");
        person.setLastName("Bauer");
        person.setAddress("Rio grande do sul");
        person.setGender("Male");
        return person;
    }

    private PersonModel mockPerson(int i) {
        logger.info("Finding all people");
        PersonModel person = new PersonModel();
        person.setId(counter.incrementAndGet());
        person.setFirstName("first name: " + i);
        person.setLastName("last name: " + i);
        person.setAddress("some address in Brazil: " + i);
        person.setGender("Male");
        return person;
    }

}
