package ru.goth.service;

import org.springframework.stereotype.Service;
import ru.goth.entity.Author;
import ru.goth.entity.dto.AuthorDTO;
import ru.goth.repository.AuthorDAO;

import java.util.Optional;
import java.util.logging.Logger;

@Service
public class AuthorService {
    private static final Logger logger = Logger.getLogger(AuthorService.class.getName());
    private final AuthorDAO authorDAO;

    public AuthorService(AuthorDAO authorDAO) {
        this.authorDAO = authorDAO;
    }

    public AuthorDTO getById(long id) {
        Author author = authorDAO.getAuthor(id);

        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setName(author.getName());

        return authorDTO;
    }

    public long setByDTO(AuthorDTO authorDTO) {
        Optional<Long> result = authorDAO.setAuthor(authorDTO.getName());
        return result.isPresent() ? result.get() : 0;
    }

    public void update(long id, AuthorDTO authorDTO) {
        if (authorDAO.getAuthor(id) == null) {
            logger.info("No such author");
        } else {
            authorDAO.updateAuthor(id, authorDTO.getName());
        }
    }

    public void deleteById(long id) {
        authorDAO.deleteAuthor(id);
    }
}