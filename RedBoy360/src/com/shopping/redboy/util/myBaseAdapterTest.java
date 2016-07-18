package com.shopping.redboy.util;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
/**
 * BaseAdapter的增强类，用于处理listview的适配，
 * @author Administrator
 * @param <T> list集合中需要被适配的对象的的类型
 * @param <V> ViewHolder类的类型
 */
public abstract class myBaseAdapterTest<T,V> extends BaseAdapter{
	private List<T> list;
	private Context context;
	private Class<V> clazz;
	
	
	public myBaseAdapterTest(List<T> list, Context context, Class<V> clazz) {
		this.list = list;
		this.context = context;
		this.clazz = clazz;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null){
			convertView = AnnotationUtil.initField3(clazz, convertView, context);
		}
		V holder = (V) convertView.getTag();
		T t = list.get(position);
		initholder(holder,t);
		return convertView;
	}
	/**
	 * 
	 * @param holder 初始化完毕的holder对象
	 * @param listitem list集合中对应位置的Object对象
	 */
	public abstract void initholder(V holder, T listitem); 

}
