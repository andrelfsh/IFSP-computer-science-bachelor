module com.mycompany.calculadora2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.logging;

    opens com.mycompany.calculadora2 to javafx.fxml;
    exports com.mycompany.calculadora2;
}
