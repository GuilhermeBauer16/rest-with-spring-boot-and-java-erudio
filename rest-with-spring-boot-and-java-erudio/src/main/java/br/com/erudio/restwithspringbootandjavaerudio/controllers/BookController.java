package br.com.erudio.restwithspringbootandjavaerudio.controllers;

import br.com.erudio.restwithspringbootandjavaerudio.data.vo.v1.BookVO;
import br.com.erudio.restwithspringbootandjavaerudio.services.BookServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/book/v1")
public class BookController {

    @Autowired
    private BookServices bookServices;

    @PostMapping
    public BookVO create(@RequestBody BookVO bookVO){
        return bookServices.create(bookVO);
    }

    @GetMapping
    public List<BookVO> findAll(){
        return bookServices.findAll();
    }

    @PutMapping
    public BookVO update(@RequestBody BookVO bookVO){
        return bookServices.update(bookVO);
    }

    @GetMapping(value = "/{id}")
    public BookVO findById(@PathVariable(value = "id") Long id){
        return  bookServices.findById(id);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable(value = "id")Long id){
        bookServices.delete(id);
    }
}
