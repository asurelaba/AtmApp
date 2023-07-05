package com.solvd.services;

import com.solvd.db.dao.idao.ICardDAO;
import com.solvd.db.model.Card;

import java.util.List;

public class CardService extends EntityService<Card, ICardDAO> implements ICardDAO {

    @Override
    protected String getTableName() {
        return "cards";
    }

    @Override
    public Card getCardByCardNumber(long cardNumber) {
        return dao.getCardByCardNumber(cardNumber);
    }

    @Override
    public List<Card> getCardsByUserId(int userId) {
        return dao.getCardsByUserId(userId);
    }

    @Override
    public List<Card> getCardsByCardStatus(String statusName) {
        return dao.getCardsByCardStatus(statusName);
    }

    @Override
    public List<Card> getCardsByCardType(String typeName) {
        return dao.getCardsByCardType(typeName);
    }

}
