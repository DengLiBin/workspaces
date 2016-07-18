package com.bin.loadpic;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void click(View v){
    	//����ͼƬʱ��Ҫʹ�õ��Ĳ�������װ�������������
    	Options opt = new Options();
    	//��Ϊ���������ڴ棬ֻ��ȡͼƬ���
    	opt.inJustDecodeBounds = true;
    	BitmapFactory.decodeFile("sdcard/dog.jpg", opt);
    	//�õ�ͼƬ���
    	int imageWidth = opt.outWidth;
    	int imageHeight = opt.outHeight;
    	
    	Display dp = getWindowManager().getDefaultDisplay();
    	//�õ���Ļ���
		int screenWidth = dp.getWidth();
    	int screenHeight = dp.getHeight();
    	
    	//�������ű���
    	int scale = 1;
    	int scaleWidth = imageWidth / screenWidth;
    	int scaleHeight = imageHeight / screenHeight;
    	if(scaleWidth >= scaleHeight && scaleWidth >= 1){
    		scale = scaleWidth;
    	}
    	else if(scaleWidth < scaleHeight && scaleHeight >= 1){
    		scale = scaleHeight;
    	}
    	
    	//�������ű���
    	opt.inSampleSize = scale;
    	opt.inJustDecodeBounds = false;
    	Bitmap bm = BitmapFactory.decodeFile("sdcard/dog.jpg", opt);
    	
    	ImageView iv = (ImageView) findViewById(R.id.iv);
    	iv.setImageBitmap(bm);
    }
}
