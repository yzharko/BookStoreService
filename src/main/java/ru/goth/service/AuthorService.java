package ru.goth.service;

import ru.goth.entity.Author;
import ru.goth.entity.dto.AuthorDTO;
import ru.goth.repository.AuthorDAO;

public class AuthorService {
    public AuthorDTO getById(long id) {
        AuthorDAO authorDAO = new AuthorDAO();
        Author author = authorDAO.getAuthor(id);

        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setName(author.getName());

        return authorDTO;
    }
}
