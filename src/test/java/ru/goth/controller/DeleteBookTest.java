package ru.goth.controller;

import org.junit.Before;
import org.junit.Test;
import ru.goth.controller.bookservlets.DeleteBook;
import ru.goth.entity.dto.BookDTO;
import ru.goth.service.BookService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class DeleteBookTest {

    private BookService mockBookService;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private DeleteBook deleteBook;
    private RequestDispatcher requestDispatcher;

    @Before
    public void setup() {
        mockBookService = mock(BookService.class);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        requestDispatcher = mock(RequestDispatcher.class);
        deleteBook = new DeleteBook(mockBookService);
    }

    @Test
    public void testDoDelete() throws ServletException, IOException {
        final String title = "Occult encyclopedia";
        when(request.getParameter("title")).thenReturn(title);

        final String jsp = "/index.jsp";
        when(request.getRequestDispatcher(jsp)).thenReturn(requestDispatcher);

        deleteBook.doDelete(request, response);

        verify(mockBookService).deleteByDTO(any(BookDTO.class));
        verify(requestDispatcher).forward(request, response);
    }
}