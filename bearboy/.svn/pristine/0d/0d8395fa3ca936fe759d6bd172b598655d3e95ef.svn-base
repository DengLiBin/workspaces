package com.itheima.redbaby.ui;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.View;

import com.itheima.redbaby.ConstantValue;
import com.itheima.redbaby.R;
import com.itheima.redbaby.bean.Product;
import com.itheima.redbaby.engine.impl.FavoriteInfoEngineImpl;
import com.itheima.redbaby.ui.manager.BaseView;
import com.itheima.redbaby.ui.manager.MiddleManager;
import com.itheima.redbaby.ui.manager.ShoppingCart;

/**
 * 收藏夹为空的时候
 * 
 * @author Crist
 * 
 */
public class FavoriteView extends BaseView {

	public FavoriteView(Context context) {
		super(context);
	}

	@Override
	protected void loadMiddleLayout() {
		middleView = View.inflate(context, R.layout.dl_favorite_empty, null);
	}

	@Override
	protected void findViewById() {

	}

	@Override
	public void onResume() {
		List<Product> pros = ShoppingCart.getInstance().getPros();
		if (pros.size() > 0) {
			MiddleManager.getInstance().changeView(
					FavoriteListViewShowGoodsDetailsView.class, bundle);
		} else {
			middleView = View
					.inflate(context, R.layout.dl_favorite_empty, null);
		}
		super.onResume();
	}

	@Override
	protected void setListener() {

	}

	@Override
	protected void processEngine() {
		/*
		 * new MyHttpTask<Map>() {
		 * 
		 * @Override protected Object doInBackground(Map... params) {
		 * FavoriteInfoEngineImpl engineImpl = new FavoriteInfoEngineImpl();
		 * Map<String, List<Product>> favoriteInfo = engineImpl
		 * .getFavoriteInfo(); // if(favoriteInfo==null){ ////
		 * PromptManager.showNoNetWork(context); // // } return favoriteInfo;
		 * 
		 * }
		 * 
		 * @Override protected void onPostExecute(Object result) {
		 * if(result!=null){ MiddleManager.getInstance().changeView(
		 * FavoriteListViewShowGoodsDetailsView.class, bundle); }
		 * super.onPostExecute(result); } }.executeProxy();
		 */

	}

	@Override
	protected int getID() {
		return ConstantValue.FAVORITE;
	}

}
