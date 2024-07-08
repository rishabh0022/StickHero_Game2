package com.example.stick_hero_ap_game_2023;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.application.Platform;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static com.example.stick_hero_ap_game_2023.HelloApplication.mediaPlayeru;

public class Playagame {


    @FXML
    private Button home;
    private Timeline extendStickTimeline;
    private Timeline rotateStickTimeline;

    private Timeline personMoveTimeline;

    private double lives=0;
    private  Timeline godown;
    @FXML
    private Stage stage;

    private int cnt=0;

    private HighScore high_score_in_game=new HighScore();

    private double stickLength = 0;

    private double oldStickLength=0;
    private double old_player_xcoord=0;
    private Pane stickPane;


    private int score=0;


    @FXML
    private Pane gamePane;

    @FXML
    private Stick stick= new Stick();

    private int high_score=0;
   
    @FXML
    private Rectangle building1;

    @FXML
    private Rectangle standing_building;

    private double fixedY = 104;

    @FXML
    private  Player player;


    @FXML
    private ImageView background;
    @FXML
    private Text revive;

    @FXML
    private Text cherry_score; //Sk
    @FXML
    private Cherry cherry1; //Sk
    @FXML
    private Cherry cherry2;
    @FXML
    private Cherry cherry3;
    private int count = 0;
    String musicfile = "game-bonus-144751.mp3";


    Sound sound_0 =Sound.getInstance(musicfile);

    String musicfile2 = "walking-cartoon-3-29625.mp3";
    Sound sound_2 = Sound.getInstance(musicfile2);

    String musicfile3 = "negative_beeps-6008.mp3";

    Sound sound_3=  Sound.getInstance(musicfile3);

    String musicfile4 = "violin-lose-1-175615.mp3";

    Sound sound_4 = Sound.getInstance(musicfile4);


   String musicfile5 = "selection-sounds-73225.mp3";
   Sound sound5 = Sound.getInstance(musicfile5);
   //Media sound5 = new Media(new File(musicfile5).toURI().toString());
    //MediaPlayer mediaPlayer5 = new MediaPlayer(sound5);
    public void setStage(Stage stage){
        this.stage=stage;
    }

    @FXML
    public void initialize() {
        gamePane.setMinWidth(1500.0);
        gamePane.setMinHeight(1250.0);
        // Set up initial game state
        // set the player's x coordinate
        initializeGame();
        // Start the game loop
        //startGameLoop();
        Random random = new Random();
        stickLength=10;
        int width = random.nextInt(200 -50) + 50;
        standing_building.setWidth(width);
        player.setY(standing_building.getY()-player.getFitHeight()+80);
        player.setX(standing_building.getX()-25);
        //player.setX(standing_building.getX());
        stick.setY(player.getY()-stickLength);
        stick.setX(player.getX()+5);

        //stick.setX(player.getX()+50);
        //stick.setY(player.getY()-30);
        stick.setHeight(30);
        double lower_bound_for_x_coordinate=standing_building.getLayoutX() + standing_building.getWidth()+10;
        double higher_bound_for_x_coordinate=background.getLayoutX()+background.getFitWidth()-20;
        building1.setLayoutX(random.nextDouble(higher_bound_for_x_coordinate-lower_bound_for_x_coordinate)+lower_bound_for_x_coordinate);
        double max_width2= background.getLayoutX()+ background.getFitWidth()-building1.getLayoutX();

        double width2 = random.nextDouble(max_width2 - 10)+10;
        building1.setWidth(width2);
        cherry1.setLayoutX(random.nextDouble(building1.getBoundsInParent().getMinX()-(standing_building.getBoundsInParent().getMaxX()))+(standing_building.getX() + standing_building.getWidth())+10);
        cherry1.setLayoutY(building1.getLayoutY());
        cherry2.setLayoutX(random.nextDouble(building1.getBoundsInParent().getMinX()-(standing_building.getBoundsInParent().getMaxX()))+(standing_building.getX() + standing_building.getWidth())+10);
        cherry2.setLayoutY(building1.getLayoutY());
        cherry3.setLayoutX(random.nextDouble(building1.getBoundsInParent().getMinX()-(standing_building.getBoundsInParent().getMaxX()))+(standing_building.getX() + standing_building.getWidth())+10);
        cherry3.setLayoutY(building1.getLayoutY());
        high_score_in_game.setHigh_score(Integer.parseInt(cherry_score.getText()));
    }

