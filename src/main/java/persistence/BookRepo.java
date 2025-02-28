package persistence;

import dto.BooksAndAuthorsDTO;
import entities.Book;
import exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookRepo {

    private DatabaseConnection databaseConnection;

    public BookRepo(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public Book findBookByName(String titel) throws DatabaseException {

        String query = "SELECT * FROM bog WHERE titel = ?";

        try (Connection connection = databaseConnection.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, titel);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    int id = resultSet.getInt("bog_id");
                    int releaseYear = resultSet.getInt("udgivelsesaar");
                    return new Book(id, titel, null, releaseYear);
                } else {
                    return null;
                }
            }

        } catch (SQLException e) {
            throw new DatabaseException("Could not get borrower from the database", e);
        }
    }


    public List<BooksAndAuthorsDTO> getAllBooksAndAuthors() throws DatabaseException {

        String query = "SELECT titel, forfatter.navn, bog.udgivelsesaar\n" +
                "FROM bog\n" +
                "JOIN forfatter ON forfatter.forfatter_id = bog.forfatter_id";

        List<BooksAndAuthorsDTO> books = new ArrayList<>();

        try (Connection connection = databaseConnection.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {


                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    String titel = resultSet.getString("titel");
                    String name = resultSet.getString("navn");
                    int releaseYear = resultSet.getInt("udgivelsesaar");

                    books.add(new BooksAndAuthorsDTO(name, titel, releaseYear));
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("could not get all books with authors", e);
        }
        return books;
    }

    public boolean updateBookTitle(String currentTitle, String newTitle) throws DatabaseException {
        boolean result = false;
        String sql = "UPDATE bog SET titel = ? WHERE titel = ?";

        try (Connection connection = databaseConnection.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                preparedStatement.setString(1, newTitle);   // Ny titel
                preparedStatement.setString(2, currentTitle); // Gammel titel

                // Udfør opdateringen
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) { // Hvis mindst én række blev opdateret
                    result = true;
                    System.out.println("Updated successfully");
                } else {
                    System.out.println("No book found with the given title.");
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Could not update title", e);
        }
        return result;
    }


}
