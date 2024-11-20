package ru.goth.service;

import ru.goth.entity.Book;
import ru.goth.entity.dto.BookDTO;
import ru.goth.repository.BookDAO;

public class BookService {
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
}
