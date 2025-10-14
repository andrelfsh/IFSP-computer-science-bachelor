package locacaodvds.entidades;

import java.util.Date;

/**
 *
 * @author André - classes tem nome do banco de dados, mas com maiusculo.
 * variaveis igual o banco de dados. Chaves estrangeiras revisadas
 */
public class Dvd {

    private int id;                      // chave primaria
    private String titulo;
    private int ano_lancamento;
    private Date data_lancamento;
    private int duracao_minutos;

    // rever!
    // private int ator_principal_id;       
    private Ator ator_principal;
    // private int ator_coadjuvante_id; 
    private Ator ator_coadjuvante;
    // private int genero_id;      
    private Genero genero;
    // private int classificacao_etaria_id   
    private Classificacao_etaria classificacao_etaria;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAno_lancamento() {
        return ano_lancamento;
    }

    public void setAno_lancamento(int ano_lancamento) {
        this.ano_lancamento = ano_lancamento;
    }

    public Date getData_lancamento() {
        return data_lancamento;
    }

    public void setData_lancamento(Date data_lancamento) {
        this.data_lancamento = data_lancamento;
    }

    public int getDuracao_minutos() {
        return duracao_minutos;
    }

    public void setDuracao_minutos(int duracao_minutos) {
        this.duracao_minutos = duracao_minutos;
    }

    public Ator getAtor_principal() {
        return ator_principal;
    }

    public void setAtor_principal(Ator ator_principal) {
        this.ator_principal = ator_principal;
    }

    public Ator getAtor_coadjuvante() {
        return ator_coadjuvante;
    }

    public void setAtor_coadjuvante(Ator ator_coadjuvante) {
        this.ator_coadjuvante = ator_coadjuvante;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Classificacao_etaria getClassificacao_etaria() {
        return classificacao_etaria;
    }

    public void setClassificacao_etaria(Classificacao_etaria classificacao_etaria) {
        this.classificacao_etaria = classificacao_etaria;
    }
   
    private String foto_url;

    // Getter para foto_url
    public String getFoto_url() {
        return foto_url;
    }

    // Setter para foto_url
    public void setFoto_url(String foto_url) {
        this.foto_url = foto_url;
    }

}
