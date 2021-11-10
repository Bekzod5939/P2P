package uz.pdp.service.interfaces;

import uz.pdp.model.CardHistory;
import uz.pdp.service.base.BaseService;

import java.util.ArrayList;
import java.util.UUID;

public interface CardHistoryService extends BaseService<CardHistory,UUID> {

    ArrayList<CardHistory> getHistoryFromCard(UUID fromId);

    ArrayList<CardHistory> getHistoryToCard(UUID toId);

    @Override
    ArrayList<CardHistory> getAll();

    @Override
    boolean add(CardHistory cardHistory);

    @Override
    CardHistory getById(UUID uuid);
}
