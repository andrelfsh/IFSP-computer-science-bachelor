package locacaodvds.entidades;
import java.util.Date;

/**
 *
 * @author André - classes tem nome do banco de dados, mas com maiusculo. variaveis igual o banco de dados.
 */
public class Ator {
    
    private int id;                // chave primaria
    private String nome;
    private String sobrenome;
    private Date data_estreia;
    
    // get e set id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // get set nome
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // get e set sobrenome
    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    // get e set data_estreia
    public Date getData_estreia() {
        return data_estreia;
    }

    public void setData_estreia(Date data_estreia) {
        this.data_estreia = data_estreia;
    }
    
    
}
