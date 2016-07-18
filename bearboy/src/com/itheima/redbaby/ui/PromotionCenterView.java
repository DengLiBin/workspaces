package com.itheima.redbaby.ui;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.itheima.redbaby.ConstantValue;
import com.itheima.redbaby.R;
import com.itheima.redbaby.bean.Promotion;
import com.itheima.redbaby.engine.PromotionEngine;
import com.itheima.redbaby.ui.manager.BaseView;
import com.itheima.redbaby.ui.manager.MiddleManager;
import com.itheima.redbaby.utils.BeanFactory;
import com.itheima.redbaby.utils.PromptManager;

public class PromotionCenterView extends BaseView {

	private ListView listView;
	/**
	 * 促销中心的集合
	 */
	private List<Promotion> promotionList;
	
	private MyAdapter myAdapter;

	public PromotionCenterView(Context context) {
		super(context);
	}

	@Override
	protected void loadMiddleLayout() {
		middleView = View.inflate(context, R.layout.dl_promotioncenter, null);
	}

	@Override
	protected void findViewById() {
		listView = (ListView) middleView.findViewById(R.id.dl_promotioncenter_listview);
	
	}

	@Override
	public void onResume() {
		new MyHttpTask<Void>() {
			@Override
			protected Object doInBackground(Void... params) {
				PromotionEngine promotionEngine = BeanFactory.getFactory().getInstance(PromotionEngine.class);
				return promotionEngine.getPromotionList();
			}

			@Override
			protected void onPostExecute(Object result) {
				if(result!=null){
					promotionList = (List<Promotion>) result;
					myAdapter = new MyAdapter();
					listView.setAdapter(myAdapter);
				}
				super.onPostExecute(result);
			}
			
		}.executeProxy();
		super.onResume();
	}
	
	
	@Override
	protected void setListener() {
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Promotion promotion = promotionList.get(position);
				bundle.putString("promotionID",promotion.getId());
				MiddleManager.getInstance().changeView(BrandItemView.class, bundle);
			}
		});
	}

	@Override
	protected void processEngine() {

	}

	@Override
	protected int getID() {
		return ConstantValue.PROMOTIONCENTER;
	}
	
	private class MyAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return promotionList.size();
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			final Promotion promotion = promotionList.get(position);
			ViewHolder holder = null;
			if (convertView!=null) {
				holder = (ViewHolder) convertView.getTag();
			}else{
				holder = new ViewHolder();
				convertView = View.inflate(context, R.layout.dl_promotioncenter_item, null);
				holder.promotionPic = (ImageView) convertView.findViewById(R.id.iv_promotion_item);
				holder.promotionName = (TextView) convertView.findViewById(R.id.tv_promotion_item);
				convertView.setTag(holder);
			}
			holder.promotionName.setText(promotion.getName());
			imageLoader.displayImage(ConstantValue.URI + promotion.getPic(), holder.promotionPic);
			
			holder.promotionPic.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					try {
						int promotionID = Integer.parseInt(promotion.getId());
						bundle.putInt("promotionID", promotionID);
						MiddleManager.getInstance().changeView(PromotionItemView.class, bundle);
					} catch (Exception e) {
						PromptManager.showToast(context, "暂无此促销信息");
						return;
					}
				}
			});
			return convertView;
		}
		
		
	}
	
	static class ViewHolder{
		ImageView promotionPic;
		TextView promotionName;
	}

}
