package padroesempratica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import java.sql.SQLException;

public class PadroesEmPratica {

    
    public static void main(String[] args) {
        
        try {
            
            PaisDAO dao = new PaisDAO();            
            Pais p = new Pais();
            p.setId(1);
            p.setNome("Venezuela");
            p.setSigla("VZ");
            
            dao.atualizar(p);
            dao.fecharConexao();
        } catch (SQLException exc) {
            exc.printStackTrace();  
        }
        
    }
    
    
}
