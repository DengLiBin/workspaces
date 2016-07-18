package com.jayqqaa12.mobilesafe.ui.comm.phone;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.jayqqaa12.abase.annotation.view.FindView;
import com.jayqqaa12.abase.core.AbaseDao;
import com.jayqqaa12.abase.core.activity.AbaseExpandableActivity;
import com.jayqqaa12.abase.util.SysIntentUtil;
import com.jayqqaa12.mobilesafe.R;
import com.jayqqaa12.mobilesafe.config.Config;
import com.jayqqaa12.mobilesafe.domain.Category;
import com.jayqqaa12.mobilesafe.domain.CommContact;
import com.jayqqaa12.mobilesafe.domain.Contact;

public class CommPhoneActivity extends AbaseExpandableActivity
{
	
	private AbaseDao dao = AbaseDao.open(Config.DB_DIR,"commonnum");

	@FindView(id = R.id.elv, childClick = true)
	private ExpandableListView elv;
	private MyAdapter adapter;
	private List<Category> catagory;
	private Map<Integer, List<CommContact>> phoneMap = new TreeMap<Integer, List<CommContact>>();

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_phone_comm);
		initAdapter();

	}

	private void initAdapter()
	{
		catagory = dao.findAll(Category.class);

		for (Category c : catagory)
		{
			phoneMap.put(c.getId() - 1, dao.findAllByWhere(CommContact.class, "id="+c.getId() + "" ));
		}
		adapter = new MyAdapter();
		elv.setAdapter(adapter);

	}

	@Override
	public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id)
	{
		Contact c = (Contact) adapter.getChild(groupPosition, childPosition);

		SysIntentUtil.goPhone(this,c.getNumber());

		return false;
	}

	private class MyAdapter extends BaseExpandableListAdapter
	{
		TextView number = null;
		TextView name = null;

		@Override
		public int getGroupCount()
		{
			return catagory.size();
		}

		@Override
		public int getChildrenCount(int groupPosition)
		{
			return phoneMap.get(groupPosition).size();
		}

		@Override
		public Object getGroup(int groupPosition)
		{
			return groupPosition;
		}

		@Override
		public Object getChild(int groupPosition, int childPosition)
		{
			return phoneMap.get(groupPosition).get(childPosition);
		}

		@Override
		public long getGroupId(int groupPosition)
		{
			return groupPosition;
		}

		@Override
		public long getChildId(int groupPosition, int childPosition)
		{
			return groupPosition * 100 + childPosition;
		}

		@Override
		public boolean hasStableIds()
		{
			return false;
		}

		@Override
		public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent)
		{
			TextView tv = new TextView(CommPhoneActivity.this);
			tv.setText("             " + catagory.get(groupPosition).getName());
			tv.setTextSize(20.0f);

			tv.setPadding(10, 10, 10, 10);

			return tv;

		}

		@Override
		public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent)
		{
			View view = View.inflate(CommPhoneActivity.this, R.layout.lv_item_contact, null);
			name = (TextView) view.findViewById(R.id.lv_tv_1);
			number = (TextView) view.findViewById(R.id.lv_tv_2);

			name.setText(phoneMap.get(groupPosition).get(childPosition).getName());
			number.setText(phoneMap.get(groupPosition).get(childPosition).getNumber());

			return view;
		}

		@Override
		public boolean isChildSelectable(int groupPosition, int childPosition)
		{
			return true;
		}

	}
}
