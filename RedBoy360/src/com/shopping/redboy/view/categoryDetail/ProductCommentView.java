package com.shopping.redboy.view.categoryDetail;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shopping.redboy.R;
import com.shopping.redboy.ViewManager.TitleManager.ButtonClickListener;
import com.shopping.redboy.ViewManager.UIManager;
import com.shopping.redboy.annotation.Category;
import com.shopping.redboy.annotation.ResID;
import com.shopping.redboy.domain.ProductComment;
import com.shopping.redboy.engine.CategoryDetailEngine;
import com.shopping.redboy.util.BeanFactory;
import com.shopping.redboy.util.MyAsynTask;
import com.shopping.redboy.view.BaseView;

@ResID(id=R.layout.prod_comments_activity)
@Category(id=R.id.imgClassify,title="商品评论",leftbutton="返回",rightbutton="")
public class ProductCommentView extends BaseView implements ButtonClickListener{

	@ResID(id=R.id.textName)
	private TextView tvProductName;	//显示商品名称
	
	@ResID(id=R.id.listComments)
	private ListView lvProductComment;		//显示商品评论
	
	@ResID(id=R.id.categoryEmptyListTv)
	private TextView tvLoading;		//加载进度
	
	private List<ProductComment> list ;	//装评论的javaBean的 list集合
	
	private MyAdapter adapter ;		//商品评论ListView的适配器
	
	/*===============方法区================*/
	public ProductCommentView(Context context) {
		super(context);
	}

	@Override
	protected void init() {
		list = new ArrayList<ProductComment>();
		adapter = new MyAdapter();
		lvProductComment.setAdapter(adapter);
	}

	@Override
	protected void setListener() {

	}
	
	@Override
	public void onResume() {
		tvLoading.setVisibility(View.VISIBLE);
		getComment();
		super.onResume();
	}
	
	/**
	 * 调用子线程请求服务器获得评论数据的方法
	 */
	public void getComment(){
		new MyAsynTask() {
			@Override
			public void onPreExecute() {
			}
			@Override
			public void onPostExecute() {
				Map<String, Object> map = UIManager.getInstance().getMap();
				String productName = (String)map.get("productName");
				tvProductName.setText(productName);
				adapter.notifyDataSetChanged();
				tvLoading.setVisibility(View.INVISIBLE);
				map.clear();
			}
			@Override
			public void doInBackground() {
				CategoryDetailEngine engine = BeanFactory.getImpl(CategoryDetailEngine.class);
				list = engine.getProductComment();
			}
		}.execute();
	}
	
	private class MyAdapter extends BaseAdapter{
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LinearLayout view =null;
			Holder holder = null;
			if(convertView == null){
				view = (LinearLayout) View.inflate(context, R.layout.prod_comments_items, null);
				holder = new Holder();
				holder.tvCommentTitle = (TextView) view.findViewById(R.id.textComTitle);
				holder.tvCommentContent = (TextView) view.findViewById(R.id.textClothesDetail);
				holder.tvCommentTime = (TextView) view.findViewById(R.id.textComDate);
				view.setTag(holder);
			}else{
				view = (LinearLayout) convertView;
				holder = (Holder) view.getTag();
			}
			holder.tvCommentTitle.setText(list.get(position).getTitle());
			holder.tvCommentContent.setText(list.get(position).getContent());
			holder.tvCommentTime.setText(list.get(position).getTime());
			return view;
		}
		
		@Override
		public int getCount() {
			return list.size();
		}
		@Override
		public Object getItem(int position) {
			return null;
		}
		@Override
		public long getItemId(int position) {
			return 0;
		}
		
		class Holder{
			TextView tvCommentTitle;
			TextView tvCommentContent;
			TextView tvCommentTime;
			
		}
	}

	@Override
	public void onLeftButtonClicked() {
		UIManager.getInstance().getMap().put("isGoback", true);
		UIManager.getInstance().goback();
	}

	@Override
	public void onRightButtonClicked() {
		
	}

}
