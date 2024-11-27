package ru.goth.controller;

import org.junit.Before;
import org.junit.Test;
import ru.goth.controller.bookservlets.SetBook;
import ru.goth.entity.dto.BookDTO;
import ru.goth.service.BookService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class SetBookTest {

    private BookService mockBookService;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private SetBook setBook;
    private RequestDispatcher requestDispatcher;

    @Before
    public void setup() {
        mockBookService = mock(BookService.class);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        requestDispatcher = mock(RequestDispatcher.class);
        setBook = new SetBook(mockBookService);
    }

    @Test
    public void testDoPost() throws ServletException, IOException {
        final String title = "Occult encyclopedia";
        final long authorId = 1L;
        final String genre = "Occult";
        final float price = 228.00F;
        final int bookAmount = 1;

        when(request.getParameter("title")).thenReturn(title);
        when(request.getParameter("author.id")).thenReturn(String.valueOf(authorId));
        when(request.getParameter("genre")).thenReturn(genre);
        when(request.getParameter("price")).thenReturn(String.valueOf(price));
        when(request.getParameter("amount")).thenReturn(String.valueOf(bookAmount));

        final String jsp = "/index.jsp";
        when(request.getRequestDispatcher(jsp)).thenReturn(requestDispatcher);
        when(response.getStatus()).thenReturn(HttpServletResponse.SC_CREATED);

        setBook.doPost(request, response);

        verify(mockBookService).setByDTO(any(BookDTO.class));
        verify(requestDispatcher).forward(request, response);
    }
}