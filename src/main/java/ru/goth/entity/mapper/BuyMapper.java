package ru.goth.entity.mapper;

import org.mapstruct.Mapper;
import ru.goth.entity.Buy;
import ru.goth.entity.dto.BuyDTO;

@Mapper
public interface BuyMapper {
    public BuyDTO toBuyDTO(Buy buy);
}
