package com.solvd.db.dao.idao;

import com.solvd.db.model.Card;
import java.util.List;

/* The operations to get by id, get all from the DB, insert, update,
and delete are covered by the IBaseDao interface*/
public interface ICardDAO extends IBaseDAO<Card> {

    Card getCardByCardNumber(long cardNumber);

    List<Card> getCardsByUserId(int userId); // Get all users' cards

    List<Card> getCardsByCardStatus(
        String statusName); // Status: active(Unlocked) / inactive(Locked)

    List<Card> getCardsByCardType(String typeName); // TypeName: Admin/Client

}