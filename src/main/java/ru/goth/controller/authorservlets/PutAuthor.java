package ru.goth.controller.authorservlets;

import ru.goth.entity.dto.AuthorDTO;
import ru.goth.service.AuthorService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet(name = "updateAuthor", value = "/updateAuthor")
public class PutAuthor extends HttpServlet {
    private static final Logger logger = Logger.getLogger(PutAuthor.class.getName());

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html");

        try {
            long id = Long.parseLong(request.getParameter("id"));
            AuthorDTO authorDTO = new AuthorDTO();
            authorDTO.setName(request.getParameter("name"));

            AuthorService authorService = new AuthorService();
            authorService.update(id, authorDTO);

            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            logger.info(e.getMessage());
        }
    }
}
