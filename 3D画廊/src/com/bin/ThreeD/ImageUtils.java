package com.bin.ThreeD;

import android.R.integer;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;

public class ImageUtils {
	/**
	 * 返回处理后的图片
	 * @param imageId
	 * @return
	 */
	public static Bitmap getInvertImage(Resources res,int imageId){
		//获取原图
		Bitmap sourceBitmap=BitmapFactory.decodeResource(res, imageId);
		//生成倒影图片
		Matrix m=new Matrix();//图形矩阵
		m.setScale(1f, -1f);//让图形按照矩阵垂直反转
		Bitmap copyBitmap=Bitmap.createBitmap(sourceBitmap,0,sourceBitmap.getHeight()/2,
								sourceBitmap.getWidth(),sourceBitmap.getHeight()/2,m,false);
		//两张图片合在一起
		Bitmap resultBitmap=Bitmap.createBitmap(sourceBitmap.getWidth(),
						(int)(sourceBitmap.getHeight()*1.5+5),Config.ARGB_8888);
		
		Canvas canvas=new Canvas(resultBitmap);//指定画板画在合成图片上
		canvas.drawBitmap(sourceBitmap,0,0,null);//把原图画在合成图片的上面
		canvas.drawBitmap(copyBitmap, 0,sourceBitmap.getHeight()+5,null);
		//遮罩效果
		
		return resultBitmap;
	}
}
