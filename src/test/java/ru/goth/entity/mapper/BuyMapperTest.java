package ru.goth.entity.mapper;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import ru.goth.entity.Buy;
import ru.goth.entity.dto.BuyDTO;

import static org.junit.jupiter.api.Assertions.*;

class BuyMapperTest {

    @Test
    void toBuyDTO() {
        final long id = 1L;
        final String description = "Pablo";
        Buy buy = new Buy();
        buy.setId(id);
        buy.setDescription(description);
        BuyMapper buyMapper = Mappers.getMapper(BuyMapper.class);
        BuyDTO buyDTO = buyMapper.toBuyDTO(buy);
        assertEquals(buy.getDescription(), buyDTO.getDescription());
    }
}