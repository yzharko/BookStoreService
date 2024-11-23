package ru.goth.controller;

import org.junit.Before;
import org.junit.Test;
import ru.goth.controller.bookservlets.GetBook;
import ru.goth.service.BookService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class GetBookTest {

    private BookService mockBookService;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private GetBook getBook;
    private RequestDispatcher requestDispatcher;

    @Before
    public void setup() {
        mockBookService = mock(BookService.class);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        requestDispatcher = mock(RequestDispatcher.class);
        getBook = new GetBook(mockBookService);
    }

    @Test
    public void testDoGet() throws ServletException, IOException {
        final long id = 1L;
        when(request.getParameter("id")).thenReturn(String.valueOf(id));

        final String jsp = "/WEB-INF/jsp/bookGetRes.jsp";
        when(request.getRequestDispatcher(jsp)).thenReturn(requestDispatcher);

        getBook.doGet(request, response);

        verify(mockBookService).getById(any(long.class));
        verify(requestDispatcher).forward(request, response);
    }
}