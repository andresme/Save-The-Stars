package com.anmorales.save_the_stars.world.actors;


import com.anmorales.save_the_stars.rendering.Renderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Enemy {

	public static final String[] TYPE = { "star", "asteroid", "ufo",
			"blackhole" };
	public static final float MAX_VEL = 7.5f;

	private Vector2 mPosition;
	private Rectangle mArea;
	private Vector2 mVelocity;
	private String mEnemyType;
	private float rotation;

	public Enemy(String pEnemyType) {
		mPosition = new Vector2();
		mArea = new Rectangle();
		mVelocity = new Vector2();
		mEnemyType = pEnemyType;
		calculateArea();
	}

	private void calculateArea() {
		if (mEnemyType.equals(TYPE[0])) {
			mArea.width = (Renderer.CAM_WIDTH / 5.6532f) * 0.60f;
			mArea.height = (Renderer.CAM_HEIGHT / 3.1859f) * 0.60f;
		} else if (mEnemyType.equals(TYPE[1])) {
			mArea.width = (Renderer.CAM_WIDTH / 4.3239f) * 0.60f;
			mArea.height = (Renderer.CAM_HEIGHT / 2.8414f) * 0.60f;
		} else if (mEnemyType.equals(TYPE[2])) {
			mArea.width = (Renderer.CAM_WIDTH / 4.5259f) * 0.60f;
			mArea.height = (Renderer.CAM_HEIGHT / 5.1478f) * 0.60f;
		} else if (mEnemyType.equals(TYPE[3])) {
			mArea.width = (Renderer.CAM_WIDTH / 5.4680f) * 0.60f;
			mArea.height = (Renderer.CAM_HEIGHT / 3.2827f) * 0.60f;
		}
	}

	public void update(float pDelta) {

		if (mPosition.x + mArea.width > Renderer.CAM_WIDTH) {
			mPosition.x = Renderer.CAM_WIDTH - mArea.width;
			mVelocity.x = -mVelocity.x;
			if (Math.random() > 0.85) {
				mVelocity.y = -mVelocity.y;
			}
		} else if (mPosition.x <= 0) {
			mPosition.x = 0;
			mVelocity.x = -mVelocity.x;
			if (Math.random() > 0.85) {
				mVelocity.y = -mVelocity.y;
			}
		}
		if (mPosition.y + mArea.height > Renderer.CAM_HEIGHT) {
			mPosition.y = Renderer.CAM_HEIGHT - mArea.height;
			mVelocity.y = -mVelocity.y;
			if (Math.random() > 0.85) {
				mVelocity.x = -mVelocity.x;
			}
		} else if (mPosition.y <= 0) {
			mPosition.y = 0;
			mVelocity.y = -mVelocity.y;
			if (Math.random() > 0.85) {
				mVelocity.x = -mVelocity.x;
			}
		}
		mPosition.add(mVelocity.cpy().mul(pDelta));
		mArea.x = mPosition.x;
		mArea.y = mPosition.y;
	}

	public Vector2 getPosition() {
		return mPosition;
	}

	public void setPosition(Vector2 pPosition) {
		this.mPosition = pPosition;
	}

	public Rectangle getArea() {
		return mArea;
	}

	public void setArea(Rectangle pArea) {
		this.mArea = pArea;
	}

	public Vector2 getVelocity() {
		return mVelocity;
	}

	public void setVelocity(Vector2 pVelocity) {
		this.mVelocity = pVelocity;
	}

	public String getmEnemyType() {
		return mEnemyType;
	}

	public void setmEnemyType(String mEnemyType) {
		this.mEnemyType = mEnemyType;
	}

	public float getRotation() {
		return rotation;
	}

	public void setRotation(float rotation) {
		this.rotation = rotation;
	}

}
