package com.mycompany.calculadora2;

/**
 * Autores: André Lyra Fernandes && Victoria Carolina Ferreira da Silva
 */

import java.io.IOException;
import javafx.fxml.FXML;

//classe com botões utilizados na interface, sendo métodos que passam de interface
//e métodos que adicionam atributos as  variaveis utilizadas para o calculo,
//que serão utilizados na classe "cálculo"

public class Controladores {

    private Calculo calculo;

    public Controladores() {
        calculo = new Calculo(this); // Passa a instância atual de Controladores
    }
    
    @FXML //static, para aparecer no gráfico
    private static double gastoMensalEletricidade; //botão para inserir valor
    private static double gastoMensalGas; //botão para inserir valor
    private static double gastoMensalOleo; //botão para inserir valor
    private static double gastoMensalCarro; //botão para inserir valor
    private static double gastoMensalVoosMenos; //botão para inserir valor
    private static double gastoMensalVoosMais; //botão para inserir valor
    private static boolean gastoMensalJornal; //botão para inserir valor (sim ou não, portanto, boolean)
    private static boolean gastoMensalAluminioEstanho; //botão para inserir valor (sim ou não, portanto, boolean)  
    
    //botões para incrementar, de acordo com valores do usuário
    //botões para eletricidade
    @FXML
    public void botaoUmEletricidade() {
        gastoMensalEletricidade = 25; //média entre 0 e 50
    } 
    
    @FXML
    public void botaoDoisEletricidade() {
        gastoMensalEletricidade = 75; //media entre 50 e 100
    } 
    
    @FXML
    public void botaoTresEletricidade() {
        gastoMensalEletricidade = 125; //media entre 100 e 150
    } 
    
    @FXML
    public void botaoQuatroEletricidade() {
        gastoMensalEletricidade = 175; //media entre 150 e 200
    } 
    
    @FXML
    public void botaoCincoEletricidade() {
        gastoMensalEletricidade = 225; //media entre 200 e 250
    }    
    
    
    //botões para gas
    @FXML
    public void botaoUmGas() {
        gastoMensalGas = 10; //media entre 0 e 20 
    } 
    
    @FXML
    public void botaoDoisGas() {
        gastoMensalGas = 30; //media entre 20 e 40 
    } 
    
    @FXML
    public void botaoTresGas() {
        gastoMensalGas = 50; //media entre 41 e 60 
    } 
    
    @FXML
    public void botaoQuatroGas() {
        gastoMensalGas = 70; //media entre 61 e 80 
    } 
    
    @FXML
    public void botaoCincoGas() {
        gastoMensalGas = 90; //media entre 81 e 100  
    }    
    
    
    //botões para oleo
    @FXML
    public void botaoUmOleo() {
        gastoMensalOleo = 15; //media entre 0 e 30
    } 
    
    @FXML
    public void botaoDoisOleo() {
        gastoMensalOleo = 45; //media entre 30 e 60
    } 
    
    @FXML
    public void botaoTresOleo() {
        gastoMensalOleo = 75; //media entre 61 e 90
    } 
    
    @FXML
    public void botaoQuatroOleo() {
        gastoMensalOleo = 105; //media entre 91 e 120
    } 
    
    @FXML
    public void botaoCincoOleo() {
        gastoMensalOleo = 135; //media entre 120 e 150  
    }    
    
    
    //botões para carro
    @FXML
    public void botaoUmCarro() {
        gastoMensalCarro = 0; 
    } 
    
    @FXML
    public void botaoDoisCarro() {
        gastoMensalCarro = 2500; //media entre 0 e 5000
    } 
    
    @FXML
    public void botaoTresCarro() {
        gastoMensalCarro = 7500; //media entre 5000 e 10000
    } 
    
    @FXML
    public void botaoQuatroCarro() {
        gastoMensalCarro = 12500; //media entre 10000 e 15000
    } 
    
    @FXML
    public void botaoCincoCarro() {
        gastoMensalCarro = 17500; //media entre 15000 e 20000
    }    
    

    //botões para voos com menos de 4 horas
    @FXML
    public void botaoUmVoosMenos() {
        gastoMensalVoosMenos = 0; //nenhum
    } 
    
    @FXML
    public void botaoDoisVoosMenos() {
        gastoMensalVoosMenos = 3.0/2.0; //media entre 1 e 2
    } 
    
    @FXML
    public void botaoTrêsVoosMenos() {
        gastoMensalVoosMenos = 7.0/2.0; //media entre 3 e 4
    } 
    
    @FXML
    public void botaoQuatroVoosMenos() {
        gastoMensalVoosMenos = 11.0/2.0; //media entre 5 e 6 
    } 
    
    @FXML
    public void botaoCincoVoosMenos() {
        gastoMensalVoosMenos = 13.0/2.0; //media entre 6 e 7     
    }    
    
    
    //botões para voos com mais de 4 horas
    @FXML
    public void botaoUmVoosMais() {
        gastoMensalVoosMais = 0; //nenhum
    } 
    
