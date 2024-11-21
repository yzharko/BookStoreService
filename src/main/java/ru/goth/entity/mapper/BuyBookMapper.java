package ru.goth.entity.mapper;

import org.mapstruct.Mapper;
import ru.goth.entity.BuyBook;
import ru.goth.entity.dto.BuyBookDTO;

@Mapper
public interface BuyBookMapper {
    BuyBookDTO toBuyBookDTO(BuyBook buyBook);
}
