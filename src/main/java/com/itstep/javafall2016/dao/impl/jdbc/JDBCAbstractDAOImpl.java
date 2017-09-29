package com.itstep.javafall2016.dao.impl.jdbc;

import com.itstep.javafall2016.dbconnection.DatabaseManager;
import com.itstep.javafall2016.dbconnection.impl.jdbc.JDBCDatabaseManager;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public abstract class JDBCAbstractDAOImpl {

    @Resource(type=JDBCDatabaseManager.class)
    private DatabaseManager _databaseManager;

    public List<?> findAll() {
        return findEntitiesByQuery("SELECT * FROM " + getTableName());
    }

    public List<?> findAllPages(int pageSize, int pageNumber){
        return findEntitiesByQuery ("SELECT * FROM " + getTableName () +" LIMIT " + pageSize + " OFFSET "+ pageNumber);
    }

    protected List<?> findEntitiesByQuery(String query) {
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            return processResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    protected abstract List<?> processResultSet(ResultSet resultSet) throws SQLException;

    protected abstract String getTableName();

    protected Connection getConnection() {
        return (Connection) _databaseManager.getDatabase();
    }
}
