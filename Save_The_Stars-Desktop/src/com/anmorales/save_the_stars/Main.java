package com.anmorales.save_the_stars;

import com.anmorales.save_the_stars.SaveTheStars;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Save the Stars!";
		cfg.useGL20 = true;
		cfg.width = 960;
		cfg.height = 540;
		cfg.resizable = false;
		
		new LwjglApplication(new SaveTheStars(), cfg);
	}
}
