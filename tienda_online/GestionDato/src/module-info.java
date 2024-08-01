module tienda_online {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    
    opens com.tuempresa.gestiondatos.model to javafx.base;
    exports com.tuempresa.gestiondatos;
}