    private void startGameLoop() {
            //extendStickTimeline.play();
    }
    @FXML
    private void onMousePressed() {
        sound5.getMediaPlayer().play();
        // Start extending stick when the mouse is pressed
        oldStickLength=stickLength;
        old_player_xcoord=player.getLayoutX();
        extendStickTimeline.play();

    }
    @FXML
    public void onMouseDragged(){ //Sk //Actually this is mouse pressed on player
        System.out.println("PLAYER INVERTED");
        player.setRotate(180.0);
        player.setY(player.getY()-10);
        if (compare_X(player, cherry1))
            remove_cherry(cherry1);
        if (compare_X(player, cherry2))
            remove_cherry(cherry2);
        if (compare_X(player, cherry3))
            remove_cherry(cherry3);
    }
    public void remove_cherry(Cherry cherry){ //Sk
        cherry.remove_a_cherry(sound_0.getMediaPlayer(),cherry_score,this);
    }
    public boolean compare_X(ImageView one, ImageView two){
        System.out.println("unmatched"+ one.getX()+"  "+ two.getLayoutX());
        if (Math.abs(one.getX()+50 - two.getLayoutX()) <= 75){
            System.out.println("matched"+ one.getX()+"  "+ two.getX());
            return true;
        }
        return false;
    }
    @FXML
    public void onMousedropReleased(){ //Sk //Actually this is mouse released from player
        System.out.println("PLAYER UPRIGHT AGAIN");
        player.setY(player.getY()+10);
        player.setRotate(0.0);
    }
    public void initializeGame() {
        revive.setText("");
        extendStickTimeline = new Timeline(
                new KeyFrame(Duration.seconds(1 / 90.0), event -> {
                    // Update game logic
                    extendStick();
                })
        );
        extendStickTimeline.setCycleCount(Timeline.INDEFINITE);
        rotateStickTimeline = new Timeline(
                new KeyFrame(Duration.seconds(1 /60.0), event -> {
                    // Update rotation logic
                    rotateStick();
                })
        );
        rotateStickTimeline.setCycleCount(Timeline.INDEFINITE);
        personMoveTimeline = new Timeline(new KeyFrame(Duration.seconds((1/10.0)),event -> {
            moveCharacter();
        })
        );
         godown= new Timeline(new KeyFrame(Duration.seconds(1/60.0),event -> {
             try {
                 persongoDown();
             } catch (IOException e) {
                 throw new RuntimeException(e);
             } catch (ClassNotFoundException e) {
                 throw new RuntimeException(e);
             }
         })
        );
        godown.setCycleCount(Timeline.INDEFINITE);
        godown.pause();
        personMoveTimeline.setCycleCount(Timeline.INDEFINITE);

    }

    public void moveCharacter() {
        player.movetoNextBuilding(sound_2.getMediaPlayer(),old_player_xcoord,stick,stickLength,oldStickLength,building1,cherry_score,personMoveTimeline,rotateStickTimeline,extendStickTimeline,godown,this,fixedY);
    }

    public void persongoDown() throws IOException, ClassNotFoundException {
        sound_3.getMediaPlayer().play();
        player.setY(player.getY()+1);
        stick.setY((stick.getY()+1));
        if(player==null){
            System.out.println("PLAYER IS NULL");
        }
        player.goDown(stick,building1,sound_3.getMediaPlayer(),lives,godown,extendStickTimeline,revive,lives,cherry_score,this);

    }

