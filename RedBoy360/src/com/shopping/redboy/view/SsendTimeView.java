package com.shopping.redboy.view;

import java.util.Map;

import com.shopping.redboy.ConstantValue;
import com.shopping.redboy.R;
import com.shopping.redboy.ViewManager.UIManager;
import com.shopping.redboy.annotation.Category;
import com.shopping.redboy.annotation.ResID;
import com.shopping.redboy.domain.SubmitTally;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
@ResID(id=R.layout.paytype_sendtime_activity)
@Category(id=R.id.imgHome,title="选择送货时间",leftbutton="返回",rightbutton="保存")
public class SsendTimeView extends BaseView {
	@ResID(id = R.id.pay_money_rel)
	private RelativeLayout pay_money_rel;// 均可
	@ResID(id = R.id.pay_money_img)
	private ImageView pay_money_img;

	@ResID(id = R.id.pay_pos_rel)
	private RelativeLayout pay_pos_rel;// 休息日
	@ResID(id = R.id.pay_pos_img)
	private ImageView pay_pos_img;

	@ResID(id = R.id.pay_alipay_rel)
	private RelativeLayout pay_alipay_rel;// 工作日
	@ResID(id = R.id.pay_alipay_img)
	private ImageView pay_alipay_img;

	public SsendTimeView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setListener() {
		pay_money_rel.setOnClickListener(this);
		pay_pos_rel.setOnClickListener(this);
		pay_alipay_rel.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
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
			submit.setSendtime(ConstantValue.PAYMONEY);
			pay_money_img.setVisibility(View.VISIBLE);
			break;
		case R.id.pay_pos_rel:
			submit.setSendtime(ConstantValue.POS);
			pay_pos_img.setVisibility(View.VISIBLE);
			break;
		case R.id.pay_alipay_rel:
			submit.setSendtime(ConstantValue.ALIPAY);
			pay_alipay_img.setVisibility(View.VISIBLE);
			break;

		default:
			break;
		}
		map.put("submit", submit);
		UIManager.getInstance().changeView(SubmitTallyView.class);

	}
}
