package com.example.stick_hero_ap_game_2023;

import javafx.scene.image.ImageView;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;

public class Cherry extends ImageView {


    public void remove_a_cherry(MediaPlayer mediaPlayer, Text cherry_score, Playagame playagame) {
        playagame.setCount(playagame.getCount()+1);
        this.setLayoutX(0.0);
        this.setLayoutY(0.0);
        System.out.println("PLAY SOUND");
        mediaPlayer.play();
        mediaPlayer.seek(mediaPlayer.getStartTime());
        System.out.println("SOUND PLAYED");
        String s = cherry_score.getText();
        int sc = Integer.parseInt(s);
        sc++;
        playagame.setLives(playagame.getLives()+0.5);
        s=Integer.toString(sc);
        cherry_score.setText(s);
    }
}
