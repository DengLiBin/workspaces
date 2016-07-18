package com.jikexueyuan.myrect;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

public class MyRect extends View {

	public MyRect(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MyView);
		
		int color = ta.getColor(R.styleable.MyView_rect_color, 0xffff0000);
		setBackgroundColor(color);
		
		ta.recycle();
	}

	public MyRect(Context context) {
		super(context);
	}

}
