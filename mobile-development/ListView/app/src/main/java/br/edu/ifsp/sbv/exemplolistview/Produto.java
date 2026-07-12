package br.edu.ifsp.sbv.exemplolistview;

import java.io.Serializable;

public class Produto implements Serializable {
    private int id;
    private String descricao;
    private double valor;

    public Produto(int id, String descricao, double valor) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
    }

    // listView usa pra saber o que escrever na tela.
    @Override
    public String toString() {
        return descricao + " - R$ " + valor;
    }

    public int getId() { return id; }
    public String getDescricao() { return descricao; }
    public double getValor() { return valor; }
}