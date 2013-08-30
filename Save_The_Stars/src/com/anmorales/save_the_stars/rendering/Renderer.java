package com.anmorales.save_the_stars.rendering;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract class Renderer {

	public static final float CAM_WIDTH = 100f;
	public static final float CAM_HEIGHT = CAM_WIDTH
			* ((float) Gdx.graphics.getHeight() / Gdx.graphics.getWidth());

	protected int mWidth;
	protected int mHeight;
	protected float mPpuX;
	protected float mPpuY;
	protected OrthographicCamera mCamera;
	protected SpriteBatch mSpriteBatch;
	protected ShapeRenderer mShapeRenderer;

	public Renderer() {
		mCamera = new OrthographicCamera(CAM_WIDTH, CAM_HEIGHT);
		mCamera.position.set(CAM_WIDTH / 2, CAM_HEIGHT / 2, 0);
		mCamera.update();
		mSpriteBatch = new SpriteBatch();
		mShapeRenderer = new ShapeRenderer();
	}

	public abstract void render(float pDelta);

	public abstract void dispose();

	public void resize(int pWidth, int pHeight) {
		this.mWidth = pWidth;
		this.mHeight = pHeight;

		mPpuX = (float) mWidth / CAM_WIDTH;
		mPpuY = (float) mHeight / CAM_HEIGHT;

		// Gdx.app.log("WIDTH", CAM_WIDTH + "");
		// Gdx.app.log("HEIGHT", CAM_HEIGHT + "");
	}

	public float getPpuX() {
		return mPpuX;
	}

	public void setPpuX(float pPpuX) {
		this.mPpuX = pPpuX;
	}

	public float getPpuY() {
		return mPpuY;
	}

	public void setPpuY(float pPpuY) {
		this.mPpuY = pPpuY;
	}

	public OrthographicCamera getCamera() {
		return mCamera;
	}

	public void setCamera(OrthographicCamera pCamera) {
		this.mCamera = pCamera;
	}

	public SpriteBatch getSpriteBatch() {
		return mSpriteBatch;
	}

	public void setSpriteBatch(SpriteBatch pSpriteBatch) {
		this.mSpriteBatch = pSpriteBatch;
	}

	public int getWidth() {
		return mWidth;
	}

	public void setWidth(int pWidth) {
		this.mWidth = pWidth;
	}

	public int getHeight() {
		return mHeight;
	}

	public void setHeight(int pHeight) {
		this.mHeight = pHeight;
	}

	public ShapeRenderer getShapeRenderer() {
		return mShapeRenderer;
	}

	public void setShapeRenderer(ShapeRenderer pShapeRenderer) {
		this.mShapeRenderer = pShapeRenderer;
	}

}
