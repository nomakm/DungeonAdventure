module main.dungeonadventure {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    exports main.dungeonadventure.controller;
    opens main.dungeonadventure.controller to javafx.fxml;
    exports main.dungeonadventure.view;
    opens main.dungeonadventure.view to javafx.fxml;

}