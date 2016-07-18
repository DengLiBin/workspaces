package com.shopping.redboy.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.shopping.redboy.ConstantValue;
import com.shopping.redboy.R;
import com.shopping.redboy.ViewManager.TitleManager.ButtonClickListener;
import com.shopping.redboy.ViewManager.UIManager;
import com.shopping.redboy.annotation.Category;
import com.shopping.redboy.annotation.ResID;
import com.shopping.redboy.domain.Person;
import com.shopping.redboy.domain.UserInfo;
import com.shopping.redboy.engine.AccountEngine;
import com.shopping.redboy.util.BeanFactory;
import com.shopping.redboy.util.MyAsynTask;

@ResID(id = R.layout.my_account_activity)
@Category(id = R.id.imgHome, leftbutton = "更多", rightbutton = "退出登录", title = "账户中心")
public class AccountView extends BaseView implements ButtonClickListener {

	@ResID(id = R.id.tv_username)
	private TextView tv_username;
	@ResID(id = R.id.tv_grade)
	private TextView tv_grade;
	@ResID(id = R.id.tv_integral)
	private TextView tv_integral;
	@ResID(id = R.id.my_name_lin1)
	private LinearLayout lin1;
	@ResID(id = R.id.my_name_lin2)
	private LinearLayout lin2;
	@ResID(id = R.id.my_name_lin3)
	private LinearLayout lin3;
	@ResID(id = R.id.my_name_lin4)
	private LinearLayout lin4;

	private SharedPreferences sp;
	
	private UserInfo user;

	public AccountView(Context context) {
		super(context);

	}

	@Override
	public void onLeftButtonClicked() {
		UIManager.getInstance().changeView(MoreView.class);

	}

	@Override
	public void onRightButtonClicked() {
		UIManager.getInstance().changeView(LoginView.class);

	}

	@Override
	public void onResume() {
		new MyAsynTask() {
			@Override
			public void onPreExecute() {
			}

			@Override
			public void onPostExecute() {
				for (Person item : ConstantValue.PERSONS) {
					tv_username.setText(item.getName());
				}
				
				tv_grade.setText(user.getLevel());
				tv_integral.setText(user.getBonus() + "");
			}

			@Override
			public void doInBackground() {
				AccountEngine engine = BeanFactory.getImpl(AccountEngine.class);
				user = engine.getUser();
			}
		}.execute();
	}

	@Override
	protected void init() {

		sp = context.getSharedPreferences("regist", Context.MODE_PRIVATE);
		tv_username.setText(sp.getString("username", null));
	}


	@Override
	protected void setListener() {
		lin1.setOnClickListener(this);
		lin2.setOnClickListener(this);
		lin3.setOnClickListener(this);
		lin4.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.my_name_lin1:
			UIManager.getInstance().changeView(MyOrderView.class);
			break;

		case R.id.my_name_lin2:
			UIManager.getInstance().changeView(Addresses_List.class);
			break;

		case R.id.my_name_lin3:
			UIManager.getInstance().changeView(Addresses_List.class);
			break;

		case R.id.my_name_lin4:
			UIManager.getInstance().changeView(MyFavoriteView.class);
			break;

		}
		super.onClick(v);
	}

}
