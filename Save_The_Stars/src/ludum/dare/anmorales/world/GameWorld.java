package ludum.dare.anmorales.world;

import java.util.ArrayList;
import java.util.List;

import ludum.dare.anmorales.SaveTheStars;
import ludum.dare.anmorales.rendering.Renderer;
import ludum.dare.anmorales.screens.GameScreen;
import ludum.dare.anmorales.world.actors.Enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;

public class GameWorld implements World {

	private List<Enemy> activeEnemies;
	private long mScore;
	private int mLevel;
	private float mTimer;
	private int[] mSign = { -1, 1 };
	private boolean mLose = false;
	private int mSavedStars = 0;
	private int mLives = 4;

	private Sound mHitStarSound;
	private Sound mEnemyDestroyedSound;

	public GameWorld() {
		activeEnemies = new ArrayList<Enemy>();
		mScore = 0;
		mLevel = 1;
		createWorld(mLevel);

		mHitStarSound = Gdx.audio.newSound(Gdx.files
				.internal("sounds/star_touched.wav"));

		mEnemyDestroyedSound = Gdx.audio.newSound(Gdx.files
				.internal("sounds/enemy_destroyed.wav"));

	}

	private void createWorld(int pLevel) {
		int minEnemyNumber = 3;
		int maxEnemyNumber = (int) (3 + Math.random()
				* (minEnemyNumber + mLevel * 0.85));

		for (int i = 0; i < maxEnemyNumber; i++) {
			String enemyType = Enemy.TYPE[(int) (1 + Math.random()
					* (Enemy.TYPE.length - 1))];
			// Gdx.app.log("Created", enemyType);
			activeEnemies.add(new Enemy(enemyType));

		}

		for (int i = 0; i < mLevel * 0.5f; i++) {
			activeEnemies.add(new Enemy("star"));
		}

		for (int i = 0; i < activeEnemies.size(); i++) {
			float x = (float) (Math.random() * (Renderer.CAM_WIDTH - 15));
			float y = (float) (Math.random() * (Renderer.CAM_HEIGHT - 15));
			float velX = (float) (Enemy.MAX_VEL + Math.random()
					* (Enemy.MAX_VEL * mLevel));
			float velY = (float) (Enemy.MAX_VEL + Math.random()
					* (Enemy.MAX_VEL * mLevel));
			if (velX > Enemy.MAX_VEL * 7f) {
				velX = Enemy.MAX_VEL * 7f;
			}
			if (velY > Enemy.MAX_VEL * 7f) {
				velY = Enemy.MAX_VEL * 7f;
			}
			float rotation = (float) (Math.random() * 360f);
			int negX = (int) (Math.random() * 2);
			int negY = (int) (Math.random() * 2);
			activeEnemies.get(i).setPosition(new Vector2(x, y));
			activeEnemies.get(i).setVelocity(
					new Vector2(velX * mSign[negX], velY * mSign[negY]));
			if (!activeEnemies.get(i).getmEnemyType().equals("ufo"))
				activeEnemies.get(i).setRotation(rotation);
		}
		setTimer(9.9999f);

	}

	@Override
	public boolean update(float pDelta) {
		mTimer -= pDelta;
		if (mTimer < 0 || mLives < 0) {
			GameScreen.mPause = true;
			mLose = true;

		}
		boolean incrementLevel = false;
		for (int i = 0; i < activeEnemies.size(); i++) {
			if (!activeEnemies.get(i).getmEnemyType().equals("star"))
				break;
			if (i == activeEnemies.size() - 1) {
				incrementLevel = true;
			}
		}
		if (incrementLevel) {
			setSavedStars(getSavedStars() + activeEnemies.size());
			mScore += activeEnemies.size() * mTimer;
			incrementLevel();
		} else {
			for (Enemy i : activeEnemies) {
				i.update(pDelta);
			}
		}
		return mLose;
	}

	@Override
	public void dispose() {

		mHitStarSound.dispose();
		mEnemyDestroyedSound.dispose();
	}

	public List<Enemy> getActiveEnemies() {
		return activeEnemies;
	}

	public void setActiveEnemies(List<Enemy> pActiveEnemies) {
		this.activeEnemies = pActiveEnemies;
	}

	public long getScore() {
		return mScore;
	}

	public void setScore(long pScore) {
		this.mScore = pScore;
	}

	public int getLevel() {
		return mLevel;
	}

	public void setLevel(int pLevel) {
		this.mLevel = pLevel;
	}

	public void deleteEnemy() {

	}

	public void incrementLevel() {
		GameScreen.mPause = true;
		int limit = activeEnemies.size();
		for (int i = 0; i < limit; i++) {
			activeEnemies.remove(0);
		}
		mLevel++;
		if (activeEnemies.isEmpty())
			createWorld(mLevel);
		GameScreen.mPause = false;
	}

	public void touchDetected(float pX, float pY) {
		int index = 0;
		boolean hitStar = false;
		boolean hit = false;
		for (index = 0; index < activeEnemies.size(); index++) {
			if (activeEnemies.get(index).getArea().contains(pX, pY)) {
				if (activeEnemies.get(index).getmEnemyType().equals("star")) {
					hitStar = true;
				} else {
					hit = true;
					break;
				}
			}
		}
		if (hit) {
			if (SaveTheStars.mSoundOn) {
				long id = mEnemyDestroyedSound.play();
				mEnemyDestroyedSound.setVolume(id, 0.25f);
			}
			activeEnemies.remove(index);
			mScore += 1;
		} else if (hitStar) {
			if (SaveTheStars.mSoundOn) {
				long id = mHitStarSound.play();
				mHitStarSound.setVolume(id, 0.25f);
			}
			if (mScore >= 15) {
				mScore -= 15;
			}
			if (mSavedStars > 0)
				mSavedStars--;
			mLives--;
		}
	}

	public float getTimer() {
		return mTimer;
	}

	public void setTimer(float timer) {
		this.mTimer = timer;
	}

	public int getSavedStars() {
		return mSavedStars;
	}

	public void setSavedStars(int pSavedStars) {
		this.mSavedStars = pSavedStars;
	}

	public int getLives() {
		return mLives;
	}

	public void setLives(int pLives) {
		this.mLives = pLives;
	}

}
