package br.edu.ifsp.sbv.exemplointent;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ExibirDadosActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exibir_dados);

        TextView tvResultado = (TextView) findViewById(R.id.tvResultado);

        Cliente c = (Cliente) getIntent().getSerializableExtra("dadosCliente");

        if (c != null) {
            String texto = "ID: " + c.getId() + "\nNome: " + c.getNome() + "\nIdade: " + c.getIdade();
            tvResultado.setText(texto);
        }
    }
}