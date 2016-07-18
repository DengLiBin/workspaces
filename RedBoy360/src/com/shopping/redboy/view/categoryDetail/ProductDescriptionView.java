package com.shopping.redboy.view.categoryDetail;

import android.content.Context;

import com.shopping.redboy.R;
import com.shopping.redboy.ViewManager.UIManager;
import com.shopping.redboy.ViewManager.TitleManager.ButtonClickListener;
import com.shopping.redboy.annotation.Category;
import com.shopping.redboy.annotation.ResID;
import com.shopping.redboy.view.BaseView;

@ResID(id=R.layout.product_description)
@Category(id=R.id.imgClassify,title="商品描述",leftbutton="返回",rightbutton="")
public class ProductDescriptionView extends BaseView implements ButtonClickListener{

	public ProductDescriptionView(Context context) {
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
		UIManager.getInstance().getMap().put("isGoback", true);
		UIManager.getInstance().goback();
	}

	@Override
	public void onRightButtonClicked() {
		// TODO Auto-generated method stub
		
	}

}
