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
import java.sql.SQLException;
import java.util.logging.Logger;

@WebServlet(name = "getBuyBook", value = "/getBuyBook")
public class GetBuyBook extends HttpServlet {
    private static final Logger logger = Logger.getLogger(GetBuyBook.class.getName());
    private final BuyBookService buyBookService;

    public GetBuyBook() throws SQLException {
        this.buyBookService = new BuyBookService();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html");

        try {
            long id = Long.parseLong(request.getParameter("buyBook.id"));

            BuyBookDTO buyBookDTO = buyBookService.getById(id);

            request.setAttribute("buyBook", buyBookDTO);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/buyBookGetRes.jsp");
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            logger.info(e.getMessage());
        }
    }

}
