# Carbon Footprint Calculator

**[Versão em Português](README-PT.md)**

This project is a desktop application developed in **Java** using **JavaFX** to calculate individual environmental impact based on monthly and annual consumption. The software estimates $CO_2$ emissions and provides comparative visual feedback through charts.



## Technologies and Tools
* **Java 17+**: Core programming language.
* **JavaFX**: Framework for building the Graphical User Interface (GUI).
* **FXML**: Declarative language for UI structure.
* **Maven**: Dependency management and build automation.



## Software Architecture (OOP)
The project applies fundamental **Object-Oriented Programming** principles:
* **Encapsulation**: User consumption data is protected via `private` fields and accessed through `getters` and `setters`.
* **Modularity**: Separation of concerns between Logic (`Calculo.java`), UI Control (`Controladores.java`), and Data Visualization (`Grafico.java`).
* **State Management**: Use of static members to persist user input data while navigating between different FXML scenes.



## Calculation Criteria
The impact formulas were adapted from *Just Energy* studies, converting global metrics to the Brazilian context (including currency adjustments and emission averages).

| Category | Factors Considered |
| :--- | :--- |
| **Energy** | Electricity, Gas, and Heating Oil |
| **Transport** | Car mileage and flight frequency (short/long haul) |
| **Recycling** | Footprint reduction via proper disposal of paper, aluminum, and tin |



## Results and Classification
After entering the data, the program calculates the total footprint and classifies it into four levels:

* **Low**: $< 6,000$
* **Ideal**: $6,000 - 15,999$
* **Average**: $16,000 - 22,000$
* **High**: $> 22,000$

The final screen displays a `BarChart` comparing your result against the national averages of Brazil and the USA.

