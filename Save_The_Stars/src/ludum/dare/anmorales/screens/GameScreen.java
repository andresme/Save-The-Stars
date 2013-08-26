package ludum.dare.anmorales.screens;

import ludum.dare.anmorales.SaveTheStars;
import ludum.dare.anmorales.rendering.GameRenderer;
import ludum.dare.anmorales.world.GameWorld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;

public class GameScreen implements Screen, InputProcessor {

	public static boolean mPause = false;
	private SaveTheStars mGame;
	private GameWorld mWorld;
	private GameRenderer mRenderer;
	private boolean mLose = false;
	public static Music mGameMusic;

	public GameScreen(SaveTheStars pGame) {
		mGame = pGame;
	}

	@Override
	public void render(float pDelta) {
		Gdx.gl.glClearColor(0.2313f, 0.1058f, 0.4784f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if (!mPause) {
			mLose = mWorld.update(pDelta);
			mRenderer.render(pDelta);
		}
		if (mLose) {
			mGame.changeScreen(new GameOverScreen(mGame,
					mWorld.getSavedStars(), mWorld.getScore()));
		}
	}

	@Override
	public void resize(int pWidth, int pHeight) {
		mRenderer.resize(pWidth, pHeight);
	}

	@Override
	public void show() {
		mWorld = new GameWorld();
		mRenderer = new GameRenderer(mWorld);
		mGameMusic = Gdx.audio.newMusic(Gdx.files
				.internal("soundtrack/game.mp3"));
		mGameMusic.setLooping(true);
		if (SaveTheStars.mSoundOn) {
			SaveTheStars.mMenu.stop();
			mGameMusic.play();
		}
		Gdx.input.setInputProcessor(this);
		Gdx.input.setCatchBackKey(true);

	}

	@Override
	public void hide() {
		mPause = true;

	}

	@Override
	public void pause() {
		mPause = true;

	}

	@Override
	public void resume() {
		if(SaveTheStars.mSoundOn)
			mGameMusic.stop();
		mGame.changeScreen(new MenuScreen(mGame));
		mPause = false;
	}

	@Override
	public void dispose() {
		mRenderer.dispose();
		mWorld.dispose();
		mPause = false;
	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.BACK) {
			Gdx.input.setCatchBackKey(false);
			mGame.changeScreen(new MenuScreen(mGame));
			
			if (SaveTheStars.mSoundOn) {
				mGameMusic.stop();
				SaveTheStars.mMenu.play();
			}
		}
		return true;
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
		float x = screenX / mRenderer.getPpuX();
		float y = (Gdx.graphics.getHeight() - screenY) / mRenderer.getPpuY();
		mWorld.touchDetected(x, y);
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {

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
