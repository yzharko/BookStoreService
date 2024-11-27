package ru.goth.controller;

import org.junit.Before;
import org.junit.Test;
import ru.goth.controller.authorservlets.PutAuthor;
import ru.goth.entity.dto.AuthorDTO;
import ru.goth.service.AuthorService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PutAuthorTest {

    private AuthorService mockAuthorService;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private RequestDispatcher requestDispatcher;
    private PutAuthor putAuthor;

    @Before
    public void setup() {
        mockAuthorService = mock(AuthorService.class);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        requestDispatcher = mock(RequestDispatcher.class);
        putAuthor = new PutAuthor(mockAuthorService);
    }

    @Test
    public void testDoPut() throws ServletException, IOException {

        final long id = 1L;
        final String name = "G.O.M.";

        when(request.getParameter("id")).thenReturn(String.valueOf(id));
        when(request.getParameter("name")).thenReturn(name);

        final String jsp = "/index.jsp";
        when(request.getRequestDispatcher(jsp)).thenReturn(requestDispatcher);
        when(response.getStatus()).thenReturn(HttpServletResponse.SC_OK);

        putAuthor.doPut(request, response);

        verify(mockAuthorService).update(any(long.class), any(AuthorDTO.class));
        verify(requestDispatcher).forward(request, response);
    }
}
