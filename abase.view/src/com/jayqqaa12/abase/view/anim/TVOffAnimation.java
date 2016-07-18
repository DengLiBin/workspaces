package com.jayqqaa12.abase.view.anim;



import android.graphics.Matrix;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * 电视 关闭时 一闪的 效果 动画
 * 
 * @author  jayqqaa12
 *
 */
public class TVOffAnimation extends Animation {

	private int halfWidth;

	private int halfHeight;

	@Override
	public void initialize(int width, int height, int parentWidth,
			int parentHeight) {

		super.initialize(width, height, parentWidth, parentHeight);
		setDuration(500);
		setFillAfter(true);
		//����View�����ĵ�
		halfWidth = width / 2;
		halfHeight = height / 2;
		setInterpolator(new AccelerateDecelerateInterpolator());
		
	}

	@Override
	protected void applyTransformation(float interpolatedTime, Transformation t) {

		final Matrix matrix = t.getMatrix();
		if (interpolatedTime < 0.8) {
			matrix.preScale(1+0.625f*interpolatedTime, 1-interpolatedTime/0.8f+0.01f,halfWidth,halfHeight);
		}else{
			matrix.preScale(7.5f*(1-interpolatedTime),0.01f,halfWidth,halfHeight);
		}
	}
}