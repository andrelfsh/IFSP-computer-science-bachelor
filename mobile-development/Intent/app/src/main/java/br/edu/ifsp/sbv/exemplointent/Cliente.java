package br.edu.ifsp.sbv.exemplointent;

public class Cliente implements java.io.Serializable {
    private int id;
    private String nome;
    private int idade;

    // Construtor
    public Cliente(int id, String nome, int idade) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public int getIdade() { return idade; }
}