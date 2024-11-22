package ru.goth.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ru.goth.entity.Author;
import ru.goth.entity.Book;
import ru.goth.entity.dto.BookDTO;
import ru.goth.repository.BookDAO;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class BookServiceTest {
    private BookDAO mockBookDAO;
    private BookService mockBookService;
    private final BookDTO mockBookDTO = new BookDTO();

    @Before
    public void setup() {
        mockBookDAO = mock(BookDAO.class);
        mockBookService = new BookService(mockBookDAO);
    }

    @Test
    public void getById() {
        final long id = 1L;
        final String title = "Occult encyclopedia";
        Book book = new Book();
        book.setTitle(title);

        mockBookDTO.setTitle(title);

        Mockito.when(mockBookDAO.getBook(id)).thenReturn(book);
        assertEquals(mockBookDTO.getTitle(), mockBookService.getById(id).getTitle());
    }

    @Test
    public void setByDTO() {
        final String title = "Occult encyclopedia";
        final Author author = new Author();
        author.setId(1L);
        author.setName(title);
        final String genre = "Occult";
        final float price = 228.00F;
        final int amount = 1;

        mockBookDTO.setTitle(title);

        final int expected = 1;
        Mockito.when(mockBookDAO.setBook(title, author, genre, price, amount)).thenReturn(expected);

        int actual = mockBookService.setByDTO(mockBookDTO);
        assertEquals(expected, actual);
    }

    @Test
    public void update() {
        final long id = 1L;
        final String title = "Occult encyclopedia";
        mockBookDTO.setTitle(title);

        final Author author = new Author();
        author.setId(1L);
        author.setName(title);
        final String genre = "Occult";
        final float price = 228.00F;
        final int amount = 1;

        Mockito.when(mockBookDAO.getBook(id)).thenReturn(new Book());
        Mockito.doNothing().when(mockBookDAO).updateBook(id, title, author, genre, price, amount);

        mockBookService.update(id, mockBookDTO);

    }

    @Test
    public void deleteByDTO() {

        final String title = "Occult encyclopedia";
        mockBookDTO.setTitle(title);

        Mockito.doNothing().when(mockBookDAO).deleteBook(mockBookDTO.getTitle());

        mockBookService.deleteByDTO(mockBookDTO);
    }
}