package br.edu.ifsp.sbv.webservices;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONObject;
import br.edu.ifsp.sbv.webservices.dao.BatmanDatabaseDAO;
import br.edu.ifsp.sbv.webservices.modelo.Personagem;

public class MainActivity extends AppCompatActivity {

    private EditText edNomeAlvo;
    private TextView txtConsole;
    private ProgressDialog load;
    private BatmanDatabaseDAO dbHelper;

    // COLOQUE SEU TOKEN AQUI
    private final String ACCESS_TOKEN = "acd8aa2d1538a762b709cfa4afc090e6";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edNomeAlvo = findViewById(R.id.edNomeAlvo);
        txtConsole = findViewById(R.id.txtConsole);
        dbHelper = new BatmanDatabaseDAO(getApplicationContext());
    }

    public void pesquisar(View v) {
        String nomeTarget = edNomeAlvo.getText().toString().trim();
        if (!nomeTarget.isEmpty()) {
            new ScanTargetTask().execute(nomeTarget);
        } else {
            Toast.makeText(this, "Insira um codinome para pesquisa.", Toast.LENGTH_SHORT).show();
        }
    }

    public class ScanTargetTask extends AsyncTask<String, Void, Personagem> {
        private boolean isLocal = false;

        @Override
        protected void onPreExecute() {
            load = ProgressDialog.show(MainActivity.this, "Bat-Computador", "Processando diretrizes e cruzando referências táticas...");
        }

        @Override
        protected Personagem doInBackground(String... params) {
            String alvo = params[0].toLowerCase().trim();

            Personagem localTarget = dbHelper.buscarLocal(alvo);
            if (localTarget != null) {
                isLocal = true;
                return localTarget;
            }

            String url = "https://superheroapi.com/api/" + ACCESS_TOKEN + "/search/" + alvo;
            try {
                String responseJson = HttpUtils.get(url);
                JSONObject jsonObject = new JSONObject(responseJson);

                if ("success".equalsIgnoreCase(jsonObject.getString("response"))) {
                    JSONArray results = jsonObject.getJSONArray("results");
                    JSONObject firstResult = results.getJSONObject(0);

                    Personagem novoPersonagem = new Personagem(firstResult);
                    dbHelper.salvarLocal(novoPersonagem);
                    return novoPersonagem;
                }
            } catch (Exception e) {
                Log.e("BatIntel", "Varredura falhou: ", e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Personagem result) {
            load.dismiss();
            if (result != null) {
                String canal = isLocal ? "[BASE DE DADOS LOCAL - CACHED]" : "[LINK EXPLICITO - SINCERIZADO AGORA]";
                txtConsole.setText(
                        "====== RELATÓRIO INTELECTUAL " + canal + " ======\n\n" +
                                "CODINOME OFICIAL : " + result.getNome().toUpperCase() + "\n" +
                                "IDENTIDADE REAL  : " + result.getNomeReal() + "\n" +
                                "MÉTRICA INTELECTO: " + result.getInteligenca() + "/100\n\n" +
                                "DIRETRIZ TÁTICA  : PROTOCOLO DE CONTINGÊNCIA ATIVO"
                );
            } else {
                txtConsole.setText("[-] ALVO NÃO ENCONTRADO.\nA base de dados global não possui registros desse codinome.");
            }
        }
    }
}