package locacaodvds.entidades;

/**
 *
 * author André - classes tem nome do banco de dados, mas com maiusculo. variaveis igual o banco de dados.
 */
public class Genero {
    
    private int id;                // chave primaria
    private String descricao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
}
