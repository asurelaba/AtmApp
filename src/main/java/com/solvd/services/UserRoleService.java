package com.solvd.services;

import com.solvd.db.dao.idao.IUserRoleDAO;
import com.solvd.db.model.UserRole;

public class UserRoleService extends EntityService<UserRole, IUserRoleDAO> implements IUserRoleDAO {

    @Override
    protected String getTableName() {
        return "userroles";
    }

    @Override
    public UserRole getUserRoleByRoleName(String roleName) {
        return dao.getUserRoleByRoleName(roleName);
    }

}
