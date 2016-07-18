package com.jayqqaa12.mobilesafe.ui.space.lock;

import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.appipv6.android.slipbutton.OnChangedListener;
import com.jayqqaa12.abase.annotation.view.FindEngine;
import com.jayqqaa12.abase.annotation.view.FindView;
import com.jayqqaa12.abase.core.activity.AbaseActivity;
import com.jayqqaa12.abase.core.adapter.AbaseSimpleAdapter;
import com.jayqqaa12.abase.util.ui.AnimUitl;
import com.jayqqaa12.mobilesafe.R;
import com.jayqqaa12.mobilesafe.engine.space.AppLockEngine;
import com.jayqqaa12.mobilesafe.view.LockSwitchButton;

public class AppLockActivity extends AbaseActivity implements OnChangedListener
{
	@FindView(id = R.id.title_1_tv, textId = R.string.space_lock_label)
	private TextView tv_title;
	@FindView(id = R.id.tv)
	private TextView tv;
	@FindView(id = R.id.lv_unlock, itemClick = true, tag = "unlock")
	private ListView lv_unlock;
	@FindView(id = R.id.lv_lock, itemClick = true, tag = "lock")
	private ListView lv_lock;
	@FindView(id = R.id.sbt)
	private LockSwitchButton sbt;
	@FindView(id = R.id.pb)
	private View pb;

	@FindEngine
	private AppLockEngine engine;
	private AbaseSimpleAdapter unlockAdapter;
	private AbaseSimpleAdapter lockAdapter;

	private List<Map<String, Object>> unlockData;
	private List<Map<String, Object>> lockData;

	private Handler handler = new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{
			initAdapter();
			initViewData();
		}

	};

	private void initViewData()
	{
		pb.setVisibility(View.INVISIBLE);
		lv_unlock.setVisibility(View.VISIBLE);

		sbt.setOnChangeListener(this);
		changeTilte(getString(R.string.space_lock_label_unlock), unlockData);
	}

	private void changeTilte(String title, List data)
	{
		tv.setText(title + "(" + data.size() + ")");
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		//TODO 以后增加 左右 滑动  viewpage
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lock);
		getData();
	}

	private void initAdapter()
	{
		unlockAdapter = new AbaseSimpleAdapter(this, unlockData, R.layout.lv_item_3, new String[] { "icon", "title", "icon2" }, new int[] {
				R.id.lv_iv_1, R.id.lv_tv, R.id.lv_iv_2 });
		lockAdapter = new AbaseSimpleAdapter(this, lockData, R.layout.lv_item_3, new String[] { "icon", "title", "icon2" }, new int[] { R.id.lv_iv_1,
				R.id.lv_tv, R.id.lv_iv_2 });

		lv_unlock.setAdapter(unlockAdapter);
		lv_lock.setAdapter(lockAdapter);

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id)
	{
		String tag = parent.getTag() + "";
		
		AnimUitl.swing(view,new SwingListener(position,tag));

	}

	private class SwingListener implements AnimationListener
	{
		private int position;
		private String tag;

		public SwingListener(int position,String tag)
		{
			this.position = position;
			this.tag =tag;
		}

		@Override
		public void onAnimationStart(Animation animation)
		{
			lv_lock.setEnabled(false);
			lv_unlock.setEnabled(false);
			
		}

		@Override
		public void onAnimationEnd(Animation animation)
		{
			if (tag.equals("unlock"))
			{
				Map<String, Object> m = (Map<String, Object>) unlockData.remove(position);
				engine.notifyProviderInsert((String) m.get("packname"));
				m.put("icon2", R.drawable.lock_clolsed);
				lockData.add(m);
				changeTilte(getString(R.string.space_lock_label_unlock), unlockData);

			}
			else if (tag.equals("lock"))
			{
				Map<String, Object> m2 = (Map<String, Object>) lockData.remove(position);
				engine.notifyProviderDelete((String) m2.get("packname"));
				m2.put("icon2", R.drawable.lock_open);
				unlockData.add(m2);
				changeTilte(getString(R.string.space_lock_label_lock), lockData);

			}

			unlockAdapter.notifyDataSetChanged();
			lockAdapter.notifyDataSetChanged();
			
			lv_lock.setEnabled(true);
			lv_unlock.setEnabled(true);
			

		}

		@Override
		public void onAnimationRepeat(Animation animation)
		{
		}
	}

	private void getData()
	{
		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				unlockData = engine.getUnlockData();
				lockData = engine.getLockData();
				handler.sendEmptyMessage(0);
			}
		}).start();
	}

	@Override
	public void OnChanged(boolean change, View v)
	{

		if (change)
		{
			lv_lock.setVisibility(View.INVISIBLE);
			lv_unlock.setVisibility(View.VISIBLE);
			changeTilte(getString(R.string.space_lock_label_unlock), unlockData);

		}
		else
		{
			lv_lock.setVisibility(View.VISIBLE);
			lv_unlock.setVisibility(View.INVISIBLE);
			changeTilte(getString(R.string.space_lock_label_lock), lockData);
		}

	}

}
