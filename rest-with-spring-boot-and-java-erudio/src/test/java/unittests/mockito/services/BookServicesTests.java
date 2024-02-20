package unittests.mockito.services;

import br.com.erudio.restwithspringbootandjavaerudio.data.vo.v1.BookVO;
import br.com.erudio.restwithspringbootandjavaerudio.exceptions.RequiredObjectsNullException;
import br.com.erudio.restwithspringbootandjavaerudio.model.BookModel;
import br.com.erudio.restwithspringbootandjavaerudio.repositories.BookRepository;
import br.com.erudio.restwithspringbootandjavaerudio.services.BookServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import unittests.mocks.MockBook;

import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class BookServicesTests {

    MockBook input;

    @InjectMocks
    private BookServices bookServices;


    @Mock
    private BookRepository bookRepository;


    @BeforeEach
    void setUpMocks() {
        input = new MockBook();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateWithNullPerson(){
        Exception exception = assertThrowsExactly(RequiredObjectsNullException.class,
                () -> bookServices.create(null));

        String expectedMessage = "It is not allowed persisted a null object!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test

    void testUpdateWithNullBook(){
        Exception exception = assertThrows(RequiredObjectsNullException.class, () ->
                bookServices.update(null));
        String expectedMessage = "It is not allowed persisted a null object!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }


    @Test
    void findByIdTest() {

        BookModel entity = input.mockEntity();
        entity.setId(1L);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(entity));
        BookVO result = bookServices.findById(1L);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        System.out.println(result.toString());
        assertTrue(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
        assertEquals("Michael C. Feathers0", result.getAuthor());
        assertEquals(49.00, result.getPrice());
        assertEquals("Working effectively with legacy code0", result.getTitle());
        assertNotNull(result.getLaunchDate());

    }

    @Test
    void testCreate(){
        var entity = input.mockEntity(1);
        var persisted = entity;
        persisted.setId(1L);
        var vo = input.mockVO(1);
        vo.setKey(1L);
        when(bookRepository.save(entity)).thenReturn(persisted);
        BookVO result = bookServices.create(vo);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertTrue(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
        assertEquals("Michael C. Feathers1", result.getAuthor());
        assertEquals(49.0, result.getPrice());
        assertEquals("Working effectively with legacy code1", result.getTitle() );
        assertNotNull(result.getLaunchDate());
    }

    @Test
    void updateTest(){
        BookModel entity = input.mockEntity(1);
        BookModel persisted = entity;
        persisted.setId(1L);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(bookRepository.save(entity)).thenReturn(persisted);
        BookVO vo = input.mockVO(1);
        vo.setKey(1L);
        BookVO result = bookServices.update(vo);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertNotNull(result.getLinks());
        assertTrue(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
        assertEquals("Michael C. Feathers1", result.getAuthor());
        assertEquals(49.0, result.getPrice());
        assertEquals("Working effectively with legacy code1", result.getTitle() );
        assertNotNull(result.getLaunchDate());

    }



    @Test
    void testFindAll() {
        List<BookModel> list = input.mockEntityList();
        when(bookRepository.findAll()).thenReturn(list);
        List<BookVO> books = bookServices.findAll();
        assertNotNull(books);
        assertEquals(14, list.size());

        BookVO book1 = books.get(1);
        book1.setKey(1L);
        assertNotNull(book1);
        assertNotNull(book1.getKey());
        assertNotNull(book1.getLinks());
        assertTrue(book1.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
        assertEquals("Michael C. Feathers1", book1.getAuthor());
        assertEquals(49.0, book1.getPrice());
        assertEquals("Working effectively with legacy code1", book1.getTitle());
        assertNotNull(book1.getLaunchDate());

        BookVO book4 = books.get(4);
        book4.setKey(4L);
        assertNotNull(book4);
        assertNotNull(book4.getKey());
        assertNotNull(book4.getLinks());
        assertTrue(book4.toString().contains("links: [</api/book/v1/4>;rel=\"self\"]"));
        assertNotNull("Michael C. Feathers4", book4.getAuthor());
        assertEquals(49.0, book4.getPrice());
        assertEquals("Working effectively with legacy code4", book4.getTitle());
        assertNotNull(book4.getLaunchDate());

        BookVO book10 = books.get(10);
        book10.setKey(10L);
        assertNotNull(book10);
        assertNotNull(book10.getKey());
        assertNotNull(book10.getLinks());
        assertTrue(book10.toString().contains("links: [</api/book/v1/10>;rel=\"self\"]"));
        assertEquals("Michael C. Feathers10", book10.getAuthor());
        assertEquals(49.0, book10.getPrice());
        assertEquals("Working effectively with legacy code10", book10.getTitle());
        assertNotNull(book10.getLaunchDate());
    }

    @Test
    void testDelete(){
        BookModel entity = input.mockEntity(1);
        entity.setId(1L);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(entity));
        bookServices.delete(1L);
    }

}
