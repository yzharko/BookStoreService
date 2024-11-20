package ru.goth.controller;

import ru.goth.entity.dto.AuthorDTO;
import ru.goth.service.AuthorService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "getAuthor", value = "/getAuthor")
public class getAuthor extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html");
        long id = Long.parseLong(request.getParameter("id"));

        AuthorService authorService = new AuthorService();
        AuthorDTO authorDTO = authorService.getById(id);

        request.setAttribute("author", authorDTO);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/author.jsp");

        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
