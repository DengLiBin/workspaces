package com.shopping.redboy.view;

import org.apache.commons.lang3.StringUtils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.shopping.redboy.ConstantValue;
import com.shopping.redboy.R;
import com.shopping.redboy.ViewManager.TitleManager.ButtonClickListener;
import com.shopping.redboy.ViewManager.UIManager;
import com.shopping.redboy.annotation.Category;
import com.shopping.redboy.annotation.ResID;
import com.shopping.redboy.domain.Person;
import com.shopping.redboy.domain.UserInfo;
import com.shopping.redboy.engine.Impl.RegistEngineImpl;
import com.shopping.redboy.util.MyAsynTask;
import com.shopping.redboy.util.PromptManager;

/**
 * 注册
 */

@ResID(id = R.layout.regist_activity)
@Category(id = R.id.imgHome, leftbutton = "", rightbutton = "", title = "注册")
public class RegistView extends BaseView implements ButtonClickListener {
	@ResID(id = R.id.regist_name_edit)
	private EditText ed_username;
	@ResID(id = R.id.regist_pwd_edit)
	private EditText ed_password;
	@ResID(id = R.id.regist_qrpwd_edit)
	private EditText ed_qrpassword;
	@ResID(id = R.id.zzregist_text)
	private TextView regist_text;
	
	@Override
	public void onPause() {
		ed_username.setText("");
		ed_password.setText("");
		ed_qrpassword.setText("");
		super.onPause();
	}

	public RegistView(Context context) {
		super(context);
	}

	@Override
	public void onLeftButtonClicked() {
		UIManager.getInstance().goback();

	}

	@Override
	public void onRightButtonClicked() {
		// TODO Auto-generated method stub

	}

	private RegistEngineImpl registEngineImpl; 

	@Override
	protected void setListener() {
		regist_text.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 String username = ed_username.getText().toString().trim();
				 String password = ed_password.getText().toString().trim();
				String qrpassword = ed_qrpassword.getText().toString().trim();

				Person person=new Person();
				person.setName(username);
				person.setPwd(password);
				ConstantValue.PERSONS.add(person);
				//注册成功
				if (StringUtils.isEmpty(username)
						|| StringUtils.isEmpty(password)
						|| StringUtils.isEmpty(qrpassword)) {
					PromptManager.showToast(context, "有户名或者密码或者确认密码不能为空！");
				} else if (!password.equals(qrpassword)) {
					PromptManager.showToast(context, "两次密码输入不一致，请重新输入！");
				} else {
					for(Person item : ConstantValue.PERSONS){
						if(username.equals(item.getName())&&password.equals(item.getPwd())){
							PromptManager.showToast(context, "该用户已经注册过！");
						}else{
							PromptManager.showToast(context, "注册成功,欢迎主人进入登录界面！");
							UIManager.getInstance().changeView(LoginView.class);
						}
					}
				}
				
			}
		});
	}

	@Override
	protected void init() {
		registEngineImpl  = new RegistEngineImpl();
		
	}

}
