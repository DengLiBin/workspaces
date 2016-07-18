package com.jike.bmobdemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.listener.SaveListener;

/**
 * Bmob�����
 * 
 * @author Administrator
 * 
 */

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Bmob.initialize(this, "12ed804e54e40b64213bedb8e8827af0");
		Student stu = new Student();
		stu.setAge("19");
		stu.setName("����1");
		stu.save(this, new SaveListener() {

			@Override
			public void onSuccess() {
				Toast.makeText(MainActivity.this, "ע��ɹ�", Toast.LENGTH_SHORT)
						.show();
			}

			@Override
			public void onFailure(int arg0, String arg1) {
				Toast.makeText(MainActivity.this, "ע��ʧ�ܣ�" + arg1,
						Toast.LENGTH_SHORT).show();
			}
		});
		// JkUser user = new JkUser();
		// user.setUsername("user");
		// user.setPassword("123456");
		// user.setJikeNick("����ѧԺѧ��");
		// user.setNumber(90);
		// user.signUp(this, new SaveListener() {
		//
		// @Override
		// public void onSuccess() {
		// Toast.makeText(MainActivity.this, "ע��ɹ�", Toast.LENGTH_SHORT)
		// .show();
		// }
		//
		// @Override
		// public void onFailure(int arg0, String arg1) {
		// Toast.makeText(MainActivity.this, "ע��ʧ�ܣ�" + arg1,
		// Toast.LENGTH_SHORT).show();
		// }
		// });
	}

}
