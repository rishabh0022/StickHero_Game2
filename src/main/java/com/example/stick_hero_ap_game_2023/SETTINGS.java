package com.example.stick_hero_ap_game_2023;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.util.Optional;

import static com.example.stick_hero_ap_game_2023.HelloApplication.mediaPlayeru;

public class SETTINGS {
    @FXML
    private Button home;
    @FXML
    private Stage stage;
    @FXML
    private Button details;

    HighScore hs2= new HighScore();

    public void setStage(Stage stage){
        this.stage=stage;
    }
    @FXML
    protected void goBackToHome(ActionEvent event ) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        // Get the reference to the current stage
        Stage currentStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        currentStage.setScene(scene);
        currentStage.setFullScreen(true);
        currentStage.show();
    }
    @FXML
    protected void display_details(ActionEvent event) throws Exception{
        Platform.runLater(() ->{
            mediaPlayeru.pause();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("How to Play...");
            alert.setHeaderText(null);
            alert.setContentText("Game Info");
            DialogPane dialogPane = alert.getDialogPane();
            if (dialogPane.getContent() != null) {
                // Add additional text or nodes as needed
                Text additionalText = new Text("This is a single player game consisting of a character with stick." +"\n"+
                        "You have got a stick to cross the buildings without falling into the abyss." +"\n"+
                        "The stick extends if you hold the mouse pressing it. There are cherries along the way which the player" +"\n"+
                        "can collect by pressing on the character when it's just above the cherries." +"\n"+"Each cherry collection adds " +
                        "one point to the score and so does a successful crossover between buildings..." +"\n"+
                        "The character can be revived at a cost of 2 cherry points." + "\n"+
                        "The game ends when the character falls into the abyss and has less than 2 cherry points" +"\n"+
                        "HAPPY PLAYING !!!");
                dialogPane.setContent(new VBox(additionalText, dialogPane.getContent()));
            } else {
                // If existing content is null, set only the additional text
                Text additionalText = new Text("This is a single player game consisting of a character with stick." +"\n"+
                        "You have got a stick to cross the buildings without falling into the abyss." +"\n"+
                        "The stick extends if you hold the mouse pressing it. There are cherries along the way which the player" +"\n"+
                        "can collect by pressing on the character when it's just above the cherries." +"\n"+"Each cherry collection adds " +
                        "one point to the score and so does a successful crossover between buildings..." +"\n"+
                        "The character can be revived at a cost of 2 cherry points." + "\n"+
                        "The game ends when the character falls into the abyss and has less than 2 cherry points" +"\n"+
                        "HAPPY PLAYING !!!");
                dialogPane.setContent(additionalText);
            }
            ButtonType buttonExit = new ButtonType("Exit");
            alert.getButtonTypes().setAll(buttonExit);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent()){
                mediaPlayeru.play();
                stage.show();
            }
        });
    }

    @FXML
    protected  void display_user_stats(ActionEvent event) throws IOException, ClassNotFoundException {
        double score_to_be_displayed=0;


        ObjectInputStream in =null;
        try{
             in = new ObjectInputStream(new FileInputStream("savedstate.txt"));
             hs2=(HighScore)in.readObject();

        }
        finally {
            if(in!=null) {
                in.close();
            }
        }
        Platform.runLater(() ->{
            mediaPlayeru.pause();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("User Stats");
            alert.setHeaderText(null);
            alert.setContentText("User Stats");
            DialogPane dialogPane = alert.getDialogPane();
            if (dialogPane.getContent() != null) {
                // Add additional text or nodes as needed
                Text additionalText = new Text("HIGH SCORE -" + hs2.getHigh_score());
                dialogPane.setContent(new VBox(additionalText, dialogPane.getContent()));
            } else {
                // If existing content is null, set only the additional text
                Text additionalText = new Text("HIGH SCORE-" + hs2.getHigh_score());
                dialogPane.setContent(additionalText);
            }
            ButtonType buttonExit = new ButtonType("Exit");
            alert.getButtonTypes().setAll(buttonExit);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent()){
                mediaPlayeru.play();
                stage.show();
            }
        });

    }

}
