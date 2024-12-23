package ru.goth.controller;

import org.springframework.web.bind.annotation.*;
import ru.goth.entity.dto.AuthorDTO;
import ru.goth.service.AuthorService;

@RestController
public class AuthorController {
    private AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public void getAuthor(@RequestParam long id) {
        authorService.getById(id);
    }

    @PatchMapping
    public void createAuthor(@RequestBody AuthorDTO authorDTO) {
        authorService.setByDTO(authorDTO);
    }

    @PutMapping
    public void updateAuthor(@RequestParam long id, @RequestBody AuthorDTO authorDTO) {
        authorService.update(id, authorDTO);
    }

    @DeleteMapping
    public void deleteAuthor(@RequestBody AuthorDTO authorDTO) {
        authorService.deleteByDTO(authorDTO);
    }
}
