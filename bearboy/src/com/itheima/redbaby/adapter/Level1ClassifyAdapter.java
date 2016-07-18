package com.itheima.redbaby.adapter;

import java.util.List;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima.redbaby.ConstantValue;
import com.itheima.redbaby.R;
import com.itheima.redbaby.bean.ClassifyFist;

public class Level1ClassifyAdapter extends BaseAdapter {
	private Context context;

	// 一级分类名称
	private List<ClassifyFist> infoClassify;

	public Level1ClassifyAdapter(Context context, List<ClassifyFist> infoClassify) {
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
			convertView = View.inflate(context, R.layout.dl_item_gv_level1_classify, null);
			holder = new ViewHolder();
			holder.dl_gv_level1_classify_iv_mericon = (ImageView) convertView.findViewById(R.id.dl_gv_level1_classify_iv_mericon);
			holder.dl_gv_level1_classify_tv_mername = (TextView) convertView.findViewById(R.id.dl_gv_level1_classify_tv_mername);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		Uri iconUrl = Uri.parse(ConstantValue.URI+infoClassify.get(position).getPic() + "");
		holder.dl_gv_level1_classify_iv_mericon.setImageURI(iconUrl);
		holder.dl_gv_level1_classify_tv_mername.setText(infoClassify.get(position).getBigname());
		return convertView;
	}

	static class ViewHolder {
		ImageView dl_gv_level1_classify_iv_mericon;
		TextView dl_gv_level1_classify_tv_mername;
	}

}
