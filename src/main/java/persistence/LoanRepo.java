package persistence;

import dto.LoanWithBooksAndAuthorsDTO;
import exceptions.DatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoanRepo {

    DatabaseConnection databaseConnection;

    public LoanRepo(DatabaseConnection databaseConnection){
        this.databaseConnection = databaseConnection;
    }


    public List<LoanWithBooksAndAuthorsDTO> GetAllLoanersWithBooktitelAndAuthors() throws DatabaseException{

        List<LoanWithBooksAndAuthorsDTO> loanBookAndAuthorsList = new ArrayList<>();

        String query = "SELECT l.navn, b.titel, udgivelsesaar, f.navn\n" +
                "FROM laaner l\n" +
                "JOIN udlaan u ON l.laaner_id = u.laaner_id\n" +
                "JOIN bog b ON b.bog_id = u.bog_id\n" +
                "JOIN forfatter f ON f.forfatter_id = b.forfatter_id";

        try(Connection connection = databaseConnection.getConnection()){
            try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
                ResultSet resultSet = preparedStatement.executeQuery();

                while(resultSet.next()){

                    String name = resultSet.getString("navn");
                    String titel = resultSet.getString("titel");
                    int releaseYear = resultSet.getInt("udgivelsesaar");
                    String authorName = resultSet.getString("navn");

                    loanBookAndAuthorsList.add(new LoanWithBooksAndAuthorsDTO(titel, releaseYear, name, authorName));
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("could not get all loaners", e);
        }
        return loanBookAndAuthorsList;
    }

//    public Loan Insertloaner(Loan loan, Book book) throws DatabaseException{
//        String query = "INSERT INTO udlaan(bog_id, laaner_id, dato)\n" +
//                "VALUES(?, ?, ?)";
//
//        boolean result = false;
//        int newId = 0;
//        try(Connection connection = databaseConnection.getConnection()){
//            try(PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
//                preparedStatement.setString(1, borrower.getName());
//                preparedStatement.setString(2, borrower.getAddress());
//                preparedStatement.setInt(3, borrower.getZip());
//
//                int rowsAffected = preparedStatement.executeUpdate();
//                if (rowsAffected == 1) {
//                    result = true;
//                }
//                ResultSet idResultset = preparedStatement.getGeneratedKeys();
//                if (idResultset.next()) {
//                    newId = idResultset.getInt(1);
//                    borrower.setBorrower_id(newId);
//                    System.out.println("Insert successful");
//                } else {
//                    borrower = null;
//                }
//            }
//        } catch (SQLException e) {
//            throw new DatabaseException("Could not insert new Borrower", e);
//        }
//        return borrower;
//    }


    public boolean deleteLoaner(int loaner_id, int book_id) throws DatabaseException{
        boolean result = false;
        String sql = "DELETE FROM udlaan WHERE udlaan.laaner_id = ? AND udlaan.bog_id = ?";
        try (Connection connection = databaseConnection.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, book_id);
                ps.setInt(2, loaner_id);
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    result = true;
                    System.out.println("Deleted successfully");
                }
            } catch (SQLException throwables) {
                // TODO: Make own throwable exception and let it bubble upwards
                throwables.printStackTrace();
            }
        } catch (SQLException throwables) {
            // TODO: Make own throwable exception and let it bubble upwards
            throwables.printStackTrace();
        }
        return result;
    }


}
