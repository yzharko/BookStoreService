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
import java.util.logging.Logger;

@WebServlet(name = "deleteBook", value = "/deleteBook")
public class DeleteBook extends HttpServlet {
    private static final Logger logger = Logger.getLogger(DeleteBook.class.getName());

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html");

        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle(request.getParameter("title"));

        BookService bookService = new BookService();
        bookService.deleteByDTO(bookDTO);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            logger.info(e.getMessage());
        }
    }
}
