package com.example.stick_hero_ap_game_2023;


// this is an example of flyweight design pattern

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Sound implements Effects{
    String URL;
    private Media media;
    private MediaPlayer mediaPlayer;
    private static Map<String, Sound> instances
            = new HashMap<String, Sound>();
    private Sound(String URL){
        this.URL=URL;
        media = new Media(Objects.requireNonNull(getClass().getResource(URL)).toExternalForm());
        mediaPlayer= new MediaPlayer(media);
    }

    public static Sound getInstance(String URL){
        if(!instances.containsKey(URL)){
            instances.put(URL,new Sound(URL));
        }
        return instances.get(URL);
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media sound) {
        this.media = sound;
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public void setMediaPlayer(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }

    public static Map<String, Sound> getInstances() {
        return instances;
    }

    public static void setInstances(Map<String, Sound> instances) {
        Sound.instances = instances;
    }

    @Override
    public void change_intensity() {

    }

}
