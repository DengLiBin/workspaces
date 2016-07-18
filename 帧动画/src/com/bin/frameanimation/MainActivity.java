package com.bin.frameanimation;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

public class MainActivity extends Activity {

	//����ʵ�ֵ�ԭ��y=at+b��Ҳ������������Ӧ��ϵ��,t:ʱ�䣬yλ�á�λ����ʱ��仯���Ƕ�����
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ImageView iv=(ImageView) this.findViewById(R.id.iv);
		//��֡��������Դ�ļ�ָ��Ϊiv�ı���
		iv.setBackgroundResource(R.drawable.frameanimation);
		//��ȡiv�ı���
		AnimationDrawable ad=(AnimationDrawable) iv.getBackground();
		ad.start();
	}
}
