package com.shopping.redboy.view;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.shopping.redboy.R;
import com.shopping.redboy.ViewManager.TitleManager.ButtonClickListener;
import com.shopping.redboy.ViewManager.UIManager;
import com.shopping.redboy.annotation.Category;
import com.shopping.redboy.annotation.ResID;
import com.shopping.redboy.util.HttpClientUtil;
import com.shopping.redboy.util.MyAsynTask;

import android.content.Context;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
@ResID(id=R.layout.il_first)
@Category(id=R.id.imgHome,title="first",leftbutton="返回",rightbutton="帮助")
public class FirstView extends BaseView implements ButtonClickListener{
	@ResID(id=R.id.tv_show)
	private TextView tv_show;
	@ResID(id=R.id.pb_load)
	private ProgressBar pb_loading;
	
	public FirstView(Context context) {
		super(context);
		new MyAsynTask() {
			
			@Override
			public void onPreExecute() {
				pb_loading.setVisibility(View.VISIBLE);
				tv_show.setVisibility(View.INVISIBLE);
			}
			
			@Override
			public void onPostExecute() {
				pb_loading.setVisibility(View.INVISIBLE);
				tv_show.setVisibility(View.VISIBLE);
			}
			
			@Override
			public void doInBackground() {
				SystemClock.sleep(4000);
			}
		}.execute();
	}

	@Override
	protected void setListener() {}

	@Override
	public void onLeftButtonClicked() {
		Toast.makeText(context, "goback", 0).show();
	}

	@Override
	public void onRightButtonClicked() {
		Toast.makeText(context, "second", 0).show();
		UIManager.getInstance().changeView(SecondView.class);
		
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub
		
	}
	
	

}
