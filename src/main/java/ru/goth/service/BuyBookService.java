package ru.goth.service;

import ru.goth.entity.BuyBook;
import ru.goth.entity.dto.BookDTO;
import ru.goth.entity.dto.BuyBookDTO;
import ru.goth.entity.dto.BuyDTO;
import ru.goth.repository.BuyBookDAO;

import java.util.logging.Logger;

public class BuyBookService {
    private static final Logger logger = Logger.getLogger(BuyBookService.class.getName());
    private BuyBookDAO buyBookDAO;

    public BuyBookService () {}

    public BuyBookService (BuyBookDAO buyBookDAO) {
        this.buyBookDAO = buyBookDAO;
    }

    public BuyBookDTO getById(long id) {
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

        buyBookDAO.setBuyBook(buyId, bookId, buyBookDTO.getAmount());
    }
    public void update(long buyId, BuyDTO buyDTO, long bookId, BookDTO bookDTO, long buyBookId, BuyBookDTO buyBookDTO) {
        BuyService buyService = new BuyService();
        buyService.update(buyId, buyDTO);

        BookService bookService = new BookService();
        bookService.update(bookId, bookDTO);

        if (buyBookDAO.getBuyBook(buyId) == null) {
            logger.info("No such buyBook");
        } else {

            buyBookDAO.updateBuyBook(buyBookId, buyId, bookId, buyBookDTO.getAmount());
        }
    }
    public void deleteById(long id) {
        buyBookDAO.deleteBuyBook(id);
    }
}
