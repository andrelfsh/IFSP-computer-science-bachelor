package br.edu.ifsp.sbv.calculadoraTriangulo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText edN01; // Base
    private EditText edN02; // Altura

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edN01 = (EditText) findViewById(R.id.edtN01);
        edN02 = (EditText) findViewById(R.id.edtN02);
    }

    public void calcularArea(View v) {

        float base = Float.parseFloat(edN01.getText().toString());
        float altura = Float.parseFloat(edN02.getText().toString());
        float area = (base * altura) / 2;

        Toast.makeText(this, "Área: " + area, Toast.LENGTH_LONG).show();
    }
}