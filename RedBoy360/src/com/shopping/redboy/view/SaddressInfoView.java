package com.shopping.redboy.view;

import java.util.Map;

import com.shopping.redboy.R;
import com.shopping.redboy.ViewManager.TitleManager.ButtonClickListener;
import com.shopping.redboy.ViewManager.UIManager;
import com.shopping.redboy.annotation.Category;
import com.shopping.redboy.annotation.ResID;
import com.shopping.redboy.domain.SubmitTally;

import android.content.Context;
import android.widget.EditText;
@ResID(id=R.layout.payment_address_activity)
@Category(id=R.id.imgHome,title="填写地址",leftbutton="返回",rightbutton="确定")
public class SaddressInfoView extends BaseView implements ButtonClickListener{
	@ResID(id = R.id.sub_address_name)
	// 姓名
	private EditText sub_address_name;
	@ResID(id = R.id.sub_address_phone)
	// 电话
	private EditText sub_address_phone;
	@ResID(id = R.id.sub_address_address)
	// 地址
	private EditText sub_address_address;
	@ResID(id = R.id.sub_address_email)
	// 邮箱
	private EditText sub_address_email;

	public SaddressInfoView(Context context) {
		super(context);
	}

	@Override
	protected void init() {

	}

	@Override
	protected void setListener() {

	}

	public void getMap() {

	}

	@Override
	public void onLeftButtonClicked() {
		UIManager.getInstance().changeView(UIManager.class);

	}

	@Override
	public void onRightButtonClicked() {

		Map<String, Object> map = UIManager.getInstance().getMap();

		SubmitTally submit = null;
		Object obj = map.get("submit");

		if (obj == null) {
			submit = new SubmitTally();
		} else {
			submit = (SubmitTally) obj;
		}


		submit.setPhone(sub_address_phone.getText().toString());
		submit.setAddress(sub_address_address.getText().toString());
		submit.setEmail(sub_address_email.getText().toString());
		submit.setName(sub_address_name.getText().toString());
		map.put("submit", submit);
		UIManager.getInstance().changeView(SubmitTallyView.class);
	}
}
