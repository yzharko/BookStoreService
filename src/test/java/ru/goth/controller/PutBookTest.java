package ru.goth.controller;

import org.junit.Before;
import org.junit.Test;
import ru.goth.controller.bookservlets.PutBook;
import ru.goth.entity.dto.BookDTO;
import ru.goth.service.BookService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PutBookTest {

    private BookService mockBookService;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private PutBook putBook;
    private RequestDispatcher requestDispatcher;

    @Before
    public void setup() {
        mockBookService = mock(BookService.class);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        requestDispatcher = mock(RequestDispatcher.class);
        try {
            putBook = new PutBook();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testDoPut() throws ServletException, IOException {

        final long bookId = 1L;
        final String title = "Occult encyclopedia";
        final long authorId = 1L;
        final String genre = "Occult";
        final float price = 228.00F;
        final int bookAmount = 1;

        when(request.getParameter("book.id")).thenReturn(String.valueOf(bookId));
        when(request.getParameter("title")).thenReturn(title);
        when(request.getParameter("author.id")).thenReturn(String.valueOf(authorId));
        when(request.getParameter("genre")).thenReturn(genre);
        when(request.getParameter("price")).thenReturn(String.valueOf(price));
        when(request.getParameter("amount")).thenReturn(String.valueOf(bookAmount));

        final String jsp = "/index.jsp";
        when(request.getRequestDispatcher(jsp)).thenReturn(requestDispatcher);
        when(response.getStatus()).thenReturn(HttpServletResponse.SC_OK);

        putBook.doPut(request, response);

        verify(mockBookService).update(any(long.class), any(BookDTO.class));
        verify(requestDispatcher).forward(request, response);
    }
}