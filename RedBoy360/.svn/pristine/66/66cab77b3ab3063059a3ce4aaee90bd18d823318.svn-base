package com.shopping.redboy.view;

import java.util.Map;

import com.shopping.redboy.R;
import com.shopping.redboy.ViewManager.UIManager;
import com.shopping.redboy.ViewManager.TitleManager.ButtonClickListener;
import com.shopping.redboy.annotation.Category;
import com.shopping.redboy.annotation.ResID;
import com.shopping.redboy.domain.SubmitTally;

import android.app.Fragment;
import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;
@ResID(id=R.layout.invoice_activity)
@Category(id=R.id.imgHome,title="填写发票信息",leftbutton="返回",rightbutton="保存")
public class SinvoceView extends BaseView implements ButtonClickListener{
	@ResID(id=R.id.invoice_title_edit1)
	private EditText invoice_title_edit1;
	@ResID(id=R.id.invoice_title_edit2)
	private EditText invoice_title_edit2;
	
	public SinvoceView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void setListener() {
		
	}

	@Override
	public void onLeftButtonClicked() {
		Toast.makeText(context, "返回", 0).show();

	}

	@Override
	public void onRightButtonClicked() {
		Toast.makeText(context, "保存", 0).show();
		Map<String, Object> map = UIManager.getInstance().getMap();

		SubmitTally submit = null;
		Object obj = map.get("submit");

		if (obj== null) {
			submit = new SubmitTally();
		} else {
			submit = (SubmitTally) obj;
		}
		submit.setInvoicetitle(invoice_title_edit1.getText().toString());
		submit.setInvoiceedit(invoice_title_edit2.getText().toString());
		map.put("submit", submit);
		UIManager.getInstance().changeView(SubmitTallyView.class);
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub

	}

}
