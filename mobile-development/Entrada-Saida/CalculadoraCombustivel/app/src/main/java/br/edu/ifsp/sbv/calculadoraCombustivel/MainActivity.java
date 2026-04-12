package br.edu.ifsp.sbv.calculadoraCombustivel;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText edN01; // Etanol
    private EditText edN02; // Gasolina

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edN01 = (EditText) findViewById(R.id.edtN01);
        edN02 = (EditText) findViewById(R.id.edtN02);
    }

    public void calcularCombustivel(View v) {

        float precoEtanol = Float.parseFloat(edN01.getText().toString());
        float precoGasolina = Float.parseFloat(edN02.getText().toString());
        float resultado = precoEtanol / precoGasolina;
        float percentual = resultado * 100;

        String mensagem;

        if (precoEtanol < precoGasolina) {
            mensagem = "Use Etanol";
        } else if (precoEtanol < precoGasolina) {
            mensagem = "Use gasolina";
        } else {
            mensagem = "Mesmo preço";
        }

        Toast.makeText(this, mensagem + " (Relação: " + percentual + "%)", Toast.LENGTH_LONG).show();
    }
}