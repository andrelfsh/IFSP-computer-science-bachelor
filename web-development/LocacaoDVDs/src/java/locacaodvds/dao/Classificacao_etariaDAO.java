/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package locacaodvds.dao;

import locacaodvds.entidades.Classificacao_etaria;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Victória - Revisado por André
 */

public class Classificacao_etariaDAO extends DAO<Classificacao_etaria>{
    public Classificacao_etariaDAO() throws SQLException{
    }

    @Override 
    public void salvar (Classificacao_etaria obj) throws SQLException{

        PreparedStatement stmt = getConnection().prepareStatement(
                """
                INSERT INTO 
                Classificacao_etaria(descricao)
                VALUES(?);
                """, PreparedStatement.RETURN_GENERATED_KEYS); // ADICIONADO PARA PRINTAR O ID NO TERMINAL
        
        stmt.setString(1, obj.getDescricao());
        
        stmt.executeUpdate();
        
        ResultSet rs = stmt.getGeneratedKeys(); // ADICIONADO PARA PRINTAR O ID NO TERMINAL
        if (rs.next()) { // ADICIONADO PARA PRINTAR O ID NO TERMINAL
            obj.setId(rs.getInt(1));
        }
        
        stmt.close();

    }
    
    @Override
    public void atualizar(Classificacao_etaria obj) throws SQLException{
        
        PreparedStatement stmt = getConnection().prepareStatement(
            """
            UPDATE classificacao_etaria
            SET 
               descricao = ?
            WHERE 
                id = ?;
            """);
        
        stmt.setString(1, obj.getDescricao());
        stmt.setInt(2,obj.getId());

        stmt.executeUpdate();
        stmt.close();
        
    }
    
    @Override
    public void excluir (Classificacao_etaria obj) throws SQLException{
        
        PreparedStatement stmt = getConnection().prepareStatement(
                """
                DELETE FROM classificacao_etaria
                WHERE
                    id =?;
                """);
        
        stmt.setInt(1,obj.getId());

        stmt.executeUpdate();
        stmt.close();

    }
    
    @Override
    public List<Classificacao_etaria> listarTodos() throws SQLException{
        
        List<Classificacao_etaria> lista = new ArrayList<>();

        PreparedStatement stmt = getConnection().prepareStatement(
                """
                SELECT * FROM classificacao_etaria
                ORDER BY descricao
                """);
        
        ResultSet rs=stmt.executeQuery();

        while(rs.next()){
            
            Classificacao_etaria c = new Classificacao_etaria();

            c.setId(rs.getInt ("id"));
            c.setDescricao(rs.getString("descricao"));

            lista.add(c);

        }

        rs.close();
        
        stmt.close();
        
        return lista;
    }
    
    @Override 
    public Classificacao_etaria obterPorId(int id) throws SQLException{
    
        Classificacao_etaria classificacao_etaria= null;

        PreparedStatement stmt = getConnection().prepareStatement(
                """
                SELECT * FROM classificacao_etaria
                WHERE id = ?;
                """);

        stmt.setInt(1,id);

        ResultSet rs = stmt.executeQuery();

        if (rs.next()){

            classificacao_etaria = new Classificacao_etaria();

            classificacao_etaria.setId(rs.getInt ("id"));
            classificacao_etaria.setDescricao(rs.getString("descricao"));

        }
        
        rs.close();
        stmt.close();
        
        return classificacao_etaria;
        
    }

}

