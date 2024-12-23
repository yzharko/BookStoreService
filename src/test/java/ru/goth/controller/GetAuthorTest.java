package ru.goth.controller;

import org.junit.Before;
import org.junit.Test;
import ru.goth.controller.authorservlets.GetAuthor;
import ru.goth.entity.dto.AuthorDTO;
import ru.goth.service.AuthorService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class GetAuthorTest {

    private AuthorService mockAuthorService;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private RequestDispatcher requestDispatcher;
    private GetAuthor getAuthor;
    private JsonConvertor <AuthorDTO> jsonConvertor;
    private final AuthorDTO mockAuthorDTO = new AuthorDTO();

    @Before
    public void setup() {
        mockAuthorService = mock(AuthorService.class);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        requestDispatcher = mock(RequestDispatcher.class);
        getAuthor = new GetAuthor(mockAuthorService);

        jsonConvertor = (JsonConvertor<AuthorDTO>) mock(JsonConvertor.class);
    }

    @Test
    public void testDoGet() throws ServletException, IOException {
        final long authorId = 1L;
        when(request.getParameter("id")).thenReturn(String.valueOf(authorId));
        mockAuthorDTO.setName(String.valueOf(authorId));
//        final String jsp = "/WEB-INF/jsp/authorGetRes.jsp";
//        when(request.getRequestDispatcher(jsp)).thenReturn(requestDispatcher);

        getAuthor.doGet(request, response);

        verify(mockAuthorService).getById(any(long.class));
        verify(jsonConvertor, times(1)).convertToJson(any(AuthorDTO.class));
        //verify(requestDispatcher).forward(request, response);
    }
}
