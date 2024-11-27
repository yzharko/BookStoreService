package ru.goth.controller;

import org.junit.Before;
import org.junit.Test;
import ru.goth.controller.buybookservlets.DeleteBuyBook;
import ru.goth.service.BuyBookService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class DeleteBuyBookTest {

    private BuyBookService mockBuyBookService;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private DeleteBuyBook deleteBuyBook;
    private RequestDispatcher requestDispatcher;

    @Before
    public void setup() {
        mockBuyBookService = mock(BuyBookService.class);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        requestDispatcher = mock(RequestDispatcher.class);
        try {
            deleteBuyBook = new DeleteBuyBook();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testDoDelete() throws ServletException, IOException {
        final long buyBookId = 1L;
        when(request.getParameter("id")).thenReturn(String.valueOf(buyBookId));

        final String jsp = "/index.jsp";
        when(request.getRequestDispatcher(jsp)).thenReturn(requestDispatcher);

        deleteBuyBook.doDelete(request, response);

        verify(mockBuyBookService).deleteById(any(long.class));
        verify(requestDispatcher).forward(request, response);
    }
}