package br.edu.ifsp.sbv.exemplowidget;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnContext = findViewById(R.id.btnContext);

        registerForContextMenu(btnContext);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.add(0, 1, 0, "Copiar");
        menu.add(0, 2, 0, "Colar");
        menu.add(0, 3, 0, "Excluir");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case 1:
                Toast.makeText(this, "Ação: Copiar", Toast.LENGTH_SHORT).show();
                return true;
            case 2:
                Toast.makeText(this, "Ação: Colar", Toast.LENGTH_SHORT).show();
                return true;
            case 3:
                Toast.makeText(this, "Ação: Excluir", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}