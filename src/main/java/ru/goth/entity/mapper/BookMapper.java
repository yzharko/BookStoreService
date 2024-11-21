package ru.goth.entity.mapper;

import org.mapstruct.Mapper;
import ru.goth.entity.Book;
import ru.goth.entity.dto.BookDTO;

@Mapper
public interface BookMapper {
    BookDTO toBookDTO(Book book);
}
