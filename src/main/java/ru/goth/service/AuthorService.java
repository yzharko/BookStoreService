package ru.goth.service;

import ru.goth.entity.Author;
import ru.goth.entity.dto.AuthorDTO;
import ru.goth.repository.AuthorDAO;

import java.sql.SQLException;
import java.util.logging.Logger;

public class AuthorService {
    private static final Logger logger = Logger.getLogger(AuthorService.class.getName());
    private final AuthorDAO authorDAO;

    public AuthorService() throws SQLException {
        this.authorDAO = new AuthorDAO();
    }

    public AuthorDTO getById(long id) {
        Author author = authorDAO.getAuthor(id);

        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setName(author.getName());

        return authorDTO;
    }

    public void setByDTO(AuthorDTO authorDTO) {
        authorDAO.setAuthor(authorDTO.getName());
    }

    public void update(long id, AuthorDTO authorDTO) {
        if (authorDAO.getAuthor(id) == null) {
            logger.info("No such author");
        } else {
            authorDAO.updateAuthor(id, authorDTO.getName());
        }
    }

    public void deleteByDTO(AuthorDTO authorDTO) {
        authorDAO.deleteAuthor(authorDTO.getName());
    }
}