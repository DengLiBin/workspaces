package com.itheima.redbaby.ui;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

import com.itheima.redbaby.ConstantValue;
import com.itheima.redbaby.R;
import com.itheima.redbaby.adapter.TopicAdpter;
import com.itheima.redbaby.bean.Product;
import com.itheima.redbaby.engine.PromotionEngine;
import com.itheima.redbaby.engine.TopicEngine;
import com.itheima.redbaby.ui.manager.BaseView;
import com.itheima.redbaby.ui.manager.MiddleManager;
import com.itheima.redbaby.utils.BeanFactory;
import com.itheima.redbaby.utils.PromptManager;
import com.itheima.redbaby.view.RefreshListView;
import com.itheima.redbaby.view.RefreshListView.OnRefreshListener;

/**
 * 品牌的view
 * 
 * @author zhangyun
 * 
 */
public class PromotionItemView extends BaseView {

	public PromotionItemView(Context context) {
		super(context);
	}

	private RefreshListView listView;

	private int pageNum = 1;
	private int offset = 10;

	private List<Product> productList;

	private TopicAdpter topicAdpter;

	@Override
	protected void loadMiddleLayout() {
		middleView = View.inflate(context, R.layout.dl_brand_item_list, null);
	}

	@Override
	protected void findViewById() {
		listView = (RefreshListView) middleView.findViewById(R.id.dl_topic_rlv);
	}

	@Override
	public void onResume() {
		promotionID = bundle.getInt("promotionID");

		isRefresh = false;
		// 获取服务器上的数据
		accessNetGetData();
		super.onResume();
	}

	/**
	 * 访问网络获取数据
	 */
	public void accessNetGetData() {

		new MyHttpTask<Integer>() {

			@Override
			protected Object doInBackground(Integer... params) {
				PromotionEngine topicEngine = BeanFactory.getFactory()
						.getInstance(PromotionEngine.class);
				return topicEngine.getPromotionProduct(params[0], params[1],
						params[2]);
			}

			@Override
			protected void onPostExecute(Object result) {
				if (result != null) {
					productList = (List<Product>) result;
					topicAdpter = new TopicAdpter(context, productList,
							imageLoader, bundle);
					listView.setAdapter(topicAdpter);

					listView.onRefreshFinish();
				}
				super.onPostExecute(result);
			}

		}.executeProxy(pageNum, offset, promotionID);

	}

	// 标记哪种方式获取网络数据
	boolean isRefresh = false;

	private int promotionID;

	@Override
	protected void setListener() {
		listView.setOnRefreshListener(new OnRefreshListener() {

			@Override
			public void onRefresh() {
				isRefresh = true;
				accessNetGetData();
			}

			@Override
			public void onLoadMoring() {
				pageNum++;
				new MyHttpTask<Integer>() {

					@Override
					protected Object doInBackground(Integer... params) {
						PromotionEngine topicEngine = BeanFactory.getFactory()
								.getInstance(PromotionEngine.class);
						return topicEngine.getPromotionProduct(params[0],
								params[1], params[2]);
					}

					@Override
					protected void onPostExecute(Object result) {
						if (result != null) {
							productList.addAll((List<Product>) result);
							if (topicAdpter == null) {
								topicAdpter = new TopicAdpter(context,
										productList, imageLoader, bundle);
								listView.setAdapter(topicAdpter);
							} else {
								listView.onRefreshFinish();
							}
						}
						super.onPostExecute(result);
					}

				}.executeProxy(pageNum, offset, promotionID);

			}
		});

		listView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				Product product = productList.get(position - 1);
				bundle.putString("id", product.getId());
				MiddleManager.getInstance().changeView(GoodsDetailsView.class,
						bundle);
				return false;
			}
		});

	}

	@Override
	protected void processEngine() {

	}

	@Override
	protected int getID() {
		return ConstantValue.PROMOTIONITEM;
	}

}
