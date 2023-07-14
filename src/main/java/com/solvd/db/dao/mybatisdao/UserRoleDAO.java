package com.solvd.db.dao.mybatisdao;


import com.solvd.db.dao.factory.MyBatisSqlFactory;
import com.solvd.db.dao.idao.IUserRoleDAO;
import com.solvd.db.model.UserRole;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class UserRoleDAO implements IUserRoleDAO {

    private final SqlSessionFactory sqlSessionFactory = MyBatisSqlFactory.getSqlSessionFactory();

    @Override
    public void insert(UserRole userRole) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IUserRoleDAO iuserRoleDAO = sqlSession.getMapper(IUserRoleDAO.class);
            iuserRoleDAO.insert(userRole);
            sqlSession.commit();
        }
    }

    @Override
    public void update(UserRole userRole) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IUserRoleDAO iuserRoleDAO = sqlSession.getMapper(IUserRoleDAO.class);
            iuserRoleDAO.update(userRole);
            sqlSession.commit();
        }
    }

    @Override
    public void delete(int userRoleId) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IUserRoleDAO iuserRoleDAO = sqlSession.getMapper(IUserRoleDAO.class);
            iuserRoleDAO.delete(userRoleId);
            sqlSession.commit();
        }
    }

    @Override
    public UserRole getById(int userRoleId) {
        UserRole userRole;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IUserRoleDAO iuserRoleDAO = sqlSession.getMapper(IUserRoleDAO.class);
            userRole = iuserRoleDAO.getById(userRoleId);
        }
        return userRole;
    }

    @Override
    public List<UserRole> getAll() {
        List<UserRole> userRoles;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IUserRoleDAO iuserRoleDAO = sqlSession.getMapper(IUserRoleDAO.class);
            userRoles = iuserRoleDAO.getAll();
        }
        return userRoles;
    }

    @Override
    public UserRole getUserRoleByRoleName(String roleName) {
        UserRole userRole;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IUserRoleDAO iuserRoleDAO = sqlSession.getMapper(IUserRoleDAO.class);
            userRole = iuserRoleDAO.getUserRoleByRoleName(roleName);
        }
        return userRole;
    }

}
