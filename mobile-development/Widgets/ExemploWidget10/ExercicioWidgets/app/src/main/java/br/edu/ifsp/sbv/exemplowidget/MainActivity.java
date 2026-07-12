package br.edu.ifsp.sbv.exemplowidget;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private Button btnAumentar;
    private int progressoAtual = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        btnAumentar = findViewById(R.id.btnAumentar);

        btnAumentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (progressoAtual < 100) {
                    progressoAtual += 10; //
                    progressBar.setProgress(progressoAtual);
                } else {
                    Toast.makeText(MainActivity.this, "Completo", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}