    public void display_game_over_screen() throws IOException, ClassNotFoundException {
        double high_score_0=0;
        high_score_in_game.setHigh_score(Integer.parseInt(cherry_score.getText()));
       high_score_in_game.serialize();
        try {
            HighScore highScore2 = high_score_in_game.deserialize();
            high_score_in_game.setHigh_score(highScore2.getHigh_score());

        }
        catch(Exception e){
            System.out.println("SOMETHING BAD");

        }


        Platform.runLater(() -> {
            sound_4.getMediaPlayer().play();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Game Over");
            alert.setHeaderText(null);
            alert.setContentText("Game Over!");
            DialogPane dialogPane = alert.getDialogPane();
            if (dialogPane.getContent() != null) {
                // Add additional text or nodes as needed
                Text additionalText = new Text("SCORE -" + cherry_score.getText() +"\n" + "HIGHEST SCORE:" +high_score_in_game.getHigh_score());
                dialogPane.setContent(new VBox(additionalText, dialogPane.getContent()));
            } else {
                // If existing content is null, set only the additional text
                Text additionalText = new Text("SCORE -" + cherry_score.getText() +"\n" + "HIGHEST SCORE:"+high_score_in_game.getHigh_score());
                dialogPane.setContent(additionalText);
            }
            cherry_score.setText("0");
            ButtonType buttonRestart = new ButtonType("Restart");
            ButtonType buttonExit = new ButtonType("Exit");
            alert.getButtonTypes().setAll(buttonRestart, buttonExit);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent()) {
                if (result.get() == buttonRestart) {
                    // Handle restart button click
                    System.out.println("CALLING RESTART AGAIN");
                    initialize();
                } else if (result.get() == buttonExit) {
                    // Handle exit button click
                }
            }

            //alert.showAndWait();

        });
    }

    private void rotateStick() {
        stick.rotateStickAroundPivot(rotateStickTimeline,personMoveTimeline,personMoveTimeline,this);
    }

    @FXML
    private void onMouseReleased() {
        // Stop extending stick when the mouse is released
        extendStickTimeline.pause();
        sound5.getMediaPlayer().pause();
        sound5.getMediaPlayer().seek(sound5.getMediaPlayer().getStartTime());
        // now make another timeline for rotating the stick
        //rotateStickTimeline.setCycleCount(45);
        cnt=0;
        rotateStickTimeline.play();

       // now the rod is completely horizontal
        // now start the timeline for the person to move



    }
    private void extendStick() {
        stick.increaseStickLength(this);
    }

    @FXML
    protected void goBackToHome(ActionEvent event ) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        mediaPlayeru.play();
        // Get the reference to the current stage
        Stage currentStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        currentStage.setScene(scene);
        currentStage.setFullScreen(true);
        currentStage.show();
    }

    public Button getHome() {
        return home;
    }

    public void setHome(Button home) {
        this.home = home;
    }

    public Timeline getExtendStickTimeline() {
        return extendStickTimeline;
    }

    public void setExtendStickTimeline(Timeline extendStickTimeline) {
        this.extendStickTimeline = extendStickTimeline;
    }

    public Timeline getRotateStickTimeline() {
        return rotateStickTimeline;
    }

    public void setRotateStickTimeline(Timeline rotateStickTimeline) {
        this.rotateStickTimeline = rotateStickTimeline;
    }

    public Timeline getPersonMoveTimeline() {
        return personMoveTimeline;
    }

    public void setPersonMoveTimeline(Timeline personMoveTimeline) {
        this.personMoveTimeline = personMoveTimeline;
    }

    public double getLives() {
        return lives;
    }

    public void setLives(double lives) {
        this.lives = lives;
    }

    public Timeline getGodown() {
        return godown;
    }

    public void setGodown(Timeline godown) {
        this.godown = godown;
    }

    public Stage getStage() {
        return stage;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public HighScore getHigh_score_in_game() {
        return high_score_in_game;
    }

    public void setHigh_score_in_game(HighScore high_score_in_game) {
        this.high_score_in_game = high_score_in_game;
    }

    public double getStickLength() {
        return stickLength;
    }

    public void setStickLength(double stickLength) {
        this.stickLength = stickLength;
    }

    public double getOldStickLength() {
        return oldStickLength;
    }

    public void setOldStickLength(double oldStickLength) {
        this.oldStickLength = oldStickLength;
    }

    public double getOld_player_xcoord() {
        return old_player_xcoord;
    }

    public void setOld_player_xcoord(double old_player_xcoord) {
        this.old_player_xcoord = old_player_xcoord;
    }

    public Pane getStickPane() {
        return stickPane;
    }

    public void setStickPane(Pane stickPane) {
        this.stickPane = stickPane;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Pane getGamePane() {
        return gamePane;
    }

    public void setGamePane(Pane gamePane) {
        this.gamePane = gamePane;
    }

    public Rectangle getStick() {
        return stick;
    }

    public void setStick(Stick stick) {
        this.stick = stick;
    }

    public int getHigh_score() {
        return high_score;
    }

    public void setHigh_score(int high_score) {
        this.high_score = high_score;
    }

    public Rectangle getBuilding1() {
        return building1;
    }

    public void setBuilding1(Rectangle building1) {
        this.building1 = building1;
    }

    public Rectangle getStanding_building() {
        return standing_building;
    }

    public void setStanding_building(Rectangle standing_building) {
        this.standing_building = standing_building;
    }

    public double getFixedY() {
        return fixedY;
    }

    public void setFixedY(double fixedY) {
        this.fixedY = fixedY;
    }

    public ImageView getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ImageView getBackground() {
        return background;
    }

    public void setBackground(ImageView background) {
        this.background = background;
    }

    public Text getRevive() {
        return revive;
    }

    public void setRevive(Text revive) {
        this.revive = revive;
    }

    public Text getCherry_score() {
        return cherry_score;
    }

    public void setCherry_score(Text cherry_score) {
        this.cherry_score = cherry_score;
    }

    public ImageView getCherry1() {
        return cherry1;
    }

    public void setCherry1(Cherry cherry1) {
        this.cherry1 = cherry1;
    }

    public ImageView getCherry2() {
        return cherry2;
    }

    public void setCherry2(Cherry cherry2) {
        this.cherry2 = cherry2;
    }

    public ImageView getCherry3() {
        return cherry3;
    }

    public void setCherry3(Cherry cherry3) {
        this.cherry3 = cherry3;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getMusicfile() {
        return musicfile;
    }

    public void setMusicfile(String musicfile) {
        this.musicfile = musicfile;
    }

    public Sound getSound_0() {
        return sound_0;
    }

    public void setSound_0(Sound sound_0) {
        this.sound_0 = sound_0;
    }

    public String getMusicfile2() {
        return musicfile2;
    }

    public void setMusicfile2(String musicfile2) {
        this.musicfile2 = musicfile2;
    }

    public Sound getSound_2() {
        return sound_2;
    }

    public void setSound_2(Sound sound_2) {
        this.sound_2 = sound_2;
    }

    public String getMusicfile3() {
        return musicfile3;
    }

    public void setMusicfile3(String musicfile3) {
        this.musicfile3 = musicfile3;
    }

    public Sound getSound_3() {
        return sound_3;
    }

    public void setSound_3(Sound sound_3) {
        this.sound_3 = sound_3;
    }

    public String getMusicfile4() {
        return musicfile4;
    }

    public void setMusicfile4(String musicfile4) {
        this.musicfile4 = musicfile4;
    }

    public Sound getSound_4() {
        return sound_4;
    }

    public void setSound_4(Sound sound_4) {
        this.sound_4 = sound_4;
    }

    public String getMusicfile5() {
        return musicfile5;
    }

    public void setMusicfile5(String musicfile5) {
        this.musicfile5 = musicfile5;
    }

    public Sound getSound5() {
        return sound5;
    }

    public void setSound5(Sound sound5) {
        this.sound5 = sound5;
    }



    @FXML
    protected void onSaveButton(ActionEvent event) throws IOException {
        high_score_in_game.setHigh_score(Integer.parseInt(cherry_score.getText()));
        high_score_in_game.serialize();
        System.out.println("WRITTEN IN THE FILE SUCCESFULLY");
    }

}