    @FXML
    public void botaoDoisVoosMais() {
        gastoMensalVoosMais = 3.0/2.0; //media entre 1 e 2
    } 
    
    @FXML
    public void botaoTrêsVoosMais() {
        gastoMensalVoosMais = 7.0/2.0; //media entre 3 e 4
    } 
    
    @FXML
    public void botaoQuatroVoosMais() {
        gastoMensalVoosMais = 11.0/2.0; //media entre 5 e 6 
    } 
    
    @FXML
    public void botaoCincoVoosMais() {
        gastoMensalVoosMais = 13.0/2.0; //media entre 6 e 7        
    }    
    
    
    //botões para reciclagem de jornal
    @FXML
    public void botaoSimJornais() {
        gastoMensalJornal = true; 
    } 
    
    @FXML
    public void botaoNaoJornais() {
        gastoMensalJornal = false; //não doa, logo, efetua cálculo
    } 
    
    
    //botões para reciclagem de aluminio e estanho
    @FXML
    public void botaoSimAluminioEstanho() {
        gastoMensalAluminioEstanho = true; 
    } 
    
    @FXML
    public void botaoNaoAluminioEstanho() {
        gastoMensalAluminioEstanho = false; //não doa, logo, efetua cálculo
    } 
    
    
  
    //métodos getters
    public double getGastoMensalEletricidade() {
        return gastoMensalEletricidade;
    }

    public double getGastoMensalGas() {
        return gastoMensalGas;
    }

    public double getGastoMensalOleo() {
        return gastoMensalOleo;
    }

    public double getGastoMensalCarro() {
        return gastoMensalCarro;
    }

    public double getGastoMensalVoosMenos() {
        return gastoMensalVoosMenos;
    }

    public double getGastoMensalVoosMais() {
        return gastoMensalVoosMais;
    }

    public boolean isGastoMensalJornal() {
        return gastoMensalJornal;
    }

    public boolean isGastoMensalAluminioEstanho() {
        return gastoMensalAluminioEstanho;
    }
    
    
     //metodos setters estaticos, permitindo que todas as instâncias da classe 
    //acessem e modifiquem os mesmos valores de gastos mensais
    public static void setGastoMensalEletricidade(double gastoMensalEletricidade) {
        Controladores.gastoMensalEletricidade = gastoMensalEletricidade;
    }

    public static void setGastoMensalGas(double gastoMensalGas) {
        Controladores.gastoMensalGas = gastoMensalGas;
    }

    public static void setGastoMensalOleo(double gastoMensalOleo) {
        Controladores.gastoMensalOleo = gastoMensalOleo;
    }

    public static void setGastoMensalCarro(double gastoMensalCarro) {
        Controladores.gastoMensalCarro = gastoMensalCarro;
    }

    public static void setGastoMensalVoosMenos(double gastoMensalVoosMenos) {
        Controladores.gastoMensalVoosMenos = gastoMensalVoosMenos;
    }

    public static void setGastoMensalVoosMais(double gastoMensalVoosMais) {
        Controladores.gastoMensalVoosMais = gastoMensalVoosMais;
    }

    public static void setGastoMensalJornal(boolean gastoMensalJornal) {
        Controladores.gastoMensalJornal = gastoMensalJornal;
    }

    public static void setGastoMensalAluminioEstanho(boolean gastoMensalAluminioEstanho) {
        Controladores.gastoMensalAluminioEstanho = gastoMensalAluminioEstanho;
    }

    
    //botões para mudar interfaces:
    @FXML
    private void mudarParaIniciar() throws IOException { //usado na interface
        App.setRoot("iniciar");
    }
    
    @FXML
    private void mudarParaEletricidade() throws IOException { //usado na interface
        App.setRoot("eletricidade");
    }
    
    @FXML
    private void mudarParaGas() throws IOException { //usado na interface
        App.setRoot("gas");
    }
    
    @FXML
    private void mudarParaOleo() throws IOException { //usado na interface
        App.setRoot("oleo");
    }
    
    @FXML
    private void mudarParaCarro() throws IOException { //usado na interface
        App.setRoot("carro");
    }
    
    @FXML
    private void mudarParavoosMenos() throws IOException { //usado na interface
        App.setRoot("voosMenos");
    }
    
    @FXML
    private void mudarParavoosMais() throws IOException { //usado na interface
        App.setRoot("voosMais");
    }
    
    @FXML
    private void mudarParajornais() throws IOException { //usado na interface
        App.setRoot("jornais");
    }
    
    @FXML
    private void mudarParaaluminioEstanho() throws IOException { //usado na interface
        App.setRoot("aluminioEstanho");
    }
    
    @FXML
    private void mudarPararesultado() throws IOException { //usado na interface
        App.setRoot("resultado");
    }
}