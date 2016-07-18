package com.itheima.redbaby.ui;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.itheima.redbaby.ConstantValue;
import com.itheima.redbaby.R;
import com.itheima.redbaby.bean.Brand;
import com.itheima.redbaby.engine.BrandEngine;
import com.itheima.redbaby.ui.manager.BaseView;
import com.itheima.redbaby.ui.manager.MiddleManager;
import com.itheima.redbaby.utils.BeanFactory;
import com.itheima.redbaby.utils.PromptManager;

/**
 * 品牌View
 * 
 * @author my
 * 
 */
public class BrandView extends BaseView {

	private GridView gv_brand_list;
	private MyAdapter adapter;
	private List<Brand> brandList;

	public BrandView(Context context) {
		super(context);
	}

	@Override
	protected void loadMiddleLayout() {
		middleView = View.inflate(context, R.layout.dl_brand, null);
	}

	@Override
	protected void findViewById() {
		gv_brand_list = (GridView) middleView.findViewById(R.id.gv_brand_list);

	}

	@Override
	protected void setListener() {
		gv_brand_list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Brand brand = brandList.get(position);
				try {
					int brandId = Integer.parseInt(brand.getId());
					bundle.putInt("brandId", brandId);
					MiddleManager.getInstance().changeView(BrandItemView.class, bundle);
				} catch (Exception e) {
					PromptManager.showToast(context, "暂无此品牌信息");
					return;
				}
			}
		});
	}

	@Override
	protected void processEngine() {

	}

	@Override
	public void onResume() {
		new MyHttpTask<Void>() {
			@Override
			protected Object doInBackground(Void... params) {
				BrandEngine brandEngine = BeanFactory.getFactory().getInstance(
						BrandEngine.class);
				return brandEngine.getBrand();
			}

			@Override
			protected void onPostExecute(Object result) {
				if (result != null) {
					brandList = (List<Brand>) result;
					adapter = new MyAdapter();
					gv_brand_list.setAdapter(adapter);
				}
			}
		}.executeProxy();

		super.onResume();
	}

	@Override
	protected int getID() {
		return ConstantValue.VIEW_BRAND;
	}

	private class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return brandList.size();
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

			Brand brand = brandList.get(position);
			ViewHoder hoder = null;
			if (convertView == null) {
				hoder = new ViewHoder();
				convertView = View.inflate(context, R.layout.dl_brand_item,
						null);
				hoder.brandImage = (ImageView) convertView
						.findViewById(R.id.iv_brand_item);
				convertView.setTag(hoder);
			} else {

				hoder = (ViewHoder) convertView.getTag();
			}

			imageLoader.displayImage(ConstantValue.URI + brand.getPic(), hoder.brandImage, options);

			return convertView;
		}

	}

	static class ViewHoder {
		ImageView brandImage;
	}

}
