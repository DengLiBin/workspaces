package com.example.heimaweixin;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TabIndicatorView extends RelativeLayout{
	private ImageView mIvIcon;
	private TextView mTvHint,mTvUnread;
	private int mNormalIconId;
	private int mFocusIconId;
	
	public TabIndicatorView(Context context) {
		this(context,null);
	}

	public TabIndicatorView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		View.inflate(context, R.layout.tab_indicator, this);//��������Դ���ص���ǰ���view��
		mIvIcon=(ImageView) findViewById(R.id.tab_indicator_icon);
		mTvHint=(TextView) findViewById(R.id.tab_indicator_hint);
		mTvUnread=(TextView) findViewById(R.id.tab_indicator_unread);
		
		setTabUnreadCount(0);//��ʼ��Ĭ��û��δ����Ϣ
		
	}
	public void setTitle(String title){
		mTvHint.setText(title);
	}
	
	public void setTabIcon(int normalIconId,int focusIconId){
		this.mFocusIconId=focusIconId;
		this.mNormalIconId=normalIconId;
		mIvIcon.setImageResource(mNormalIconId);//Ĭ����ʾ��ͼ��
	}
	public void setTabUnreadCount(int unreadCount){//δ����Ϣ����
		if(unreadCount<=0){//û����Ϣ��������
			mTvUnread.setVisibility(View.GONE);
		}else if(unreadCount<=99){
			mTvUnread.setVisibility(View.VISIBLE);
			mTvUnread.setText(unreadCount+"");
		}else{
			mTvUnread.setVisibility(View.VISIBLE);
			mTvUnread.setText("99+");
		}
	}
	
	//�����Ƿ�ѡ�����ò�ͬ��ͼ��
	public void setTabSelected(boolean isSelected){
		if(isSelected){
			mIvIcon.setImageResource(mFocusIconId);
		}else{
			mIvIcon.setImageResource(mNormalIconId);
		}
	}
	
	
	
}
