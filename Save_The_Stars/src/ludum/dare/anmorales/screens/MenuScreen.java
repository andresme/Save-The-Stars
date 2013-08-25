package ludum.dare.anmorales.screens;

import ludum.dare.anmorales.SaveTheStars;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class MenuScreen implements Screen, InputProcessor {

	private SaveTheStars mGame;

	private int mWidth;
	private int mHeight;

	private Texture mPlayButtonSpriteSheet;
	private Texture mScoreButtonSpriteSheet;
	private Texture mAboutButtonSpriteSheet;
	private Texture mSoundButtonSpriteSheet;
	private Texture mBackground;

	private TextureRegion[] mPlay = new TextureRegion[2];
	private TextureRegion[] mExit = new TextureRegion[2];
	private TextureRegion[] mAbout = new TextureRegion[2];
	private TextureRegion[] mSound = new TextureRegion[2];

	private Sound mButtonPressed;

	private boolean mPlayPressed = false;
	private boolean mExitPressed = false;
	private boolean mAboutPressed = false;

	private Rectangle mPlayRect = new Rectangle();
	private Rectangle mExitRect = new Rectangle();
	private Rectangle mAboutRect = new Rectangle();
	private Rectangle mSoundRect = new Rectangle();

	private float mBtn_width;
	private float mBtn_height;

	private float mBtnSound_width;
	private float mBtnSound_height;

	private SpriteBatch mSpriteBatch;

	public MenuScreen(SaveTheStars pGdxGame) {
		this.mGame = pGdxGame;

	}

	@Override
	public void render(float pDelta) {
		Gdx.gl.glClearColor(0.2313f, 0.1058f, 0.4784f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		mSpriteBatch.begin();
		mSpriteBatch.draw(mBackground, 0, 0, mWidth, mHeight);

		if (mPlayPressed) {
			mSpriteBatch.draw(mPlay[0], mPlayRect.x, mPlayRect.y, mBtn_width,
					mBtn_height);
		} else {
			mSpriteBatch.draw(mPlay[1], mPlayRect.x, mPlayRect.y, mBtn_width,
					mBtn_height);
		}
		if (mExitPressed) {
			mSpriteBatch.draw(mExit[0], mExitRect.x, mExitRect.y, mBtn_width,
					mBtn_height);
		} else {
			mSpriteBatch.draw(mExit[1], mExitRect.x, mExitRect.y, mBtn_width,
					mBtn_height);
		}
		if (mAboutPressed) {
			mSpriteBatch.draw(mAbout[0], mAboutRect.x, mAboutRect.y,
					mBtn_width, mBtn_height);
		} else {
			mSpriteBatch.draw(mAbout[1], mAboutRect.x, mAboutRect.y,
					mBtn_width, mBtn_height);
		}
		if (SaveTheStars.mSoundOn) {
			mSpriteBatch.draw(mSound[0], mSoundRect.x, mSoundRect.y,
					mBtnSound_width, mBtnSound_height);
		} else {
			mSpriteBatch.draw(mSound[1], mSoundRect.x, mSoundRect.y,
					mBtnSound_width, mBtnSound_height);
		}
		mSpriteBatch.end();

	}

	@Override
	public void resize(int width, int height) {
		this.mWidth = width;
		this.mHeight = height;

		mBtn_width = (this.mWidth / 2.2550f) * 0.55f;
		mBtn_height = (this.mHeight / 4.8113f) * 0.55f;

		mBtnSound_width = (this.mWidth / 7.1296f) * 0.55f;
		mBtnSound_height = (this.mHeight / 4.0778f) * 0.55f;

		mPlayRect.x = (width / 2) - (mBtn_width / 2);
		mPlayRect.y = (height / 2) - (mBtn_height / 7f);
		mPlayRect.width = mBtn_width;
		mPlayRect.height = mBtn_height;

		mAboutRect.x = (width / 2) - (mBtn_width / 2);
		mAboutRect.y = (height / 2) - (mBtn_height / 0.75f);
		mAboutRect.width = mBtn_width;
		mAboutRect.height = mBtn_height;

		mExitRect.x = (width / 2) - (mBtn_width / 2);
		mExitRect.y = (height / 2) - (mBtn_height / 0.4f);
		mExitRect.width = mBtn_width;
		mExitRect.height = mBtn_height;

		mSoundRect.x = (float) width / (float) 50;
		mSoundRect.y = (float) height / (float) 90;
		mSoundRect.width = mBtnSound_width;
		mSoundRect.height = mBtnSound_height;

	}

	@Override
	public void show() {
		mBackground = new Texture(Gdx.files.internal("images/main_menu.png"));

		mPlayButtonSpriteSheet = new Texture(
				Gdx.files.internal("images/playbtn.png"));
		mScoreButtonSpriteSheet = new Texture(
				Gdx.files.internal("images/exitbtn.png"));
		mAboutButtonSpriteSheet = new Texture(
				Gdx.files.internal("images/aboutbtn.png"));
		mSoundButtonSpriteSheet = new Texture(
				Gdx.files.internal("images/soundbtn.png"));

		mPlay[0] = new TextureRegion(mPlayButtonSpriteSheet, 0, 0, 0.5f, 1);
		mPlay[1] = new TextureRegion(mPlayButtonSpriteSheet, 0.5f, 0, 1, 1);

		mExit[0] = new TextureRegion(mScoreButtonSpriteSheet, 0, 0, 0.5f, 1);
		mExit[1] = new TextureRegion(mScoreButtonSpriteSheet, 0.5f, 0, 1, 1);

		mAbout[0] = new TextureRegion(mAboutButtonSpriteSheet, 0, 0, 0.5f, 1);
		mAbout[1] = new TextureRegion(mAboutButtonSpriteSheet, 0.5f, 0, 1, 1);

		mSound[0] = new TextureRegion(mSoundButtonSpriteSheet, 0, 0, 0.5f, 1);
		mSound[1] = new TextureRegion(mSoundButtonSpriteSheet, 0.5f, 0, 1, 1);

		mSpriteBatch = new SpriteBatch();

		mButtonPressed = Gdx.audio.newSound(Gdx.files
				.internal("sounds/button_click.wav"));
		Gdx.input.setInputProcessor(this);
		Gdx.input.setCatchBackKey(true);
	}

	@Override
	public void hide() {

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
		mPlayButtonSpriteSheet.dispose();
		mScoreButtonSpriteSheet.dispose();
		mAboutButtonSpriteSheet.dispose();
		mSpriteBatch.dispose();
		mBackground.dispose();
		mButtonPressed.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.BACK)
			Gdx.app.exit();
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		float x = screenX;
		float y = mHeight - screenY;
		if (mPlayRect.contains(x, y)) {
			mPlayPressed = true;
			if (SaveTheStars.mSoundOn){
				long id = mButtonPressed.play();
				mButtonPressed.setVolume(id, 0.45f);
			}
		} else if (mExitRect.contains(x, y)) {
			mExitPressed = true;
			if (SaveTheStars.mSoundOn){
				long id = mButtonPressed.play();
				mButtonPressed.setVolume(id, 0.45f);
			}
		} else if (mAboutRect.contains(x, y)) {
			mAboutPressed = true;
			if (SaveTheStars.mSoundOn){
				long id = mButtonPressed.play();
				mButtonPressed.setVolume(id, 0.45f);
			}
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		float x = screenX;
		float y = mHeight - screenY;
		if (mPlayRect.contains(x, y)) {
			mGame.changeScreen(new HowToScreen(mGame));
		} else if (mExitRect.contains(x, y)) {
			Gdx.app.exit();
		} else if (mAboutRect.contains(x, y)) {
			mGame.changeScreen(new AboutScreen(mGame));
		} else {
			mPlayPressed = false;
			mExitPressed = false;
			mAboutPressed = false;
		}
		if (mSoundRect.contains(x, y)) {
			SaveTheStars.mSoundOn = !SaveTheStars.mSoundOn;
			SaveTheStars.mPreferences.putBoolean("sound_on",
					SaveTheStars.mSoundOn);
			if (SaveTheStars.mSoundOn && !SaveTheStars.mMenu.isPlaying())
				SaveTheStars.mMenu.play();
			else if (!SaveTheStars.mSoundOn && SaveTheStars.mMenu.isPlaying()) {
				SaveTheStars.mMenu.stop();
			}
		}
		return true;
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
