module com.example.polyclinic {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires org.controlsfx.controls;
    requires javafx.base;
    requires de.jensd.fx.glyphs.fontawesome;
    requires de.jensd.fx.glyphs.commons;
    requires de.jensd.fx.glyphs.materialdesignicons;

    exports controllers;
    opens controllers to javafx.fxml;
    exports models;
    opens models to javafx.base;
}