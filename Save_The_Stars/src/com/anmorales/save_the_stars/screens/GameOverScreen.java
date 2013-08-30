package com.anmorales.save_the_stars.screens;


import com.anmorales.save_the_stars.SaveTheStars;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GameOverScreen implements Screen, InputProcessor {

	private SaveTheStars mGame;
	private int mSavedStars;
	private long mScore;
	private Texture mBackground;
	private Texture mNumberSpriteSheet;
	private TextureRegion[] mNumbers;
	private int mWidth = 0;
	private int mHeight = 0;
	private SpriteBatch mSpriteBatch;
	private float mTime = 0;
	private Sound mGameOverSound;
	private boolean mFirstTime = true;

	public GameOverScreen(SaveTheStars pGame, int savedStars, long score) {
		mGame = pGame;
		this.mSavedStars = savedStars;
		this.mScore = score;
	}

	private void loadNumbers() {
		mNumberSpriteSheet = new Texture(
				Gdx.files.internal("images/numbers.png"));
		float stepX = 0.1f;
		float x = 0;
		for (int i = 0; i < mNumbers.length; i++) {
			mNumbers[i] = new TextureRegion(mNumberSpriteSheet, x, 0,
					x += stepX, 1);
		}
	}

	private void drawScore() {
		String number = mScore + "";
		while (number.length() < 4)
			number = "0" + number;
		float tempX = mWidth - mWidth / 5;
		for (int i = 3; i >= 0; i--) {
			tempX -= (mWidth / 33.1907f) * 1.5f;
			mSpriteBatch.draw(mNumbers[number.charAt(i) - 48], tempX,
					(mHeight - (mHeight / 14.5120f) * 12f),
					(mWidth / 33.1907f) * 1.5f, (mHeight / 14.5120f) * 1.5f);
		}
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.2313f, 0.1058f, 0.4784f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if (mFirstTime) {
			// Gdx.app.log("DEBUG", "Playing sound");
			if (SaveTheStars.mSoundOn) {
				long id = mGameOverSound.play();
				mGameOverSound.setVolume(id, 0.45f);
			}
			mFirstTime = !mFirstTime;
		}
		mSpriteBatch.begin();
		mSpriteBatch.draw(mBackground, 0, 0, mWidth, mHeight);
		drawScore();
		drawSavedStars();
		mSpriteBatch.end();
		mTime += delta;
	}

	private void drawSavedStars() {
		String number = mSavedStars + "";
		while (number.length() < 4)
			number = "0" + number;
		float tempX = mWidth - mWidth / 2.6f;
		for (int i = 3; i >= 0; i--) {
			tempX -= (mWidth / 33.1907f) * 1.5f;
			mSpriteBatch.draw(mNumbers[number.charAt(i) - 48], tempX,
					(mHeight - (mHeight / 14.5120f) * 7.5f),
					(mWidth / 33.1907f) * 1.5f, (mHeight / 14.5120f) * 1.5f);
		}

	}

	@Override
	public void resize(int width, int height) {
		this.mWidth = width;
		this.mHeight = height;

	}

	@Override
	public void show() {

		mGameOverSound = Gdx.audio.newSound(Gdx.files
				.internal("sounds/game_over.wav"));

		mBackground = new Texture(Gdx.files.internal("images/game_over.png"));
		mSpriteBatch = new SpriteBatch();
		mNumbers = new TextureRegion[10];
		loadNumbers();

		Gdx.input.setInputProcessor(this);
		Gdx.input.setCatchBackKey(true);
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		mBackground.dispose();
		mSpriteBatch.dispose();
		mNumberSpriteSheet.dispose();
		mGameOverSound.dispose();
		GameScreen.mGameMusic.dispose();
		if (SaveTheStars.mSoundOn) {
			SaveTheStars.mMenu.play();
		}
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Keys.BACK)
			mGame.changeScreen(new MenuScreen(mGame));
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (mTime > 3f)
			mGame.changeScreen(new MenuScreen(mGame));
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
