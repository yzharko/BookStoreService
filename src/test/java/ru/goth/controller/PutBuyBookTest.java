package ru.goth.controller;

import org.junit.Before;
import org.junit.Test;
import ru.goth.controller.buybookservlets.PutBuyBook;
import ru.goth.entity.dto.BookDTO;
import ru.goth.entity.dto.BuyBookDTO;
import ru.goth.entity.dto.BuyDTO;
import ru.goth.service.BuyBookService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PutBuyBookTest {
    private BuyBookService mockBuyBookService;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private PutBuyBook putBuyBook;
    private RequestDispatcher requestDispatcher;

    @Before
    public void setup() {
        mockBuyBookService = mock(BuyBookService.class);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        requestDispatcher = mock(RequestDispatcher.class);
        try {
            putBuyBook = new PutBuyBook();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testDoPut() throws ServletException, IOException {

        final long buyBookId = 1L;
        final long buyId = 1L;
        final String description = "Deliver only during night time";
        final String client = "GothGamerGhoul282";

        final long bookId = 1L;
        final String title = "Occult encyclopedia";
        final long authorId = 1L;
        final String genre = "Occult";
        final float price = 228.00F;
        final int bookAmount = 1;

        final int buyBookAmount = 1;

        when(request.getParameter("buyBook.id")).thenReturn(String.valueOf(buyBookId));
        when(request.getParameter("buy.id")).thenReturn(String.valueOf(buyId));
        when(request.getParameter("description")).thenReturn(description);
        when(request.getParameter("client")).thenReturn(client);
        when(request.getParameter("book.id")).thenReturn(String.valueOf(bookId));
        when(request.getParameter("title")).thenReturn(title);
        when(request.getParameter("author.id")).thenReturn(String.valueOf(authorId));
        when(request.getParameter("genre")).thenReturn(genre);
        when(request.getParameter("price")).thenReturn(String.valueOf(price));
        when(request.getParameter("book.amount")).thenReturn(String.valueOf(bookAmount));
        when(request.getParameter("buyBook.amount")).thenReturn(String.valueOf(buyBookAmount));

        final String jsp = "/index.jsp";
        when(request.getRequestDispatcher(jsp)).thenReturn(requestDispatcher);
        when(response.getStatus()).thenReturn(HttpServletResponse.SC_CREATED);

        putBuyBook.doPut(request, response);

        verify(mockBuyBookService).update(any(long.class), any(BuyDTO.class), any(long.class),
                any(BookDTO.class), any(long.class), any(BuyBookDTO.class));
        verify(requestDispatcher).forward(request, response);
    }
}