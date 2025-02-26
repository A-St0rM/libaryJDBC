package persistence;

import DTO.BorrowerAndAddressDTO;
import entities.Borrower;
import exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
}
