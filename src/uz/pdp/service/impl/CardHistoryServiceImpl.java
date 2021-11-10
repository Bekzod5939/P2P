package uz.pdp.service.impl;

import uz.pdp.model.CardHistory;
import uz.pdp.service.interfaces.CardHistoryService;

import java.util.ArrayList;
import java.util.UUID;

public class CardHistoryServiceImpl implements CardHistoryService {

    private ArrayList<CardHistory> histories=new ArrayList<>();

    @Override
    public  ArrayList<CardHistory> getAll() {
        return histories;
    }

    @Override
    public boolean add(CardHistory cardHistory) {
        histories.add(cardHistory);
        return true;
    }

    @Override
    public CardHistory getById(UUID uuid) {
        for (CardHistory history : histories)
            if(history.getId().equals(uuid))
                return history;

        return null;
    }

    @Override
    public  ArrayList<CardHistory> getHistoryFromCard(UUID fromId) {
        ArrayList<CardHistory> cardHistoryArrayList=new ArrayList<>();
        for (CardHistory history : histories) {
            if(history.getFrom().equals(fromId))
            cardHistoryArrayList.add(history);
        }
        return cardHistoryArrayList;
    }

    @Override
    public ArrayList<CardHistory> getHistoryToCard(UUID toId) {
        ArrayList<CardHistory> cardHistoryArrayList=new ArrayList<>();
        for (CardHistory history : histories) {
            if(history.getTo().equals(toId))
                cardHistoryArrayList.add(history);
        }
        return cardHistoryArrayList;
    }
}
