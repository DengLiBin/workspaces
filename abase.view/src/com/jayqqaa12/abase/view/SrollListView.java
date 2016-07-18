package com.jayqqaa12.abase.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/***
 * 解决 srollview +listview 只显示一行的问题
 * @author 12
 *
 */
public class SrollListView extends ListView {

	public SrollListView(Context context) {
		super(context);
	}

	public SrollListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public SrollListView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,  
	            MeasureSpec.AT_MOST);  
	    super.onMeasure(widthMeasureSpec, expandSpec);  
	}

}