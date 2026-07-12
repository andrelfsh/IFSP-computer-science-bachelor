package com.example.gestorfinanceiro;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gestorfinanceiro.data.AppDatabase;
import com.example.gestorfinanceiro.data.TransacaoDao;
import com.example.gestorfinanceiro.data.api.ApiClient;
import com.example.gestorfinanceiro.data.api.ApiService;
import com.example.gestorfinanceiro.model.Transacao;
import com.example.gestorfinanceiro.ui.TransacaoAdapter;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.navigation.NavigationView;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText etDescricao, etValor;
    private MaterialButton btnReceita, btnDespesa;
    private Button btnAdicionar;
    private AutoCompleteTextView spCategoria;

    private TextView tvSaldo, tvTotalReceitas, tvTotalDespesas;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private MaterialToolbar topAppBar;

    private String tipoSelecionado = "RECEITA";

    private AppDatabase db;
    private TransacaoDao transacaoDao;
    private TransacaoAdapter adapter;
    private final ExecutorService databaseExecutor = Executors.newSingleThreadExecutor();

    private List<Transacao> listaCompleta = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Inicializar Componentes Visuais
        etDescricao = findViewById(R.id.etDescricao);
        etValor = findViewById(R.id.etValor);
        btnReceita = findViewById(R.id.btnReceita);
        btnDespesa = findViewById(R.id.btnDespesa);
        btnAdicionar = findViewById(R.id.btnAdicionar);
        tvSaldo = findViewById(R.id.tvSaldo);
        tvTotalReceitas = findViewById(R.id.tvTotalReceitas);
        tvTotalDespesas = findViewById(R.id.tvTotalDespesas);
        spCategoria = findViewById(R.id.spCategoria);

        // Dropdown de categorias
        String[] categorias = {"Alimentação", "Transporte", "Moradia", "Lazer", "Salário", "Outros"};
        ArrayAdapter<String> adapterCategorias = new ArrayAdapter<>(this, R.layout.item_dropdown, categorias);
        spCategoria.setAdapter(adapterCategorias);
        spCategoria.setText(categorias[0], false);

        configurarSelecaoTipo();

        btnReceita.setOnClickListener(v -> {
            tipoSelecionado = "RECEITA";
            configurarSelecaoTipo();
        });

        btnDespesa.setOnClickListener(v -> {
            tipoSelecionado = "DESPESA";
            configurarSelecaoTipo();
        });

        // 2. Configurar RecyclerView
        RecyclerView rvTransacoes = findViewById(R.id.rvTransacoes);
        rvTransacoes.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TransacaoAdapter();
        rvTransacoes.setAdapter(adapter);

        // Swipe to delete
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

                    @Override
                    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                        int posicao = viewHolder.getAdapterPosition();
                        Transacao transacaoParaApagar = adapter.getTransacaoNaPosicao(posicao);

                        databaseExecutor.execute(() -> {
                            transacaoDao.deletar(transacaoParaApagar);
                            runOnUiThread(() -> {
                                atualizarListaHistorico();
                                Toast.makeText(MainActivity.this, "Transação apagada!", Toast.LENGTH_SHORT).show();
                            });
                        });
                    }
                });
        itemTouchHelper.attachToRecyclerView(rvTransacoes);

        // 3. Banco de Dados
        db = AppDatabase.getDatabase(this);
        transacaoDao = db.transacaoDao();

        // 4. Carregar dados
        atualizarListaHistorico();

        // 5. Botão Salvar
        btnAdicionar.setOnClickListener(v -> salvarTransacao());

        // 6. Drawer + Toolbar
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        topAppBar = findViewById(R.id.topAppBar);

        setSupportActionBar(topAppBar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, topAppBar, R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(android.R.color.white));

        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_filtro_receitas) {
                aplicarFiltroHistorico("RECEITA");
            } else if (id == R.id.nav_filtro_despesas) {
                aplicarFiltroHistorico("DESPESA");
            } else if (id == R.id.nav_filtro_tudo || id == R.id.nav_inicio) {
                aplicarFiltroHistorico("TUDO");
            } else if (id == R.id.nav_relatorios) {
                android.content.Intent intent = new android.content.Intent(MainActivity.this, RelatoriosActivity.class);
                startActivity(intent);
            } else if (id == R.id.nav_exportar) {
                exportarPlanilhaCSV();
            } else if (id == R.id.nav_limpar) {
                confirmarLimpezaDados();
            } else if (id == R.id.nav_backup) {
                realizarBackupNaNuvem();
            }

            item.setChecked(true);
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });
    }

    // ── BACKUP NA NUVEM ──

    private void realizarBackupNaNuvem() {
        android.util.Log.d("TESTE_BACKUP", "Iniciando processo de backup...");

        databaseExecutor.execute(() -> {
            List<Transacao> listaLocal = transacaoDao.obterTodas();

            if (listaLocal.isEmpty()) {
                runOnUiThread(() -> Toast.makeText(MainActivity.this, "Nada para sincronizar.", Toast.LENGTH_SHORT).show());
                return;
            }

            ApiService apiService = ApiClient.getApiService();

            final int total = listaLocal.size();
            final int[] enviados = {0};
            final int[] falhas = {0};
            final List<String> detalhesErros = new ArrayList<>();

            runOnUiThread(() -> Toast.makeText(MainActivity.this,
                    "Enviando " + total + " transações...", Toast.LENGTH_SHORT).show());

            for (Transacao t : listaLocal) {
                apiService.enviarTransacaoNuvem(t).enqueue(new Callback<Transacao>() {
                    @Override
                    public void onResponse(Call<Transacao> call, Response<Transacao> response) {
                        if (response.isSuccessful()) {
                            enviados[0]++;
                        } else {
                            falhas[0]++;
                            String erro = "Erro " + response.code() + ": " + response.message();
                            android.util.Log.e("DEBUG_BACKUP", erro);
                            detalhesErros.add(erro);
                        }
                        verificarConclusao(total, enviados[0], falhas[0], detalhesErros);
                    }

                    @Override
                    public void onFailure(Call<Transacao> call, Throwable t2) {
                        falhas[0]++;
                        String erro = "Falha de rede: " + t2.getMessage();
                        android.util.Log.e("DEBUG_BACKUP", erro, t2);
                        detalhesErros.add(erro);
                        verificarConclusao(total, enviados[0], falhas[0], detalhesErros);
                    }
                });
            }
        });
    }

    private void verificarConclusao(int total, int enviados, int falhas, List<String> detalhesErros) {
        if (enviados + falhas == total) {
            runOnUiThread(() -> {
                if (falhas == 0) {
                    Toast.makeText(MainActivity.this,
                            "Backup concluído! " + enviados + " transações enviadas.", Toast.LENGTH_LONG).show();
                } else {
                    StringBuilder mensagem = new StringBuilder();
                    mensagem.append(enviados).append(" enviadas, ").append(falhas).append(" falharam.\n\n");

                    // Mostra só as primeiras 5 mensagens de erro para não poluir a tela
                    int limite = Math.min(detalhesErros.size(), 5);
                    for (int i = 0; i < limite; i++) {
                        mensagem.append("• ").append(detalhesErros.get(i)).append("\n");
                    }
                    if (detalhesErros.size() > limite) {
                        mensagem.append("... e mais ").append(detalhesErros.size() - limite).append(" erro(s).");
                    }

                    new android.app.AlertDialog.Builder(MainActivity.this)
                            .setTitle("Erro no Backup")
                            .setMessage(mensagem.toString())
                            .setPositiveButton("OK", null)
                            .show();
                }
            });
        }
    }


    // ── FILTROS E SELEÇÃO ──

    private void aplicarFiltroHistorico(String tipo) {
        if (tipo.equals("TUDO")) {
            adapter.setTransacoes(listaCompleta);
            return;
        }

        List<Transacao> listaFiltrada = new ArrayList<>();
        for (Transacao t : listaCompleta) {
            if (t.getTipo().equals(tipo)) {
                listaFiltrada.add(t);
            }
        }
        adapter.setTransacoes(listaFiltrada);
    }

    private void configurarSelecaoTipo() {
        boolean isReceita = "RECEITA".equals(tipoSelecionado);
        btnReceita.setSelected(isReceita);
        btnDespesa.setSelected(!isReceita);
    }

    // ── CRUD LOCAL ──

    private void salvarTransacao() {
        String descricao = etDescricao.getText().toString().trim();
        String valorStr = etValor.getText().toString().trim();

        if (TextUtils.isEmpty(descricao) || TextUtils.isEmpty(valorStr)) {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            return;
        }

        double valor;
        try {
            valor = Double.parseDouble(valorStr.replace(",", "."));
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Valor inválido!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (valor <= 0) {
            Toast.makeText(this, "O valor deve ser maior que zero!", Toast.LENGTH_SHORT).show();
            return;
        }

        String categoria = spCategoria.getText().toString().trim();
        if (categoria.isEmpty()) {
            categoria = "Outros";
        }

        Transacao novaTransacao = new Transacao(descricao, valor, System.currentTimeMillis(), tipoSelecionado, categoria);

        databaseExecutor.execute(() -> {
            transacaoDao.inserir(novaTransacao);

            runOnUiThread(() -> {
                Toast.makeText(MainActivity.this, "Salvo com sucesso!", Toast.LENGTH_SHORT).show();
                etDescricao.setText("");
                etValor.setText("");
                atualizarListaHistorico();
            });
        });
    }

    private void atualizarListaHistorico() {
        databaseExecutor.execute(() -> {
            listaCompleta = transacaoDao.obterTodas();

            double totalReceitas = 0.0;
            double totalDespesas = 0.0;

            for (Transacao t : listaCompleta) {
                if ("RECEITA".equals(t.getTipo())) {
                    totalReceitas += t.getValor();
                } else if ("DESPESA".equals(t.getTipo())) {
                    totalDespesas += t.getValor();
                }
            }

            double saldoFinal = totalReceitas - totalDespesas;

            final double viewReceitas = totalReceitas;
            final double viewDespesas = totalDespesas;
            final double viewSaldo = saldoFinal;

            runOnUiThread(() -> {
                adapter.setTransacoes(listaCompleta);

                tvTotalReceitas.setText(formatarMoeda(viewReceitas));
                tvTotalDespesas.setText(formatarMoeda(viewDespesas));
                tvSaldo.setText(formatarMoeda(viewSaldo));
            });
        });
    }

    private String formatarMoeda(double valor) {
        return String.format(Locale.getDefault(), "R$ %.2f", valor);
    }

    // ── AÇÕES DO SISTEMA ──

    private void confirmarLimpezaDados() {
        new android.app.AlertDialog.Builder(this)
                .setTitle("Apagar Tudo?")
                .setMessage("Tem certeza que deseja apagar todo o histórico financeiro? Esta ação não pode ser desfeita.")
                .setPositiveButton("Sim, Limpar", (dialog, which) -> {
                    databaseExecutor.execute(() -> {
                        transacaoDao.deletarTodas();
                        runOnUiThread(() -> {
                            atualizarListaHistorico();
                            Toast.makeText(MainActivity.this, "Dados apagados com sucesso!", Toast.LENGTH_SHORT).show();
                        });
                    });
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }

    private void exportarPlanilhaCSV() {
        databaseExecutor.execute(() -> {
            List<Transacao> lista = transacaoDao.obterTodas();

            if (lista.isEmpty()) {
                runOnUiThread(() -> Toast.makeText(MainActivity.this, "Não há dados para exportar.", Toast.LENGTH_SHORT).show());
                return;
            }

            StringBuilder csv = new StringBuilder();
            csv.append("Data,Descrição,Tipo,Categoria,Valor\n");

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

            for (Transacao t : lista) {
                String descricaoEscapada = "\"" + t.getDescricao().replace("\"", "\"\"") + "\"";

                csv.append(sdf.format(t.getDataTimestamp())).append(",")
                        .append(descricaoEscapada).append(",")
                        .append(t.getTipo()).append(",")
                        .append(t.getCategoria()).append(",")
                        .append(String.format(Locale.US, "%.2f", t.getValor())).append("\n");
            }

            try {
                File pastaDownloads = android.os.Environment.getExternalStoragePublicDirectory(android.os.Environment.DIRECTORY_DOWNLOADS);
                File arquivoCSV = new File(pastaDownloads, "Relatorio_GestorFinanceiro.csv");

                try (FileWriter writer = new FileWriter(arquivoCSV)) {
                    writer.append(csv.toString());
                    writer.flush();
                }

                runOnUiThread(() -> Toast.makeText(MainActivity.this, "Planilha salva na pasta Downloads!", Toast.LENGTH_LONG).show());
            } catch (Exception e) {
                e.printStackTrace();
                runOnUiThread(() -> Toast.makeText(MainActivity.this, "Erro ao salvar planilha: " + e.getMessage(), Toast.LENGTH_LONG).show());
            }
        });

    }

}