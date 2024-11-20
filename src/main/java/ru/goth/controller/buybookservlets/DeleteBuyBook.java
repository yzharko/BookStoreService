package ru.goth.controller.buybookservlets;

import ru.goth.entity.BuyBook;
import ru.goth.service.BuyBookService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "deleteBuyBook", value = "/deleteBuyBook")
public class DeleteBuyBook extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html");
        BuyBook buyBook = new BuyBook();
        buyBook.setId(Long.parseLong(request.getParameter("id")));

        BuyBookService buyBookService = new BuyBookService();
        buyBookService.deleteById(buyBook.getId());
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");

        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
