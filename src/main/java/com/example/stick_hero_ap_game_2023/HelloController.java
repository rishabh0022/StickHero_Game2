package com.example.stick_hero_ap_game_2023;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

import static com.example.stick_hero_ap_game_2023.HelloApplication.mediaPlayeru;


public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private Stage stage=new Stage();

    //@FXML
    //private  Button difficulty_button;
    @FXML
    private Button settings;
    @FXML
    private Button play_a_game;
    @FXML
    private ImageView imageView;
    public void setStage(Stage stage) {
        this.stage = stage;

    }

    /*@FXML
    protected void onDifficultyClick(ActionEvent event) throws IOException {
        stage.setResizable(false);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DIFFICULTY_SCREEN.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        Stage currentStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        currentStage.setScene(scene);
        currentStage.setFullScreen(true);
        currentStage.show();

    }*/
    @FXML
    protected void onSettingClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SETTINGS.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        Stage currentStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        currentStage.setScene(scene);
        currentStage.show();

    }
    @FXML
    protected  void onPlayButton(ActionEvent event ) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("playagame.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        Stage currentStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        currentStage.setScene(scene);
        currentStage.setFullScreen(true);
        mediaPlayeru.pause();
        currentStage.show();
    }
}