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
    private final BookService bookService;
    private final BuyService buyService;

    public BuyBookService(BookService bookService, BuyService buyService) {
        this.bookService = bookService;
        this.buyService = buyService;
    }

    public BuyBookService(BuyBookDAO buyBookDAO, BookService bookService, BuyService buyService) {
        this.buyBookDAO = buyBookDAO;
        this.bookService = bookService;
        this.buyService = buyService;
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
        int bookId = bookService.setByDTO(bookDTO);
        int buyId = buyService.setByDTO(buyDTO);
        buyBookDAO.setBuyBook(buyId, bookId, buyBookDTO.getAmount());
    }

    public void update(long buyId, BuyDTO buyDTO, long bookId, BookDTO bookDTO, long buyBookId, BuyBookDTO buyBookDTO) {
        buyService.update(buyId, buyDTO);
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
