package ru.goth.service;

import org.springframework.stereotype.Service;
import ru.goth.entity.Author;
import ru.goth.entity.Book;
import ru.goth.entity.dto.BookDTO;
import ru.goth.repository.BookDAO;

import java.util.Optional;
import java.util.logging.Logger;

@Service
public class BookService {
    private static final Logger logger = Logger.getLogger(BookService.class.getName());
    private BookDAO bookDAO;

    public BookService(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    public BookDTO getById(long id) {
        Book book = bookDAO.getBook(id);

        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle(book.getTitle());
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setGenre(book.getGenre());
        bookDTO.setPrice(book.getPrice());
        bookDTO.setAmount(book.getAmount());

        return bookDTO;
    }

    public long setByDTO(BookDTO bookDTO) {
        String title = bookDTO.getTitle();
        Author author = bookDTO.getAuthor();
        String genre = bookDTO.getGenre();
        float price = bookDTO.getPrice();
        int amount = bookDTO.getAmount();

        Optional<Long> result = bookDAO.setBook(title, author, genre, price, amount);
        return result.isPresent() ? result.get() : 0;
    }

    public void update(long id, BookDTO bookDTO) {
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

    public void deleteById(long id) {
        bookDAO.deleteBook(id);
    }

}
