package persistence;

import entities.Author;
import entities.Book;
import exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorRepo {

    private DatabaseConnection databaseConnection;

    public AuthorRepo(DatabaseConnection databaseConnection){
        this.databaseConnection = databaseConnection;
    }

    public Book findBookByAuthor(String navn) throws DatabaseException{

        String query = "SELECT bog.titel, bog.udgivelsesaar, forfatter.navn FROM forfatter JOIN bog ON bog.forfatter_id = forfatter.forfatter_id WHERE forfatter.navn = ?";

        try(Connection connection = databaseConnection.getConnection()){
            try(PreparedStatement preparedStatement = connection.prepareStatement(query)){

                preparedStatement.setString(1, navn);
                ResultSet resultSet = preparedStatement.executeQuery();

                if(resultSet.next()){
                    String titel = resultSet.getString("titel");
                    int releaseYear = resultSet.getInt("udgivelsesaar");
                    String authorName = resultSet.getString("navn");

                    Author author = new Author(authorName, null);
                    List<Author> authorList = new ArrayList<>();
                    authorList.add(author);
                    return new Book(titel, authorList, releaseYear);
                }
                else{
                    return null;
                }

            }

        } catch (SQLException e) {
            throw new DatabaseException(" Could not find book "+ e);
        }
    }
}
