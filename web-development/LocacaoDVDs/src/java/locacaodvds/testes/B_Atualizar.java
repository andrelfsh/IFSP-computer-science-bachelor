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
import java.sql.Date;


/**
 *
 * @author André - feito apenas para ator e classificacao 
 */

public class B_Atualizar {

    public static void main(String[] args) {

        try {
            
            AtorDAO atorDAO = new AtorDAO();
            Classificacao_etariaDAO classificacaoDAO = new Classificacao_etariaDAO();
            GeneroDAO generoDAO = new GeneroDAO();
            DvdDAO dvdDAO = new DvdDAO();

            Ator ator = atorDAO.obterPorId(53); // exemplo com id=0
            if (ator != null) {
                ator.setSobrenome("Silva"); // novo sobrenome
                atorDAO.atualizar(ator);
            } else {
                System.out.println("Ator nao encontrado.");
            }

            Classificacao_etaria classificacao = classificacaoDAO.obterPorId(37); // exemplo com id=0
            
            if (classificacao != null) {
                classificacao.setDescricao("Somente Adultos"); 
                classificacaoDAO.atualizar(classificacao);
            } else {
                System.out.println("Classificacapo nao encontrada.");
            }
            
            Genero genero = generoDAO.obterPorId(8); // exemplo com id=0
            if (genero != null) {
                genero.setDescricao("Muito drama"); 
                generoDAO.atualizar(genero);
            } else {
                System.out.println("Genero nao encontrad.");
            }
            
            Dvd dvd = dvdDAO.obterPorId(4);
            if (dvd != null) {
                dvd.setTitulo("Monster Hight");
                dvd.setAno_lancamento(2003);
                dvd.setData_lancamento(Date.valueOf("2003-05-15"));
                dvd.setDuracao_minutos(138);

                // exemplo de trocar gênero e classificação
                dvd.setGenero(genero);
                dvd.setClassificacao_etaria(classificacao);

                dvdDAO.atualizar(dvd);
            } else {
                System.out.println("DVD no encontrado.");
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
