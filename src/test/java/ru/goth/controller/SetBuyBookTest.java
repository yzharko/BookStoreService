package ru.goth.controller;

import org.junit.Before;
import org.junit.Test;
import ru.goth.controller.buybookservlets.SetBuyBook;
import ru.goth.entity.dto.BookDTO;
import ru.goth.entity.dto.BuyBookDTO;
import ru.goth.entity.dto.BuyDTO;
import ru.goth.service.BuyBookService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class SetBuyBookTest {
    private BuyBookService mockBuyBookService;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private SetBuyBook setBuyBook;
    private RequestDispatcher requestDispatcher;

    @Before
    public void setup() {
        mockBuyBookService = mock(BuyBookService.class);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        requestDispatcher = mock(RequestDispatcher.class);
        setBuyBook = new SetBuyBook(mockBuyBookService);
    }

    @Test
    public void testDoPost() throws ServletException, IOException {
        final String description = "Deliver only during night time";
        final String client = "GothGamerGhoul282";
        final String title = "Occult encyclopedia";
        final long authorId = 1L;
        final String genre = "Occult";
        final float price = 228.00F;
        final int bookAmount = 1;
        final int buyBookAmount = 1;

        when(request.getParameter("description")).thenReturn(description);
        when(request.getParameter("client")).thenReturn(client);
        when(request.getParameter("title")).thenReturn(title);
        when(request.getParameter("author.id")).thenReturn(String.valueOf(authorId));
        when(request.getParameter("genre")).thenReturn(genre);
        when(request.getParameter("price")).thenReturn(String.valueOf(price));
        when(request.getParameter("book.amount")).thenReturn(String.valueOf(bookAmount));
        when(request.getParameter("buyBook.amount")).thenReturn(String.valueOf(buyBookAmount));

        final String jsp = "/index.jsp";
        when(request.getRequestDispatcher(jsp)).thenReturn(requestDispatcher);
        when(response.getStatus()).thenReturn(HttpServletResponse.SC_CREATED);

        setBuyBook.doPost(request, response);

        verify(mockBuyBookService).setByDTO(any(BuyDTO.class), any(BookDTO.class), any(BuyBookDTO.class));
        verify(requestDispatcher).forward(request, response);
    }
}