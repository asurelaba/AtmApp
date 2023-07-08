package com.solvd.services;

import com.solvd.db.dao.factory.DAONotFoundException;
import com.solvd.db.dao.factory.MyBatisSqlFactory;
import com.solvd.db.dao.idao.IBaseDAO;

import java.util.List;

public abstract class EntityService<T, D extends IBaseDAO<T>> {

    protected abstract String getTableName();

    protected D dao;

    {
        try {
            dao = (D) new MyBatisSqlFactory().getDAO(getTableName());
        } catch (DAONotFoundException e) {
            //TODO change to log
            e.printStackTrace();
        }
    }

    public void insert(T t) {
        dao.insert(t);
    }

    public void update(T t) {
        dao.update(t);
    }

    public void delete(int id) {
        dao.delete(id);
    }

    public T getById(int id) {
        return dao.getById(id);
    }

    public List<T> getAll() {
        return dao.getAll();
    }

}
