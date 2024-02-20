package br.com.erudio.restwithspringbootandjavaerudio.controllers;

import br.com.erudio.restwithspringbootandjavaerudio.data.vo.v1.BookVO;
import br.com.erudio.restwithspringbootandjavaerudio.data.vo.v1.PersonVO;
import br.com.erudio.restwithspringbootandjavaerudio.services.BookServices;
import br.com.erudio.restwithspringbootandjavaerudio.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/book/v1")
@Tag(name = "Book", description = "Endpoints for Manager Book")
public class BookController {

    @Autowired
    private BookServices bookServices;

    @PostMapping(produces = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_YML,MediaType.APPLICATION_XML},
    consumes = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_YML,MediaType.APPLICATION_XML})
    @Operation(summary = "Adds a new Book", description = "Adds a new  Book",
            tags = {"Book"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content =
                            @Content(schema = @Schema(implementation = BookVO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized ", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)

            })
    public BookVO create(@RequestBody BookVO bookVO){
        return bookServices.create(bookVO);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_YML,MediaType.APPLICATION_XML})
    @Operation(summary = "Finds All books", description = "Finds all books",
            tags = {"Book"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(
                                                    schema = @Schema(implementation = BookVO.class))
                                    )}),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized ", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)

            })
    public List<BookVO> findAll(){
        return bookServices.findAll();
    }

    @PutMapping(produces = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_YML,MediaType.APPLICATION_XML},
            consumes = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_YML,MediaType.APPLICATION_XML})
    @Operation(summary = "Updates a Book", description = "Updates a Book",
            tags = {"Book"},
            responses = {
                    @ApiResponse(description = "Updated", responseCode = "200",
                            content =
                            @Content(schema = @Schema(implementation = BookVO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized ", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)

            })
    public BookVO update(@RequestBody BookVO bookVO){
        return bookServices.update(bookVO);
    }

    @GetMapping(value = "/{id}",
            produces = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_YML,MediaType.APPLICATION_XML}
    )
    @Operation(summary = "Finds a Book", description = "Finds a Book",
            tags = {"Book"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content =
                            @Content(schema = @Schema(implementation = BookVO.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized ", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)

            })
    public BookVO findById(@PathVariable(value = "id") Long id){
        return  bookServices.findById(id);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable(value = "id")Long id){
        bookServices.delete(id);
    }
}
