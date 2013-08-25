package ludum.dare.anmorales.rendering;

import ludum.dare.anmorales.world.GameWorld;
import ludum.dare.anmorales.world.actors.Enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class GameRenderer extends Renderer {

	private GameWorld mWorld;
	private Texture mNumberSpriteSheet;
	private Texture mBackground;
	private Texture mScore;
	private Texture mTime;
	private Texture mLives;
	private Texture mLive;
	private TextureRegion[] mNumbers;
	private Texture[] mEnemies;
	private int current = 9;
	private boolean debug = false;

	@Override
	public void dispose() {
		mNumberSpriteSheet.dispose();
		mBackground.dispose();
		mScore.dispose();
		mTime.dispose();
		mLives.dispose();
		mLive.dispose();
		mSpriteBatch.dispose();
		mShapeRenderer.dispose();
		for (int i = 0; i < mEnemies.length; i++)
			mEnemies[i].dispose();
	}

	public GameRenderer(GameWorld pWorld) {
		super();
		mWorld = pWorld;
		mNumbers = new TextureRegion[10];
		mEnemies = new Texture[4];
		mBackground = new Texture(Gdx.files.internal("images/background.png"));
		mScore = new Texture(Gdx.files.internal("images/score.png"));
		mTime = new Texture(Gdx.files.internal("images/time.png"));
		mLives = new Texture(Gdx.files.internal("images/lives.png"));
		mLive = new Texture(Gdx.files.internal("images/live.png"));
		loadNumbers();
		loadEnemies();
	}

	private void loadEnemies() {

		mEnemies[0] = new Texture(Gdx.files.internal("images/star.png"));
		mEnemies[1] = new Texture(Gdx.files.internal("images/asteroid.png"));
		mEnemies[2] = new Texture(Gdx.files.internal("images/ufo.png"));
		mEnemies[3] = new Texture(Gdx.files.internal("images/blackhole.png"));

	}

	private void loadNumbers() {
		float stepX = 0.1f;
		float x = 0;

		mNumberSpriteSheet = new Texture(
				Gdx.files.internal("images/numbers.png"));

		for (int i = 0; i < mNumbers.length; i++) {
			mNumbers[i] = new TextureRegion(mNumberSpriteSheet, x, 0,
					x += stepX, 1);
		}
	}

	@Override
	public void render(float pDelta) {
		current = (int) mWorld.getTimer();
		mSpriteBatch.begin();
		mSpriteBatch.draw(mBackground, 0, 0, CAM_WIDTH * mPpuX, CAM_HEIGHT
				* mPpuY);
		drawTime();
		drawScore();
		drawLives();
		drawEnemies();
		mSpriteBatch.end();
		if (debug) {
			mShapeRenderer.begin(ShapeType.Rectangle);
			drawDebug();
			mShapeRenderer.end();
		}

	}

	private void drawLives() {
		float x = ((CAM_WIDTH / 2) - (CAM_WIDTH / 6.4202f));
		mSpriteBatch.draw(mLives, x * mPpuX,
				(CAM_HEIGHT - CAM_HEIGHT / 14.3884f) * mPpuY,
				(CAM_WIDTH / 6.4202f) * mPpuX, (CAM_HEIGHT / 14.3884f) * mPpuY);

		float tempX = 1.5f + x + (CAM_WIDTH / 6.4202f);
		for (int i = 0; i < mWorld.getLives(); i++) {
			mSpriteBatch.draw(mLive, tempX * mPpuX,
					(CAM_HEIGHT - CAM_HEIGHT / 20.4064f) * mPpuY,
					(CAM_WIDTH / 35.8430f) * mPpuX, (CAM_HEIGHT / 20.4064f)
							* mPpuY);
			tempX += (CAM_WIDTH / 35.8430f) + 1.5f;
		}

	}

	private void drawTime() {
		mSpriteBatch.draw(mTime, 0, (CAM_HEIGHT - CAM_HEIGHT / 14.1839f)
				* mPpuY, (CAM_WIDTH / 3.7153f) * mPpuX, (CAM_HEIGHT / 14.1839f)
				* mPpuY);

		mSpriteBatch
				.draw(mNumbers[current],
						((CAM_WIDTH / 3.7153f) + 1.5f) * mPpuX,
						(CAM_HEIGHT - CAM_HEIGHT / 14.5120f) * mPpuY,
						(CAM_WIDTH / 33.1907f) * mPpuX, (CAM_HEIGHT / 14.5120f)
								* mPpuY);

	}

	private void drawScore() {
		String number = mWorld.getScore() + "";
		while (number.length() < 4)
			number = "0" + number;
		float tempX = CAM_WIDTH;
		for (int i = 3; i >= 0; i--) {
			tempX -= (CAM_WIDTH / 33.1907f);
			mSpriteBatch.draw(mNumbers[number.charAt(i) - 48], tempX * mPpuX,
					(CAM_HEIGHT - (CAM_HEIGHT / 14.5120f)) * mPpuY,
					(CAM_WIDTH / 33.1907f) * mPpuX, (CAM_HEIGHT / 14.5120f)
							* mPpuY);
		}
		tempX -= CAM_WIDTH / 44.8933f + CAM_WIDTH / 6.4202f;

		mSpriteBatch.draw(mScore, tempX * mPpuX,
				(CAM_HEIGHT - (CAM_HEIGHT / 15.4798f)) * mPpuX,
				(CAM_WIDTH / 6.4202f) * mPpuX, (CAM_HEIGHT / 15.4798f) * mPpuY);

		// (CAM_WIDTH / 33.1907f), (CAM_HEIGHT / 14.5120f)
	}

	private void drawEnemies() {
		for (Enemy i : mWorld.getActiveEnemies()) {
			int texture = findTexture(i.getmEnemyType());
			mSpriteBatch.draw(mEnemies[texture], i.getPosition().x * mPpuX,
					i.getPosition().y * mPpuY, i.getArea().width * mPpuX,
					i.getArea().height * mPpuY);
		}

	}

	private void drawDebug() {
		for (Enemy i : mWorld.getActiveEnemies()) {
			mShapeRenderer.setColor(1, 0, 0, 1);
			mShapeRenderer.rect(i.getArea().x * mPpuX, i.getArea().y * mPpuY,
					i.getArea().width * mPpuX, i.getArea().height * mPpuY);
		}
	}

	private int findTexture(String pEnemyType) {
		if (pEnemyType.equals(Enemy.TYPE[0]))
			return 0;
		else if (pEnemyType.equals(Enemy.TYPE[1]))
			return 1;
		else if (pEnemyType.equals(Enemy.TYPE[2]))
			return 2;
		else if (pEnemyType.equals(Enemy.TYPE[3]))
			return 3;
		return 0;
	}

}
