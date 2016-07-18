package com.itheima.redbaby.adapter;

import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima.redbaby.ConstantValue;
import com.itheima.redbaby.R;
import com.itheima.redbaby.bean.Product;
import com.itheima.redbaby.ui.GoodsDetailsView;
import com.itheima.redbaby.ui.manager.MiddleManager;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * 专题的适配器
 * @author zhangyun
 *
 */
public class TopicAdpter extends BaseAdapter{
	private List<Product> productList;
	private Context context;
	private ImageLoader imageLoader;
	private Bundle bundle;
	
	public TopicAdpter(Context context,List<Product> productList,ImageLoader imageLoader,Bundle bundle) {
		super();
		this.context = context;
		this.productList = productList;
		this.imageLoader = imageLoader;
		this.bundle = bundle;
	}
	
	@Override
	public int getCount() {
		return productList.size();
	}

	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final Product product = productList.get(position);
		
		ViewHolder holder = null;
		if(convertView!=null){
			holder = (ViewHolder) convertView.getTag();
		}else{
			convertView = View.inflate(context, R.layout.dl_topic_listview_item, null);
			holder = new ViewHolder();
			holder.productImage = (ImageView) convertView.findViewById(R.id.dl_topic_iv_pimage);
			holder.productName = (TextView) convertView.findViewById(R.id.dl_topic_iv_pname);
			holder.productPrice = (TextView) convertView.findViewById(R.id.dl_topic_iv_pice);
			holder.productMarketPrice = (TextView) convertView.findViewById(R.id.dl_topic_iv_marketpice);
			convertView.setTag(holder);
		}
		//填充数据
		if(holder.productImage==null){
			imageLoader.displayImage(ConstantValue.URI + "/pic/zhuanti/zhuanti8.png", holder.productImage);
		}else{
			imageLoader.displayImage(ConstantValue.URI + product.getPic(), holder.productImage);
		}
		
		holder.productName.setText(product.getName());
		holder.productPrice.setText(String.valueOf(product.getPrice()));
		holder.productMarketPrice.setText(String.valueOf(product.getMarketprice()));
		return convertView;
	}
	
	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	static class ViewHolder{
		ImageView productImage;
		TextView  productName;
		TextView productPrice;
		TextView productMarketPrice;
	}

}
