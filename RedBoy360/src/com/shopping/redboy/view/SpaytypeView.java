package com.shopping.redboy.view;

import java.util.Map;

import com.shopping.redboy.R;
import com.shopping.redboy.ViewManager.UIManager;
import com.shopping.redboy.annotation.Category;
import com.shopping.redboy.annotation.ResID;
import com.shopping.redboy.domain.SubmitTally;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
@ResID(id=R.layout.paytype_activity)
@Category(id=R.id.imgHome,title="支付方式",leftbutton="返回",rightbutton="")
public class SpaytypeView extends BaseView {

	@ResID(id = R.id.pay_money_rel)
	private RelativeLayout pay_money_rel; // 现金支付
	@ResID(id = R.id.pay_money_img)
	private ImageView pay_money_img;

	@ResID(id = R.id.pay_pos_rel)
	private RelativeLayout pay_pos_rel; // 货到付款
	@ResID(id = R.id.pay_pos_img)
	private ImageView pay_pos_img;

	@ResID(id = R.id.pay_alipay_rel)
	private RelativeLayout pay_alipay_rel; // 支付宝
	@ResID(id = R.id.pay_alipay_img)
	private ImageView pay_alipay_img;

	public SpaytypeView(Context context) {
		super(context);
	}

	@Override
	protected void setListener() {
		pay_money_rel.setOnClickListener(this);// 现金支付
		pay_pos_rel.setOnClickListener(this);// 货到付款
		pay_alipay_rel.setOnClickListener(this);// 支付宝
	}

	@Override
	protected void init() {
	}


	@Override
	public void onClick(View v) {

		Map<String, Object> map = UIManager.getInstance().getMap();

		SubmitTally submit = null;
		Object obj = map.get("submit");

		if (map.get("submit") == null) {
			submit = new SubmitTally();
		} else {
			submit = (SubmitTally) obj;
		}

		switch (v.getId()) {
		case R.id.pay_money_rel:
			submit.setPaytype("1");
			pay_money_img.setVisibility(View.VISIBLE);
			break;
		case R.id.pay_pos_rel:
			submit.setPaytype("2");
			pay_pos_img.setVisibility(View.VISIBLE);
			break;
		case R.id.pay_alipay_rel:
			submit.setPaytype("3");
			pay_alipay_img.setVisibility(View.VISIBLE);
			break;

		default:
			break;
		}

		map.put("submit", submit);
		UIManager.getInstance().changeView(SubmitTallyView.class);
		super.onClick(v);
	}
}
