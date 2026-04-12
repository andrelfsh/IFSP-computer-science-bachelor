package br.edu.ifsp.sbv.calculadoraCompleta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private EditText edN01;
    private EditText edN02;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edN01 = (EditText) findViewById(R.id.edtN01);
        edN02 = (EditText) findViewById(R.id.edtN02);

    }

    public void somar (View v)
    {
        int n01 = Integer.parseInt(edN01.getText().toString());
        int n02 = Integer.parseInt(edN02.getText().toString());

        int res = n01+n02;

        Toast.makeText(this,"O resultado é: "+res,Toast.LENGTH_LONG).show();

    }

    public void subtrair(View v) {
        int n01 = Integer.parseInt(edN01.getText().toString());
        int n02 = Integer.parseInt(edN02.getText().toString());
        int res = n01 - n02;
        Toast.makeText(this, "O resultado é: " + res, Toast.LENGTH_LONG).show();
    }

    public void dividir(View v) {
        int n01 = Integer.parseInt(edN01.getText().toString());
        int n02 = Integer.parseInt(edN02.getText().toString());

        if (n02 != 0) {
            int res = n01 / n02;
            Toast.makeText(this, "O resultado é: " + res, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Erro! Operação inválida", Toast.LENGTH_LONG).show();
        }
    }

    public void multiplicar(View v) {
        int n01 = Integer.parseInt(edN01.getText().toString());
        int n02 = Integer.parseInt(edN02.getText().toString());
        int res = n01 * n02;
        Toast.makeText(this, "O resultado é: " + res, Toast.LENGTH_LONG).show();
    }

}