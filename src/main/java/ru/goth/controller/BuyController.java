package ru.goth.controller;

import org.springframework.web.bind.annotation.*;
import ru.goth.entity.dto.BuyDTO;
import ru.goth.service.BuyService;

@RestController
@RequestMapping("/buy")
public class BuyController {
    private BuyService buyService;

    public BuyController(BuyService buyService) {
        this.buyService = buyService;
    }

    @GetMapping
    public BuyDTO getBuy(@RequestParam long id) {
        return buyService.getById(id);
    }

    @PostMapping
    public long createBuy(@RequestBody BuyDTO buyDTO) {
        return buyService.setByDTO(buyDTO);
    }

    @PutMapping
    public void updateBuy(@RequestParam long id, @RequestBody BuyDTO buyDTO) {
        buyService.update(id, buyDTO);
    }

    @DeleteMapping
    public void deleteBuy(@RequestParam long id) {
        buyService.deleteById(id);
    }
}
