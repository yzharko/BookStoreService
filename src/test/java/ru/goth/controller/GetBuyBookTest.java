package ru.goth.controller;

import org.junit.Before;
import org.junit.Test;
import ru.goth.controller.buybookservlets.GetBuyBook;
import ru.goth.service.BuyBookService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class GetBuyBookTest {
    private BuyBookService mockBuyBookService;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private GetBuyBook getBuyBook;
    private RequestDispatcher requestDispatcher;

    @Before
    public void setup() {
        mockBuyBookService = mock(BuyBookService.class);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        requestDispatcher = mock(RequestDispatcher.class);
        getBuyBook = new GetBuyBook(mockBuyBookService);
    }

    @Test
    public void testDoGet() throws ServletException, IOException {
        final long buyBookId = 1L;
        when(request.getParameter("buyBook.id")).thenReturn(String.valueOf(buyBookId));

        final String jsp = "/WEB-INF/jsp/buyBookGetRes.jsp";
        when(request.getRequestDispatcher(jsp)).thenReturn(requestDispatcher);

        getBuyBook.doGet(request, response);

        verify(mockBuyBookService).getById(any(long.class));
        verify(requestDispatcher).forward(request, response);

    }
}