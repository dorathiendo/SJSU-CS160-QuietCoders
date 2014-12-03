package DAO;

import Model.*;
import java.sql.PreparedStatement;
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
    private PreparedStatement addAccountStatement;
    private PreparedStatement updateAccountStatement;
    private PreparedStatement deleteAccountStatement;
    
    /**
     * Constructor creates the prepared statements.
     * @throws SQLException 
     */
    public UserAccountDAO() throws SQLException 
    {
        addAccountStatement = connection.prepareStatement("INSERT INTO Users"
            + "(first, last, email, password) "
            + "VALUES ('?', '?', '?', ?)");
        deleteAccountStatement = connection.prepareStatement("DELETE FROM Users"
            + "WHERE id = ?");
    }
    
    /**
     * Adds a new account.
     * @param user the new account to create.
     * @throws SQLException 
     */
    public void addNewAccount(User user) throws SQLException
    {

        addAccountStatement.setString(1, user.getFirst());
        addAccountStatement.setString(2, user.getLast());
        addAccountStatement.setString(3, user.getEmail());
        addAccountStatement.setInt(4, user.getPassword());
        addAccountStatement.executeUpdate();
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
        updateAccountStatement = connection.prepareStatement("UPDATE Users"
                + " SET " + attribute + "=" + value 
                + " WHERE id = " + id);
        updateAccountStatement.executeUpdate();
    }
    
    /**
     * Deletes a user account.
     * @param id the id of the user to delete.
     * @throws java.sql.SQLException
     */
    public void deleteAccount(int id) throws SQLException
    {
        deleteAccountStatement.setInt(1, id);
        deleteAccountStatement.executeUpdate();
    }
}