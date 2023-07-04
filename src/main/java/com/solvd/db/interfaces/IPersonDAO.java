package com.solvd.db.interfaces;

import com.solvd.db.model.Person;

public interface IPersonDAO extends IBaseDAO<Person> {
    /* The operations to get by id, get all from the DB, insert, update,
and delete are covered by the IBaseDao interface*/

    Person getPersonByUserId(int userId);

    Person getPersonByName(String personName);
}