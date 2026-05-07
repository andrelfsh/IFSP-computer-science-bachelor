# Calculadora de Pegada de Carbono 

**[Versão em Inglês](README.md)**

Este projeto é uma aplicação desktop desenvolvida em **Java** com **JavaFX** para calcular o impacto ambiental individual baseado no consumo mensal e anual. O software avalia emissões de CO2 e fornece um feedback visual comparativo.

## Tecnologias e Ferramentas
* **Java 17+**: Linguagem de programação principal.
* **JavaFX**: Biblioteca para construção da interface gráfica (GUI).
* **FXML**: Definição da interface de usuário de forma declarativa.
* **Maven**: Gerenciamento de dependências.


## Critérios de Cálculo
As fórmulas de impacto foram adaptadas de estudos da *Just Energy*, convertendo métricas globais para a realidade brasileira (ajuste de moeda e médias de emissão).

| Categoria | Fator Considerado |
| :--- | :--- |
| **Energia** | Eletricidade, Gás e Óleo |
| **Transporte** | Quilometragem de carro e frequência de voos (curtos/longos) |
| **Reciclagem** | Redução de pegada via descarte correto de metal e papel |


## Resultados e Classificação
Ao final da inserção dos dados, o programa classifica sua pegada como:
* **Baixa**: < 6.000
* **Ideal**: 6.000 - 15.999
* **Média**: 16.000 - 22.000
* **Alta**: > 22.000
