package DAO;

import Model.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Data Access Object for manipulating accounts.
 * 
 * @author Kevin Tan
 * @version 1.0
 * @since 11/16/2014
 */
public class UserAccountDAO extends DAOFactory 
{
    /**
     * Adds a new account.
     * @param user the new account to create.
     * @throws SQLException 
     */
    public void addNewAccount(User user) throws SQLException
    {
        preparedStatement = connection.prepareStatement("INSERT INTO Users"
                + "(first, last, email, password) "
                + "VALUES ('?', '?', '?', ?)");
        preparedStatement.setString(1, user.getFirst());
        preparedStatement.setString(2, user.getLast());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setInt(4, user.getPassword());
        preparedStatement.executeUpdate();
    }
    
    /**
     * Edit an attribute of a user.
     * *** Content parameter needs quotes if a string. ***
     * @param id the id of the user to edit.
     * @param attribute the attribute being altered.
     * @param value the new content to replace the old content.
     * @throws java.sql.SQLException
     */
    public void updateAccount(int id, String attribute, String value) throws SQLException
    {
        preparedStatement = connection.prepareStatement("UPDATE Users"
                + " SET " + attribute + "=" + value 
                + " WHERE id = " + id);
        preparedStatement.executeUpdate();
    }
    
    /**
     * Deletes a user account.
     * @param id the id of the user to delete.
     * @throws java.sql.SQLException
     */
    public void deleteAccount(int id) throws SQLException
    {
        preparedStatement = connection.prepareStatement("DELETE FROM Users"
                + "WHERE id = " + id);
        preparedStatement.executeUpdate();
    }
}