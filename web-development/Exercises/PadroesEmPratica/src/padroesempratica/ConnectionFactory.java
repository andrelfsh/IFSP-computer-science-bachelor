/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package padroesempratica;

import java.sql.Connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Samsung
 */
public class ConnectionFactory {
    
    public Connection getConnection() throws SQLException{
        
        return DriverManager.getConnection(
                "jdbc:mariadb://localhost/testes_padroes",
                "root",
                ""
            );
        
        }
    
}
