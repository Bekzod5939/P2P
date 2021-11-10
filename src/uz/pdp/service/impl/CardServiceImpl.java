package uz.pdp.service.impl;

import uz.pdp.model.Card;
import uz.pdp.service.interfaces.CardService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.UUID;

public class CardServiceImpl implements CardService {

    private ArrayList<Card> cards = new ArrayList<>();

    public CardServiceImpl() {
        this.cards.add(new Card("adminCard",UserServiceImpl.getOwner().getId(), BigDecimal.valueOf(0),BigDecimal.valueOf(0),"0000000000000000"));
    }

    @Override
    public ArrayList<Card> getAll() {
        return cards;
    }


    @Override
    public ArrayList<Card> getCardsOfOwner(UUID ownerId) {
        ArrayList<Card> result = new ArrayList<>();
        for (Card card : cards) {
            if (card.getOwnerId().equals(ownerId))
                result.add(card);
        }
        return result;
    }

    @Override
    public boolean add(Card card) {
        cards.add(card);
        return true;
    }

    @Override
    public boolean editCard(Card card) {
        if (cards.removeIf(card1 -> card1.getId().equals(card.getId()))) {
            cards.add(card);
            return true;
        }
        return false;
    }

    @Override
    public boolean checkCardNum(String cardNum) {
        for (Card card : cards)
            if(card.getCardNum().equals(cardNum))
                return true;
        return false;
    }

    @Override
    public Card getById(UUID id) {
        for (Card card : cards) {
            if(card.getId().equals(id))
                return card;
        }
        return null;
    }
}
