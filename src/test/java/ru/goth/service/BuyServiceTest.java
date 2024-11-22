package ru.goth.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ru.goth.entity.Buy;
import ru.goth.entity.dto.BuyDTO;
import ru.goth.repository.BuyDAO;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class BuyServiceTest {
    private BuyDAO mockBuyDAO;
    private BuyService mockBuyService;
    private final BuyDTO mockBuyDTO = new BuyDTO();

    @Before
    public void setup() {
        mockBuyDAO = mock(BuyDAO.class);
        mockBuyService = new BuyService(mockBuyDAO);
    }

    @Test
    public void getById() {
        final long id = 1L;
        final String description = "Deliver only during night time";

        mockBuyDTO.setDescription(description);
        Buy buy = new Buy();
        buy.setDescription(description);

        Mockito.when(mockBuyDAO.getBuy(id)).thenReturn(buy);
        assertEquals(mockBuyDTO.getDescription(), mockBuyService.getById(id).getDescription());
    }

    @Test
    public void setByDTO() {

        final int id = 1;
        final String description = "Deliver only during night time";
        final String client = "GothGamerGhoul282";

        mockBuyDTO.setDescription(description);
        mockBuyDTO.setClient(client);

        Mockito.when(mockBuyDAO.setBuy(description, client)).thenReturn(id);

        assertEquals(id, mockBuyService.setByDTO(mockBuyDTO));
    }

    @Test
    public void update() {

        final long id = 1;
        final String description = "Deliver only during night time";
        final String client = "GothGamerGhoul282";

        mockBuyDTO.setDescription(description);
        mockBuyDTO.setClient(client);

        Buy buy = new Buy();
        buy.setDescription(description);
        buy.setClient(client);

        Mockito.when(mockBuyDAO.getBuy(id)).thenReturn(buy);

        mockBuyService.update(id, mockBuyDTO);
        verify(mockBuyDAO, times(1)).updateBuy(id, description, client);
    }
}