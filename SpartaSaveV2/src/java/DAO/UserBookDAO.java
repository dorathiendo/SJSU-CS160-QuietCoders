package DAO;

import Model.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Data Access Object for manipulating book listings posted by users.
 * Only makes alterations to the UserListings table in spartasavedb.
 * 
 * @author Kevin Tan
 * @version 1.0
 * @since 11/16/2014
 */
public class UserBookDAO extends DAOFactory 
{
    private PreparedStatement getBooksStatement;
    private PreparedStatement insertBooksStatement;
    private PreparedStatement updateBooksStatement;
    private PreparedStatement deleteBooksStatement;
    
    /**
     * Constructor creates the prepared statements.
     * @throws SQLException 
     */
    public UserBookDAO() throws SQLException 
    {
        getBooksStatement = connection.prepareStatement("SELECT * FROM UserListings "
                + "WHERE user_id = ?");
        deleteBooksStatement = connection.prepareStatement("DELETE FROM UserListings "
                + "WHERE id = ?");
        insertBooksStatement = connection.prepareStatement("INSERT INTO UserListings"
                + "(user_id, isbn, title, author, book_condition, category, price, post_date) "
                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
    }
    
    /**
     * Get books posted by a specific user.
     * @param id the user_id of the user.
     * @return userListings a list of books posted by the users.
     * @throws SQLException 
     */
    public ArrayList<Book> getUserBooks(int id) throws SQLException
    {
        // Execute statement to get results from database.
        getBooksStatement.setInt(1, id);
        ResultSet result = getBooksStatement.executeQuery();
        
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
                result.getString("post_date")
            );
            book.setId(result.getInt("id"));
            userListings.add(book);
        }
        
        return userListings;
    }
    
    /**
     * Adds a new book to the listings.
     * @param book the book being added.
     * @throws SQLException 
     */
    public void insertUserBook(Book book) throws SQLException
    {
        insertBooksStatement.setInt(1,book.getUser_id());
        insertBooksStatement.setString(2, book.getIsbn());
        insertBooksStatement.setString(3, book.getTitle());
        insertBooksStatement.setString(4, book.getAuthor());
        insertBooksStatement.setString(5, book.getBook_condition());
        insertBooksStatement.setString(6, book.getCategory());
        insertBooksStatement.setInt(7, book.getPrice());
        insertBooksStatement.setString(8, book.getPost_date());
        insertBooksStatement.executeUpdate();
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
        updateBooksStatement = connection.prepareStatement("UPDATE UserListings"
                + " SET " + attribute + "=" + value 
                + " WHERE id = " + bookId);
        updateBooksStatement.executeUpdate();
    }
    
    /**
     * Deletes a book posted by a user.
     * @param id the id of the book to delete.
     * @throws java.sql.SQLException
     */
    public void deleteUserBook(int id) throws SQLException
    {
        deleteBooksStatement.setInt(1, id);
        deleteBooksStatement.executeUpdate();
    }
}