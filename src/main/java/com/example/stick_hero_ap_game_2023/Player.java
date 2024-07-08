package com.example.stick_hero_ap_game_2023;

import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;

import java.io.IOException;

public class Player extends ImageView {
    private int state;
    private int stick_length;

    private static Player player=null;
    private int score;
    //public Player(){};
    public void invert(int state){};
    public void extend_stick(int stick_length){};
    public void collect_cherries(int score){};
    public void run(){};
    public void drop(){};

    public static Player getInstanceof(){
        if(player==null){
            return new Player();
        }
        return player;
    }






    public void goDown(Rectangle stick, Rectangle building1, MediaPlayer mediaPlayer3, double lives, Timeline godown, Timeline extendStickTimeline, Text revive, double lives1, Text cherry_score,Playagame playagame) throws IOException, ClassNotFoundException {
        this.setY(this.getY()+1);
        stick.setY((stick.getY()+1));
        System.out.println("PERSON GOING DOWN");
        System.out.println("PLAYER Y COORDINATE:" + this.getLayoutY());
        System.out.println("BUILDING COMPARED:" + building1.getLayoutY());
        if(this.getY()>=(building1.getY() + building1.getHeight())){
            godown.pause();
            mediaPlayer3.seek(mediaPlayer3.getStartTime());
            // display the game over section with summary
            System.out.println("PERSON REACHED END POINT");
            if(Math.floor(playagame.getLives())<=0){
                playagame.display_game_over_screen();
                godown.pause();
                extendStickTimeline.pause();
            }
            else{ //sk
                revive.setText("REVIVED FOR 2 CHERRIES !!!");
                playagame.setLives(playagame.getLives()-1);
                String s = cherry_score.getText();
                int sc = Integer.parseInt(s);
                sc = sc - 2;
                s = Integer.toString(sc);
                cherry_score.setText(s);
                playagame.initialize();
            }

        }
    }

    public void movetoNextBuilding(MediaPlayer mediaPlayer2, double old_player_xcoord, Rectangle stick, double stickLength, double oldStickLength, Rectangle building1, Text cherry_score, Timeline personMoveTimeline, Timeline rotateStickTimeline, Timeline extendStickTimeline, Timeline godown, Playagame playagame,double fixedY) {

        double duration = 2.0; // Adjust the duration as needed
        System.out.println("INSIDE THE FUNCTION ");
        mediaPlayer2.setVolume(0.3);
        mediaPlayer2.play();
        //TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(duration), player);
        this.setX(this.getX()+10);
        int good1=0;
        // player.translateXProperty()
        //translateTransition.setByX(10); // Adjust the distance to move (X direction) as needed
        //translateTransition.setAutoReverse(false); // Set to true if you want it to return to the original position
        if(this.getX()-old_player_xcoord>= stickLength - oldStickLength){
            // person has moved to the stick distance
            double player_max=this.getBoundsInParent().getMaxX();
            double player_min =this.getBoundsInParent().getMinX();
            double building_max=building1.getBoundsInParent().getMaxX();
            double building_min=building1.getBoundsInParent().getMinX();
            System.out.println("PLAYER X MAX COORDINATES ARE : " + player_max);
            System.out.println("BUILDING X MINX COORDINATES ARE : "+ building1.getBoundsInParent().getMinX());
            System.out.println("PLAYER X MIN COORDINATES ARE: "+ this.getBoundsInParent().getMinX());
            System.out.println("BUILDING MAX COORDINATES ARE :"+ building1.getBoundsInParent().getMaxX());
            if((player_max < building_min) ||  (player_min>building_max)){
                // start the falling timeline;
                // send the person downward
                good1=-1;
                godown.play();

            }
            if (good1==0) {
                String s = cherry_score.getText(); //name is cherry score but it is universal
                int sc = Integer.parseInt(s);
                sc++;
                s = Integer.toString(sc);
                cherry_score.setText(s);
            }
            mediaPlayer2.pause();
            mediaPlayer2.seek(mediaPlayer2.getStartTime());
            personMoveTimeline.pause();
            Rotate rotate = new Rotate(-90, stick.getX() + stick.getWidth() / 2, fixedY);

            //
            stick.getTransforms().add(rotate);
            stick.setX(this.getX());
            playagame.setStickLength(0);
            playagame.setOldStickLength(0);
            if(good1==0){
                rotateStickTimeline.stop();
                extendStickTimeline.stop();
                personMoveTimeline.stop();
                godown.stop();
                playagame.initialize();
            }
            //also have to do one case when the player moves out of the frame , then we simply start again


        }
    }


}
