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
    private PreparedStatement getAccountPasswordStatement;
    private PreparedStatement getUserIDStatement;
    
    /**
     * Constructor creates the prepared statements.
     * @throws SQLException 
     */
    public UserAccountDAO() throws SQLException 
    {
        addAccountStatement = connection.prepareStatement("INSERT INTO Users"
                + "(first, last, email, password) "
                + "VALUES (?, ?, ?, ?)");
        deleteAccountStatement = connection.prepareStatement("DELETE FROM Users"
                + "WHERE id = ?");
        getAccountPasswordStatement = connection.prepareStatement("SELECT password FROM Users "
                + "WHERE email = ?");
        getUserIDStatement = connection.prepareStatement("SELECT id FROM Users WHERE email = ?");
        
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
        addAccountStatement.setString(4, user.getPassword());
        System.out.println(addAccountStatement);
        addAccountStatement.executeUpdate();
    }
    
    /**
     * Compares if the inputted password matches the password stored in the database.
     * @param email the email of the user trying to login.
     * @param password the password entered to be compared.
     * @return true only if password matches.
     * @throws SQLException 
     */
    public boolean compareAccountPassword(String email, String password) throws SQLException
    {
        getAccountPasswordStatement.setString(1, email);
        ResultSet results = getAccountPasswordStatement.executeQuery();
        
        // If no results returned, return false.
        if (!results.isBeforeFirst())
        {
            return false;
        }
        // Compare password with password stored in the database.
        else
        {
            String realPassword = null;
            while (results.next())
                realPassword = results.getString("password");
            return realPassword.equals(password);
        }
    }
    
    /**
     * Gets the user id using the unique email.
     * @param email the email of the user to find.
     * @return the id.
     * @throws SQLException 
     */
    public int getUserID(String email) throws SQLException
    {
        getUserIDStatement.setString(1, email);
        ResultSet results = getUserIDStatement.executeQuery();
        int id = 0;
        while (results.next()) id = results.getInt("id");
        return id;
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