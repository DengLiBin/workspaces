package com.shopping.redboy.view;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.shopping.redboy.R;
import com.shopping.redboy.ViewManager.TitleManager.ButtonClickListener;
import com.shopping.redboy.ViewManager.UIManager;
import com.shopping.redboy.annotation.Category;
import com.shopping.redboy.annotation.ResID;
import com.shopping.redboy.util.AnnotationUtil;
@ResID(id = R.layout.il_second)
@Category(id=R.id.imgClassify,title="second",leftbutton="",rightbutton="进入购物车")
public class SecondView extends BaseView implements ButtonClickListener{
	@ResID(id=R.id.tv_show)
	private TextView tv_show;
	@ResID(id=R.id.bt_changevalue)
	private Button bt_change;
	
	public SecondView(Context context) {
		super(context);
	}

	@Override
	protected void setListener() {
		bt_change.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				tv_show.setText("text changed!");
			}
		});
	}

	@Override
	protected void init() {
		tv_show.setText("你好");
	}

	@Override
	public void onLeftButtonClicked() {
		
	}

	@Override
	public void onRightButtonClicked() {
		//UIManager.getInstance().changeView(SubmitTallyView.class);
	}

}
