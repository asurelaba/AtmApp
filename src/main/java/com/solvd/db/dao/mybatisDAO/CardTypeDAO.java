package com.solvd.db.dao.mybatisDAO;

import com.solvd.db.dao.interfacesDAO.ICardTypeDAO;
import com.solvd.db.model.CardType;
import com.solvd.util.MyBatisSqlFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class CardTypeDAO implements ICardTypeDAO {
    private final SqlSessionFactory sqlSessionFactory = MyBatisSqlFactory.getSqlSessionFactory();

    @Override
    public void insert(CardType cardType) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ICardTypeDAO icardTypeDAO = sqlSession.getMapper(ICardTypeDAO.class);
            icardTypeDAO.insert(cardType);
            sqlSession.commit();
        }
    }

    @Override
    public void update(CardType cardType) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ICardTypeDAO icardTypeDAO = sqlSession.getMapper(ICardTypeDAO.class);
            icardTypeDAO.update(cardType);
            sqlSession.commit();
        }
    }

    @Override
    public void delete(int cardTypeId) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ICardTypeDAO icardTypeDAO = sqlSession.getMapper(ICardTypeDAO.class);
            icardTypeDAO.delete(cardTypeId);
            sqlSession.commit();
        }
    }

    @Override
    public CardType getById(int cardTypeId) {
        CardType cardType;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ICardTypeDAO icardTypeDAO = sqlSession.getMapper(ICardTypeDAO.class);
            cardType = icardTypeDAO.getById(cardTypeId);
        }
        return cardType;
    }

    @Override
    public List<CardType> getAll() {
        List<CardType> cardTypes;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ICardTypeDAO icardTypeDAO = sqlSession.getMapper(ICardTypeDAO.class);
            cardTypes = icardTypeDAO.getAll();
        }
        return cardTypes;
    }
}