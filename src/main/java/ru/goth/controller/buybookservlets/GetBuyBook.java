package ru.goth.controller.buybookservlets;

import ru.goth.controller.JsonConvertor;
import ru.goth.entity.dto.BuyBookDTO;
import ru.goth.service.BuyBookService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.logging.Logger;

@WebServlet(name = "getBuyBook", value = "/getBuyBook")
public class GetBuyBook extends HttpServlet {
    private static final Logger logger = Logger.getLogger(GetBuyBook.class.getName());
    private final BuyBookService buyBookService;

    public GetBuyBook() throws SQLException {
        this.buyBookService = new BuyBookService();
    }

    public GetBuyBook(BuyBookService buyBookService) {
        this.buyBookService = buyBookService;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html");

        try {
            long id = Long.parseLong(request.getParameter("buyBook.id"));

            BuyBookDTO buyBookDTO = buyBookService.getById(id);

            JsonConvertor<BuyBookDTO> jsonConvertor = new JsonConvertor<>();
            jsonConvertor.convertToJson(response, buyBookDTO);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
    }

}
