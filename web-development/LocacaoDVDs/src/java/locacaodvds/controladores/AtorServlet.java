package locacaodvds.controladores;

import locacaodvds.dao.AtorDAO;
import locacaodvds.entidades.Ator;
import java.io.IOException;
import java.sql.SQLException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;

@WebServlet( name = "AtorServlet", 
             urlPatterns = { "/processaAtor" } )
public class AtorServlet extends HttpServlet {

    protected void processRequest( 
            HttpServletRequest request, 
            HttpServletResponse response )
            throws ServletException, IOException {
        
        String acao = request.getParameter( "acao" );
        AtorDAO dao = null;
        RequestDispatcher disp = null;

        try {

            dao = new AtorDAO();

            if ( acao.equals( "inserir" ) ) {

                String nome = request.getParameter( "nome" );
                String sobrenome = request.getParameter( "sobrenome" );
                String dataStr = request.getParameter("data");
                Date data_estreia = Date.valueOf(dataStr); // converte String esta certo?

                Ator a = new Ator();
                a.setNome( nome );
                a.setSobrenome( sobrenome );
                a.setData_estreia(data_estreia);

                dao.salvar( a );

                disp = request.getRequestDispatcher(
                        "/formularios/ator/listagem.jsp" );

            } else if ( acao.equals( "alterar" ) ) {

                int id = Integer.parseInt(request.getParameter( "id" ));
                String nome = request.getParameter( "nome" );
                String sobrenome = request.getParameter( "sobrenome" );
                String dataStr = request.getParameter("data");
                Date data_estreia = Date.valueOf(dataStr); // coverte String esta certo?

                Ator a = new Ator();
                a.setId(id);
                a.setNome( nome );
                a.setSobrenome( sobrenome );
                a.setData_estreia(data_estreia);

                dao.atualizar( a );

                disp = request.getRequestDispatcher(
                        "/formularios/ator/listagem.jsp" );

            } else if ( acao.equals( "excluir" ) ) {

                int id = Integer.parseInt(request.getParameter( "id" ));

                Ator a = new Ator();
                a.setId( id );

                dao.excluir( a );

                disp = request.getRequestDispatcher(
                        "/formularios/ator/listagem.jsp" );

            } else {
                
                int id = Integer.parseInt(request.getParameter( "id" ));
                Ator a = dao.obterPorId( id );
                request.setAttribute( "ator", a );
                
                if ( acao.equals( "prepararAlteracao" ) ) {
                    disp = request.getRequestDispatcher( 
                            "/formularios/ator/alterar.jsp" );
                } else if ( acao.equals( "prepararExclusao" ) ) {
                    disp = request.getRequestDispatcher( 
                            "/formularios/ator/excluir.jsp" );
                }
                
            }

        } catch ( SQLException exc ) {
            exc.printStackTrace();
        } finally {
            if ( dao != null ) {
                try {
                    dao.fecharConexao();
                } catch ( SQLException exc ) {
                    exc.printStackTrace();
                }
            }
        }

        if ( disp != null ) {
            disp.forward( request, response );
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
        return "AtorServlet";
    }

}
