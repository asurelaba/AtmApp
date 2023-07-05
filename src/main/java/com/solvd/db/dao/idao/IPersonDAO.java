package com.solvd.db.dao.idao;

import com.solvd.db.model.Person;

import java.util.List;

/* The operations to get by id, get all from the DB, insert, update,
and delete are covered by the IBaseDao interface*/
public interface IPersonDAO extends IBaseDAO<Person> {

    List<Person> getPersonByName(String FirstName, String Lastname);
}