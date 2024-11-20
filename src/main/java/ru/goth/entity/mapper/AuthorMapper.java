package ru.goth.entity.mapper;

import org.mapstruct.Mapper;
import ru.goth.entity.Author;
import ru.goth.entity.dto.AuthorDTO;

@Mapper
public interface AuthorMapper {
    public AuthorDTO toAuthorDTO(Author author);
}
