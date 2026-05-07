package com.mycompany.calculadora2;

/**
 * Autores: André Lyra Fernandes && Victoria Carolina Ferreira da Silva
 */

public class Calculo {
    
    //formula obtida via https://justenergy.com/blog/how-to-calculate-your-carbon-footprint/
    //calcula-se valores individuais com métodos individuais, e método final soma todos resultados
    
    private Controladores controladores;

    public Calculo(Controladores controladores) {
        this.controladores = controladores;
    }
    
    // Métodos para calcular a pegada de carbono, adapatadas de dolar para real:
    // Método para calcular valor de gasto mensal de eletricidade
    public double calcularEletricidade() {
        return controladores.getGastoMensalEletricidade() * 105/5; //1 real = 5 dolares
    }

    // Método para calcular valor de gas
    public double calcularGas() {
        return controladores.getGastoMensalGas() * 105/5; //1 real = 5 dolares 
    }

    // Método para calcular valor de gasto mensal em oleo
    public double calcularOleo() {
        return controladores.getGastoMensalOleo() * 113/5; //1 real = 5 dolares
    }

    // Método para calcular valor da kilometragem anual do veiculo
    public double calcularCarro() {
        return controladores.getGastoMensalCarro() * 0.79;
    }

    // Método para calcular valor de voos com mais de 4 horas anuais
    public double calcularVoosMenos() {
        return controladores.getGastoMensalVoosMenos() * 1100;
    }

    // Método para calcular valor de voos com mais de 4 horas anuais
    public double calcularVoosMais() {
        return controladores.getGastoMensalVoosMais() * 4400;
    }

    // Método para calcular caso usuario recicle jornais
    public double calcularJornais() {
        if (controladores.isGastoMensalJornal() == false ) {
            return 0;
        } else {
            return 184;
        }
    }

    // Método para calcular caso usuario recicle aluminio e estranho
    public double calcularAluminioEstanho() {
        if (controladores.isGastoMensalAluminioEstanho() == false) {
            return 0;
        } else {
            return 166; //aproximação de 184/5
        }
    }

    // Método para calcular pegada de carbono total (soma métodos anteriores)
    public double calcularPegadaDeCarbonoTotal() {
        return calcularEletricidade() + calcularGas() + calcularOleo() + 
                calcularCarro() + calcularVoosMenos() + calcularVoosMais() + 
                calcularJornais() + calcularAluminioEstanho();
    }
    
    // Método para exibir número obtido por usuário
    public void imprimirResultado() {
        System.out.println("RESULTADO: " + calcularPegadaDeCarbonoTotal());
    }
}