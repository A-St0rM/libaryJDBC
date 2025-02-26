package persistence;

import entities.Borrower;
import exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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


}
