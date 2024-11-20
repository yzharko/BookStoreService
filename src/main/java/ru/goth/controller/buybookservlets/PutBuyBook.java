package ru.goth.controller.buybookservlets;

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

@WebServlet(name = "updateBuyBook", value = "/updateBuyBook")
public class PutBuyBook extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html");
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

        BookService bookService = new BookService();
        bookService.update(id, bookDTO);

        response.setStatus(HttpServletResponse.SC_OK);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");

        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
