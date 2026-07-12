package br.edu.ifsp.sbv.exemplosqlite;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.List;
import br.edu.ifsp.sbv.exemplosqlite.dao.VilaoDAO;
import br.edu.ifsp.sbv.exemplosqlite.modelo.Vilao;

public class MainActivity extends androidx.appcompat.app.AppCompatActivity {

    private Vilao vilaoAtual;
    private List<Vilao> listaViloes;
    private VilaoDAO dao;
    private EditText edID, edNome, edPerigo;
    private ListView lvViloes;
    private String operacao = "Novo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        edID = findViewById(R.id.edID);
        edNome = findViewById(R.id.edNome);
        edPerigo = findViewById(R.id.edPerigo);
        lvViloes = findViewById(R.id.lvViloes);

        dao = new VilaoDAO(getApplicationContext());

        lvViloes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                operacao = "Atualizar";
                vilaoAtual = listaViloes.get(position);
                edID.setText(String.valueOf(vilaoAtual.getId()));
                edNome.setText(vilaoAtual.getNome());
                edPerigo.setText(String.valueOf(vilaoAtual.getPerigo()));
            }
        });

        lvViloes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                confirmarExclusao(listaViloes.get(position).getId());
                return true;
            }
        });

        atualizarLista();
    }

    private void confirmarExclusao(final int idVilao) {
        new AlertDialog.Builder(this)
                .setTitle("Transferência Necessária")
                .setMessage("Deseja eliminar este registro de Arkham?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (dao.deletar(idVilao)) {
                            atualizarLista();
                            Toast.makeText(MainActivity.this, "Registro apagado.", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("Não", null)
                .show();
    }

    public void salvar(View v) {
        if (edNome.getText().toString().isEmpty() || edPerigo.getText().toString().isEmpty()) {
            Toast.makeText(this, "Preencha todos os dados criminais!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (operacao.equalsIgnoreCase("Novo")) {
            vilaoAtual = new Vilao();
        }

        vilaoAtual.setNome(edNome.getText().toString());
        vilaoAtual.setPerigo(Integer.valueOf(edPerigo.getText().toString()));

        if (operacao.equalsIgnoreCase("Novo")) {
            dao.salvar(vilaoAtual);
            Toast.makeText(this, "Vilão enclausurado em Arkham!", Toast.LENGTH_SHORT).show();
        } else {
            dao.atualizar(vilaoAtual);
            Toast.makeText(this, "Ficha criminal atualizada!", Toast.LENGTH_SHORT).show();
        }

        atualizarLista();
        limparCampos();
    }

    public void novo(View v) {
        operacao = "Novo";
        limparCampos();
    }

    private void limparCampos() {
        edID.setText("");
        edNome.setText("");
        edPerigo.setText("");
        edNome.requestFocus();
    }

    private void atualizarLista() {
        listaViloes = dao.listAll();
        VilaoListAdapter adapter = new VilaoListAdapter(getApplicationContext(), listaViloes);
        lvViloes.setAdapter(adapter);
    }
}