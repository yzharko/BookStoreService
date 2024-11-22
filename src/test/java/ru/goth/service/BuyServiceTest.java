package ru.goth.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.goth.entity.dto.BookDTO;
import ru.goth.entity.dto.BuyDTO;

import static org.junit.jupiter.api.Assertions.*;

class BuyServiceTest {

    @Test
    void getById() {
        BuyService mockBuyService = Mockito.mock(BuyService.class);

        final long id = 1L;
        final String description = "Deliver only during night time";

        BuyDTO mockBuyDTO = new BuyDTO();
        mockBuyDTO.setDescription(description);

        Mockito.when(mockBuyService.getById(id)).thenReturn(mockBuyDTO);
        assertEquals(mockBuyDTO, mockBuyService.getById(id));
    }

    @Test
    void setByDTO() {
        BuyService mockBuyService = Mockito.mock(BuyService.class);

        final int id = 1;
        final String description = "Deliver only during night time";

        BuyDTO mockBuyDTO = new BuyDTO();
        mockBuyDTO.setDescription(description);

        Mockito.when(mockBuyService.setByDTO(mockBuyDTO)).thenReturn(id);

        assertEquals(id, mockBuyService.setByDTO(mockBuyDTO));
    }

    @Test
    void update() {
        BuyService mockBuyService = Mockito.mock(BuyService.class);

        final int id = 1;
        final String description = "Deliver only during night time";

        BuyDTO mockBuyDTO = new BuyDTO();
        mockBuyDTO.setDescription(description);

        Mockito.doNothing().when(mockBuyService).update(id, mockBuyDTO);

        mockBuyService.update(id, mockBuyDTO);
        Mockito.verify(mockBuyService).update(id, mockBuyDTO);
    }
}