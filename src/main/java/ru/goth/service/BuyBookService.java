package ru.goth.service;

import ru.goth.entity.Author;
import ru.goth.entity.BuyBook;
import ru.goth.entity.dto.BookDTO;
import ru.goth.entity.dto.BuyBookDTO;
import ru.goth.entity.dto.BuyDTO;
import ru.goth.repository.BookDAO;
import ru.goth.repository.BuyBookDAO;
import ru.goth.repository.BuyDAO;

import java.util.logging.Logger;

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
    public void update(long buyId, BuyDTO buyDTO, long bookId, BookDTO bookDTO, long buyBookId, BuyBookDTO buyBookDTO) {
        BookDAO bookDAO = new BookDAO();
        BuyDAO buyDAO = new BuyDAO();
        BuyBookDAO buyBookDAO = new BuyBookDAO();

        if (buyBookDAO.getBuyBook(buyId) == null) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.info("No such buyBook");
        } else {
            String description = buyDTO.getDescription();
            String client = buyDTO.getClient();
            buyDAO.updateBuy(buyId, description, client);

            String title = bookDTO.getTitle();
            Author author = bookDTO.getAuthor();
            String genre = bookDTO.getGenre();
            float price = bookDTO.getPrice();
            int amount = bookDTO.getAmount();
            bookDAO.updateBook(bookId, title, author, genre, price, amount);

            buyBookDAO.updateBuyBook(buyBookId, buyId, bookId, buyBookDTO.getAmount());
        }
    }
    public void deleteById(long id) {
        BuyBookDAO buyBookDAO = new BuyBookDAO();
        buyBookDAO.deleteBuyBook(id);
    }
}
