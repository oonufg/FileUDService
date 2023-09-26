package ru.pablo.Persistence.Tables;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import ru.pablo.Persistence.Tables.DataSource.PGTable;
import org.springframework.beans.factory.annotation.Value;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Component
public class FilesTable extends PGTable {

    @Value("${pgsql.url}")
    private String url;
    @Value("${pgsql.username}")
    private String username;
    @Value("${pgsql.password}")
    private String password;


    @PostConstruct
    private void initConnection(){
        setupConnection(url,username,password);
    }

    public Map<String,Object> getFileInfoByUID(String uid){
        Map<String, Object> result = new HashMap<>();
        try{
            PreparedStatement query = getFileByUID(uid);
            ResultSet queryResult = executeQuery(query);
            queryResult.next();
            result.putAll(resultSetToMap(queryResult));
        }catch(SQLException e){
            System.out.println("->" + e.getMessage());
        }
        return result;
    }

    public void saveFileInfo(String uit, String title, String extension){
        try{
            PreparedStatement query = getSaveFileInfoStatement(uit, title, extension);
            executeQuery(query);
        }catch(SQLException e){
            System.out.println("->" +e.getMessage());
        }
    }

    private PreparedStatement getSaveFileInfoStatement(String uit, String title, String extension) throws SQLException {
        String query = "INSERT INTO files VALUES(?, ?, ?)";
        PreparedStatement statement = getStatement(query);
        statement.setString(1, uit);
        statement.setString(2, title);
        statement.setString(3, extension);
        return statement;
    }

    private PreparedStatement getFileByUID(String uid) throws SQLException{
        String query = "SELECT * FROM files WHERE uid = ?";
        PreparedStatement statement = getStatement(query);
        statement.setString(1, uid);
        return statement;
    }

}
