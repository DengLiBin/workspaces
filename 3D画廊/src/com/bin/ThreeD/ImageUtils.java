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
	 * ���ش�����ͼƬ
	 * @param imageId
	 * @return
	 */
	public static Bitmap getInvertImage(Resources res,int imageId){
		//��ȡԭͼ
		Bitmap sourceBitmap=BitmapFactory.decodeResource(res, imageId);
		//���ɵ�ӰͼƬ
		Matrix m=new Matrix();//ͼ�ξ���
		m.setScale(1f, -1f);//��ͼ�ΰ��վ���ֱ��ת
		Bitmap copyBitmap=Bitmap.createBitmap(sourceBitmap,0,sourceBitmap.getHeight()/2,
								sourceBitmap.getWidth(),sourceBitmap.getHeight()/2,m,false);
		//����ͼƬ����һ��
		Bitmap resultBitmap=Bitmap.createBitmap(sourceBitmap.getWidth(),
						(int)(sourceBitmap.getHeight()*1.5+5),Config.ARGB_8888);
		
		Canvas canvas=new Canvas(resultBitmap);//ָ�����廭�ںϳ�ͼƬ��
		canvas.drawBitmap(sourceBitmap,0,0,null);//��ԭͼ���ںϳ�ͼƬ������
		canvas.drawBitmap(copyBitmap, 0,sourceBitmap.getHeight()+5,null);
		//����Ч��
		
		return resultBitmap;
	}
}
