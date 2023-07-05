package com.solvd.db.dao.factory;

import com.solvd.db.dao.idao.IBaseDAO;
import com.solvd.db.dao.mybatisDAO.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class MyBatisSqlFactory {

    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

    public IBaseDAO getDAO(String tableName) throws DAONotFoundException {
        return switch (tableName.toLowerCase()) {
            case "account" -> new AccountDAO();
            case "card" -> new CardDAO();
            case "cardtype" -> new CardTypeDAO();
            case "event" -> new EventDAO();
            case "eventtype" -> new EventTypeDAO();
            case "person" -> new PersonDAO();
            case "transaction" -> new TransactionDAO();
            case "user" -> new UserDAO();
            case "userrole" -> new UserRoleDAO();
            default -> throw new DAONotFoundException(tableName + "DAO not found");
        };
    }
}
