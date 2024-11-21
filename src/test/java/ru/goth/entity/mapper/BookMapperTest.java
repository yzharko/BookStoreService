package ru.goth.entity.mapper;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import ru.goth.entity.Book;
import ru.goth.entity.dto.BookDTO;

import static org.junit.jupiter.api.Assertions.*;

class BookMapperTest {

    @Test
    void toBookDTO() {
        final long id = 1L;
        final String title = "Pablo";
        Book book = new Book();
        book.setId(id);
        book.setTitle(title);
        BookMapper bookMapper = Mappers.getMapper(BookMapper.class);
        BookDTO bookDTO = bookMapper.toBookDTO(book);
        assertEquals(book.getTitle(), bookDTO.getTitle());
    }
}