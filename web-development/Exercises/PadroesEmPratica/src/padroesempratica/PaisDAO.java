/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package padroesempratica;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Samsung
 */
public class PaisDAO extends DAO<Pais>{
    
    public PaisDAO() throws SQLException {
    }

    @Override
    public void salvar(Pais obj) throws SQLException {
        
        PreparedStatement stmt = conexao.prepareStatement(
                """
                INSERT INTO pais (nome, sigla) VALUES (?,?); 
                """
        );

        stmt.setString(1, obj.getNome());
        stmt.setString(2, obj.getSigla());

        stmt.executeUpdate(); // ou stmt.executeQuery 
        stmt.close();
    }

    @Override
    public void atualizar(Pais obj) throws SQLException {
        PreparedStatement stmt = conexao.prepareStatement(
           """
           UPDATE pais
           SET
                nome = ?
                sigla = ?
           WHERE
                id = ?
           """
        );
                   
        stmt.setString(1, obj.getNome());
        stmt.setString(2, obj.getSigla());
        stmt.setInt(3, obj.getId());
    }

    @Override
    public void excluir(Pais obj) throws SQLException {
        PreparedStatement stmt = conexao.prepareStatement(
           """
           DELETE FROM pais WHERE id = ?;
           """
        );
                   
        stmt.setInt(3, obj.getId());
        stmt.executeUpdate();
        stmt.close();
    }

    @Override
    public Pais obterPorID(int id) throws SQLException {
        
        List<Pais> paises = new ArrayList<>;
                
        PreparedStatement stmt = conexao.prepareStatement(
           """
           SELECT * FROM pais;
           """
        );
        
                   
        stmt.setInt(1, id);
        
        ResultSet rs = stmt.executeQuery();
        
        while (rs.next() ){
            Pais p = new Pais();
            p.setId( rs.getInt("id") );
            p.setNome(rs.getString("nome"));
            p.setSigla(rs.getString("sigla"));
        }
        
        
        rs.close();
        
    } // missing return statment

    @Override
    public List<Pais> obterTodos() {
        
        Pais p = null;
                
        PreparedStatement stmt = conexao.prepareStatement(
           """
           SELECT * FROM pais WHERE id = ?
           """
        );
        
                   
        stmt.setInt(1, id);
        
        ResultSet rs = stmt.executeQuery();
        
        if (rs.next() ){
            p = new Pais();
            p.setId( rs.getInt("id") );
            p.setNome(rs.getString("nome"));
            p.setSigla(rs.getString("sigla"));
        }
        
        
        rs.close();
    }
      
}
