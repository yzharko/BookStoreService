package ru.goth.controller;

import org.springframework.web.bind.annotation.*;
import ru.goth.entity.dto.BuyBookDTO;
import ru.goth.service.BuyBookService;

@RestController
public class BuyBookController {
    private BuyBookService buyBookService;

    public BuyBookController(BuyBookService buyBookService) {
        this.buyBookService = buyBookService;
    }

    @GetMapping
    public void getBuyBook(@RequestParam long id) {
        buyBookService.getById(id);
    }

    @PatchMapping
    public void createBuyBook(@RequestBody BuyBookDTO buyBookDTO) {
        //TODO: figure out 01
    }

    @PutMapping
    public void updateBuyBook(@RequestParam long id, @RequestBody BuyBookDTO buyBookDTO) {
        //TODO: figure out 02
    }

    @DeleteMapping
    public void deleteAuthor(@RequestParam long id) {
        buyBookService.deleteById(id);
    }
}
