package locacaodvds.controladores;

import locacaodvds.dao.Classificacao_etariaDAO;
import locacaodvds.entidades.Classificacao_etaria;
import java.io.IOException;
import java.sql.SQLException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet( name = "Classificacao_etariaServlet", 
             urlPatterns = { "/processaClassificacao" } )
public class Classificacao_etariaServlet extends HttpServlet {

    protected void processRequest( 
            HttpServletRequest request, 
            HttpServletResponse response )
            throws ServletException, IOException {
        
        String acao = request.getParameter( "acao" );
        Classificacao_etariaDAO dao = null;
        RequestDispatcher disp = null;

        try {

            dao = new Classificacao_etariaDAO();

            if ( acao.equals( "inserir" ) ) {

                String descricao = request.getParameter( "descricao" );

                Classificacao_etaria a = new Classificacao_etaria();
                a.setDescricao( descricao );


                dao.salvar( a );

                disp = request.getRequestDispatcher(
                        "/formularios/classificacao_etaria/listagem.jsp" );

            } else if ( acao.equals( "alterar" ) ) {

                int id = Integer.parseInt(request.getParameter( "id" ));
                String descricao = request.getParameter( "descricao" );

                Classificacao_etaria a = new Classificacao_etaria();
                a.setId(id);
                a.setDescricao( descricao );


                dao.atualizar( a );

                disp = request.getRequestDispatcher(
                        "/formularios/classificacao_etaria/listagem.jsp" );

            } else if ( acao.equals( "excluir" ) ) {

                int id = Integer.parseInt(request.getParameter( "id" ));

                Classificacao_etaria a = new Classificacao_etaria();
                a.setId( id );

                dao.excluir( a );

                disp = request.getRequestDispatcher(
                        "/formularios/classificacao_etaria/listagem.jsp" );

            } else {
                
                int id = Integer.parseInt(request.getParameter( "id" ));
                Classificacao_etaria a = dao.obterPorId( id );
                request.setAttribute( "classificacao_etaria", a );
                
                if ( acao.equals( "prepararAlteracao" ) ) {
                    disp = request.getRequestDispatcher( 
                            "/formularios/classificacao_etaria/alterar.jsp" );
                } else if ( acao.equals( "prepararExclusao" ) ) {
                    disp = request.getRequestDispatcher( 
                            "/formularios/classificacao_etaria/excluir.jsp" );
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
        return "Classificacao_etariaServlet";
    }

}
