package com.itheima.redbaby.ui;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.itheima.redbaby.ConstantValue;
import com.itheima.redbaby.R;
import com.itheima.redbaby.bean.order.AddressInfo;
import com.itheima.redbaby.engine.AddressEngine;
import com.itheima.redbaby.ui.manager.BaseView;
import com.itheima.redbaby.ui.manager.MiddleManager;
import com.itheima.redbaby.utils.BeanFactory;
import com.itheima.redbaby.utils.PromptManager;
/**
 * 地址列表 
 * @author Administrator
 *
 */
public class MyAddressList extends BaseView implements OnClickListener {

	private ListView my_address_list;
	private LinearLayout ll_loading;
	private TextView tv_status;

	private List<AddressInfo> addressList;
	private AddressInfo addressInfo;
	private AddressAdapter adapter;
	
	public MyAddressList(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void loadMiddleLayout() {
		middleView = View.inflate(context, R.layout.dl_address_list, null);

	}

	@Override
	protected void findViewById() {

		// ll_loading = (LinearLayout) middleView.findViewById(R.id.ll_loading);
		my_address_list = (ListView) middleView
				.findViewById(R.id.my_address_list);
	}


	@Override
	protected void setListener() {
		my_address_list.setOnItemClickListener(new OnItemClickListener() {
			
			

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					final int position, long id) {
				//点击条目,弹出dialog

				 AlertDialog.Builder builder = new Builder(context);
				 StringBuffer dialog = new StringBuffer();
				 if(bundle.get("from")!=null){
					 dialog.append("修改,");
					 dialog.append("删除,");
					 dialog.append("选择,");
					
				 }else{
					 dialog.append("修改,");
					 dialog.append("删除,");
				 }
				String[] s = StringUtils.split(dialog.toString(), ",");
				
				 bundle = new Bundle();
					AddressInfo info = addressList.get(position);
					bundle.putSerializable("address", info);
				builder.setTitle("请选择");
				builder.setSingleChoiceItems(s, -1, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						switch (which) {
						case 2:
							//回跳到订单页面
							//选择
							MiddleManager.getInstance().changeView(ShoppingView.class, bundle);
							break;
						case 0:
							//修改地址
							MiddleManager.getInstance().changeView(MyNewAddress.class, bundle);
							break;
						case 1:
							//删除地址
							final String id=addressList.get(position).getId();
							
//							AddressEngine addressEngine = BeanFactory.getFactory()
//							.getInstance(AddressEngine.class);
//							String deleteAddress = addressEngine.deleteAddress(id);
							new MyHttpTask<String>() {

								@Override
								protected Object doInBackground(
										String... params) {
									AddressEngine addressEngine = BeanFactory.getFactory()
											.getInstance(AddressEngine.class);
									String deleteAddress = addressEngine.deleteAddress(id);
									return deleteAddress;
								}
								protected void onPostExecute(Object result) {
//									if(result!=null){
//										adapter.notifyDataSetChanged();
//									}
									if(adapter!=null){
										addressList.remove(addressList.get(position));
										adapter.notifyDataSetChanged();
									}
								};
							}.executeProxy(id);
							break;

						default:
							break;
						}
//						adapter.notifyDataSetChanged();
						dialog.dismiss();
					}
				});
				builder.setNegativeButton("取消", null);
				builder.show();
			}
			
		});
		
	}

	@Override
	protected void processEngine() {
		// TODO Auto-generated method stub

	}

	@Override
	protected int getID() {
		// TODO Auto-generated method stub
		return ConstantValue.ADDRESSLIST;
	}

	@Override
	public void onResume() {
		super.onResume();
		//进度条
		PromptManager.showProgressDialog(getContext());
		new MyHttpTask<String>() {


			@Override
			protected Object doInBackground(String... params) {

				AddressEngine addressEngine = BeanFactory.getFactory()
						.getInstance(AddressEngine.class);
				return addressEngine.getAddress();

			}

			protected void onPostExecute(Object result) {
				addressList = (List<AddressInfo>) result;
				if (addressList != null) {
					//关闭进度条
					PromptManager.closeProgressDialog();
					if (adapter == null) {
						adapter = new AddressAdapter();
						my_address_list.setAdapter(adapter);
					} else {
						adapter.notifyDataSetChanged();
					}
				}

			};
		}.executeProxy();
		
	}


	private class AddressAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
//			return addressList.size();
			return addressList.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view;
			ViewHolder holder;

			if (convertView != null) {
				// 复用缓存
				view = convertView;
				holder = (ViewHolder) view.getTag();
			} else {
				view = View.inflate(getContext(),
						R.layout.dl_address_list_view, null);
				holder = new ViewHolder();
				holder.tv_name = (TextView) view
						.findViewById(R.id.address_listitem_receiver_text);
				holder.tv_phone = (TextView) view
						.findViewById(R.id.address_listitem_phone_text);
				holder.tv_address = (TextView) view
						.findViewById(R.id.address_listitem_ads_text);
				
				view.setTag(holder);
			}
//			holder.tv_name.setText("111");
			addressInfo = addressList.get(position);
			holder.tv_name.setText(addressInfo.getName());
			holder.tv_phone.setText(addressInfo.getPhonenumber());
			holder.tv_address.setText(addressInfo.getAreaId()
					+ addressInfo.getAreaDetail());

			return view;
		}

	}

	static class ViewHolder {
		TextView tv_name;
		TextView tv_phone;
		TextView tv_address;
		TextView tv_update;
		TextView tv_delete;

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}
