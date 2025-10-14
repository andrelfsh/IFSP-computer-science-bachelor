/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package locacaodvds.servicos;

import locacaodvds.dao.Classificacao_etariaDAO;
import locacaodvds.entidades.Classificacao_etaria;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vic, Revisado e ajustado por André
 */
public class Classificacao_etariaServices {
    
    public List<Classificacao_etaria> getTodos() {
        
    List<Classificacao_etaria> lista = new ArrayList<>();
    Classificacao_etariaDAO dao = null;
    
    try {
        dao = new Classificacao_etariaDAO();
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
