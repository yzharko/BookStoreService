package ru.goth.controller;

import org.springframework.web.bind.annotation.*;
import ru.goth.entity.dto.BookDTO;
import ru.goth.service.BookService;

@RestController
public class BookController {
    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public void getBook(@RequestParam long id) {
        bookService.getById(id);
    }

    @PatchMapping
    public void createBook(@RequestBody BookDTO bookDTO) {
        bookService.setByDTO(bookDTO);
    }

    @PutMapping
    public void updateBook(@RequestParam long id, @RequestBody BookDTO bookDTO) {
        bookService.update(id, bookDTO);
    }

    @DeleteMapping
    public void deleteBook(@RequestBody BookDTO bookDTO) {
        bookService.deleteByDTO(bookDTO);
    }
}
