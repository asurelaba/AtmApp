package com.solvd.db.dao.idao;

import java.util.List;

public interface IBaseDAO<T> {

    // CRUD Create
    void insert(T t);

    // CRUD Update
    void update(T t);

    // CRUD Delete
    void delete(int id);

    // CRUD Read
    T getById(int id);

    // Get All
    List<T> getAll();

}