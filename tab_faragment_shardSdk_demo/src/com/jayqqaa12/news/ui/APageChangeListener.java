package com.jayqqaa12.news.ui;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Matrix;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;


/***
 * 解决 滑动条的 玩意
 * 
 * @author 12
 *
 */
public class APageChangeListener implements OnPageChangeListener
{
	private int currIndex = 0;
	private List<Integer> postiion = new ArrayList<Integer>();
	/**
	 * 滑动条 设置 自己的滑动条来 自动生成 滑动动画
	 */
	private View title;
	
	public APageChangeListener(Context context, int count,  View lineView)
	{
	
		DisplayMetrics dm = new DisplayMetrics();
		((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
		int size = (int) (dm.widthPixels / count);
		int i = -1;
	
		while (count > i++)
		{
			postiion.add(size * i);
		}
		this.title = lineView;
		
	}

	public APageChangeListener(Context context, int count, int bmw,ImageView lineView)
	{
		
	
		DisplayMetrics dm = new DisplayMetrics();
		((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
//		int size = (int) (dm.widthPixels / count);
		int i = 0;
		int offset = (dm.widthPixels / count - bmw) / count;
	
		postiion.add(offset);
		while (count > i++)
		{
			postiion.add(offset * i+bmw);
		}
		this.title = lineView;
		
		Matrix matrix = new Matrix();
		matrix.postTranslate(offset, 0);
		lineView.setImageMatrix(matrix);
	}

	@Override
	public void onPageScrollStateChanged(int arg0)
	{

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2)
	{

	}

	@Override
	public void onPageSelected(int pos)
	{
		Animation animation = new TranslateAnimation(postiion.get(currIndex), postiion.get(pos), 0, 0);

		if (animation == null) return;

		currIndex = pos;
		animation.setFillAfter(true);
		animation.setDuration(300);
		title.startAnimation(animation);

	}

}
