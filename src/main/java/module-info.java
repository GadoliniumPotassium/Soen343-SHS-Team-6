module com.mycompany.shsimulator {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.shsimulator to javafx.fxml;
    exports com.mycompany.shsimulator;
}
