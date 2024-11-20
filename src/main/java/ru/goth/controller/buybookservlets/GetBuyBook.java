package ru.goth.controller.buybookservlets;

import ru.goth.entity.dto.BuyBookDTO;
import ru.goth.service.BuyBookService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "getBuyBook", value = "/getBuyBook")
public class GetBuyBook extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html");
        long id = Long.parseLong(request.getParameter("buyBook.id"));

        BuyBookService buyBookService = new BuyBookService();
        BuyBookDTO buyBookDTO = buyBookService.getById(id);

        request.setAttribute("buyBook", buyBookDTO);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/buyBookGetRes.jsp");

        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

}
