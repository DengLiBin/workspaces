package com.bin.admin;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	private DevicePolicyManager mDPM;
	private ComponentName mDeviceAdminSample;
	private Button btnLock;
	private Button btnUnstall;
	private Button btnActive;
	private PowerManager pm;
	private PowerManager.WakeLock wakeLock;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mDPM = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);// ��ȡ�豸���Է���
		mDeviceAdminSample = new ComponentName(this, AdminReceiver.class);// �豸�������
		
		pm = (PowerManager) getSystemService(Context.POWER_SERVICE); //��Դ������
		
		
		
		btnActive = (Button) findViewById(R.id.activeManager);
		btnActive.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				activeAdmin();
			}
		});
		btnLock = (Button) findViewById(R.id.lock);
		btnLock.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				lockScreen();
				finish();
			}
		});
		btnUnstall = (Button) findViewById(R.id.unstall);
		btnUnstall.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				unInstall();
			}
		});
		
	}

	// �����豸������, Ҳ����������->��ȫ->�豸���������ֶ�����
	public void activeAdmin() {
		Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
		intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN,
				mDeviceAdminSample);
		intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,
				"���ڼ����豸������...");
		startActivity(intent);
	}

	// һ������
	public void lockScreen() {
		if (mDPM.isAdminActive(mDeviceAdminSample)) {// �ж��豸�������Ƿ��Ѿ�����
			
			mDPM.lockNow();// ��������
			//mDPM.resetPassword("123456", 0);
		} else {
			Toast.makeText(this, "�����ȼ����豸������!", Toast.LENGTH_SHORT).show();
		}
	}

	public void clearData() {
		if (mDPM.isAdminActive(mDeviceAdminSample)) {// �ж��豸�������Ƿ��Ѿ�����
			mDPM.wipeData(0);// �������,�ָ���������
		} else {
			Toast.makeText(this, "�����ȼ����豸������!", Toast.LENGTH_SHORT).show();
		}
	}

	public void unInstall() {
		mDPM.removeActiveAdmin(mDeviceAdminSample);// ȡ������

		// ж�س���
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.addCategory(Intent.CATEGORY_DEFAULT);
		intent.setData(Uri.parse("package:" + getPackageName()));
		startActivity(intent);
	}
}
