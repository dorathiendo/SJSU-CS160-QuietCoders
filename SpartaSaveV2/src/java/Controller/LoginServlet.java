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

public class LoginServlet extends HttpServlet 
{
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException 
    {
        // Get parameters from the request.
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        System.out.println(email);
        System.out.println(password);

        // Connect and add to the database.
        try 
        {
            UserAccountDAO dao = new UserAccountDAO();
            if(dao.compareAccountPassword(email, password))
            {
                Cookie cookie = new Cookie("useremail", email);
                response.addCookie(cookie);
                response.sendRedirect("AccountPage.jsp");
            }
            else
            {
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.println("<script>alert(\"Incorrect email/password!\");</script>");
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(NewUserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
