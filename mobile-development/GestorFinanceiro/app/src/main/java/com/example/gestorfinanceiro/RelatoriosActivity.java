package com.example.gestorfinanceiro;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.gestorfinanceiro.data.AppDatabase;
import com.example.gestorfinanceiro.data.TransacaoDao;
import com.example.gestorfinanceiro.model.Transacao;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.google.android.material.appbar.MaterialToolbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RelatoriosActivity extends AppCompatActivity {

    private PieChart graficoPizza;
    private LineChart graficoLinha;
    private TextView tvResumoReceitas, tvResumoDespesas;
    private LinearLayout legendaPizza;
    private TransacaoDao transacaoDao;
    private final ExecutorService databaseExecutor = Executors.newSingleThreadExecutor();
    private final List<String> labelsEixoX = new ArrayList<>();

    // Mesma paleta usada nas fatias da pizza — fonte única de verdade para as cores
    private static final String[] PALETA_CATEGORIAS = {
            "#EF5350", "#FFB74D", "#4FC3F7", "#AED581", "#BA68C8", "#90A4AE"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorios);

        MaterialToolbar toolbar = findViewById(R.id.toolbarRelatorios);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> getOnBackPressedDispatcher().onBackPressed());

        graficoPizza = findViewById(R.id.graficoPizza);
        graficoLinha = findViewById(R.id.graficoLinha);
        tvResumoReceitas = findViewById(R.id.tvResumoReceitas);
        tvResumoDespesas = findViewById(R.id.tvResumoDespesas);
        legendaPizza = findViewById(R.id.legendaPizza);

        configurarEstiloGraficos();
        transacaoDao = AppDatabase.getDatabase(this).transacaoDao();
        carregarDadosEPlotar();
    }

    private void configurarEstiloGraficos() {
        graficoPizza.getDescription().setEnabled(false);
        graficoPizza.getLegend().setEnabled(false); // legenda nativa desligada; usamos a nossa customizada
        graficoPizza.setDrawEntryLabels(false);
        graficoPizza.setUsePercentValues(true);
        graficoPizza.setDrawHoleEnabled(true);
        graficoPizza.setHoleColor(Color.WHITE);
        graficoPizza.setHoleRadius(58f);
        graficoPizza.setTransparentCircleRadius(0f);
        graficoPizza.setExtraOffsets(8f, 8f, 8f, 8f);

        graficoLinha.getDescription().setEnabled(false);
        graficoLinha.getLegend().setEnabled(false);
        graficoLinha.setDrawGridBackground(false);

        XAxis xAxis = graficoLinha.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f);
        xAxis.setTextColor(Color.parseColor("#6B6B67"));
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                int i = (int) value;
                return (i >= 0 && i < labelsEixoX.size()) ? labelsEixoX.get(i) : "";
            }
        });
        graficoLinha.getAxisLeft().setTextColor(Color.parseColor("#6B6B67"));
        graficoLinha.getAxisRight().setEnabled(false);
    }

    private void carregarDadosEPlotar() {
        databaseExecutor.execute(() -> {
            List<Transacao> lista = transacaoDao.obterTodas();
            double totalReceitas = 0, totalDespesas = 0;

            // LinkedHashMap preserva a ordem de inserção -> garante que a legenda
            // siga exatamente a mesma ordem das fatias desenhadas no gráfico
            Map<String, Double> despesasPorCategoria = new LinkedHashMap<>();

            for (Transacao t : lista) {
                if ("RECEITA".equals(t.getTipo())) {
                    totalReceitas += t.getValor();
                } else {
                    totalDespesas += t.getValor();
                    String cat = (t.getCategoria() == null || t.getCategoria().isEmpty()) ? "Outros" : t.getCategoria();
                    despesasPorCategoria.put(cat, despesasPorCategoria.getOrDefault(cat, 0.0) + t.getValor());
                }
            }

            final double viewReceitas = totalReceitas, viewDespesas = totalDespesas;
            Collections.sort(lista, (t1, t2) -> Long.compare(t1.getDataTimestamp(), t2.getDataTimestamp()));

            List<Entry> entradasLinha = new ArrayList<>();
            List<String> labelsTemp = new ArrayList<>();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM", Locale.getDefault());
            double saldoAcumulado = 0;
            int index = 0;

            entradasLinha.add(new Entry(0, 0f));
            labelsTemp.add("Início");

            for (Transacao t : lista) {
                saldoAcumulado += ("RECEITA".equals(t.getTipo())) ? t.getValor() : -t.getValor();
                entradasLinha.add(new Entry(++index, (float) saldoAcumulado));
                labelsTemp.add(sdf.format(t.getDataTimestamp()));
            }

            runOnUiThread(() -> {
                labelsEixoX.clear();
                labelsEixoX.addAll(labelsTemp);
                plotarPizza(despesasPorCategoria);
                plotarLinha(entradasLinha);
                tvResumoReceitas.setText(formatarMoeda(viewReceitas));
                tvResumoDespesas.setText(formatarMoeda(viewDespesas));
            });
        });
    }

    private void plotarPizza(Map<String, Double> categorias) {
        List<PieEntry> fatias = new ArrayList<>();
        List<Integer> cores = new ArrayList<>();
        List<String> nomesOrdenados = new ArrayList<>();

        if (categorias.isEmpty()) {
            fatias.add(new PieEntry(1f, "Sem Dados"));
            cores.add(Color.parseColor("#E0E0E0"));
            nomesOrdenados.add("Sem Dados");
        } else {
            int i = 0;
            for (Map.Entry<String, Double> e : categorias.entrySet()) {
                fatias.add(new PieEntry(e.getValue().floatValue(), e.getKey()));
                cores.add(Color.parseColor(PALETA_CATEGORIAS[i % PALETA_CATEGORIAS.length]));
                nomesOrdenados.add(e.getKey());
                i++;
            }
        }

        PieDataSet dataSet = new PieDataSet(fatias, "");
        dataSet.setColors(cores);
        dataSet.setValueTextColor(Color.WHITE);
        dataSet.setValueTextSize(12f);
        dataSet.setSliceSpace(3f);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter(graficoPizza));
        graficoPizza.setData(data);
        graficoPizza.animateY(600);
        graficoPizza.invalidate();

        construirLegendaDinamica(nomesOrdenados, cores);
    }


    private void construirLegendaDinamica(List<String> nomes, List<Integer> cores) {
        legendaPizza.removeAllViews();

        LinearLayout linhaAtual = null;

        for (int i = 0; i < nomes.size(); i++) {
            // Cria uma nova linha a cada 2 itens
            if (i % 2 == 0) {
                linhaAtual = new LinearLayout(this);
                linhaAtual.setOrientation(LinearLayout.HORIZONTAL);
                linhaAtual.setGravity(Gravity.CENTER);
                LinearLayout.LayoutParams paramsLinha = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                paramsLinha.topMargin = dpParaPx(6);
                legendaPizza.addView(linhaAtual, paramsLinha);
            }

            // Bolinha colorida
            View bolinha = new View(this);
            int tamanhoBolinha = dpParaPx(10);
            LinearLayout.LayoutParams paramsBolinha = new LinearLayout.LayoutParams(tamanhoBolinha, tamanhoBolinha);
            paramsBolinha.gravity = Gravity.CENTER_VERTICAL;
            paramsBolinha.rightMargin = dpParaPx(6);
            paramsBolinha.leftMargin = dpParaPx(12);

            GradientDrawable forma = new GradientDrawable();
            forma.setShape(GradientDrawable.OVAL);
            forma.setColor(cores.get(i));
            bolinha.setBackground(forma);
            bolinha.setLayoutParams(paramsBolinha);

            // Texto com o nome da categoria
            TextView texto = new TextView(this);
            texto.setText(nomes.get(i));
            texto.setTextSize(13f);
            texto.setTextColor(Color.parseColor("#6B6B67"));
            LinearLayout.LayoutParams paramsTexto = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            paramsTexto.rightMargin = dpParaPx(12);
            texto.setLayoutParams(paramsTexto);

            if (linhaAtual != null) {
                linhaAtual.addView(bolinha);
                linhaAtual.addView(texto);
            }
        }
    }

    private int dpParaPx(int dp) {
        float densidade = getResources().getDisplayMetrics().density;
        return (int) (dp * densidade);
    }

    private void plotarLinha(List<Entry> entradas) {
        LineDataSet dataSet = new LineDataSet(entradas, "Saldo");
        dataSet.setColor(Color.parseColor("#1D9E75"));
        dataSet.setCircleColor(Color.parseColor("#1D9E75"));
        dataSet.setLineWidth(2.5f);
        dataSet.setCircleRadius(4f);
        dataSet.setDrawCircleHole(true);
        dataSet.setCircleHoleColor(Color.WHITE);
        dataSet.setDrawValues(false);
        dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        dataSet.setCubicIntensity(0.15f);
        dataSet.setDrawFilled(true);
        dataSet.setFillColor(Color.parseColor("#1D9E75"));
        dataSet.setFillAlpha(30);
        dataSet.setDrawHorizontalHighlightIndicator(false);
        dataSet.setDrawVerticalHighlightIndicator(false);

        graficoLinha.setData(new LineData(dataSet));
        graficoLinha.animateX(600);
        graficoLinha.invalidate();
    }

    private String formatarMoeda(double v) {
        return String.format(Locale.US, "R$ %,.2f", v).replace(",", "@").replace(".", ",").replace("@", ".");
    }
}