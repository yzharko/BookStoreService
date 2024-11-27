package ru.goth.controller.bookservlets;

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

@WebServlet(name = "getBook", value = "/getBook")
public class GetBook extends HttpServlet {
    private static final Logger logger = Logger.getLogger(GetBook.class.getName());

    private final BookService bookService;

    public GetBook() throws SQLException {
        this.bookService = new BookService();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html");

        try {
            long id = Long.parseLong(request.getParameter("id"));

            BookDTO bookDTO = bookService.getById(id);

            request.setAttribute("book", bookDTO);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/bookGetRes.jsp");
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            logger.info(e.getMessage());
        }
    }

}
