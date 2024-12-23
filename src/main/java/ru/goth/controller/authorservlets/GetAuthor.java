package ru.goth.controller.authorservlets;

import ru.goth.controller.JsonConvertor;
import ru.goth.entity.dto.AuthorDTO;
import ru.goth.service.AuthorService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.logging.Logger;

@WebServlet(name = "getAuthor", value = "/getAuthor")
public class GetAuthor extends HttpServlet {
    private static final Logger logger = Logger.getLogger(GetAuthor.class.getName());
    private final AuthorService authorService;

    public GetAuthor() throws SQLException {
        this.authorService = new AuthorService();
    }

    public GetAuthor(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html");

        try {
            long id = Long.parseLong(request.getParameter("id"));

            AuthorDTO authorDTO = authorService.getById(id);

            JsonConvertor <AuthorDTO> jsonConvertor = new JsonConvertor<>(response);
            jsonConvertor.convertToJson(authorDTO);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }

    }
}
