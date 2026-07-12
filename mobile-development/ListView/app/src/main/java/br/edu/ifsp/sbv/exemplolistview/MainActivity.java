package br.edu.ifsp.sbv.exemplolistview;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText edtId, edtDesc, edtVal;
    private ListView lstProdutos;

    private List<Produto> listaDeProdutos = new ArrayList<>();
    private ArrayAdapter<Produto> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        edtId = findViewById(R.id.edtIdProduto);
        edtDesc = findViewById(R.id.edtDescricao);
        edtVal = findViewById(R.id.edtValor);
        lstProdutos = findViewById(R.id.lstProdutos);
        Button btnAdd = findViewById(R.id.btnAdicionar);

        // inicializa e conecta o adapter ao list view, com layour padrao
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaDeProdutos);

        lstProdutos.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int id = Integer.parseInt(edtId.getText().toString());
                String desc = edtDesc.getText().toString();
                double val = Double.parseDouble(edtVal.getText().toString());

                Produto p = new Produto(id, desc, val);

                listaDeProdutos.add(p);
                adapter.notifyDataSetChanged();

                // Limpar campos
                edtId.setText("");
                edtDesc.setText("");
                edtVal.setText("");
            }
        });
    }
}