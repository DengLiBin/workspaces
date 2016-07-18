package com.jayqqaa12.abase.util.ui;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.view.KeyEvent;
import android.view.View;

public class DialogUtil
{

	public static void showSingleChoiceDiaog(Context context, String title, String[] items, int checkedItem,
			DialogInterface.OnClickListener oklistener, DialogInterface.OnClickListener canellistener)
	{
		AlertDialog.Builder builder = new Builder(context);
		builder.setTitle(title);
		builder.setSingleChoiceItems(items, checkedItem, oklistener);
		builder.setPositiveButton("取消", canellistener);
		builder.create().show();
	}

	public static ProgressDialog showProgressDialog(Context context, String title, boolean celable, int style)
	{
		final ProgressDialog pd = createProgressDialog(context, title, celable, style);
		pd.show();
		return pd;
	}

	/**
	 * 
	 * @param context
	 * @param title
	 * @param celable
	 * @param style
	 * @return
	 */
	public static ProgressDialog createProgressDialog(Context context, String title, boolean celable, int style)
	{
		final ProgressDialog pd = new ProgressDialog(context);
		pd.setProgressStyle(style);
		pd.setCancelable(celable);
		pd.setMessage(title);
		return pd;
	}

	/**
	 * 
	 * @param context
	 * @param mydialog
	 * @param b
	 * @param view
	 * @return
	 */
	public static Dialog createDialog(Context context, int style, boolean cancelable, View view)
	{
		Dialog dialog = new Dialog(context, style);
		dialog.setCancelable(cancelable);
		dialog.setContentView(view);
		if (!cancelable) dialog.setOnKeyListener(new OnKeyListener()
		{
			@Override
			public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event)
			{
				if (keyCode == KeyEvent.KEYCODE_SEARCH ) return true;
				else return false;
			}
		});
		return dialog;
	}

	/**
	 * 
	 * @param context
	 * @param mydialog
	 * @param b
	 * @param view
	 * @return
	 */
	public static Dialog createDialog(Context context, int style, boolean cancelable, int contentId)
	{
		Dialog dialog = new Dialog(context, style);
		dialog.setCancelable(cancelable);
		dialog.setContentView(contentId);

		if (!cancelable) dialog.setOnKeyListener(new OnKeyListener()
		{
			@Override
			public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event)
			{
				if (keyCode == KeyEvent.KEYCODE_SEARCH) return true;
				else return false;
			}
		});
		
		
		return dialog;
	}

	/**
	 * 
	 * @param context
	 * @param mydialog
	 * @param b
	 * @param view
	 * @return
	 */
	public static Dialog showDialog(Context context, int style, boolean cancelable, View view)
	{
		Dialog dialog = createDialog(context, style, cancelable, view);
		dialog.show();
		return dialog;
	}

	/**
	 * 
	 * @param context
	 * @param mydialog
	 * @param b
	 * @param view
	 * @return
	 */
	public static Dialog showDialog(Context context, int style, boolean cancelable, int contentId)
	{
		Dialog dialog = createDialog(context, style, cancelable, contentId);
		dialog.show();
		return dialog;
	}

}
