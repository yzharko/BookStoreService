package ru.goth.service;

import ru.goth.entity.BuyBook;
import ru.goth.entity.dto.BookDTO;
import ru.goth.entity.dto.BuyBookDTO;
import ru.goth.entity.dto.BuyDTO;
import ru.goth.repository.BuyBookDAO;

public class BuyBookService {
    public BuyBookDTO getById(long id) {
        BuyBookDAO buyBookDAO = new BuyBookDAO();
        BuyBook buyBook = buyBookDAO.getBuyBook(id);

        BuyBookDTO buyBookDTO = new BuyBookDTO();
        buyBookDTO.setBuy(buyBook.getBuy());
        buyBookDTO.setBook(buyBook.getBook());
        buyBookDTO.setAmount(buyBook.getAmount());

        return buyBookDTO;
    }
    public void setByDTO(BuyDTO buyDTO, BookDTO bookDTO, BuyBookDTO buyBookDTO) {
        BookService bookService = new BookService();
        int bookId = bookService.setByDTO(bookDTO);

        BuyService buyService = new BuyService();
        int buyId = buyService.setByDTO(buyDTO);

        BuyBookDAO buyBookDAO = new BuyBookDAO();
        buyBookDAO.setBuyBook(buyId, bookId, buyBookDTO.getAmount());
    }
    public void deleteById(long id) {
        BuyBookDAO buyBookDAO = new BuyBookDAO();
        buyBookDAO.deleteBuyBook(id);
    }
}
