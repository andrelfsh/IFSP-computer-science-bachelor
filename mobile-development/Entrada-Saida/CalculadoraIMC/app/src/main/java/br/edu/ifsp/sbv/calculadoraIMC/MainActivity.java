package br.edu.ifsp.sbv.calculadoraIMC;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText edN01; // Peso
    private EditText edN02; // Altura

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edN01 = (EditText) findViewById(R.id.edtN01);
        edN02 = (EditText) findViewById(R.id.edtN02);
    }

    public void calcular(View v) {
        float n01 = Float.parseFloat(edN01.getText().toString());
        float n02 = Float.parseFloat(edN02.getText().toString());

        float res = n01 / (n02 * n02);

        Toast.makeText(this, "O IMC é: " + res, Toast.LENGTH_LONG).show();
    }
}