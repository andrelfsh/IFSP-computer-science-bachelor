package br.edu.ifsp.sbv.exemplowidget;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnEscolherCor;
    private LinearLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // componentes da tela
        btnEscolherCor = findViewById(R.id.btnEscolherCor);
        mainLayout = findViewById(R.id.mainLayout);

        btnEscolherCor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String[] cores = {"Vermelho", "Verde", "Azul"};

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Escolha uma cor de fundo");

                builder.setItems(cores, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                mainLayout.setBackgroundColor(Color.RED);
                                break;
                            case 1:
                                mainLayout.setBackgroundColor(Color.GREEN);
                                break;
                            case 2:
                                mainLayout.setBackgroundColor(Color.BLUE);
                                break;
                        }
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
}