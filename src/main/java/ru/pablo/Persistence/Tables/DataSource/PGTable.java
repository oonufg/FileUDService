package ru.pablo.Persistence.Tables.DataSource;

import java.sql.DriverManager;
import java.sql.SQLException;

public class PGTable extends AbstractTable{
    @Override
    protected void initConnection(String url, String username, String password) {
        try{
            if(connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(url, username, password);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    protected void initJDBCDriver() {
        try {
            Class.forName("org.postgresql.Driver");
        }catch (ClassNotFoundException exception){
            System.out.println(exception.getMessage());
        }
    }
}
