package ru.goth.service;

import ru.goth.entity.dto.BuyDTO;
import ru.goth.repository.BuyDAO;

public class BuyService {

    public int setByDTO(BuyDTO buyDTO) {
        BuyDAO buyDAO = new BuyDAO();
        return buyDAO.setBuy(buyDTO.getDescription(), buyDTO.getClient());
    }
}
