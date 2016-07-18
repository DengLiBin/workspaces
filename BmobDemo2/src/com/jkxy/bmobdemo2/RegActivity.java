package com.jkxy.bmobdemo2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import cn.bmob.v3.listener.SaveListener;

import com.jkxy.bmobdemo2.entity.JkUser;

public class RegActivity extends Activity implements OnClickListener {
	private EditText et_name, et_password;
	private Button btn_login, btn_reg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reg);
		initView();
	}

	private void initView() {
		et_name = (EditText) this.findViewById(R.id.et_name);
		et_password = (EditText) this.findViewById(R.id.et_password);
		btn_login = (Button) this.findViewById(R.id.btn_login);
		btn_reg = (Button) this.findViewById(R.id.btn_reg);
		btn_login.setOnClickListener(this);
		btn_reg.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_login:
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
			break;
		case R.id.btn_reg:
			JkUser user = new JkUser();
			user.setUsername(et_name.getText().toString().trim());
			user.setPassword(et_password.getText().toString().trim());
			user.setInfo("学生用户");
			user.signUp(this, new SaveListener() {

				@Override
				public void onSuccess() {
					Toast.makeText(RegActivity.this, "注册成功", Toast.LENGTH_LONG)
							.show();
				}

				@Override
				public void onFailure(int arg0, String arg1) {
					Toast.makeText(RegActivity.this, "注册失败：" + arg1,
							Toast.LENGTH_LONG).show();
				}
			});

			break;
		default:
			break;
		}
	}
}
