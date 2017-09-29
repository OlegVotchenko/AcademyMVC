package com.itstep.javafall2016.dao.impl.jdbc;

import com.itstep.javafall2016.dao.PassportDAO;
import com.itstep.javafall2016.entities.Passport;
import com.itstep.javafall2016.enums.Result;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class JDBCPassportDAOImpl extends JDBCAbstractDAOImpl implements PassportDAO {

    @Override
    protected String getTableName() {
        return DBSchema.PASSPORTS.name();
    }

    protected List<Passport> processResultSet(ResultSet resultSet) throws SQLException {
        List<Passport> result = new ArrayList<>();
        while (resultSet.next()) {
            long id = resultSet.getLong(DBSchema.PASSPORTS.ID);
            String fn = resultSet.getString(DBSchema.PASSPORTS.FIRST_NAME);
            String ln = resultSet.getString(DBSchema.PASSPORTS.LAST_NAME);
            Passport p = new Passport(fn, ln);
            p.setId(id);
            result.add(p);
        }

        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Passport> findAll() {
        return (List<Passport>) super.findAll();
    }

    @Override
    public List<Passport> findAll(int pageSize, int pageNumber) {
        return (List<Passport>) super.findAllPages (pageSize,pageNumber);
    }

    @Override
    public void save(Passport entity) {
        Connection connection = getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO " + DBSchema.PASSPORTS.name() +
                    "( " + DBSchema.PASSPORTS.FIRST_NAME + ", " + DBSchema.PASSPORTS.LAST_NAME + ")" +
                    "VALUES(?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, entity.getFirstName());
            statement.setString(2, entity.getLastName());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            generatedKeys.next();
            entity.setId(generatedKeys.getLong(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean update(Passport entity) {
        return false;
    }

    @Override
    public Result delete(long id) {
        Connection connection = getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM " + DBSchema.PASSPORTS.name()
                    + " WHERE " + DBSchema.PASSPORTS.ID + " = ?");
            ps.setLong(1, id);
            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                return Result.SUCCESS;
            }
            return Result.NOT_FOUND;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Result.ERROR;
    }

    @Override
    public Optional<Passport> findById(long id) {
        Connection connection = getConnection();
        String query = String.format("SELECT * FROM %s WHERE %s = %d",
                DBSchema.PASSPORTS.name(),
                DBSchema.PASSPORTS.ID,
                id);
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            List<Passport> passports = processResultSet(resultSet);
            if (passports.isEmpty()) {
                return Optional.empty();
            }
            return Optional.of(passports.get(0));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Passport> findAllExcept(List<Long> ids) {
        return (List<Passport>) findEntitiesByQuery(String.format("SELECT * FROM %s WHERE %s NOT IN ( %s )",
                getTableName(), DBSchema.PASSPORTS.ID, convertIdListToString(ids)));
    }

    private String convertIdListToString(List<Long> ids) {
        StringBuilder sb = new StringBuilder("");
        ids.forEach(id -> sb.append(id).append(", "));
        return sb.substring(0, sb.length() - 2);
    }
}
