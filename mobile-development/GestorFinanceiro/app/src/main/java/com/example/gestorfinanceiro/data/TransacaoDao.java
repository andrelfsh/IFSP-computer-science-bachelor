package com.example.gestorfinanceiro.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.gestorfinanceiro.model.Transacao;

import java.util.List;

@Dao
public interface TransacaoDao {

    @Insert
    void inserir(Transacao transacao);

    @Update
    void atualizar(Transacao transacao);

    @Delete
    void deletar(Transacao transacao);

    @Query("SELECT * FROM transacoes ORDER BY dataTimestamp DESC")
    List<Transacao> obterTodas();

    @Query("SELECT * FROM transacoes WHERE tipo = :tipo ORDER BY dataTimestamp DESC")
    List<Transacao> obterPorTipo(String tipo);

    @Query("DELETE FROM transacoes")
    void deletarTodas();


    @Query("SELECT * FROM transacoes WHERE dataTimestamp >= :inicio AND dataTimestamp <= :fim ORDER BY dataTimestamp DESC")
    List<Transacao> obterPorPeriodo(long inicio, long fim);
}