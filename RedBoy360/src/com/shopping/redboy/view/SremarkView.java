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
@ResID(id=R.layout.payment_center_remark)
@Category(id=R.id.imgHome,title="请留言",leftbutton="返回",rightbutton="保存")
public class SremarkView extends BaseView implements ButtonClickListener{
	@ResID(id=R.id.dialog_remark_edit)
	private EditText dialog_remark_edit;
	public SremarkView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setListener() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onLeftButtonClicked() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRightButtonClicked() {
		Map<String, Object> map=UIManager.getInstance().getMap();
		Object obj=map.get("submit");
		SubmitTally submit=null;
		if(obj!=null){
			submit=(SubmitTally) obj;
		}else{
			submit=new SubmitTally();
		}
		submit.setRemark(dialog_remark_edit.getText().toString());
		map.put("submit", submit);
		UIManager.getInstance().changeView(SubmitTallyView.class);
		
	}
}
