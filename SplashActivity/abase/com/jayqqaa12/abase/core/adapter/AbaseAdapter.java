package com.jayqqaa12.abase.core.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.jayqqaa12.abase.util.common.ReflectUtil;
import com.jayqqaa12.abase.util.common.TAG;

public class AbaseAdapter<T> extends SimpleAdapter
{
	private boolean cache = false;
	private List<T> data;
	private int[] mTo;
	private String[] mFrom;
	private LayoutInflater inflater;
	private int resource;
	private ViewBinder viewBinder;

	/**
	 * I' think don't to see
	 * 
	 * @param context
	 * @param data
	 * @param resource
	 *            listView item layout Id
	 * @param from
	 * @param to
	 */
	public AbaseAdapter(Context context, List<T> data, int resource, String[] from, int[] to)
	{
		super(context, null, 0, null, null);
		this.data = data;
		this.resource = resource;
		this.mFrom = from;
		this.mTo = to;
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public void setCache(boolean cache)
	{
		this.cache = cache;
	}

	
	

	public void setData(List<T> data)
	{
		this.data = data;
	}

	@Override
	public int getCount()
	{
		return data.size();
	}

	@Override
	public Object getItem(int position)
	{
		return data.get(position);

	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		if (cache && convertView != null) return convertView;

		View v;
		if (convertView == null)
		{
			v = inflater.inflate(resource, parent, false);
		}
		else
		{
			v = convertView;
		}

		bindView(position, v);

		return v;
	}

	private void bindView(int position, View view)
	{
		final Object obj = data.get(position);
		if (obj == null) { return; }

		final ViewBinder binder = viewBinder;
		final String[] from = mFrom;
		final int[] to = mTo;
		final int count = to.length;

		for (int i = 0; i < count; i++)
		{
			final View v = view.findViewById(to[i]);
			if (v != null)
			{
				final Object data = ReflectUtil.getValueAndParent(obj, from[i]);
				

				
				String text = data == null ? "" : data.toString();
				if (text == null)
				{
					text = "";
				}

				boolean bound = false;
				if (binder != null)
				{
					bound = binder.setViewValue(v, data, text);
				}

				if (!bound)
				{
					if (v instanceof Checkable)
					{
						if (data instanceof Boolean)
						{
							((Checkable) v).setChecked((Boolean) data);
						}
						else if (v instanceof TextView)
						{
							// Note: keep the instanceof TextView check at the
							// bottom of these
							// ifs since a lot of views are TextViews (e.g.
							// CheckBoxes).
							setViewText((TextView) v, text);
						}
						else
						{
							throw new IllegalStateException(v.getClass().getName() + " should be bound to a Boolean, not a "
									+ (data == null ? "<unknown type>" : data.getClass()));
						}
					}
					else if (v instanceof TextView)
					{
						setViewText((TextView) v, text);
					}
					else if (v instanceof ImageView)
					{
						if (data instanceof Drawable)
						{

							((ImageView) v).setImageDrawable((Drawable) data);

						}

						else if (data instanceof Integer)
						{
							setViewImage((ImageView) v, (Integer) data);
						}
						else
						{
							setViewImage((ImageView) v, text);
						}

				
					}
					else
					{
						throw new IllegalStateException(v.getClass().getName() + " is not a " + " view that can be bounds by this SimpleAdapter");
					}
				}
			}
		}
	}

}
