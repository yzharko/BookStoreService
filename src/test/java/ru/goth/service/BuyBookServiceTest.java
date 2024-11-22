package ru.goth.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.goth.entity.Book;
import ru.goth.entity.Buy;
import ru.goth.entity.dto.BookDTO;
import ru.goth.entity.dto.BuyBookDTO;
import ru.goth.entity.dto.BuyDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BuyBookServiceTest {
    @Test
    void getById() {
        BuyBookService mockBuyBookService = Mockito.mock(BuyBookService.class);

        final String title = "Occult encyclopedia";
        Book mockBook = new Book();
        mockBook.setTitle(title);

        final String description = "Deliver only during night time";
        Buy mockBuy = new Buy();
        mockBuy.setDescription(description);

        BuyBookDTO mockBuyBookDTO = new BuyBookDTO();
        mockBuyBookDTO.setBook(mockBook);
        mockBuyBookDTO.setBuy(mockBuy);

        final long id = 1L;

        Mockito.when(mockBuyBookService.getById(id)).thenReturn(mockBuyBookDTO);
        assertEquals(mockBuyBookDTO, mockBuyBookService.getById(id));
    }

    @Test
    void setByDTO() {
        BuyBookService mockBuyBookService = Mockito.mock(BuyBookService.class);

        final String title = "Occult encyclopedia";
        BookDTO mockBookDTO = new BookDTO();
        mockBookDTO.setTitle(title);

        final String description = "Deliver only during night time";
        BuyDTO mockBuyDTO = new BuyDTO();
        mockBuyDTO.setDescription(description);

        BuyBookDTO mockBuyBookDTO = new BuyBookDTO();

        Mockito.doNothing().when(mockBuyBookService).setByDTO(mockBuyDTO, mockBookDTO, mockBuyBookDTO);

        mockBuyBookService.setByDTO(mockBuyDTO, mockBookDTO, mockBuyBookDTO);
        Mockito.verify(mockBuyBookService).setByDTO(mockBuyDTO, mockBookDTO, mockBuyBookDTO);
    }

    @Test
    void update() {
        BuyBookService mockBuyBookService = Mockito.mock(BuyBookService.class);

        final String title = "Occult encyclopedia";
        BookDTO mockBookDTO = new BookDTO();
        mockBookDTO.setTitle(title);

        final String description = "Deliver only during night time";
        BuyDTO mockBuyDTO = new BuyDTO();
        mockBuyDTO.setDescription(description);

        BuyBookDTO mockBuyBookDTO = new BuyBookDTO();

        final long buyId = 1L;
        final long bookId = 1L;
        final long buyBookId = 1L;

        Mockito.doNothing().when(mockBuyBookService).update(buyId, mockBuyDTO, bookId, mockBookDTO, buyBookId, mockBuyBookDTO);

        mockBuyBookService.update(buyId, mockBuyDTO, bookId, mockBookDTO, buyBookId, mockBuyBookDTO);
        Mockito.verify(mockBuyBookService).update(buyId, mockBuyDTO, bookId, mockBookDTO, buyBookId, mockBuyBookDTO);
    }

    @Test
    void deleteById() {
        BuyBookService mockBuyBookService = Mockito.mock(BuyBookService.class);

        final long id = 1L;

        Mockito.doNothing().when(mockBuyBookService).deleteById(id);

        mockBuyBookService.deleteById(id);
        Mockito.verify(mockBuyBookService).deleteById(id);
    }
}