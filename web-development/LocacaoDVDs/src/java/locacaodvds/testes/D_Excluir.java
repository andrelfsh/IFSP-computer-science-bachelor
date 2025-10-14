package locacaodvds.testes;

import locacaodvds.dao.AtorDAO;
import locacaodvds.dao.GeneroDAO;
import locacaodvds.dao.Classificacao_etariaDAO;
import locacaodvds.dao.DvdDAO;
import java.sql.SQLException;


/**
 *
 * @author André - feito apenas para ator e classificacao
 */

public class D_Excluir {
    
    public static void main(String[] args) {
        
        try {
            

            
            DvdDAO dvdDAO = new DvdDAO();

            dvdDAO.listarTodos().forEach(dvd -> {
                try {
                    dvdDAO.excluir(dvd); 
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
            
           AtorDAO atorDAO = new AtorDAO();

            atorDAO.listarTodos().forEach(ator -> {
                try {
                    atorDAO.excluir(ator); 
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
            
            Classificacao_etariaDAO classificacaoDAO = new Classificacao_etariaDAO();

            classificacaoDAO.listarTodos().forEach(classificacao -> {
                try {
                    classificacaoDAO.excluir(classificacao); 
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
            
            GeneroDAO generoDAO = new GeneroDAO();

            generoDAO.listarTodos().forEach(genero -> {
                try {
                    generoDAO.excluir(genero); 
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
            

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
