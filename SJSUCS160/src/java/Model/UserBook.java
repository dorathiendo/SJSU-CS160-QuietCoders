package Model;

import java.sql.Date;

/**
 * Model for a book posted by a user.
 * 
 * @author Kevin Tan
 * @version 1.0
 * @since 11/15/2014
 */
public class UserBook 
{
    private int user_id, price;
    private String isbn, title, author, book_condition, category;
    private Date post_date;
    
    /**
     * Constructs a book created by a user.
     * Follows the construction of the UserListings table on spartasavedb.
     */
    public UserBook(int user_id, String isbn, String title, String author, 
            String book_condition, String category, int price, Date post_date)
    {
        this.user_id = user_id;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.book_condition = book_condition;
        this.category = category;
        this.price = price;
        this.post_date = post_date;
    }

    /**
     * Getter and setter methods below.
     */
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setBook_condition(String book_condition) {
        this.book_condition = book_condition;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPost_date(Date post_date) {
        this.post_date = post_date;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getPrice() {
        return price;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getBook_condition() {
        return book_condition;
    }

    public String getCategory() {
        return category;
    }

    public Date getPost_date() {
        return post_date;
    }
}