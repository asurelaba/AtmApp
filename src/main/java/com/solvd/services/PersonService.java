package com.solvd.services;

import com.solvd.db.dao.idao.IPersonDAO;
import com.solvd.db.model.Person;
import java.util.List;

public class PersonService extends EntityService<Person, IPersonDAO> implements IPersonDAO {

    @Override
    protected String getTableName() {
        return "person";
    }

    @Override
    public List<Person> getPersonByName(String firstName, String lastName) {
        return dao.getPersonByName(firstName, lastName);
    }

}
