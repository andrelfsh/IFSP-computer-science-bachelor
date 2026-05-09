package locacaodvds.entidades;

/**
 *
 * @author André - classes tem nome do banco de dados, mas com maiusculo. variaveis igual o banco de dados.
 */
public class Classificacao_etaria {
    
    private int id;               // chave primaria
    private String descricao;

    // get e set id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // get e set descricao
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
}
