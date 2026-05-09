/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package locacaodvds.servicos;

import locacaodvds.dao.GeneroDAO;
import locacaodvds.entidades.Genero;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vic, Revisado e ajustado por André
 */
public class GeneroServices {
    
    public List<Genero> getTodos() {
        
    List<Genero> lista = new ArrayList<>();
    GeneroDAO dao = null;
    
    try {
        dao = new GeneroDAO();
        lista = dao.listarTodos();
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
    
    return lista;
    
    }
    
}
