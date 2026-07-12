package br.edu.ifsp.sbv.exemplowidget;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_configuracoes) {
            Toast.makeText(this, "Você selecionou Configurações", Toast.LENGTH_SHORT).show();
            return true;


        } else if (id == R.id.action_perfil) {
            Toast.makeText(this, "Você selecionou Perfil", Toast.LENGTH_SHORT).show();
            return true;

        } else if (id == R.id.action_sair) {
            Toast.makeText(this, "Você selecionou Sair", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}