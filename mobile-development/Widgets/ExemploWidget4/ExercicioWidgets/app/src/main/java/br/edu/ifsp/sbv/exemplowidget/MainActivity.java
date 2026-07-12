package br.edu.ifsp.sbv.exemplowidget;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerFrutas;
    private TextView tvSelecionada;
    private ImageView imgFruta;

    private String[] frutas = {"Maçã", "Laranja", "Banana"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerFrutas = findViewById(R.id.spinnerFrutas);
        tvSelecionada = findViewById(R.id.tvSelecionada);
        imgFruta = findViewById(R.id.imgFruta);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                frutas
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFrutas.setAdapter(adapter);

        spinnerFrutas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String frutaEscolhida = frutas[position];

                tvSelecionada.setText("Fruta: " + frutaEscolhida);

                // Altera a imagem no ImageView dependendo da selecaoo
                switch (frutaEscolhida) {
                    case "Maçã":
                        imgFruta.setImageResource(R.drawable.maca);
                        break;
                    case "Laranja":
                        imgFruta.setImageResource(R.drawable.laranja);
                        break;
                    case "Banana":
                        imgFruta.setImageResource(R.drawable.banana);
                        break;
                    default:
                        imgFruta.setImageDrawable(null);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                tvSelecionada.setText("Nenhuma fruta selecionada");
                imgFruta.setImageDrawable(null);
            }
        });
    }
}