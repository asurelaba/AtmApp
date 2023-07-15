package com.solvd.db.dao.factory;

import com.solvd.db.dao.idao.IBaseDAO;
import com.solvd.db.dao.mybatisdao.AccountDAO;
import com.solvd.db.dao.mybatisdao.CardDAO;
import com.solvd.db.dao.mybatisdao.CardTypeDAO;
import com.solvd.db.dao.mybatisdao.EventDAO;
import com.solvd.db.dao.mybatisdao.EventTypeDAO;
import com.solvd.db.dao.mybatisdao.PersonDAO;
import com.solvd.db.dao.mybatisdao.TransactionDAO;
import com.solvd.db.dao.mybatisdao.UserDAO;
import com.solvd.db.dao.mybatisdao.UserRoleDAO;
import java.io.IOException;
import java.io.Reader;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisSqlFactory {

    private static SqlSessionFactory sqlSessionFactory;
    private static final String mysql_mybatis_config = "mybatis-config.xml";
    private static final String sqlite_mybatis_config = "mybatis-config-sqlite.xml";

    static {
        try {
            Reader reader = Resources.getResourceAsReader(sqlite_mybatis_config);
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
            case "card_types" -> new CardTypeDAO();
            case "events" -> new EventDAO();
            case "event_types" -> new EventTypeDAO();
            case "persons" -> new PersonDAO();
            case "transactions" -> new TransactionDAO();
            case "users" -> new UserDAO();
            case "user_roles" -> new UserRoleDAO();
            default -> throw new DAONotFoundException(tableName + "DAO not found");
        };
    }

}
