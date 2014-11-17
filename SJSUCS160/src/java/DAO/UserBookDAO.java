package DAO;

import Model.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Data Access Object for manipulating book listings posted by users.
 * Only makes alterations to the UserListings table in spartasavedb.
 * 
 * @author Kevin Tan
 * @version 1.0
 * @since 11/15/2014
 */
public class UserBookDAO extends DAOFactory 
{
    /**
     * Get books posted by a specific user.
     * @param id the user_id of the user.
     * @return userListings a list of books posted by the users.
     * @throws SQLException 
     */
    public ArrayList<Book> getUserBooks(int id) throws SQLException
    {
        // Execute statement to get results from database.
        preparedStatement = connection.prepareStatement("SELECT * FROM UserListings "
                + "WHERE user_id = " + id);
        ResultSet result = preparedStatement.executeQuery();
        
        // Create UserBook objects and create an array of them from the results.
        ArrayList<Book> userListings = new ArrayList<Book>();
        
        while (result.next()) 
        {
            Book book = new Book
            (
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
    
    public void insertUserBook(Book book) throws SQLException
    {
        preparedStatement = connection.prepareStatement("INSERT INTO UserListings"
                + "(user_id, isbn, title, author, book_condition, category, price, post_date)"
                + "VALUES('" + book.getUser_id() + "', '"
                             + book.getIsbn() + "', '"
                             + book.getTitle() + "', '"
                             + book.getAuthor() + "', '"
                             + book.getBook_condition() + "', '"
                             + book.getCategory() + "', '"
                             + book.getPrice() + "', '"
                             + book.getPost_date() + "'"
                + ")");
        preparedStatement.executeUpdate();
    }
    
    /**
     * Edit an attribute of a book.
     * *** Content parameter needs quotes if a string. ***
     * @param bookId the id of the book to edit.
     * @param attribute the attribute being altered.
     * @param value the new content to replace the old content.
     * @throws java.sql.SQLException
     */
    public void updateUserBook(int bookId, String attribute, String value) throws SQLException
    {
        preparedStatement = connection.prepareStatement("UPDATE UserListings"
                + " SET " + attribute + "=" + value 
                + " WHERE id = " + bookId);
        preparedStatement.executeUpdate();
    }
    
    /**
     * Deletes a book posted by a user.
     * @param id the id of the book to delete.
     * @throws java.sql.SQLException
     */
    public void deleteUserBook(int id) throws SQLException
    {
        preparedStatement = connection.prepareStatement("DELETE FROM UserListings"
                + "WHERE id = " + id);
        preparedStatement.executeUpdate();
    }
}