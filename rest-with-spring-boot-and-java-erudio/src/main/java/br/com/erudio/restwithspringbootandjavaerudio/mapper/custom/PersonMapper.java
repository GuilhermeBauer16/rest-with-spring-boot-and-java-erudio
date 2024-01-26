package br.com.erudio.restwithspringbootandjavaerudio.mapper.custom;

import br.com.erudio.restwithspringbootandjavaerudio.data.vo.v2.PersonVOV2;
import br.com.erudio.restwithspringbootandjavaerudio.model.PersonModel;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersonMapper {

    public PersonVOV2 convertEntityToVO(PersonModel personModel){
        PersonVOV2 vo = new PersonVOV2();
        vo.setId(personModel.getId());
        vo.setAddress(personModel.getAddress());
        vo.setBirthday(new Date());
        vo.setFirstName(personModel.getFirstName());
        vo.setLastName(personModel.getLastName());
        vo.setGender(personModel.getGender());

        return vo;

    }

    public PersonModel convertVOToEntity(  PersonVOV2 personVOV2){
        PersonModel entity = new PersonModel();
        entity.setId(personVOV2.getId());
        entity.setAddress(personVOV2.getAddress());
//        entity.setBirthday(new Date());
        entity.setFirstName(personVOV2.getFirstName());
        entity.setLastName(personVOV2.getLastName());
        entity.setGender(personVOV2.getGender());

        return entity;

    }
}
