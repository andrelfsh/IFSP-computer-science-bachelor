/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package padroesempratica;

/**
 *
 * @author Samsung
 */
public class Reflexao {
    
    public static String extrairInsert(Object o){
        
        String nomeTabela = c.getSimpleName().toLowerCase()
        Field[] campos = c.DeclaredFields();
        String camposSql = "";
        for (int i = 0; i < campos.lenght; i++){
            if (i != 0){
                camposSql += ", ";
                parametros += ", ";
            }
        }
        
        String template = "inser INTo %s( %s) VALUES (%s);";
        return String.format(template, nomeTabela, "", "");
    }
    
    public static void main(String[] args ){
        System.out.println(extrairInser(Pais.class);
    }
    
}
