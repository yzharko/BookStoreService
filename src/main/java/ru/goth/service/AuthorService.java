package ru.goth.service;

import ru.goth.entity.Author;
import ru.goth.entity.dto.AuthorDTO;
import ru.goth.repository.AuthorDAO;

import java.util.logging.Logger;

public class AuthorService {
    public AuthorDTO getById(long id) {
        AuthorDAO authorDAO = new AuthorDAO();
        Author author = authorDAO.getAuthor(id);

        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setName(author.getName());

        return authorDTO;
    }
    public void setByDTO(AuthorDTO authorDTO) {
        AuthorDAO authorDAO = new AuthorDAO();
        authorDAO.setAuthor(authorDTO.getName());
    }
    public void update(long id, AuthorDTO authorDTO) {
        AuthorDAO authorDAO = new AuthorDAO();
        if (authorDAO.getAuthor(id) == null) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.info("No such author");
        } else {
            authorDAO.updateAuthor(id, authorDTO.getName());
        }
    }
    public void deleteByDTO(AuthorDTO authorDTO) {
        AuthorDAO authorDAO = new AuthorDAO();
        authorDAO.deleteAuthor(authorDTO.getName());
    }
}
