package Model;

/**
 * Model for a book posted by a user.
 * 
 * @author Kevin Tan
 * @version 1.0
 * @since 11/15/2014
 */
public class User 
{
    private int id;
    private String first, last, email;
    
    /**
     * Constructs a user.
     * Follows the construction of the Users table on spartasavedb.
     */
    public User(int id, String first, String last, String email)
    {
        this.id = id;
        this.first = first;
        this.last = last;
        this.email = email;
    }
    
    /**
     * Getter and setter methods below.
     */
    public void setId(int id) {
        this.id = id;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getFirst() {
        return first;
    }

    public String getLast() {
        return last;
    }

    public String getEmail() {
        return email;
    }
}