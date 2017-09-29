package com.itstep.javafall2016.dao;

import com.itstep.javafall2016.entities.Student;

import java.util.List;

public interface StudentDAO extends GlobalDAO<Student> {
    List<Long> findAllPassportIds();
}
