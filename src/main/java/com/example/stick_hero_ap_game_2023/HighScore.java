package com.example.stick_hero_ap_game_2023;

import java.io.*;

public class HighScore implements Serializable {

    private double high_score=0;

    public double getHigh_score() {
        return high_score;
    }

    public void setHigh_score(double high_score) {
        this.high_score = high_score;
    }


    public void serialize() throws IOException {
        ObjectOutputStream out=null;
        try {
           out = new ObjectOutputStream(new FileOutputStream("savedstate.txt"));
            out.writeObject(this);
        }
        finally {
            out.close();
        }

    }

    public HighScore deserialize() throws IOException, ClassNotFoundException {
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream (
                     new FileInputStream("savedstate.txt"));
            HighScore h1 = (HighScore) in.readObject();
            return h1;
        } finally {
             in.close();
             return null;
            }

    }
}
