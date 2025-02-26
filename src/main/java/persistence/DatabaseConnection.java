package persistence;

import exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection
{
    private Connection connection;
    private final String USERNAME;
    private final String PASSWORD;
    private final String URL;

    public DatabaseConnection(String username, String password, String url) throws DatabaseException
    {
        this.USERNAME = username;
        this.PASSWORD = password;
        this.URL = url;
        try
        {
            Class.forName("org.postgresql.Driver");
        }
        catch (ClassNotFoundException e)
        {
            throw new DatabaseException("Could not find the database driver");
        }
    }

    public Connection getConnection() throws SQLException
    {
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        return connection;
    }
}

