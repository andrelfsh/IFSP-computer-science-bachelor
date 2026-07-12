package com.example.gestorfinanceiro.ui;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.gestorfinanceiro.R;
import com.example.gestorfinanceiro.model.Transacao;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TransacaoAdapter extends RecyclerView.Adapter<TransacaoAdapter.TransacaoViewHolder> {

    private List<Transacao> transacoes = new ArrayList<>();

    public void setTransacoes(List<Transacao> transacoes) {
        this.transacoes = transacoes;
        notifyDataSetChanged();
    }


    public Transacao getTransacaoNaPosicao(int posicao) {
        return transacoes.get(posicao);
    }
    @NonNull
    @Override
    public TransacaoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_transacao, parent, false);
        return new TransacaoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransacaoViewHolder holder, int position) {
        Transacao transacao = transacoes.get(position);

        if (holder.tvDescricao != null) {
            holder.tvDescricao.setText(transacao.getDescricao());
        }

        if (holder.tvValor != null) {
            if ("RECEITA".equals(transacao.getTipo())) {
                holder.tvValor.setText(String.format(Locale.getDefault(), "+ R$ %.2f", transacao.getValor()));
                holder.tvValor.setTextColor(Color.parseColor("#2E7D32"));
            } else {
                holder.tvValor.setText(String.format(Locale.getDefault(), "- R$ %.2f", transacao.getValor()));
                holder.tvValor.setTextColor(Color.parseColor("#C62828"));
            }
        }

        if (holder.tvData != null) {
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            String dataFormatada = sdf.format(new java.util.Date(transacao.getDataTimestamp()));
            holder.tvData.setText(dataFormatada);
        }

        // 🟢 AQUI ESTÁ A CORREÇÃO
        if (holder.tvCategoriaItem != null) {
            holder.tvCategoriaItem.setText(transacao.getCategoria());
        }
    }

    @Override
    public int getItemCount() {
        return transacoes.size();
    }

    static class TransacaoViewHolder extends RecyclerView.ViewHolder {
        // 🟢 Adicione o tvCategoriaItem aqui
        TextView tvDescricao, tvValor, tvData, tvCategoriaItem;

        public TransacaoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDescricao = itemView.findViewById(R.id.tvDescricao);
            tvValor = itemView.findViewById(R.id.tvValor);
            tvData = itemView.findViewById(R.id.tvData);

            // 🟢 Capture o ID que você definiu no item_transacao.xml
            tvCategoriaItem = itemView.findViewById(R.id.tvCategoriaItem);
        }
    }
}