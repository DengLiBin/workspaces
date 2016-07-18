package com.shopping.redboy.view;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;

import com.shopping.redboy.R;
import com.shopping.redboy.ViewManager.TitleManager.ButtonClickListener;
import com.shopping.redboy.ViewManager.UIManager;
import com.shopping.redboy.annotation.Category;
import com.shopping.redboy.annotation.ResID;

@ResID(id = R.layout.more_activity)
@Category(id = R.id.imgHome, leftbutton = "", rightbutton = "", title = "更多")
public class MoreView extends BaseView {

	@ResID(id = R.id.my_account_rl)
	private RelativeLayout account_rl;
	@ResID(id = R.id.my_order_rl)
	private RelativeLayout order_rl;
	@ResID(id = R.id.address_manage_rl)
	private RelativeLayout manage_rl;
	@ResID(id = R.id.my_favorite_rl)
	private RelativeLayout favorite_rl;
	@ResID(id = R.id.recent_browse_rl)
	private RelativeLayout browse_rl;
	@ResID(id = R.id.helpRelLay)
	private RelativeLayout helpRelLay;
	@ResID(id = R.id.userfeedback)
	private RelativeLayout userfeedback;
	@ResID(id = R.id.aboutRelLay)
	private RelativeLayout aboutRelLay;

	public MoreView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}


	@Override
	protected void init() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setListener() {
		account_rl.setOnClickListener(this);
		order_rl.setOnClickListener(this);
		manage_rl.setOnClickListener(this);
		favorite_rl.setOnClickListener(this);
		browse_rl.setOnClickListener(this);
		helpRelLay.setOnClickListener(this);
		userfeedback.setOnClickListener(this);
		aboutRelLay.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.my_account_rl:
			UIManager.getInstance().changeView(LoginView.class);
			break;

		case R.id.my_order_rl:
			UIManager.getInstance().changeView(MyOrderView.class);
			break;

		case R.id.address_manage_rl:
			UIManager.getInstance().changeView(AddressAddresses_Manager_List.class);
			break;

		case R.id.my_favorite_rl:
			UIManager.getInstance().changeView(MyFavoriteView.class);
			break;

		case R.id.recent_browse_rl:
			break;

		case R.id.helpRelLay:
			break;

		case R.id.userfeedback:
			break;
			
		case R.id.aboutRelLay:
			break;

		}
		super.onClick(v);
	}

}
