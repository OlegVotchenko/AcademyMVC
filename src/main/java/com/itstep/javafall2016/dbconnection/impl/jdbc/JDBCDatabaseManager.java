package com.itstep.javafall2016.dbconnection.impl.jdbc;

import com.itstep.javafall2016.dbconnection.DatabaseManager;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by strazhko on 18.07.2017.
 */
@Component
public class JDBCDatabaseManager implements DatabaseManager {

    private Connection _connection;

    @Override
    public Object getDatabase() {
        if (_connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                _connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/academy?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "root");
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        return _connection;
    }

    @Override
    public void disconnect() {
        if (_connection != null) {
            try {
                _connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
