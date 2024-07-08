module com.example.stick_hero_ap_game_2023 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires javafx.media;

    opens com.example.stick_hero_ap_game_2023 to javafx.fxml;
    exports com.example.stick_hero_ap_game_2023;
}