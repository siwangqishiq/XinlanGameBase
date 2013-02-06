package com.xinlan.xinlangamebase.base;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Bitmap;

public class Waves {
	private XLDrawable mImage;
	private Bitmap mBitmap;
	private int width, height;

	public Waves(Bitmap _bitmap, int _width, int _height) {
		mBitmap = _bitmap;
		this.width = _width;
		this.height = _height;
		mImage = new XLDrawable(0, 0, 0, width << 2, height);
		mImage.loadBitmap(mBitmap, GL10.GL_REPEAT, GL10.GL_CLAMP_TO_EDGE);
		float textureCoordinates[] = { 0.0f, 1.0f, 2.0f, 1.0f, 0.0f, 0.0f,
				2.0f, 0.0f, };
		mImage.setTextureCoordinates(textureCoordinates);
	}

	public void update() {
		mImage.x-= 2;
		if (mImage.x <=- width<<1) {
			mImage.x = 0;
		}
	}

	public Mesh getMesh() {
		return mImage;
	}
}// end class
