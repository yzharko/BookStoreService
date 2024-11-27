package ru.goth.controller;

import org.junit.Before;
import org.junit.Test;
import ru.goth.controller.authorservlets.DeleteAuthor;
import ru.goth.entity.dto.AuthorDTO;
import ru.goth.service.AuthorService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class DeleteAuthorTest {

    private AuthorService mockAuthorService;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private RequestDispatcher requestDispatcher;
    private DeleteAuthor deleteAuthor;

    @Before
    public void setup() {
        mockAuthorService = mock(AuthorService.class);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        requestDispatcher = mock(RequestDispatcher.class);
        try {
            deleteAuthor = new DeleteAuthor();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testDoDelete() throws ServletException, IOException {
        final String authorName = "G.O.M.";
        when(request.getParameter("name")).thenReturn(authorName);

        final String jsp = "/index.jsp";
        when(request.getRequestDispatcher(jsp)).thenReturn(requestDispatcher);

        deleteAuthor.doDelete(request, response);

        verify(mockAuthorService).deleteByDTO(any(AuthorDTO.class));
        verify(requestDispatcher).forward(request, response);
    }
}