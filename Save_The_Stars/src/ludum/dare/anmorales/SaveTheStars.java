package ludum.dare.anmorales;

import ludum.dare.anmorales.screens.MenuScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;

public class SaveTheStars extends Game {

	public static Music mMenu;
	public static boolean mSoundOn;
	public static Preferences mPreferences;

	@Override
	public void create() {
		mPreferences = Gdx.app.getPreferences("Save the Stars!");
		mMenu = Gdx.audio.newMusic(Gdx.files.internal("soundtrack/menu.mp3"));
		mMenu.setLooping(true);
		this.setScreen(new MenuScreen(this));
		mSoundOn = mPreferences.getBoolean("sound_on", true);
		// Gdx.app.log("DEBUG", mSoundOn + "");
		if (mSoundOn)
			mMenu.play();
	}

	@Override
	public void dispose() {
		mPreferences.putBoolean("sound_on", mSoundOn);
		mPreferences.flush();
		mMenu.dispose();
		this.getScreen().dispose();
	}

	public void changeScreen(Screen nextScreen) {
		Screen screen = this.getScreen();
		this.setScreen(nextScreen);
		screen.dispose();
		screen = null;
	}

}
