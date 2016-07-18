package com.bin.copypic;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //���������ֻ����
        Bitmap bmSrc = BitmapFactory.decodeFile("sdcard/ice151228-02.jpg");
        
        //����ͼƬ����
        //1.���ڴ��д���һ����ԭͼһģһ����С��bitmap���󣬴�����ԭͼ��Сһ�µİ�ֽ
        Bitmap bmCopy = Bitmap.createBitmap(bmSrc.getWidth(), bmSrc.getHeight(), bmSrc.getConfig());
        
        //2.�������ʶ���
        Paint paint = new Paint();
        
        //3.����������󣬰Ѱ�ֽ���ڻ�����
        Canvas canvas = new Canvas(bmCopy);
        
        //4.��ʼ��������ԭͼ�����ݻ����ڰ�ֽ��
        Matrix mt=new Matrix();
        //ƽ��
//      mt.setTranslate(20, 40);
      //����
      //sx��ˮƽ��������ű���
      //sy����ֱ��������ű���
//      mt.setScale(0.5f, 0.5f);
//      mt.setScale(0.5f, 0.5f, bmCopy.getWidth() / 2, bmCopy.getHeight() / 2);
      //��ת
//      mt.setRotate(45, bmCopy.getWidth() / 2, bmCopy.getHeight() / 2);
      
      //����
//      mt.setScale(-1, 1);
//      mt.postTranslate(bmCopy.getWidth(), 0);
      //��Ӱ
        mt.setScale(1, -1);
        mt.postTranslate(0, bmCopy.getHeight());
        canvas.drawBitmap(bmSrc,mt, paint);
        
        ImageView iv_src = (ImageView) findViewById(R.id.iv_src);
        ImageView iv_copy = (ImageView) findViewById(R.id.iv_copy);
        iv_src.setImageBitmap(bmSrc);
        iv_copy.setImageBitmap(bmCopy);
    }
}
