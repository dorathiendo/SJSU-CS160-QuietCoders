package Controller;

import DAO.*;
import Model.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NewUserServlet extends HttpServlet 
{
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException 
    {
        // Get parameters from the request.
        String first = request.getParameter("first");
        String last = request.getParameter("last");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        PrintWriter writer = response.getWriter();
        String htmlRespone = "<html>";
        htmlRespone += "<h2>Your name is: " + first + " " + last + "</h2>";
        htmlRespone += "</html>";

        writer.println(htmlRespone);
        
        // Create new user bean with the form input.
        User user = new User(first, last, email, password);
        
        System.out.println(first);
        System.out.println(last);
        System.out.println(email);
        System.out.println(password);

        // Connect and add to the database.
        try 
        {
            UserAccountDAO dao = new UserAccountDAO();
            dao.addNewAccount(user);
            Cookie cookie = new Cookie("useremail", user.getEmail());
            response.addCookie(cookie);
            response.sendRedirect("AccountPage.jsp");
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(NewUserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
