package ru.goth.controller;

import org.springframework.web.bind.annotation.*;
import ru.goth.entity.dto.BuyBookDTO;
import ru.goth.service.BuyBookService;

@RestController
@RequestMapping("/buyBook")
public class BuyBookController {
    private BuyBookService buyBookService;

    public BuyBookController(BuyBookService buyBookService) {
        this.buyBookService = buyBookService;
    }

    @GetMapping
    public BuyBookDTO getBuyBook(@RequestParam long id) {
        return buyBookService.getById(id);
    }

    @PostMapping
    public void createBuyBook(@RequestBody BuyBookDTO buyBookDTO) {
        buyBookService.setByDTO(buyBookDTO);
    }

    @PutMapping
    public void updateBuyBook(@RequestParam long id, @RequestBody BuyBookDTO buyBookDTO) {
        buyBookService.update(id, buyBookDTO);
    }

    @DeleteMapping
    public void deleteAuthor(@RequestParam long id) {
        buyBookService.deleteById(id);
    }
}
