module com.terracotta004.javafxcalculator {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.scripting;


    opens com.terracotta004.javafxcalculator to javafx.fxml;
    exports com.terracotta004.javafxcalculator;
}