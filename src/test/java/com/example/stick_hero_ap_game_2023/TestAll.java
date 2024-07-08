package com.example.stick_hero_ap_game_2023;

import javafx.application.Platform;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

class TestAll {
    @BeforeAll
    public static void setUp() {

        Platform.startup(() -> {});
    }
    @Test //should check for the scenario when there are multiple objects of same song
    public void TestInstance(){
        String URL = "hip-hop-rock-beats-118000.mp3";
        Platform.runLater(() -> {
            Sound s1 = Sound.getInstance(URL);
            Sound s2 = Sound.getInstance(URL);
            Assert.assertEquals(s1, s2);
        });
    }
    //should fail due to IllegalStateException as Playagame is linked to fxml file

    @Test
    void getCherry_score() {

        Playagame game = new Playagame();
        Platform.runLater(() -> {
            Assert.assertEquals(game.getCherry_score(),0);
        });

    }
    // Test for checking the singleton Player, checks for multiple player instances
    @Test
    public void TestSingleton(){
        Player p1 =Player.getInstanceof();
        Player p2 = Player.getInstanceof();
        Platform.runLater(() -> {
            Assert.assertEquals(p1,p2);
        });
    }

    @AfterAll
    public static void tearDown() {
        // Exit JavaFX toolkit
        Platform.exit();
    }

}