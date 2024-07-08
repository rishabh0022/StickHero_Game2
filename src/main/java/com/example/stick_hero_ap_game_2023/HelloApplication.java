package com.example.stick_hero_ap_game_2023;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class HelloApplication extends Application {
    public static MediaPlayer mediaPlayeru;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        HelloController controller= new HelloController();
        //Image icon = new Image("game_icon.png");
        //stage.getIcons().add(icon);
        stage.setTitle("STICK HERO LIVE");
        stage.setScene(scene);
        controller.setStage(stage);
        String musicfile = "hip-hop-rock-beats-118000.mp3";
        Sound sound5 = Sound.getInstance(musicfile);
        //Media sound = new Media(new File(musicfile).toURI().toString());
        //mediaPlayeru = new MediaPlayer(sound);

        mediaPlayeru=sound5.getMediaPlayer();
        mediaPlayeru.play();
        stage.setFullScreen(true);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}