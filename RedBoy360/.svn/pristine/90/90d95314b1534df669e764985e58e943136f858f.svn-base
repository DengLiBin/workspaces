package com.shopping.redboy.view;

import org.apache.commons.lang3.StringUtils;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.shopping.redboy.ConstantValue;
import com.shopping.redboy.R;
import com.shopping.redboy.ViewManager.UIManager;
import com.shopping.redboy.ViewManager.TitleManager.ButtonClickListener;
import com.shopping.redboy.annotation.Category;
import com.shopping.redboy.annotation.ResID;
import com.shopping.redboy.domain.Person;
import com.shopping.redboy.domain.UserInfo;
import com.shopping.redboy.engine.Impl.RegistEngineImpl;
import com.shopping.redboy.util.MyAsynTask;
import com.shopping.redboy.util.PromptManager;

@ResID(id = R.layout.login_activity)
@Category(id = R.id.imgHome, leftbutton = "", rightbutton = "", title = "登录")
public class LoginView extends BaseView{

	@ResID(id = R.id.login_name_edit)
	private EditText ed_username;
	@ResID(id = R.id.login_pwd_edit)
	private EditText ed_password;
	@ResID(id = R.id.login_text)
	private TextView login_text;
	@ResID(id = R.id.regist_text)
	private TextView regist_text;
	
	@Override
	public void onPause() {
		ed_username.setText("");
		ed_password.setText("");
		super.onPause();
	}

	public LoginView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}



	private RegistEngineImpl registEngineImpl;

	@Override
	protected void setListener() {
		login_text.setOnClickListener(new OnClickListener() {


			@Override
			public void onClick(View v) {
				final String username = ed_username.getText().toString().trim();
				final String password = ed_password.getText().toString().trim();

				if (StringUtils.isEmpty(username)
						|| StringUtils.isEmpty(password)) {
					PromptManager.showToast(context, "有户名或者密码不能为空！");
					return ;
				}
				Person person = new Person();
				person.setName(username);
				person.setPwd(password);
				for(Person item : ConstantValue.PERSONS){
					if (username.equals(item.getName())&&password.equals(item.getPwd())) {
						// 登录成功
						
						PromptManager.showToast(context, "登录成功！");
						ConstantValue.PERSONS.remove(item);
						ConstantValue.PERSONS.add(0, item);
						UIManager.getInstance().changeView(AccountView.class);
						
						return;
					}
				}
				
				// 登录失败
				PromptManager.showToast(context, "登录失败！");
			}
		});
		regist_text.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				UIManager.getInstance().changeView(RegistView.class);
			}
		});

	}

	@Override
	protected void init() {
		registEngineImpl = new RegistEngineImpl();
	}

}
