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
import br.com.erudio.restwithspringbootandjavaerudio.repositories.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Service
public class UserServices implements UserDetailsService {
    private final Logger logger = Logger.getLogger(UserServices.class.getName());

    @Autowired
    private UserRepository repository;
    @Autowired
    private PersonMapper personMapper;

    public UserServices(UserRepository repository) {
        this.repository = repository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = repository.findByUsername(username);
        if(user != null) {
            return user;

        }else {

            throw new UsernameNotFoundException("Username: " + username + "not found!");


    }
}







}
