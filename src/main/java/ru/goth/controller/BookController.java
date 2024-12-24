package ru.goth.controller;

import org.springframework.web.bind.annotation.*;
import ru.goth.entity.dto.BookDTO;
import ru.goth.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public BookDTO getBook(@RequestParam long id) {
        return bookService.getById(id);
    }

    @PostMapping
    public long createBook(@RequestBody BookDTO bookDTO) {
        return bookService.setByDTO(bookDTO);
    }

    @PutMapping
    public void updateBook(@RequestParam long id, @RequestBody BookDTO bookDTO) {
        bookService.update(id, bookDTO);
    }

    @DeleteMapping
    public void deleteBook(@RequestParam long id) {
        bookService.deleteById(id);
    }
}
