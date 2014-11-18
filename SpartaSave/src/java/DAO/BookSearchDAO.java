package DAO;

import Model.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Data Access Object for executing search queries about book listings.
 * 
 * @author Kevin Tan
 * @version 1.0
 * @since 11/15/2014
 */
public class BookSearchDAO extends DAOFactory 
{
    private String searchMe = "";
    /**
     * Gets all of the rows from UserListings table in spartasavedb.
     * @param attribute the column name relevant to the search term.
     * @param term the search term the user is looking for.
     * @param order how the results will be sorted by.
     * @return result all the matching records from UserListings table.
     * @throws SQLException 
     */
    public ArrayList<Book> getUserListings
        (String attribute, String term, String order) throws SQLException
    {
        if (order.isEmpty()) order = "price"; // Order by price by default.
        
        // Execute statement to get results from database.
        preparedStatement = connection.prepareStatement("SELECT * FROM UserListings "
                + "WHERE ? = '?' "
                + "ORDER BY ?");
        preparedStatement.setString(1, attribute);
        preparedStatement.setString(2, term);
        preparedStatement.setString(3, order);
        
        ResultSet result = preparedStatement.executeQuery();
        
        // Create UserBook objects and create an array of them from the results.
        ArrayList<Book> userListings = new ArrayList<Book>();
        while (result.next()) 
        {
            Book book = new Book(
                    result.getInt("user_id"),
                    result.getString("isbn"),
                    result.getString("title"),
                    result.getString("author"),
                    result.getString("book_condition"),
                    result.getString("category"),
                    result.getInt("price"),
                    result.getDate("date")
            );
            userListings.add(book);
        }
        
        return userListings;
    }
        
    public void sendSearchBarText(String text){
        searchMe = text;
    }
}
