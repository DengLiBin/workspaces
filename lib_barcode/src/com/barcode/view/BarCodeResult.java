package com.barcode.view;

import com.fire.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.ClipboardManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
/**
 * 线下活动签到
 * @author 火蚁（http://my.oschina.net/LittleDY）
 * @version 1.0
 * @created 2014-03-26
 */
public class BarCodeResult extends Activity {
	
	private Button mCopy;
	private ImageButton mClose;
	private TextView mRes;
	private String barCodeResult;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result);
		this.initView();
		initData();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		this.finish();
	}
	
	private void initView() {
		mCopy = (Button) findViewById(R.id.b_copy);
		mClose = (ImageButton) findViewById(R.id.b_close_button);
		mRes = (TextView) findViewById(R.id.b_result);
		mClose.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				BarCodeResult.this.finish();
			}
		});
		mCopy.setOnClickListener(copyListener);
	}
	
	private void initData() {
		Intent data = this.getIntent();
		barCodeResult = data.getStringExtra("res");
		mRes.setText(barCodeResult);
	}
	
	private OnClickListener copyListener = new OnClickListener() {
		public void onClick(View v) {
			//获取剪贴板管理服务
			ClipboardManager cm =(ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
			//将文本数据复制到剪贴板
			cm.setText(barCodeResult);
			Toast.makeText(BarCodeResult.this, "复制成功", Toast.LENGTH_LONG).show();
		}
	};
}
