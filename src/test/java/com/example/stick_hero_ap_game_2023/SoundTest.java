package com.example.stick_hero_ap_game_2023;

import de.saxsys.javafx.test.JfxRunner;
import javafx.application.Platform;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.testng.Assert;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(JfxRunner.class)
class SoundTest {
    @BeforeAll
    public static void setUp() {
        // Initialize JavaFX toolkit
        Platform.startup(() -> {});
    }


    @Test
    public void test(){

        String URL = "hip-hop-rock-beats-118000.mp3";
        Platform.runLater(() -> {
            Sound s1 = Sound.getInstance(URL);
            Sound s2 = Sound.getInstance(URL);
            Assert.assertEquals(s1, s2);
        });
    }

    @AfterAll
    public static void tearDown() {
        // Exit JavaFX toolkit
        Platform.exit();
    }


}