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

@WebServlet(name = "getBook", value = "/getBook")
public class GetBook extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html");
        long id = Long.parseLong(request.getParameter("id"));

        BookService bookService = new BookService();
        BookDTO bookDTO = bookService.getById(id);

        request.setAttribute("book", bookDTO);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/bookGetRes.jsp");

        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

}
