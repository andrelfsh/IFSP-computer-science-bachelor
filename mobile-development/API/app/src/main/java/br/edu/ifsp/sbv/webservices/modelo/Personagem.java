package br.edu.ifsp.sbv.webservices.modelo;

import org.json.JSONException;
import org.json.JSONObject;

public class Personagem {
    private String nome;
    private String inteligenca;
    private String nomeReal;

    public Personagem(JSONObject json) throws JSONException {
        this.nome = json.getString("name");

        JSONObject stats = json.getJSONObject("powerstats");
        this.inteligenca = stats.optString("intelligence", "n/a");

        JSONObject bio = json.getJSONObject("biography");
        this.nomeReal = bio.optString("full-name", "Desconhecido");
    }

    public Personagem() {}

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getInteligenca() { return inteligenca; }
    public void setInteligenca(String inteligenca) { this.inteligenca = inteligenca; }
    public String getNomeReal() { return nomeReal; }
    public void setNomeReal(String nomeReal) { this.nomeReal = nomeReal; }
}