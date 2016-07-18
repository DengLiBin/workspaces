package com.jayqqaa12.abase.core.adapter;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

/**
 * 
 * 增加 drawabel 的直接    绑定
 *  (data instanceof Drawable)
* @author jayqqaa12 
* @date 2013-5-13
 */
public class AbaseSimpleAdapter extends SimpleAdapter
{
	private boolean cache = false;
	private List<? extends Map<String, ?>> data;
	private int[] mTo;
	private String[] mFrom;
	private LayoutInflater inflater;
	private int resource;
	private ViewBinder viewBinder;
	
	
	public AbaseSimpleAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to)
	{
		super(context, null, 0, null, null);
		this.data = data;
		this.resource = resource;
		this.mFrom = from;
		this.mTo = to;
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public void setData(List<? extends Map<String, ?>> data)
	{
		this.data = data;
	}

	public void setCache(boolean cache)
	{
		this.cache = cache;
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
		final Map dataSet = data.get(position);
		if (dataSet == null) { return; }

		final String[] from = mFrom;
		final int[] to = mTo;
		final int count = to.length;

		for (int i = 0; i < count; i++)
		{
			final View v = view.findViewById(to[i]);
			if (v != null)
			{
				final Object data = dataSet.get(from[i]);

				String text = data == null ? "" : data.toString();
				if (text == null)
				{
					text = "";
				}

				if (v instanceof Checkable)
				{
					if (data instanceof Boolean)
					{
						((Checkable) v).setChecked((Boolean) data);
					}
					else if (v instanceof TextView)
					{
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
