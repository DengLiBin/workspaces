package com.bin.drawboard;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.view.View.OnTouchListener;

public class MainActivity extends Activity {
	private ImageView iv;

	int startX;
	int startY;

	private Canvas canvas;

	private Paint paint;

	private Bitmap bmCopy;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//���ػ�����ı���ͼ
		Bitmap bmSrc = BitmapFactory.decodeResource(getResources(), R.drawable.background);
		//���ڴ��д���һ���뱳��ͼһģһ����С��bitmap���󣬴�����ԭͼ��Сһ�µİ�ֽ
		bmCopy = Bitmap.createBitmap(bmSrc.getWidth(), bmSrc.getHeight(), bmSrc.getConfig());
		paint = new Paint();
		canvas = new Canvas(bmCopy);
		//���ƣ�����������
		canvas.drawBitmap(bmSrc, new Matrix(), paint);
		
		iv = (ImageView) findViewById(R.id.iv);
		iv.setImageBitmap(bmCopy);
		
		//���ô�������
		iv.setOnTouchListener(new OnTouchListener() {
			
			//������Ļʱ�������¼�����ʱ���˷�������
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				int action = event.getAction();
				switch (action) {
				//�û���ָ������Ļ
				case MotionEvent.ACTION_DOWN:
					startX = (int) event.getX();
					startY = (int) event.getY();
					break;
				//�û���ָ���ڻ���
				case MotionEvent.ACTION_MOVE:
					int x = (int) event.getX();
					int y = (int) event.getY();
					//����������֮�仭һ����
					canvas.drawLine(startX, startY, x, y, paint);
					//ÿ�λ������֮�󣬱��λ��ƵĽ�����������һ�λ��Ƶĳ�ʼ����
					startX = x;
					startY = y;
					iv.setImageBitmap(bmCopy);
					break;
				//�û���ָ�뿪��Ļ
				case MotionEvent.ACTION_UP:
					break;

				}
				//true������ϵͳ����������¼�����������
				//false������ϵͳ����������¼��Ҳ�������ʱϵͳ��Ѵ����¼����ݸ�ImageView�ĸ��ڵ� RelativeLayout
				return true;
			}
		});
			
		
	}

	public void red(View v){
		paint.setColor(Color.RED);
	}
	public void green(View v){
		paint.setColor(Color.GREEN);
	}
	public void brush(View v){
		paint.setStrokeWidth(7);
	}
	public void save(View v){
		File file = new File("sdcard/dazuo.png");
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bmCopy.compress(CompressFormat.PNG, 100, fos);//100��ʾѹ��������0-100��
		/*
		����ͼƬ
		SDÿ��׼����ʱ��ϵͳ��ʵ�Ǳ���sd�������ļ���ϵͳ������еĶ�ý���ļ�������MediaStore���ݿ�������һ�����������ݿ��б������ļ����ļ�����·������С�����Ⱥ�������
		ͼ�⡢���֡���Ƶ����ÿ������ʱ����ʵ����ȥ����sd��Ѱ�Ҷ�ý���ļ�������ֱ�Ӵ�MediaStore���ݿ��ж�ȡ��ý���ļ���ͨ�����е������ҵ���Ӧ�Ķ�ý���ļ��󣬰��ļ���ʾ�ڽ���
		*/
		//����sd�������㲥(����Sdcard)
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_MEDIA_MOUNTED);
		intent.setData(Uri.fromFile(Environment.getExternalStorageDirectory()));
		sendBroadcast(intent);
	}
}
