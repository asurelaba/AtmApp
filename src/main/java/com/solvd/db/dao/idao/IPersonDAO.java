package com.solvd.db.dao.idao;

import com.solvd.db.model.Person;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/* The operations to get by id, get all from the DB, insert, update,
and delete are covered by the IBaseDao interface*/
public interface IPersonDAO extends IBaseDAO<Person> {

    List<Person> getPersonByName(@Param("firstName") String firstName, @Param("lastName") String lastName);
}