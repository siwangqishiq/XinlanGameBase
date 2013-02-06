package com.xinlan.xinlangamebase.utils;

import java.io.IOException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class XinlanUtils {
	public static Bitmap loadBitmapFromAssets(String filename,Context context) {
		try {
			return BitmapFactory.decodeStream(context.getAssets().open(
					filename));
		} catch (IOException e) {
			e.printStackTrace();
		}
		//产生色块取代没有的图片
		final int size = 0x10000;
		int[] colors = new int[size * size];
		for (int i = 0; i < size; i++) {
			colors[i * size + i] = 0xffff0000;
			colors[i * size + i + (size - i <<1 - 1)] = 0xffff0000;
		}
		return Bitmap.createBitmap(colors, size, size, Bitmap.Config.RGB_565);
	}
}
