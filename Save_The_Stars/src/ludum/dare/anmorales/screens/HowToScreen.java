package ludum.dare.anmorales.screens;

import ludum.dare.anmorales.SaveTheStars;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HowToScreen implements Screen, InputProcessor {

	private SaveTheStars mGame;
	private Texture mBackground;
	private SpriteBatch mSpriteBatch;

	public HowToScreen(SaveTheStars pGame) {
		mGame = pGame;

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.2313f, 0.1058f, 0.4784f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		mSpriteBatch.begin();
		mSpriteBatch.draw(mBackground, 0, 0, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
		mSpriteBatch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		mBackground = new Texture(Gdx.files.internal("images/how_to.png"));
		mSpriteBatch = new SpriteBatch();

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
		mGame.changeScreen(new GameScreen(mGame));
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