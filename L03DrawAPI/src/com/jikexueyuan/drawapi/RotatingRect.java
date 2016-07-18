package com.jikexueyuan.drawapi;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class RotatingRect extends View {

	public RotatingRect(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		
		initProperties();
	}

	public RotatingRect(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		initProperties();
	}

	public RotatingRect(Context context) {
		super(context);
		
		initProperties();
	}
	
	
	private void initProperties(){
		p = new Paint();
		p.setColor(Color.RED);
	}
	
	
	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		
		canvas.save();
//		canvas.rotate(degrees);
		canvas.translate(200, 200);//移动画布的位置
		canvas.rotate(degrees, 50, 50);//degress：旋转角度，旋转中心为canvas（画布）的50，50坐标点
		canvas.drawRect(0, 0, 100, 100, p);
		
		degrees ++;
		canvas.restore();
		
		invalidate();//使无效，会重新调用draw(canvas)方法（一般不这样做，用handler来延时绘制，节约资源）
	}
	
	private Paint p;
	private float degrees = 0;

}

