package ru.goth.service;

import ru.goth.entity.Author;
import ru.goth.entity.Book;
import ru.goth.entity.dto.BookDTO;
import ru.goth.repository.BookDAO;

import java.util.logging.Logger;

public class BookService {
    private static final Logger logger = Logger.getLogger(BookService.class.getName());

    public BookDTO getById(long id) {
        BookDAO bookDAO = new BookDAO();
        Book book = bookDAO.getBook(id);

        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle(book.getTitle());
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setGenre(book.getGenre());
        bookDTO.setPrice(book.getPrice());
        bookDTO.setAmount(book.getAmount());

        return bookDTO;
    }
    public int setByDTO(BookDTO bookDTO) {
        String title = bookDTO.getTitle();
        Author author = bookDTO.getAuthor();
        String genre = bookDTO.getGenre();
        float price = bookDTO.getPrice();
        int amount = bookDTO.getAmount();

        BookDAO bookDAO = new BookDAO();
        return bookDAO.setBook(title, author, genre, price, amount);
    }
    public void update(long id, BookDTO bookDTO) {
        BookDAO bookDAO = new BookDAO();
        if (bookDAO.getBook(id) == null) {
            logger.info("No such book");
        } else {
            String title = bookDTO.getTitle();
            Author author = bookDTO.getAuthor();
            String genre = bookDTO.getGenre();
            float price = bookDTO.getPrice();
            int amount = bookDTO.getAmount();

            bookDAO.updateBook(id, title, author, genre, price, amount);
        }
    }
    public void deleteByDTO(BookDTO bookDTO) {
        BookDAO bookDAO = new BookDAO();
        bookDAO.deleteBook(bookDTO.getTitle());
    }

}
