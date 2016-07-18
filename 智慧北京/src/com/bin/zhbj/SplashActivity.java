package com.bin.zhbj;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

public class SplashActivity extends Activity {
	RelativeLayout rlRoot;
	private SharedPreferences sp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		sp = getSharedPreferences("config",MODE_PRIVATE);
		rlRoot=(RelativeLayout) findViewById(R.id.rlRoot);
		startAnim(rlRoot);
		
	}
	/**
	 * ��������
	 */
	private void startAnim(RelativeLayout Root){
		//��������
		AnimationSet set=new AnimationSet(false);
		//��ת����
		RotateAnimation rotate=new RotateAnimation(0,360,Animation.RELATIVE_TO_SELF,
				0.5f,Animation.RELATIVE_TO_SELF,0.5f);
		rotate.setDuration(1000);//����ʱ��
		rotate.setFillAfter(true);//���ֶ���״̬
		
		//���Ŷ���
		ScaleAnimation scale=new ScaleAnimation(0,1,0,1,Animation.RELATIVE_TO_SELF,
				0.5f,Animation.RELATIVE_TO_SELF,0.5f);
		scale.setDuration(1000);
		scale.setFillAfter(true);
		
		//���䶯��
		AlphaAnimation alpha=new AlphaAnimation(0,1);
		alpha.setDuration(1000);
		alpha.setFillAfter(true);
		
		set.addAnimation(scale);
		set.addAnimation(rotate);
		set.addAnimation(alpha);
		//���ö�������
		set.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				//������ɺ���ת����һҳ
				
				jumpNextPage();
			}
		});
		Root.startAnimation(set);
	}
	/**
	 * ��ת����һҳ
	 */
	private void jumpNextPage(){
		//�ж�֮ǰ��û����ʾ����������ҳ
		
		boolean userGuide=sp.getBoolean("is_user_guide_showed", false);
		if(!userGuide){
			//��ת����������ҳ
			startActivity(new Intent(SplashActivity.this,GuideActivity.class));
			finish();
		}else{
			startActivity(new Intent(SplashActivity.this,MainActivity.class));
			finish();
		}
		
	}

}
