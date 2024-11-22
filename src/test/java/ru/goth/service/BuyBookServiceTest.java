package ru.goth.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ru.goth.entity.Author;
import ru.goth.entity.Book;
import ru.goth.entity.Buy;
import ru.goth.entity.BuyBook;
import ru.goth.entity.dto.BookDTO;
import ru.goth.entity.dto.BuyBookDTO;
import ru.goth.entity.dto.BuyDTO;
import ru.goth.repository.BookDAO;
import ru.goth.repository.BuyBookDAO;
import ru.goth.repository.BuyDAO;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class BuyBookServiceTest {
    private BuyBookDAO mockBuyBookDAO;
    private BookDAO mockBookDAO;
    private BuyDAO mockBuyDAO;

    private BuyBookService mockBuyBookService;
    private BookService mockBookService;
    private BuyService mockBuyService;

    private final BuyBookDTO mockBuyBookDTO = new BuyBookDTO();
    private final BuyDTO mockBuyDTO = new BuyDTO();
    private final BookDTO mockBookDTO = new BookDTO();

    @Before
    public void setup() {
        mockBuyBookDAO = mock(BuyBookDAO.class);
        mockBookDAO = mock(BookDAO.class);
        mockBuyDAO = mock(BuyDAO.class);

        mockBuyBookService = new BuyBookService(mockBuyBookDAO);
        mockBookService = new BookService(mockBookDAO);
        mockBuyService = new BuyService(mockBuyDAO);
    }

    @Test
    public void getById() {
        final String title = "Occult encyclopedia";
        Book mockBook = new Book();
        mockBook.setTitle(title);

        final String description = "Deliver only during night time";
        Buy mockBuy = new Buy();
        mockBuy.setDescription(description);

        mockBuyBookDTO.setBook(mockBook);
        mockBuyBookDTO.setBuy(mockBuy);

        final long id = 1L;
        BuyBook buyBook = new BuyBook();
        buyBook.setBook(mockBook);
        buyBook.setBuy(mockBuy);

        Mockito.when(mockBuyBookDAO.getBuyBook(id)).thenReturn(buyBook);
        assertEquals(mockBuyBookDTO.getBook().getTitle(), mockBuyBookService.getById(id).getBook().getTitle());
    }
//
//    @Test
//    public void setByDTO() {
//
//        final String title = "Occult encyclopedia";
//        mockBookDTO.setTitle(title);
//
//        final String description = "Deliver only during night time";
//        final String client = "GothGamerGhoul282";
//        mockBuyDTO.setDescription(description);
//        int id = 1;
//
//        Mockito.when(mockBuyDAO.setBuy(description, client)).thenReturn(id);
//
//        final Author author = new Author();
//        author.setId(1L);
//        author.setName(title);
//        final String genre = "Occult";
//        final float price = 228.00F;
//        final int amount = 1;
//
//        Mockito.when(mockBookDAO.setBook(title, author, genre, price, amount)).thenReturn(id);
//
//        Mockito.when(mockBookService.setByDTO(mockBookDTO)).thenReturn(1);
//        Mockito.when(mockBuyService.setByDTO(mockBuyDTO)).thenReturn(1);
//
//        Mockito.doNothing().when(mockBuyBookDAO).setBuyBook(id, id, mockBuyBookDTO.getAmount());
//
//        mockBuyBookService.setByDTO(mockBuyDTO, mockBookDTO, mockBuyBookDTO);
//    }
//
//    @Test
//    public void update() {
//
//        final String title = "Occult encyclopedia";
//        mockBookDTO.setTitle(title);
//
//        final String description = "Deliver only during night time";
//        mockBuyDTO.setDescription(description);
//
//        final long buyId = 1L;
//        final long bookId = 1L;
//        final long buyBookId = 1L;
//        int amount = 1;
//
//        final String client = "GothGamerGhoul282";
//        mockBuyDTO.setDescription(description);
//
//        final Author author = new Author();
//        author.setId(1L);
//        author.setName(title);
//        final String genre = "Occult";
//        final float price = 228.00F;
//
//        Mockito.doNothing().when(mockBookDAO).updateBook(bookId, title, author, genre, price, amount);
//        Mockito.doNothing().when(mockBuyDAO).updateBuy(buyId, description, client);
//        Mockito.doNothing().when(mockBuyBookDAO).updateBuyBook(buyBookId, buyId, bookId, amount);
//
//        mockBuyBookService.update(buyId, mockBuyDTO, bookId, mockBookDTO, buyBookId, mockBuyBookDTO);
//    }

    @Test
    public void deleteById() {
        final long id = 1L;

        Mockito.doNothing().when(mockBuyBookDAO).deleteBuyBook(id);

        mockBuyBookService.deleteById(id);
    }
}