package primeiroformulario.servlets;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet para processamento dos dados do formulário.
 * 
 * @author Prof. Dr. David Buzatto
 */
@WebServlet( name = "ProcessaDadosClienteServlet",
             urlPatterns = { "/processaDadosCliente" } )
public class ProcessaDadosClienteServlet extends HttpServlet {

    protected void processRequest( 
            HttpServletRequest request, 
            HttpServletResponse response )
            throws ServletException, IOException {

        request.setCharacterEncoding( "UTF-8" );
        
        String num1Str = request.getParameter( "num1" );
        String num2Str = request.getParameter( "num2" );
        String conta = request.getParameter( "conta" );
        
        double num1 = Double.parseDouble(num1Str);
        double num2 = Double.parseDouble(num2Str);

        System.out.println( "Resultado de " + num1 + " e " + num2 + ": " );

        if ( conta.equals( "+" ) ) {
            System.out.println( num1 + num2 );
        } else if ( conta.equals( "-" ) ) {
            System.out.println( num1 - num2 );
        } else if ( conta.equals( "*" ) ) {
            System.out.println( num1 * num2 );
        } else  {
            System.out.println( num1 / num2 );
        }
            
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response )
            throws ServletException, IOException {
        processRequest( request, response );
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response )
            throws ServletException, IOException {
        processRequest( request, response );
    }

    @Override
    public String getServletInfo() {
        return "ProcessaDadosClienteServlet";
    }

}
