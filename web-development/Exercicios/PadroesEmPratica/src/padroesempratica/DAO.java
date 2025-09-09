/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package padroesempratica;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Samsung
 */
public abstract class DAO<Tipo> { // modelo genético
    
    protected Connection conexao;
    
    public DAO() throws SQLException{
        
        // Connection conexao = ConnectionFactory.getConnection();
        conexao = ConnectionFactory.getConnection(); //erro nessa linha
    }
    
    public abstract void salvar (Tipo obj) throws SQLException;
    public abstract void atualizar (Tipo obj) throws SQLException;
    public abstract void excluir (Tipo obj) throws SQLException;
    public abstract Tipo obterPorID (int obj) throws SQLException;
    public abstract List<Tipo> obterTodos () throws SQLException;
    
    public void fecharConexao() throws SQLException {
        conexao.close();
    }
    
}
