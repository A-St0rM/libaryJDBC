package persistence;

import DTO.BorrowerAndAddressDTO;
import entities.Borrower;
import exceptions.DatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BorrowerRepo {

    private DatabaseConnection databaseConnection;



    public BorrowerRepo(DatabaseConnection databaseConnection){
        this.databaseConnection = databaseConnection;
    }

    public Borrower findBorrowerById(int id) throws DatabaseException{

        String sql = "SELECT navn, adresse, postnr FROM laaner WHERE laaner_id = ?";

        try (Connection connection = databaseConnection.getConnection())
        {
            try (PreparedStatement prepareStatement = connection.prepareStatement(sql))
            {
                prepareStatement.setInt(1, id);
                ResultSet resultSet = prepareStatement.executeQuery();

                if (resultSet.next())
                {
                    String name = resultSet.getString("navn");
                    String address = resultSet.getString("adresse");
                    int zip = resultSet.getInt("postnr");
                    return new Borrower(id, name, address + ", " + zip, null);

                } else
                {
                    return null;
                }
            }
        }
        catch (SQLException e)
        {
            throw new DatabaseException("Could not get borrower from the database", e);
        }
    }

    public List<BorrowerAndAddressDTO> getAllBorrowerWithAddress() throws DatabaseException{

        String query = "SELECT navn, postnummer.postnr, postnummer.by\n" +
                "FROM laaner\n" +
                "JOIN postnummer ON postnummer.postnr = laaner.postnr";

        List<BorrowerAndAddressDTO> borrowerAndAddressDTOList = new ArrayList<>();

        try(Connection connection = databaseConnection.getConnection())
        {
            try(PreparedStatement preparedStatement = connection.prepareStatement(query))
            {
                ResultSet resultSet = preparedStatement.executeQuery();

                while(resultSet.next()){

                    String name = resultSet.getString("navn");
                    int zip = resultSet.getInt("postnr");
                    String city = resultSet.getString("by");
                    borrowerAndAddressDTOList.add(new BorrowerAndAddressDTO(name, zip, city));
                }
            }
        }
        catch (SQLException e){
            throw new DatabaseException("could not get all borrowers with address", e);
        }
        return borrowerAndAddressDTOList;
    }

    public Borrower InsertNewBorrower(Borrower borrower) throws DatabaseException{
        String query = "INSERT INTO laaner (navn, adresse, postnr)\n" +
                "VALUES(?,?,?)";

        boolean result = false;
        int newId = 0;
        try(Connection connection = databaseConnection.getConnection()){
            try(PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
                preparedStatement.setString(1, borrower.getName());
                preparedStatement.setString(2, borrower.getAddress());
                preparedStatement.setInt(3, borrower.getZip());

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected == 1) {
                    result = true;
                }
                ResultSet idResultset = preparedStatement.getGeneratedKeys();
                if (idResultset.next()) {
                    newId = idResultset.getInt(1);
                    borrower.setBorrower_id(newId);
                    System.out.println("Insert successful");
                } else {
                    borrower = null;
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Could not insert new Borrower", e);
        }
        return borrower;
    }
}
