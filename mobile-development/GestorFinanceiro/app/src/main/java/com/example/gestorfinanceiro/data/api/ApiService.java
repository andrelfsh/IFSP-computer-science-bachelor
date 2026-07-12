package com.example.gestorfinanceiro.data.api;

import com.example.gestorfinanceiro.model.Transacao;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @GET("backup")
    Call<List<Transacao>> obterBackupNuvem();

    @POST("backup")
    Call<Transacao> enviarTransacaoNuvem(@Body Transacao transacao);
}