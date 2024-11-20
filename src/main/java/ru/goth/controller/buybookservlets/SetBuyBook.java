package ru.goth.controller.buybookservlets;

import ru.goth.entity.Author;
import ru.goth.entity.dto.BookDTO;
import ru.goth.entity.dto.BuyBookDTO;
import ru.goth.entity.dto.BuyDTO;
import ru.goth.service.BuyBookService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "setBuyBook", value = "/setBuyBook")
public class SetBuyBook extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html");

        BuyDTO buyDTO = new BuyDTO();
        buyDTO.setDescription(request.getParameter("description"));
        buyDTO.setClient(request.getParameter("client"));

        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle(request.getParameter("title"));

        Author author = new Author();
        author.setId(Long.parseLong(request.getParameter("author.id")));
        bookDTO.setAuthor(author);

        bookDTO.setGenre(request.getParameter("genre"));
        bookDTO.setPrice(
                Float.parseFloat(request.getParameter("price")));
        bookDTO.setAmount(
                Integer.parseInt(request.getParameter("book.amount")));

        BuyBookDTO buyBookDTO = new BuyBookDTO();
        buyBookDTO.setAmount(Integer.parseInt(request.getParameter("buyBook.amount")));

        BuyBookService buyBookService = new BuyBookService();
        buyBookService.setByDTO(buyDTO, bookDTO, buyBookDTO);
        response.setStatus(HttpServletResponse.SC_CREATED);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");

        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
