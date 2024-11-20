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

@WebServlet(name = "setBook", value = "/setBook")
public class SetBook extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html");
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

        BookService bookService = new BookService();
        bookService.setByDTO(bookDTO);
        response.setStatus(HttpServletResponse.SC_CREATED);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");

        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}