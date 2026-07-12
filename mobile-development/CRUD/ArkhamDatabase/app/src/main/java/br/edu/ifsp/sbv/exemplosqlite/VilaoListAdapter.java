package br.edu.ifsp.sbv.exemplosqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;
import br.edu.ifsp.sbv.exemplosqlite.modelo.Vilao;

public class VilaoListAdapter extends BaseAdapter {

    private Context context;
    private List<Vilao> lista;

    public VilaoListAdapter(Context context, List<Vilao> lista) {
        this.context = context;
        this.lista = lista;
    }

    public int getCount() { return lista.size(); }
    public Object getItem(int position) { return lista.get(position); }
    public long getItemId(int position) { return position; }

    public View getView(int position, View convertView, ViewGroup parent) {
        Vilao v = lista.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.viloes, null);

        TextView id = view.findViewById(R.id.txtIdVilao);
        TextView nome = view.findViewById(R.id.txtNomeVilao);
        TextView perigo = view.findViewById(R.id.txtPerigoVilao);

        id.setText("ID: " + v.getId());
        nome.setText("Alvo: " + v.getNome());
        perigo.setText("Nível de Ameaça: " + v.getPerigo() + "/5");

        return view;
    }
}