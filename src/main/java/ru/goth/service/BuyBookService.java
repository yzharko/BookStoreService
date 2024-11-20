package ru.goth.service;

import ru.goth.entity.BuyBook;
import ru.goth.entity.dto.BookDTO;
import ru.goth.entity.dto.BuyBookDTO;
import ru.goth.repository.BuyBookDAO;

public class BuyBookService {
    public BuyBookDTO getById(long id) {
        BuyBookDAO buyBookDAO = new BuyBookDAO();
        BuyBook buyBook = buyBookDAO.getBuyBook(id);

        BuyBookDTO buyBookDTO = new BuyBookDTO();
        buyBookDTO.setBuy(buyBook.getBuy());
        buyBookDTO.setBook(buyBook.getBook());
        buyBookDTO.setAmount(buyBook.getAmount());

        return buyBookDTO;
    }
}
