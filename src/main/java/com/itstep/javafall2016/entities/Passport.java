package com.itstep.javafall2016.entities;


import java.io.Serializable;

public class Passport implements Serializable {
    private long _id;
    private String _firstName;
    private String _lastName;

    public Passport() {
    }

    public Passport(String _firstName, String _lastName) {
        this._firstName = _firstName;
        this._lastName = _lastName;
    }

    public long getId() {
        return _id;
    }

    public void setId(long _id) {
        this._id = _id;
    }

    public String getFirstName() {
        return _firstName;
    }

    public void setFirstName(String _firstName) {
        this._firstName = _firstName;
    }

    public String getLastName() {
        return _lastName;
    }

    public void setLastName(String _lastName) {
        this._lastName = _lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Passport passport = (Passport) o;

        if (_id != passport._id) return false;
        if (_firstName != null ? !_firstName.equals(passport._firstName) : passport._firstName != null) return false;
        return _lastName != null ? _lastName.equals(passport._lastName) : passport._lastName == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (_id ^ (_id >>> 32));
        result = 31 * result + (_firstName != null ? _firstName.hashCode() : 0);
        result = 31 * result + (_lastName != null ? _lastName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Passport{" +
                "_id=" + _id +
                ", _firstName='" + _firstName + '\'' +
                ", _lastName='" + _lastName + '\'' +
                '}';
    }
}
