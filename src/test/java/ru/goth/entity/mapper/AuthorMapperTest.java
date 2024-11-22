package ru.goth.entity.mapper;

import org.junit.Test;
import org.mapstruct.factory.Mappers;
import ru.goth.entity.Author;
import ru.goth.entity.dto.AuthorDTO;

import static org.junit.Assert.assertEquals;

public class AuthorMapperTest {

    @Test
    public void toAuthorDTO() {
        final long id = 1L;
        final String name = "Pablo";
        Author author = new Author();
        author.setId(id);
        author.setName(name);
        AuthorMapper authorMapper = Mappers.getMapper(AuthorMapper.class);
        AuthorDTO authorDTO = authorMapper.toAuthorDTO(author);
        assertEquals(author.getName(), authorDTO.getName());
    }
}