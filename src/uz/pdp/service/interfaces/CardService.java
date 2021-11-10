package uz.pdp.service.interfaces;

import uz.pdp.model.Card;
import uz.pdp.service.base.BaseService;

import java.util.ArrayList;
import java.util.UUID;

public interface CardService extends BaseService<Card,UUID> {

    ArrayList<Card> getAll();

    @Override
    boolean add(Card card);

    @Override
    Card getById(UUID uuid);

    ArrayList<Card> getCardsOfOwner(UUID ownerId);


    boolean editCard(Card card);

    boolean checkCardNum(String cardNum);


}
