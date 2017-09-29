package com.itstep.javafall2016.dto;

import java.util.Date;

public class StudentDTO {

    private long passport;
    private Date birthday;

    public StudentDTO(long passport, Date birthday) {
        this.passport = passport;
        this.birthday = birthday;
    }

    public StudentDTO() {
    }

    public long getPassport() {
        return passport;
    }

    public void setPassport(long passport) {
        this.passport = passport;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentDTO that = (StudentDTO) o;

        if (passport != that.passport) return false;
        return birthday != null ? birthday.equals(that.birthday) : that.birthday == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (passport ^ (passport >>> 32));
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "StudentDTO{" +
                "passport=" + passport +
                ", birthday=" + birthday +
                '}';
    }
}
