package com.jikexueyuan.card2d;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private ImageView imageA;
	private ImageView imageB;
	
	private ScaleAnimation sato0 = new ScaleAnimation(1, 0, 1, 1, //缩放：x从1到0，y不变
			Animation.RELATIVE_TO_PARENT, 0.5f, Animation.RELATIVE_TO_PARENT,
			0.5f);
	private ScaleAnimation sato1 = new ScaleAnimation(0, 1, 1, 1, //缩放：x从0到1，y不变
			Animation.RELATIVE_TO_PARENT, 0.5f, Animation.RELATIVE_TO_PARENT,
			0.5f);
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		findViewById(R.id.root).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (imageA.getVisibility() == View.VISIBLE) {
					imageA.startAnimation(sato0);
				}else{
					imageB.startAnimation(sato0);
				}
			}
		});
		
	}
	
	private void shwoImageA(){
		imageA.setVisibility(View.VISIBLE);
		imageB.setVisibility(View.INVISIBLE);
	}
	
	private void showImageB(){
		imageA.setVisibility(View.INVISIBLE);
		imageB.setVisibility(View.VISIBLE);
	}
	
	private void initView(){
		imageA = (ImageView) findViewById(R.id.ivA);
		imageB = (ImageView) findViewById(R.id.ivB);
		shwoImageA();
		sato0.setDuration(500);//设置动画持续时间
		sato1.setDuration(500);
		
		sato0.setAnimationListener(new AnimationListener() {
			
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
				if (imageA.getVisibility() == View.VISIBLE) {
					imageA.setAnimation(null);
					showImageB();
					imageB.startAnimation(sato1);
				}else{
					imageB.setAnimation(null);
					shwoImageA();
					imageA.startAnimation(sato1);
				}
			}
		});
	}
}
