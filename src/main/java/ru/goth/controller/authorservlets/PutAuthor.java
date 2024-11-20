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

@WebServlet(name = "updateAuthor", value = "/updateAuthor")
public class PutAuthor extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html");
        long id = Long.parseLong(request.getParameter("id"));
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setName(request.getParameter("name"));

        AuthorService authorService = new AuthorService();
        authorService.update(id, authorDTO);

        response.setStatus(HttpServletResponse.SC_OK);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");

        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

}
