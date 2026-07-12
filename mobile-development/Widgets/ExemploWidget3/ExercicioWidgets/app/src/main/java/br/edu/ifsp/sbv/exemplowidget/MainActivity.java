package br.edu.ifsp.sbv.exemplowidget;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerFrutas;
    private TextView tvSelecionada;
    private String[] frutas = {"Maçã", "Laranja", "Banana"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerFrutas = findViewById(R.id.spinnerFrutas);
        tvSelecionada = findViewById(R.id.tvSelecionada);

        // arrayAdapter para preencher o Spinner, usa laytou padrao para os itens
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                frutas
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerFrutas.setAdapter(adapter);

        // 5- Configura o evento de seleção do usuário
        spinnerFrutas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String frutaEscolhida = frutas[position];
                tvSelecionada.setText("Fruta: " + frutaEscolhida);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Tratamento caso nenhum item seja selecionado
                tvSelecionada.setText("Nenhuma fruta selecionada");
            }
        });
    }
}