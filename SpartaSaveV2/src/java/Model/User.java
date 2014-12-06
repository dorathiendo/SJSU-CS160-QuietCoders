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
    private String first, last, email, password;
    
    /**
     * Constructs a user.
     * Follows the construction of the Users table on spartasavedb.
     */
    public User(String first, String last, String email, String password)
    {
        this.first = first;
        this.last = last;
        this.email = email;
        this.password = password;
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
    
    public void setPassword(String password) {
        this.password = password;
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
    
    public String getPassword() {
        return password;
    }
}