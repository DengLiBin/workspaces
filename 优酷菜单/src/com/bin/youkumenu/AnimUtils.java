package com.bin.youkumenu;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.RotateAnimation;
import android.widget.RelativeLayout;

public class AnimUtils {
	public static int animCount = 0;//��¼��ǰִ�еĶ�������
	public static void closeMenu(RelativeLayout rl,int startOffset){
		//�رղ˵��󣬽����е���view��Ϊ������
		for(int i=0;i<rl.getChildCount();i++){
			rl.getChildAt(i).setEnabled(false);
		}
		//��ת����
		RotateAnimation animation=new RotateAnimation(0,-180,RotateAnimation.RELATIVE_TO_SELF,
				0.5f,RotateAnimation.RELATIVE_TO_SELF,1);
		animation.setDuration(500);//����ʱ��
		animation.setFillAfter(true);//���������󱣳ֵ�ʱ��״̬
		animation.setStartOffset(startOffset);//�ӳ�ִ�У�����
		animation.setAnimationListener(new MyAnimationListener());
		rl.startAnimation(animation);
		
	}
	public static void showMenu(RelativeLayout rl,int startOffset){
		//�򿪲˵������е��Ӳ˵���Ϊ����
		for(int i=0;i<rl.getChildCount();i++){
			rl.getChildAt(i).setEnabled(true);
		}
		//��ת����
		RotateAnimation animation=new RotateAnimation(-180,0,RotateAnimation.RELATIVE_TO_SELF,
				0.5f,RotateAnimation.RELATIVE_TO_SELF,1);
		animation.setDuration(500);//����ʱ��
		animation.setFillAfter(true);//���������󱣳ֵ�ʱ��״̬
		animation.setStartOffset(startOffset);//�ӳ�ִ�У�����
		animation.setAnimationListener(new MyAnimationListener());
		rl.startAnimation(animation);
		
	}
	static class MyAnimationListener implements AnimationListener{
		@Override
		public void onAnimationStart(Animation animation) {
			animCount++;
		}
		@Override
		public void onAnimationEnd(Animation animation) {
			animCount--;
		}
		@Override
		public void onAnimationRepeat(Animation animation) {
		}
		
	}
}
