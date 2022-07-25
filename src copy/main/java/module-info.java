module dungeonadventure.dungeonadventure {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens dungeonadventure.dungeonadventure to javafx.fxml;
    exports dungeonadventure.dungeonadventure;
}