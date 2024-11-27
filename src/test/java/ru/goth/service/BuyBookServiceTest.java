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
import ru.goth.repository.BuyBookDAO;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class BuyBookServiceTest {
    private BuyBookDAO mockBuyBookDAO;

    private BuyBookService mockBuyBookService;
    private BookService mockBookService;
    private BuyService mockBuyService;

    private final BuyBookDTO mockBuyBookDTO = new BuyBookDTO();
    private final BuyDTO mockBuyDTO = new BuyDTO();
    private final BookDTO mockBookDTO = new BookDTO();

    @Before
    public void setup() {
        mockBuyBookDAO = mock(BuyBookDAO.class);

        mockBookService = mock(BookService.class);
        mockBuyService = mock(BuyService.class);
        mockBuyBookService = new BuyBookService(mockBuyBookDAO, mockBookService, mockBuyService);
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

    @Test
    public void setByDTO() {

        final String description = "Deliver only during night time";
        final String client = "GothGamerGhoul282";
        mockBuyDTO.setDescription(description);
        mockBuyDTO.setClient(client);
        final int id = 1;

        final String title = "Occult encyclopedia";
        final Author author = new Author();
        author.setId(1L);
        author.setName("Test");
        final String genre = "Occult";
        final float price = 228.00F;
        final int amount = 1;

        mockBookDTO.setTitle(title);
        mockBookDTO.setAuthor(author);
        mockBookDTO.setGenre(genre);
        mockBookDTO.setPrice(price);
        mockBookDTO.setAmount(amount);

        mockBuyBookDTO.setAmount(1);

        Mockito.when(mockBookService.setByDTO(mockBookDTO)).thenReturn(id);
        Mockito.when(mockBuyService.setByDTO(mockBuyDTO)).thenReturn(id);

        mockBuyBookService.setByDTO(mockBuyDTO, mockBookDTO, mockBuyBookDTO);

        verify(mockBuyBookDAO, times(1)).setBuyBook(mockBuyService.setByDTO(mockBuyDTO),
                mockBookService.setByDTO(mockBookDTO), mockBuyBookDTO.getAmount());
    }

    @Test
    public void update() {

        final String description = "Deliver only during night time";
        final String client = "GothGamerGhoul282";
        mockBuyDTO.setDescription(description);
        mockBuyDTO.setClient(client);
        final long id = 1;

        final String title = "Occult encyclopedia";
        final Author author = new Author();
        author.setId(1L);
        author.setName("Test");
        final String genre = "Occult";
        final float price = 228.00F;
        final int amount = 1;

        mockBookDTO.setTitle(title);
        mockBookDTO.setAuthor(author);
        mockBookDTO.setGenre(genre);
        mockBookDTO.setPrice(price);
        mockBookDTO.setAmount(amount);

        Mockito.when(mockBuyBookDAO.getBuyBook(id)).thenReturn(new BuyBook());

        mockBuyBookService.update(id, mockBuyDTO, id, mockBookDTO, id, mockBuyBookDTO);

        verify(mockBuyService, times(1)).update(id, mockBuyDTO);
        verify(mockBookService, times(1)).update(id, mockBookDTO);
        verify(mockBuyBookDAO, times(1)).updateBuyBook(id, id, id, mockBuyBookDTO.getAmount());
    }

    @Test
    public void deleteById() {
        final long id = 1L;

        mockBuyBookService.deleteById(id);
        verify(mockBuyBookDAO, times(1)).deleteBuyBook(id);
    }
}
