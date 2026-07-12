package br.edu.ifsp.sbv.exemplowidget;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private TextView tvData;
    private Button btnSelecionarData;

    // 1- Variável global criada para armazenar a data para uso posterior
    private String dataArmazenada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // referncia
        tvData = findViewById(R.id.tvData);
        btnSelecionarData = findViewById(R.id.btnSelecionarData);

        // relacao clique x botao
        btnSelecionarData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar calendario = Calendar.getInstance();

                int ano = calendario.get(Calendar.YEAR);
                int mes = calendario.get(Calendar.MONTH);
                int dia = calendario.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {

                                String dataFormatada = String.format("%02d/%02d/%d", selectedDay, (selectedMonth + 1), selectedYear);
                                tvData.setText(dataFormatada);

                                // armazena em variavel, como solicitado.
                                dataArmazenada = dataFormatada;
                            }
                        }, ano, mes, dia);

                datePickerDialog.show();
            }
        });
    }
}