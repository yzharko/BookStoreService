package ru.goth.service;

import org.springframework.stereotype.Service;
import ru.goth.entity.BuyBook;
import ru.goth.entity.dto.BuyBookDTO;
import ru.goth.repository.BuyBookDAO;

import java.util.logging.Logger;

@Service
public class BuyBookService {
    private static final Logger logger = Logger.getLogger(BuyBookService.class.getName());
    private BuyBookDAO buyBookDAO;

    public BuyBookService(BuyBookDAO buyBookDAO) {
        this.buyBookDAO = buyBookDAO;
    }

    public BuyBookDTO getById(long id) {
        BuyBook buyBook = buyBookDAO.getBuyBook(id);

        BuyBookDTO buyBookDTO = new BuyBookDTO();
        buyBookDTO.setBuy(buyBook.getBuy());
        buyBookDTO.setBook(buyBook.getBook());
        buyBookDTO.setAmount(buyBook.getAmount());

        return buyBookDTO;
    }

    public void setByDTO(BuyBookDTO buyBookDTO) {
        buyBookDAO.setBuyBook(buyBookDTO.getBuy().getId(), buyBookDTO.getBook().getId(), buyBookDTO.getAmount());
    }

    public void update(long buyBookId, BuyBookDTO buyBookDTO) {
        if (buyBookDAO.getBuyBook(buyBookId) == null) {
            logger.info("No such buyBook");
        } else {
            buyBookDAO.updateBuyBook(buyBookId, buyBookDTO.getBuy().getId(), buyBookDTO.getBook().getId(), buyBookDTO.getAmount());
        }
    }

    public void deleteById(long id) {
        buyBookDAO.deleteBuyBook(id);
    }
}
