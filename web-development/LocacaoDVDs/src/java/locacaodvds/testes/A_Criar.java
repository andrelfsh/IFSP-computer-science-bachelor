package locacaodvds.testes;

import java.sql.Date;
import java.sql.SQLException;
import locacaodvds.entidades.Classificacao_etaria;
import locacaodvds.dao.Classificacao_etariaDAO;
import locacaodvds.entidades.Ator;
import locacaodvds.dao.AtorDAO;
import locacaodvds.entidades.Genero;
import locacaodvds.dao.GeneroDAO;
import locacaodvds.entidades.Dvd;
import locacaodvds.dao.DvdDAO;

/**
 *
 * @author André - feito apenas para ator e classificacao - ID ESTÁ DANDO ERRO
 */

public class A_Criar { 
    
    public static void main(String[] args) {
        
        try {
            
            AtorDAO dao = new AtorDAO();

            // Criar ator andré
            Ator ator1 = new Ator();
            ator1.setNome("Andre");
            ator1.setSobrenome("Lyra");
            ator1.setData_estreia(Date.valueOf("2003-02-11"));
            dao.salvar(ator1);
            System.out.println("Ator 1 salvo com ID: " + ator1.getId());

            // Criar atriz vic
            Ator ator2 = new Ator();
            ator2.setNome("Victoria");
            ator2.setSobrenome("Carolina");
            ator2.setData_estreia(Date.valueOf("2005-06-16"));
            dao.salvar(ator2);
            System.out.println("Ator 2 salvo com ID: " + ator2.getId());
            
            Classificacao_etariaDAO dao2 = new Classificacao_etariaDAO();

            // Criar livre
            Classificacao_etaria c1 = new Classificacao_etaria();
            c1.setDescricao("Livre");
            dao2.salvar(c1);
            System.out.println("Classificacao 1 salvo com ID: " + c1.getId());

            // cria proibido
            Classificacao_etaria c2 = new Classificacao_etaria();
            c2.setDescricao("Proibido");
            dao2.salvar(c2);
            System.out.println("Classificacao 2 salvo com ID: " + c2.getId());
            
            GeneroDAO dao3 = new GeneroDAO();
            
            // Criar acao
            Genero g1 = new Genero();
            g1.setDescricao("Acao");
            dao3.salvar(g1);
            System.out.println("Genero 1 salvo com ID: " + c1.getId());
            
            // cria crama
            Genero g2 = new Genero();
            g2.setDescricao("Drama");
            dao3.salvar(g2);
            System.out.println("Genero 2 salvo com ID: " + c2.getId());
            
            DvdDAO dao4 = new DvdDAO();
            
            // Criar dvd 1
            Dvd d1 = new Dvd();
            d1.setTitulo("Batman");
            d1.setAno_lancamento(2022);
            d1.setAtor_principal(ator1);   // já criados acima
            d1.setAtor_coadjuvante(ator2);
            d1.setData_lancamento(Date.valueOf("1999-03-31"));
            d1.setDuracao_minutos(136);
            d1.setGenero(g1);
            d1.setClassificacao_etaria(c1);
            dao4.salvar(d1);
            System.out.println("Dvd salvo com ID: " + d1.getId());
            
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}


