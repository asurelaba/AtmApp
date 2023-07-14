package com.solvd.db.dao.mybatisdao;

import com.solvd.db.dao.factory.MyBatisSqlFactory;
import com.solvd.db.dao.idao.ICardDAO;
import com.solvd.db.model.Card;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class CardDAO implements ICardDAO {

    private final SqlSessionFactory sqlSessionFactory = MyBatisSqlFactory.getSqlSessionFactory();

    @Override
    public void insert(Card card) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ICardDAO icardDAO = sqlSession.getMapper(ICardDAO.class);
            icardDAO.insert(card);
            sqlSession.commit();
        }
    }

    @Override
    public void update(Card card) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ICardDAO icardDAO = sqlSession.getMapper(ICardDAO.class);
            icardDAO.update(card);
            sqlSession.commit();
        }
    }

    @Override
    public void delete(int cardId) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ICardDAO icardDAO = sqlSession.getMapper(ICardDAO.class);
            icardDAO.delete(cardId);
            sqlSession.commit();
        }
    }

    @Override
    public Card getById(int cardId) {
        Card card;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ICardDAO icardDAO = sqlSession.getMapper(ICardDAO.class);
            card = icardDAO.getById(cardId);
        }
        return card;
    }

    @Override
    public List<Card> getAll() {
        List<Card> cards;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ICardDAO icardDAO = sqlSession.getMapper(ICardDAO.class);
            cards = icardDAO.getAll();
        }
        return cards;
    }

    @Override
    public Card getCardByCardNumber(long cardNumber) {
        Card card;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ICardDAO icardDAO = sqlSession.getMapper(ICardDAO.class);
            card = icardDAO.getCardByCardNumber(cardNumber);
        }
        return card;
    }

    @Override
    public List<Card> getCardsByUserId(int userid) {
        List<Card> cards;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ICardDAO icardDAO = sqlSession.getMapper(ICardDAO.class);
            cards = icardDAO.getCardsByUserId(userid);
        }
        return cards;
    }

    @Override
    public List<Card> getCardsByCardStatus(String statusName) {
        List<Card> cards;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ICardDAO icardDAO = sqlSession.getMapper(ICardDAO.class);
            cards = icardDAO.getCardsByCardStatus(statusName);
        }
        return cards;
    }

    @Override
    public List<Card> getCardsByCardType(String typeName) {
        List<Card> cards;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ICardDAO icardDAO = sqlSession.getMapper(ICardDAO.class);
            cards = icardDAO.getCardsByCardType(typeName);
        }
        return cards;
    }

}