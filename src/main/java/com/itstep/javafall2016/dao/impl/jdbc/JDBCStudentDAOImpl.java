package com.itstep.javafall2016.dao.impl.jdbc;

import com.itstep.javafall2016.dao.StudentDAO;
import com.itstep.javafall2016.entities.Passport;
import com.itstep.javafall2016.entities.Student;
import com.itstep.javafall2016.enums.Result;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.*;
import java.util.Date;

@Repository
public class JDBCStudentDAOImpl extends JDBCAbstractDAOImpl implements StudentDAO {

    @Override
    @SuppressWarnings("unchecked")
    public List<Student> findAll() {
        return (List<Student>) super.findAll();
    }

    @Override
    public List<Student> findAll(int pageSize, int pageNumber) {
        return null;
    }

    @Override
    public void save(Student entity) {
        Connection connection = getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO " + DBSchema.STUDENTS.name () +
                    "( " + DBSchema.STUDENTS.ID_PASSPORT + ", " + DBSchema.STUDENTS.DATE + ")" +
                    "VALUES(?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setLong (1, entity.getPassport().getId());
            statement.setDate (2, new java.sql.Date(entity.getBirthday().getTime ()) );
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            generatedKeys.next();
            entity.setId(generatedKeys.getLong(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean update(Student entity) {
        return false;
    }

    @Override
    public Result delete(long id) {
        Connection connection = getConnection ();
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM " +
            DBSchema.STUDENTS.name () +
            " WHERE " + DBSchema.STUDENTS.ID + " =?");
            ps.setLong (1,id);
            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                return Result.SUCCESS;
            }
            return Result.NOT_FOUND;
        } catch (SQLException e) {
            e.printStackTrace ();
        }
        return Result.ERROR;
    }

    @Override
    public Optional<Student> findById(long id) {
        return null;
    }

    @Override
    protected List<Student> processResultSet(ResultSet resultSet) throws SQLException {
        List<Student> result = new ArrayList<>();
        while (resultSet.next()) {
            long id = resultSet.getLong(DBSchema.STUDENTS.ID);
            long idPassport = resultSet.getLong(DBSchema.STUDENTS.ID_PASSPORT);
            Date date = resultSet.getDate(DBSchema.STUDENTS.DATE);
            Student s = new Student();
            s.setId(id);
            Passport p = new Passport();
            p.setId(idPassport);
            s.setPassport(p);
            s.setBirthday(date);
            result.add(s);
        }

        return result;
    }

    @Override
    protected String getTableName() {
        return DBSchema.STUDENTS.name();
    }

    @Override
    public List<Long> findAllPassportIds() {
        Connection connection = getConnection();
        String query = "SELECT "+ DBSchema.STUDENTS.ID_PASSPORT +" FROM " + getTableName();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            List<Long> result = new ArrayList<>();
            while (resultSet.next()) {
                long id = resultSet.getLong(1);
                result.add(id);
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

}
