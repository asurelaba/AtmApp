package com.solvd.db.dao.idao;

import com.solvd.db.model.CardType;

/* The operations to get by id, get all from the DB, insert, update,
and delete are covered by the IBaseDao interface*/
public interface ICardTypeDAO extends IBaseDAO<CardType> {

    CardType getCardTypeByName(String typeName);

}
