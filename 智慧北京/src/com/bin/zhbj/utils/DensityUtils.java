package com.bin.zhbj.utils;

import android.content.Context;

public class DensityUtils {
	
	/**
	 * dpת��px
	 * @param ctx
	 */
	public static int dp2px(Context ctx,float dp){
		//�豸�ܶ�
		float density=ctx.getResources().getDisplayMetrics().density;
		
		int px=(int) (dp*density+0.5f);//��֤���������룩����Ȼ�������ʹ��4.9Ҳ��ֱ��ת��4������������Ҫ��0.5
		return px;
	}
	
	/**
	 * pxתdp
	 * @return
	 */
	public static float px2dp(Context ctx,int px){
		float density=ctx.getResources().getDisplayMetrics().density;
		return px/density;
	}
}
