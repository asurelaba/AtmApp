package com.solvd.db.dao.factory;

import com.solvd.db.dao.idao.IBaseDAO;
import com.solvd.db.dao.mybatisdao.*;
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
            case "accounts" -> new AccountDAO();
            case "cards" -> new CardDAO();
            case "cardtypes" -> new CardTypeDAO();
            case "events" -> new EventDAO();
            case "eventtypes" -> new EventTypeDAO();
            case "persons" -> new PersonDAO();
            case "transactions" -> new TransactionDAO();
            case "users" -> new UserDAO();
            case "userroles" -> new UserRoleDAO();
            default -> throw new DAONotFoundException(tableName + "DAO not found");
        };
    }
}
