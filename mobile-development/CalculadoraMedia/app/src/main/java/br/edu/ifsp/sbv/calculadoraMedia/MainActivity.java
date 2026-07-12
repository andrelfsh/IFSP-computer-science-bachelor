package br.edu.ifsp.sbv.calculadoraMedia;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText edN01, edN02, edN03, edN04;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edN01 = (EditText) findViewById(R.id.edtN01);
        edN02 = (EditText) findViewById(R.id.edtN02);
        edN03 = (EditText) findViewById(R.id.edtN03);
        edN04 = (EditText) findViewById(R.id.edtN04);
    }

    public void calcularMedia(View v) {
        float n1 = Float.parseFloat(edN01.getText().toString());
        float n2 = Float.parseFloat(edN02.getText().toString());
        float n3 = Float.parseFloat(edN03.getText().toString());
        float n4 = Float.parseFloat(edN04.getText().toString());

        float media = (n1 + n2 + n3 + n4) / 4;

        String status;
        if (media >= 6) {
            status = "Aprovado!";
        } else if (media >= 4) {
            status = "Exame!";
        } else {
            status = "Reprovado!";
        }

        Toast.makeText(this, "Média: " + media + " - Status: " + status, Toast.LENGTH_LONG).show();
    }
}