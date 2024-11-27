package ru.goth.controller.bookservlets;

import ru.goth.entity.Author;
import ru.goth.entity.dto.BookDTO;
import ru.goth.service.BookService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

@WebServlet(name = "updateBook", value = "/updateBook")
public class PutBook extends HttpServlet {
    private static final Logger logger = Logger.getLogger(PutBook.class.getName());

    private final BookService bookService;

    public PutBook() throws SQLException {
        this.bookService = new BookService();
    }

    public PutBook(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html");

        try {
            long id = Long.parseLong(request.getParameter("book.id"));

            BookDTO bookDTO = new BookDTO();
            bookDTO.setTitle(request.getParameter("title"));

            Author author = new Author();
            author.setId(Long.parseLong(request.getParameter("author.id")));
            bookDTO.setAuthor(author);

            bookDTO.setGenre(request.getParameter("genre"));
            bookDTO.setPrice(
                    Float.parseFloat(request.getParameter("price")));
            bookDTO.setAmount(
                    Integer.parseInt(request.getParameter("amount")));

            bookService.update(id, bookDTO);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }

        response.setStatus(HttpServletResponse.SC_OK);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");

        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            logger.info(e.getMessage());
        }
    }
}
