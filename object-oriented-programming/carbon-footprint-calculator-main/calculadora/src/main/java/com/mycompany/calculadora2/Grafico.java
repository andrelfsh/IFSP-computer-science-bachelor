package com.mycompany.calculadora2;

/**
 * Autores: André Lyra Fernandes && Victoria Carolina Ferreira da Silva
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

public class Grafico implements Initializable {    
    
    @FXML
    private BarChart<String, Number> barChart;

    @FXML
    private Label resultadoLabel;
    
    @FXML
    private Label resultado1Label;
    
    Controladores controladores = new Controladores(); 
    Calculo calculo = new Calculo(controladores);
    double resultado = calculo.calcularPegadaDeCarbonoTotal();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ObservableList<XYChart.Series<String, Number>> barChartData = FXCollections.observableArrayList();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Emissões De Carbono Por Dia");

        series.getData().add(new XYChart.Data<>("Média dos EUA", 33766));
        series.getData().add(new XYChart.Data<>("Média do Brasil", 4934));
        series.getData().add(new XYChart.Data<>("Seu resultado", resultado));

        barChartData.add(series);

        barChart.setData(barChartData);
        barChart.setTitle("");
        
        System.out.println("RESULTADO:" + resultado);
        resultado1Label.setText("Seu resultado: " + resultado);
        
        // Classificação da Pegada de Carbono:
        // Obtido via https://justenergy.com/blog/how-to-calculate-your-carbon-footprint/ 
        // e adaptada de dólares para reais
        
        // Pegada de carbono baixa está abaixo de 6000
        // Pegada de carbono ideal varia de 6000 a 15999
        // Pegada de carbono média varia de 16000 a 22000
        // Pegada de carbono alta está acima de 22.000
        
        if (resultado < 6000) {
            resultadoLabel.setText("Sua pegada de carbono é baixa");
        } else if (resultado >= 6000 && resultado <= 15999) {
            resultadoLabel.setText("Sua pegada de carbono é ideal");
        } else if (resultado >= 16000 && resultado <= 22000) {
            resultadoLabel.setText("Sua pegada de carbono é média");
        } else {
            resultadoLabel.setText("Sua pegada de carbono é alta");
        }
    }

    @FXML
    // Método para reiniciar o programa, zera o valor das variaveis
    private void reiniciar() throws IOException { 
        
        App.setRoot("iniciar");
       
        Controladores.setGastoMensalEletricidade(0);
        Controladores.setGastoMensalGas(0);
        Controladores.setGastoMensalOleo(0);
        Controladores.setGastoMensalCarro(0);
        Controladores.setGastoMensalVoosMenos(0);
        Controladores.setGastoMensalVoosMais(0);
        Controladores.setGastoMensalJornal(false);
        Controladores.setGastoMensalAluminioEstanho(false);
    }
}
