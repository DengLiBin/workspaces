package com.jayqqaa12.abase.core;

import java.lang.reflect.Field;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.view.View;
import android.widget.AbsListView;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.jayqqaa12.abase.annotation.view.FindEngine;
import com.jayqqaa12.abase.annotation.view.FindRes;
import com.jayqqaa12.abase.annotation.view.FindRes.ResType;
import com.jayqqaa12.abase.annotation.view.FindView;
import com.jayqqaa12.abase.core.activity.Listener;

/**
 * 
 * 要使用 abase 请继承 AbaseApp
 * 如果不想 继承 AbaseApp
 * 又想 使用 Abase （工具类也一样）
 * 记得 在你 当前的 application 类 里面
 * 设置  Abase.setContext(context)
 * 并且 传递 一个 context 给 他，
 * 
 * 
 * @author jayqqaa12
 * 
 *         You can open activityity service  ioc set
 * 
 */
public class Abase
{

	private static boolean openActivtiy = true;
	private static boolean openEngine = true;
	private static boolean openRes = true;
	private static FindRes findRes = null;
	private static FindEngine findEngine = null;
	private static FindView findView = null;
	private static Context context=null;


	public static Context getContext()
	{
		if(context ==null) throw new NullPointerException("Sorry use Abase  must Abase.setContext()  and context must is appliction Context ");
		
		return context;
	}

	public static void setContext(Context context)
	{
		Abase.context = context.getApplicationContext();
	}

	
	public static boolean isOpenEngine()
	{
		return openEngine;
	}

	public static void setOpenEngine(boolean openEngine)
	{
		Abase.openEngine = openEngine;
	}

	public static FindRes getFindRes()
	{
		return findRes;
	}

	public static void setFindRes(FindRes findRes)
	{
		Abase.findRes = findRes;
	}


	public static FindEngine getFindEngine()
	{
		return findEngine;
	}

	public static void setFindEngine(FindEngine findEngine)
	{
		Abase.findEngine = findEngine;
	}

	public static FindView getFindView()
	{
		return findView;
	}

	public static void setFindView(FindView findView)
	{
		Abase.findView = findView;
	}

	public static boolean isOpenRes()
	{
		return openRes;
	}

	public static void setOpenRes(boolean openRes)
	{
		Abase.openRes = openRes;
	}

	public static boolean isOpenActivtiy()
	{
		return openActivtiy;
	}

	public static void setOpenActivtiy(boolean openActivtiy)
	{
		Abase.openActivtiy = openActivtiy;
	}

	public static boolean isOpenService()
	{
		return openEngine;
	}

	public static void setOpenService(boolean openService)
	{
		Abase.openEngine = openService;
	}


	public static void init(Object obj)
	{
		findEngine = null;
		findView = null;
		findRes = null;
		Resources resources = null;
		if (getContext() != null) resources = getContext().getResources();

		Field[] fields = obj.getClass().getDeclaredFields();
		if (fields == null || fields.length < 1) return;

		for (Field field : fields)
		{
			try
			{
				if (openEngine)
				{
					findEngine = field.getAnnotation(FindEngine.class);
					if (findEngine != null)
					{
						Class clazz = field.getType();
						field.setAccessible(true);

						field.set(obj, Engine.getInstance(clazz));

						continue;
					}

				}


				if (openRes)
				{
					findRes = field.getAnnotation(FindRes.class);

					if (findRes != null)
					{
						field.setAccessible(true);
						bindAllRes(obj, field, findRes, resources);

						continue;
					}
				}

			}
			catch (IllegalArgumentException e)
			{
				e.printStackTrace();
			}
			catch (IllegalAccessException e)
			{
				e.printStackTrace();
			}

		}
	}

