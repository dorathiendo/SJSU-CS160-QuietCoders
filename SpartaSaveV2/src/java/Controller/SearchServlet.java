/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import AmazonWebServices.AmazonWebservice;
import DAO.BookSearchDAO;
import Model.Book;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Tan
 */
public class SearchServlet extends HttpServlet 
{
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        String search = request.getParameter("search");
        
        try 
        {
            BookSearchDAO dao = new BookSearchDAO(); // Create connection to database.
            // Find the listings where the search matches the title.
            ArrayList<Book> titleSearch = dao.getUserListings("title", search, "price");
            
            // Find the listings where the search matches the author.
            ArrayList<Book> authorSearch = dao.getUserListings("author", search, "price");
            
            // Find the listings where the search matches the isbn.
            ArrayList<Book> isbnSearch = dao.getUserListings("isbn", search, "price");
            System.out.println("HELLO");
            // Search through amazon web service.
            AmazonWebservice amazon = new AmazonWebservice();
            ArrayList<Book> amazonSearch = amazon.search(search);
            System.out.println(amazonSearch);
            
            // Combine the arraylists together.
            titleSearch.addAll(authorSearch);
            titleSearch.addAll(isbnSearch);
            titleSearch.addAll(amazonSearch);
            
            request.setAttribute("titleSearch", titleSearch);
            //response.sendRedirect("SearchResultsPage.jsp");
            request.getRequestDispatcher("SearchResultsPage.jsp").forward(request, response);
            
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(SearchServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InvalidKeyException ex) 
        {
            Logger.getLogger(SearchServlet.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (IllegalArgumentException ex) 
        {
            Logger.getLogger(SearchServlet.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (UnsupportedEncodingException ex) 
        {
            Logger.getLogger(SearchServlet.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (NoSuchAlgorithmException ex) 
        {
            Logger.getLogger(SearchServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
