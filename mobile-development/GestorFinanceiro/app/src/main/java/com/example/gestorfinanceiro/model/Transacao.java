package com.example.gestorfinanceiro.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.Expose;

@Entity(tableName = "transacoes")
public class Transacao {

    @PrimaryKey(autoGenerate = true)
    @Expose(serialize = false, deserialize = false)
    private int id;

    @Expose
    private String descricao;

    @Expose
    private double valor;

    @Expose
    private long dataTimestamp;

    @Expose
    private String tipo;

    @Expose
    private String categoria;

    public Transacao(String descricao, double valor, long dataTimestamp, String tipo, String categoria) {
        this.descricao = descricao;
        this.valor = valor;
        this.dataTimestamp = dataTimestamp;
        this.tipo = tipo;
        this.categoria = categoria;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }

    public long getDataTimestamp() { return dataTimestamp; }
    public void setDataTimestamp(long dataTimestamp) { this.dataTimestamp = dataTimestamp; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
}