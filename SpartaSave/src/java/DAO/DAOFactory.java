package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The database access object that connects to the database.
 * 
 * @author Kevin Tan
 * @version 1.0
 * @since 11/15/2014
 */

public abstract class DAOFactory
{
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final String SERVERNAME = "localhost";
    private static final String DATABASE = "spartasavedb";

    public Connection connection;
    public PreparedStatement preparedStatement;
    
    /**
     * Constructs the model for user posted books.
     */
    public DAOFactory() throws SQLException
    {
        this.connection = connect();
    }
    
    /**
     * Connect to the database.
     * @return 
     */
    public static Connection connect()
    {
        try 
        {
            String driverName = "com.mysql.jdbc.Driver";
            Class.forName(driverName);
        } 
        catch (Exception e) 
        {
            System.out.println("ERROR, JDBC driver not found.");
        }

        String url = "jdbc:mysql://" + SERVERNAME + "/" + DATABASE;

        try 
        {
            return DriverManager.getConnection(url, USERNAME, PASSWORD);
        }
        catch (Exception e) 
        {
            System.out.println("ERROR, connection failed.");
        }

        return null;
    }
}
