package persistence;

import entities.Book;
import exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRepo {

    private DatabaseConnection databaseConnection;

    public BookRepo(DatabaseConnection databaseConnection){
        this.databaseConnection = databaseConnection;
    }

    public Book findBookByName(String titel) throws DatabaseException{

        String query = "SELECT * FROM bog WHERE titel = ?";

        try(Connection connection = databaseConnection.getConnection()){
            try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
                preparedStatement.setString(1, titel);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()){
                    int id = resultSet.getInt("bog_id");
                    int releaseYear = resultSet.getInt("udgivelsesaar");
                    return new Book(id, titel, null, releaseYear);
                }
                else{
                    return null;
                }
            }

        } catch (SQLException e) {
            throw new DatabaseException("Could not get borrower from the database", e);
        }


    }
}
