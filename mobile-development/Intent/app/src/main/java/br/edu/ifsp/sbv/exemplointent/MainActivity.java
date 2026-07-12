package br.edu.ifsp.sbv.exemplointent;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText edtId, edtNome, edtIdade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // mapeia
        edtId = (EditText) findViewById(R.id.edtId);
        edtNome = (EditText) findViewById(R.id.edtNome);
        edtIdade = (EditText) findViewById(R.id.edtIdade);

        Button btnCadastrar = (Button) findViewById(R.id.btnCadastrar);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // novo objeto
                int id = Integer.parseInt(edtId.getText().toString());
                String nome = edtNome.getText().toString();
                int idade = Integer.parseInt(edtIdade.getText().toString());

                Cliente clienteNovo = new Cliente(id, nome, idade);

                // Intent para abrir a exibição (ExibirDadosActivity).
                Intent it = new Intent(getApplicationContext(), ExibirDadosActivity.class);

                it.putExtra("dadosCliente", clienteNovo);

                startActivity(it);
            }
        });
    }
}