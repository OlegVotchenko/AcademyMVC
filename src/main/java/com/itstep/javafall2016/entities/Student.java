package com.itstep.javafall2016.entities;

import java.io.Serializable;
import java.util.Date;

public class Student implements Serializable {
    private long _id;
    private Passport _passport;
    private Date _birthday;

    public Student(){};

    public Student(Passport passport, Date birthday) {
        _passport = passport;
        _birthday = birthday;
    }

    public long getId() {
        return _id;
    }

    public void setId(long id) {
        _id = id;
    }

    public Passport getPassport() {
        return _passport;
    }

    public void setPassport(Passport passport) {
        _passport = passport;
    }

    public Date getBirthday() {
        return _birthday;
    }

    public void setBirthday(Date birthday) {
        _birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (_id != student._id) return false;
        if (!_passport.equals(student._passport)) return false;
        return _birthday.equals(student._birthday);
    }

    @Override
    public int hashCode() {
        int result = (int) (_id ^ (_id >>> 32));
        result = 31 * result + _passport.hashCode();
        result = 31 * result + _birthday.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Student{" +
                "_id=" + _id +
                ", _passport=" + _passport +
                ", _birthday=" + _birthday +
                '}';
    }
}
