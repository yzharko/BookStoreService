package ru.goth.entity.mapper;

import org.junit.Test;
import org.mapstruct.factory.Mappers;
import ru.goth.entity.BuyBook;
import ru.goth.entity.dto.BuyBookDTO;

import static org.junit.Assert.assertEquals;

public class BuyBookMapperTest {

    @Test
    public void toBuyBookDTO() {
        final long id = 1L;
        final int amount = 15;
        BuyBook buyBook = new BuyBook();
        buyBook.setId(id);
        buyBook.setAmount(amount);
        BuyBookMapper buyBookMapper = Mappers.getMapper(BuyBookMapper.class);
        BuyBookDTO buyBookDTO = buyBookMapper.toBuyBookDTO(buyBook);
        assertEquals(buyBook.getAmount(), buyBookDTO.getAmount());
    }
}