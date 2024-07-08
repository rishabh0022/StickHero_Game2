package com.example.stick_hero_ap_game_2023;

import javafx.animation.Timeline;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;

public class Stick extends Rectangle {
    public Stick() {

    }


    public void rotateStickAroundPivot(Timeline rotateStickTimeline, Timeline personMoveTimeline, Timeline personMoveTimeline1,  Playagame playagame) {
        playagame.setCnt(playagame.getCnt() +1 );
        double rotationAngle = 2; // Change this to your desired rotation speed
        double remainingRotation = 90 - this.getRotate();

        System.out.println(this.getRotate());
        if (remainingRotation > rotationAngle) { // Rotate until it's completely horizontal (90 degrees)
            this.getTransforms().add(new Rotate(rotationAngle, this.getX() + this.getWidth() / 2, this.getY()+playagame.getStickLength()));
            if(playagame.getCnt()==45){
                System.out.println("STARTING TO MOVE THE CHARACTER NOW....");
                rotateStickTimeline.pause();

                personMoveTimeline.play();
            }
            // now start the timeline for the image to move across the stick
        } else {
            rotateStickTimeline.pause();
        }


    }

    public void increaseStickLength(Playagame playagame) {
        playagame.setStickLength(playagame.getStickLength() +1);
        this.setY((playagame.getFixedY() - playagame.getStickLength()));
        this.setHeight(playagame.getStickLength());
    }
}