	/**
	 * 绑定资源。
	 * 
	 */
	public static void bindAllRes(Object obj, Field field, FindRes res, Resources resources)
	{
		try
		{
			if (res != null)
			{
				ResType type = res.type();

				int id = res.id();
				if (type == ResType.BOOLEAN)
				{
					field.set(obj, resources.getBoolean(id));

				}
				else if (type == ResType.COLOR)
				{
					field.set(obj, resources.getColor(id));
				}
				else if (type == ResType.DRAWABLE)
				{
					field.set(obj, resources.getDrawable(id));
				}
				else if (type == ResType.INT)
				{
					field.set(obj, resources.getInteger(id));
				}
				else if (type == ResType.INT_ARRAY)
				{
					field.set(obj, resources.getIntArray(id));
				}
				else if (type == ResType.STRING)
				{
					field.set(obj, resources.getString(id));
				}
				else if (type == ResType.STRING_ARRAY)
				{
					field.set(obj, resources.getStringArray(id));
				}
				else if (type == ResType.TEXT)
				{
					field.set(obj, resources.getText(id));
				}
				else if (type == ResType.TEXT_ARRAY)
				{
					field.set(obj, ResType.TEXT_ARRAY);
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static FindView initView(Activity obj, Field field, Resources resources, Map<Integer, View> parentViews)
	{
		return initView(obj, field, resources, parentViews, null);
	}

	public static FindView initView(Activity obj, Field field, Resources resources, Map<Integer, View> parentViews, Map<Integer, View> pageViews)
	{
		FindView find = field.getAnnotation(FindView.class);

		if (find == null) return find;
		View view = null;
		try
		{
			field.setAccessible(true);
			int id = find.id();
			int parId = find.parId();
			int pageId = find.pageId();
			int pageNum = find.pageNum();

			if (parId > 0)
			{

				if (parentViews.get(parId) != null) view = parentViews.get(parId);
				else
				{
					view = obj.findViewById(parId);
					if (view != null) parentViews.put(parId, view);
				}
				if (view != null)
				{
					if (id != 0) field.set(obj, view.findViewById(id));
					else field.set(obj, view);
				}
				view = null;
			}
			else if (pageId > 0 && pageViews != null)
			{
				if (pageViews.get(pageNum) != null) view = pageViews.get(pageNum);
				else
				{
					view = obj.getLayoutInflater().inflate(pageId, null);
					if (view != null) pageViews.put(pageNum, view);
				}
				if (view != null)
				{
					if (id != 0) field.set(obj, view.findViewById(id));
					else field.set(obj, view);
				}
				view = null;
			}

			else
			{
				field.set(obj, obj.findViewById(id));
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();

			return find;
		}

		// setting text
		int textId = find.textId();
		if (textId > 0) setViewText(resources, field, textId, obj);

		int imageId = find.imageId();
		if (imageId > 0) setViewImage(resources, field, imageId, (Listener) obj);

		String tag = find.tag();
		if (!TextUtils.isEmpty(tag)) setViewTag(field, tag, (Listener) obj);

		boolean clickBind = find.click();
		if (clickBind) setClickListener(field, (Listener) obj);

		boolean longClickBind = find.longClick();
		if ((longClickBind)) setLongClickListener(field, (Listener) obj);

		boolean itemClickBind = find.itemClick();
		if ((itemClickBind)) setItemClickListener(field, (Listener) obj);

		boolean itemLongClickBind = find.itemLongClick();
		if ((itemLongClickBind)) setItemLongClickListener(field, (Listener) obj);

		boolean select = find.itemClick();
		if (select) setItemSelectListener(field, (Listener) obj);

		boolean checkedChangeBind = find.checkedChange();
		if ((checkedChangeBind)) setCheckedChangeListener(field, (Listener) obj);

		boolean conextMenuBind = find.contextMenu();
		if ((conextMenuBind)) setContextMenuBind(field, obj);

		return find;
	}

	public static void setViewTag(Field field, String tag, Listener activity)
	{
		try
		{
			Object obj = field.get(activity);
			if (obj instanceof View)
			{
				((View) obj).setTag(tag);
			}

		}
		catch (IllegalArgumentException e)
		{
			e.printStackTrace();
		}
		catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}

	}

	public static void setViewImage(Resources resources, Field field, int imageId, Listener activity)
	{

		try
		{
			Object obj = field.get(activity);
			if (obj instanceof ImageView)
			{
				((ImageView) obj).setImageResource(imageId);

			}

		}
		catch (IllegalArgumentException e)
		{
			e.printStackTrace();
		}
		catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}

	}

	public static void setViewText(Resources resources, Field field, int textId, Activity activity)
	{
		try
		{
			Object obj = field.get(activity);
			if (obj instanceof TextView)
			{
				((TextView) obj).setText(resources.getString(textId));

			}

		}
		catch (IllegalArgumentException e)
		{
			e.printStackTrace();
		}
		catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}

	}

	public static void setContextMenuBind(Field field, Activity activity)
	{

		try
		{
			Object obj = field.get(activity);
			if (obj instanceof View)
			{
				activity.registerForContextMenu(((View) obj));

			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	public static void setCheckedChangeListener(Field field, Listener activity)
	{

		try
		{
			Object obj = field.get(activity);
			if (obj instanceof CompoundButton)
			{
				((CompoundButton) obj).setOnCheckedChangeListener(activity);

			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	public static void setClickListener(Field field, Listener activity)
	{
		try
		{
			Object obj = field.get(activity);
			if (obj instanceof View)
			{

				((View) obj).setOnClickListener(activity);

			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void setLongClickListener(Field field, Listener activity)
	{
		try
		{
			Object obj = field.get(activity);
			if (obj instanceof View)
			{
				((View) obj).setOnLongClickListener(activity);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void setItemClickListener(Field field, Listener activity)
	{
		try
		{
			Object obj = field.get(activity);
			if (obj instanceof AbsListView)
			{
				((AbsListView) obj).setOnItemClickListener(activity);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void setItemLongClickListener(Field field, Listener activity)
	{
		try
		{
			Object obj = field.get(activity);
			if (obj instanceof AbsListView)
			{
				((AbsListView) obj).setOnItemLongClickListener(activity);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void setItemSelectListener(Field field, Listener activity)
	{
		try
		{
			Object obj = field.get(activity);
			if (obj instanceof View)
			{
				((AbsListView) obj).setOnItemSelectedListener(activity);

			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
