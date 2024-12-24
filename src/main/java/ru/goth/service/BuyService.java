package ru.goth.service;

import org.springframework.stereotype.Service;
import ru.goth.entity.Buy;
import ru.goth.entity.dto.BuyDTO;
import ru.goth.repository.BuyDAO;

import java.util.Optional;
import java.util.logging.Logger;

@Service
public class BuyService {
    private static final Logger logger = Logger.getLogger(BuyService.class.getName());
    private BuyDAO buyDAO;

    BuyService(BuyDAO buyDAO) {
        this.buyDAO = buyDAO;
    }

    public BuyDTO getById(long id) {
        Buy buy = buyDAO.getBuy(id);

        BuyDTO buyDTO = new BuyDTO();
        buyDTO.setDescription(buy.getDescription());
        buyDTO.setClient(buy.getClient());

        return buyDTO;
    }

    public long setByDTO(BuyDTO buyDTO) {
        Optional<Long> result = buyDAO.setBuy(buyDTO.getDescription(), buyDTO.getClient());
        return result.isPresent() ? result.get() : 0;
    }

    public void update(long id, BuyDTO buyDTO) {
        if (buyDAO.getBuy(id) == null) {
            logger.info("No such buy");
        } else {
            String description = buyDTO.getDescription();
            String client = buyDTO.getClient();

            buyDAO.updateBuy(id, description, client);
        }
    }
    public void deleteById(long id) {
        buyDAO.deleteBuy(id);
    }

}
