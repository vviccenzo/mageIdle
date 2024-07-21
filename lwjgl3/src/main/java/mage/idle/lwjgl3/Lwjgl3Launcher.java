package mage.idle.lwjgl3;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

import mage.idle.Mage;

/** Launches the desktop (LWJGL3) application. */public class Lwjgl3Launcher {
    public static void main (String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Drop");
        config.setWindowedMode(800, 480);
        config.useVsync(true);
        config.setForegroundFPS(60);
        new Lwjgl3Application(new Mage(), config);
    }
}
