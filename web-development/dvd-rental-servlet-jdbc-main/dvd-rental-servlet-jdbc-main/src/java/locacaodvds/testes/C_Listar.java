package locacaodvds.testes;

import locacaodvds.dao.AtorDAO;
import locacaodvds.dao.Classificacao_etariaDAO;
import locacaodvds.dao.GeneroDAO;
import locacaodvds.dao.DvdDAO;

import locacaodvds.entidades.Ator;
import locacaodvds.entidades.Classificacao_etaria;
import locacaodvds.entidades.Genero;
import locacaodvds.entidades.Dvd;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author André
 */
public class C_Listar {

    public static void main(String[] args) {
        try {
            // Listar Atores
            AtorDAO atorDAO = new AtorDAO();
            List<Ator> atores = atorDAO.listarTodos();
            
            for (Ator a : atores) {
                System.out.println("ID: " + a.getId() + 
                                   " | Nome: " + a.getNome() + " " + a.getSobrenome() + 
                                   " | Data estreia: " + a.getData_estreia());
            }
            
            // Listar Classificação Etária
            Classificacao_etariaDAO classificacaoDAO = new Classificacao_etariaDAO();
            List<Classificacao_etaria> classificacoes = classificacaoDAO.listarTodos();
            
            for (Classificacao_etaria c : classificacoes) {
                System.out.println("ID: " + c.getId() + " | Descricao: " + c.getDescricao());
            }
            
            // Listar Gêneros
            GeneroDAO generoDAO = new GeneroDAO();
            List<Genero> generos = generoDAO.listarTodos();
            
            for (Genero g : generos) {
                System.out.println("ID: " + g.getId() + " | Descricao: " + g.getDescricao());               
            }
            
            // Listar DVDs
            DvdDAO dvdDAO = new DvdDAO();
            List<Dvd> dvds = dvdDAO.listarTodos();

            for (Dvd d : dvds) {
                Ator atorPrincipal = d.getAtor_principal();
                Ator atorCoadjuvante = d.getAtor_coadjuvante();
                Genero genero = d.getGenero();
                Classificacao_etaria classificacao = d.getClassificacao_etaria();

                System.out.println("ID: " + d.getId() +
                        " | Título: " + d.getTitulo() +
                        " | Ano: " + d.getAno_lancamento() +
                        " | Duração: " + d.getDuracao_minutos() + " min" +
                        " | Data lançamento: " + d.getData_lancamento());

                if (atorPrincipal != null) {
                    System.out.println("    Ator Principal: " + atorPrincipal.getNome() + " " + atorPrincipal.getSobrenome());
                }
                if (atorCoadjuvante != null) {
                    System.out.println("    Ator Coadjuvante: " + atorCoadjuvante.getNome() + " " + atorCoadjuvante.getSobrenome());
                }
                if (genero != null) {
                    System.out.println("    Gênero: " + genero.getDescricao());
                }
                if (classificacao != null) {
                    System.out.println("    Classificação: " + classificacao.getDescricao());
                }

                System.out.println("----------------------------------------------------");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
