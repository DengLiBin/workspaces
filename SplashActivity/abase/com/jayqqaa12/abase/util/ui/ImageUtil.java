package com.jayqqaa12.abase.util.ui;


import com.jayqqaa12.abase.util.ManageUtil;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.Display;
import android.view.WindowManager;

public class ImageUtil {

	/**
	 * 
	 * 自适应 屏幕 设置 图片 大小
	 * 
	 * @param drawable
	 * @return
	 */
	public static Bitmap getResizedBitmap(BitmapDrawable drawable,
			Context context) {
		
		Bitmap bitmap = drawable.getBitmap();
		WindowManager manager = ManageUtil.getWindowManager();
		Display display = manager.getDefaultDisplay();
		int height = display.getHeight();
		int width = display.getWidth();
		if (height < 480 || width < 320) {
			return Bitmap.createScaledBitmap(bitmap, 32, 32, false);
		} else {
			return Bitmap.createScaledBitmap(bitmap, 48, 48, false);
		}
	}
}
