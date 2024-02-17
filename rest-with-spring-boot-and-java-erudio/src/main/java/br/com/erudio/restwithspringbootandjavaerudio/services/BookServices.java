package br.com.erudio.restwithspringbootandjavaerudio.services;

import br.com.erudio.restwithspringbootandjavaerudio.controllers.BookController;
import br.com.erudio.restwithspringbootandjavaerudio.data.vo.v1.BookVO;
import br.com.erudio.restwithspringbootandjavaerudio.exceptions.ResourceNotFoundException;
import br.com.erudio.restwithspringbootandjavaerudio.mapper.DozerMapper;
import br.com.erudio.restwithspringbootandjavaerudio.model.BookModel;
import br.com.erudio.restwithspringbootandjavaerudio.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BookServices {

    @Autowired
    private BookRepository repository;

    public BookVO create(BookVO bookVO){
        BookModel entity = DozerMapper.parseObject(bookVO, BookModel.class);
        BookVO vo = DozerMapper.parseObject(repository.save(entity), BookVO.class);
        vo.add(linkTo(methodOn(BookController.class).findById(bookVO.getKey())).withSelfRel());
        return vo;
    }

    public List<BookVO> findAll(){

        List<BookVO> vo = DozerMapper.parseListObjects(repository.findAll(), BookVO.class);
        vo.stream().forEach(
                bookVO -> {
                    bookVO.add(linkTo(methodOn(BookController.class).findById(bookVO.getKey())).withSelfRel());
                }
        );
        return vo;
    }

    public BookVO findById(Long id){
        BookModel entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for that Id!"));
        BookVO vo = DozerMapper.parseObject(entity, BookVO.class);
        vo.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
        return vo;

    }

    public BookVO update(BookVO bookVO){
        BookModel entity = repository.findById(bookVO.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for that Id!"));
        entity.setAuthor(bookVO.getAuthor());
        entity.setPrice(bookVO.getPrice());
        entity.setTitle(bookVO.getTitle());
        entity.setLaunchDate(new Date());
        BookVO vo = DozerMapper.parseObject(repository.save(entity), BookVO.class);
        vo.add(linkTo(methodOn(BookController.class).findById(bookVO.getKey())).withSelfRel());
        return vo;

    }

    public void delete(Long id){

        BookModel entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for that Id!"));
        repository.delete(entity);

    }
}
