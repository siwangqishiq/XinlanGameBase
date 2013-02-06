package com.xinlan.xinlangamebase.base;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Bitmap;

public class Background {
	private Bitmap mBitmap;
	private XLDrawable mImage;
	private int mWidth, mHeight;

	public Background(Bitmap _bitmap, int screenW, int screenH) {
		mBitmap = _bitmap;
		mWidth = screenW;
		mHeight = screenH;
		loadImage();
	}

	private void loadImage() {
		mImage = new XLDrawable(0, 0, 0, mWidth << 2, mHeight);
		mImage.loadBitmap(mBitmap, GL10.GL_REPEAT, GL10.GL_CLAMP_TO_EDGE);// 横轴上复制图片,纵轴上仅显示一次
		//mImage.z = -0.1f;
		float textureCoordinates[] = { 0.0f, 1.0f, 2.0f, 1.0f, 0.0f, 0.0f,
				2.0f, 0.0f, };
		mImage.setTextureCoordinates(textureCoordinates);
	}

	public Mesh getMesh() {
		return mImage;
	}

	public void update() {
		mImage.x -= 1;
		if (mImage.x < -mWidth * 2)
			mImage.x = 0;
	}
}// end class
