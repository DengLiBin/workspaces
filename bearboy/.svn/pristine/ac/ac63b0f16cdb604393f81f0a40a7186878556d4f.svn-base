package com.itheima.redbaby.ui;

import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.itheima.redbaby.ConstantValue;
import com.itheima.redbaby.R;
import com.itheima.redbaby.adapter.Level1ClassifyAdapter;
import com.itheima.redbaby.bean.ClassifyFist;
import com.itheima.redbaby.engine.impl.FistLevelClassifyEngineImpl;
import com.itheima.redbaby.ui.SearchView.Level2ClassifyAdapter.ViewHolder;
import com.itheima.redbaby.ui.manager.BaseView;
import com.itheima.redbaby.ui.manager.MiddleManager;
import com.itheima.redbaby.utils.PromptManager;

public class SecondClassifyView extends BaseView {
	private List<ClassifyFist> infoClassify;

	private GridView dl_gv_level1_classify;

	public SecondClassifyView(Context context) {
		super(context);
	}

	@Override
	protected void loadMiddleLayout() {
		middleView = View.inflate(context, R.layout.dl_search, null);
		dl_gv_level1_classify = (GridView) middleView
				.findViewById(R.id.dl_gv_level1_classify);
		System.out.println("dl_gv_level1_classify");

	}

	@Override
	protected void findViewById() {

	}

	@Override
	protected void setListener() {
		dl_gv_level1_classify.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String levelId = infoClassify.get(position).getId();
				System.out.println("2级菜单id为" + levelId + "被点击");
				Bundle bundle2 = new Bundle();
				bundle2.putString("one", bundle.getString("one"));
				bundle2.putString("two", infoClassify.get(position)
						.getBigname());
				bundle2.putString("cId", levelId);
				MiddleManager.getInstance().changeView(GoodsEntryView.class,
						bundle2);
			}
		});
	}

	@Override
	protected void processEngine() {
	}

	@Override
	public void onResume() {
		String cId = bundle.getString("cId");
		System.out.println(cId + "");
		new MyHttpTask<String>() {
			@Override
			protected Object doInBackground(String... params) {
				FistLevelClassifyEngineImpl classifyEngineImpl = new FistLevelClassifyEngineImpl();
				infoClassify = classifyEngineImpl.getSecondClassify(params[0]);
				return infoClassify;
			}

			@Override
			protected void onPostExecute(Object result) {
				if (result != null) {
					infoClassify = (List<ClassifyFist>) result;
					Level2ClassifyAdapter adapter = new Level2ClassifyAdapter(
							context, infoClassify);
					dl_gv_level1_classify.setAdapter(adapter);
				} else {
					PromptManager.showNoNetWork(context);
				}
				super.onPostExecute(result);
			}
		}.executeProxy(cId);
	}

	@Override
	protected int getID() {
		return ConstantValue.VIEW_SEARCH;
	}

	public class Level2ClassifyAdapter extends BaseAdapter {
		private Context context;

		// 一级分类名称
		private List<ClassifyFist> infoClassify;

		public Level2ClassifyAdapter(Context context,
				List<ClassifyFist> infoClassify) {
			super();
			this.context = context;
			this.infoClassify = infoClassify;
		}

		@Override
		public int getCount() {
			return infoClassify.size();
		}

		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			if (convertView == null) {
				convertView = View.inflate(context,
						R.layout.dl_item_gv_level1_classify, null);
				holder = new ViewHolder();
				holder.dl_gv_level1_classify_iv_mericon = (ImageView) convertView
						.findViewById(R.id.dl_gv_level1_classify_iv_mericon);
				holder.dl_gv_level1_classify_tv_mername = (TextView) convertView
						.findViewById(R.id.dl_gv_level1_classify_tv_mername);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			// Uri iconUrl =
			// Uri.parse(ConstantValue.URI+infoClassify.get(position).getPic() +
			// "");
			imageLoader.displayImage(
					ConstantValue.URI + infoClassify.get(position).getPic(),
					holder.dl_gv_level1_classify_iv_mericon);
			// holder.dl_gv_level1_classify_iv_mericon.setImageURI(iconUrl);
			holder.dl_gv_level1_classify_tv_mername.setText(infoClassify.get(
					position).getBigname());
			return convertView;
		}

		class ViewHolder {
			ImageView dl_gv_level1_classify_iv_mericon;
			TextView dl_gv_level1_classify_tv_mername;
		}

	}
}
