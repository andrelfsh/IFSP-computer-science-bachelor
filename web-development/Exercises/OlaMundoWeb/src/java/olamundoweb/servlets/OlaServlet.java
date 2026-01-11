package olamundoweb.servlets;

import java.io.IOException;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet( name = "OlaServlet", urlPatterns = { "/ola" } )
public class OlaServlet extends HttpServlet {
    
    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
    
        // chama o método processRequest
        processRequest( request, response );
    
    }
    
    @Override
    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
    
        // chama o método processRequest
        processRequest( request, response );
    
    }
    
    
    protected void processRequest( HttpServletRequest request, HttpServletResponse response )
        throws ServletException, IOException {
        
        System.out.println( "Ola Mundo!" );
        System.out.println( "Meu Primeiro Servlet!" );

    }
}
