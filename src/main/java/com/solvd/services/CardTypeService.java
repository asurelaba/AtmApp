package com.solvd.services;

import com.solvd.db.dao.idao.ICardTypeDAO;
import com.solvd.db.model.CardType;

public class CardTypeService extends EntityService<CardType, ICardTypeDAO> implements ICardTypeDAO {

    @Override
    protected String getTableName() {
        return "card_types";
    }

    @Override
    public CardType getCardTypeByName(String typeName) {
        return dao.getCardTypeByName(typeName);
    }

}
