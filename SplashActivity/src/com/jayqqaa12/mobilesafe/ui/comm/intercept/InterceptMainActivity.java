package com.jayqqaa12.mobilesafe.ui.comm.intercept;

import java.util.List;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.appipv6.android.slipbutton.OnChangedListener;
import com.jayqqaa12.abase.animation.Rotate3dAnimation;
import com.jayqqaa12.abase.annotation.view.FindEngine;
import com.jayqqaa12.abase.annotation.view.FindRes;
import com.jayqqaa12.abase.annotation.view.FindRes.ResType;
import com.jayqqaa12.abase.annotation.view.FindView;
import com.jayqqaa12.abase.core.AbaseDao;
import com.jayqqaa12.abase.core.activity.AbaseTabActivity;
import com.jayqqaa12.abase.core.adapter.AbaseAdapter;
import com.jayqqaa12.abase.util.IntentUtil;
import com.jayqqaa12.abase.util.SysIntentUtil;
import com.jayqqaa12.abase.util.ui.DialogUtil;
import com.jayqqaa12.mobilesafe.R;
import com.jayqqaa12.mobilesafe.adapter.ListViewSettingAdapter;
import com.jayqqaa12.mobilesafe.config.InterceptModel;
import com.jayqqaa12.mobilesafe.config.InterceptSaveTime;
import com.jayqqaa12.mobilesafe.domain.BlackContact;
import com.jayqqaa12.mobilesafe.domain.Contact;
import com.jayqqaa12.mobilesafe.domain.InterceptContact;
import com.jayqqaa12.mobilesafe.domain.InterceptSms;
import com.jayqqaa12.mobilesafe.domain.WhiteContact;
import com.jayqqaa12.mobilesafe.engine.comm.intercept.InterceptEngine;
import com.jayqqaa12.mobilesafe.view.RosterSwitchButton;

/**
 * 不太好 维护 哦
 * 
 * @author jayqqaa12
 * @date 2013-4-25
 */
public class InterceptMainActivity extends AbaseTabActivity implements OnChangedListener
{
	public static final int CODE_BLACK = 0;
	public static final int CODE_WHITE = 1;
	public static final int CODE_SETTING = 2;
	public static final String TAG = "InterceptMainActivity";

	// phone view
	@FindView(id = R.id.phone_tv_model)
	private TextView phone_tv_model;
	@FindView(id = R.id.phone_tv_empty, click = true)
	private ImageView phone_iv_handle;
	@FindView(id = R.id.phone_lv, itemClick = true)
	private ListView phone_lv;
	@FindView(id = R.id.phone_tv_empty)
	private TextView phone_tv_empty;

	// sms view
	@FindView(id = R.id.sms_tv_model)
	private TextView sms_tv_model;
	@FindView(id = R.id.sms_tv_empty, click = true)
	private ImageView sms_iv_handle;
	@FindView(id = R.id.sms_lv, itemClick = true)
	private ListView sms_lv;
	@FindView(id = R.id.sms_tv_empty)
	private TextView sms_tv_empty;

	// roster black view
	@FindView(id = R.id.roster_ll_black)
	private LinearLayout roster_ll_black;
	@FindView(id = R.id.roster_ll_white)
	private LinearLayout roster_ll_white;

	@FindView(id = R.id.roster_ll_black_add, click = true)
	private LinearLayout roster_ll_black_add;
	@FindView(id = R.id.roster_black_lv, itemClick = true)
	private ListView roster_black_lv;
	@FindView(id = R.id.roster_black_empty)
	private LinearLayout roster_ll_black_empty;

	@FindView(id = R.id.roster_bt_select)
	private RosterSwitchButton roster_bt_select;

	// roster white view

	@FindView(id = R.id.roster_ll_white_add, click = true)
	private LinearLayout roster_ll_white_add;
	@FindView(id = R.id.roster_white_lv, itemClick = true)
	private ListView roster_white_lv;
	@FindView(id = R.id.roster_white_empty)
	private LinearLayout roster_ll_white_empty;

	// setting view

	@FindView(id = R.id.setting_cb_server_enable, checkedChange = true)
	private CheckBox setting_cb_server;
	@FindView(id = R.id.setting_cb_nofiti_enable, checkedChange = true)
	private CheckBox setting_cb_nofiti;
	@FindView(id = R.id.setting_tv_nofiti_enable)
	private TextView setting_tv_nofiti;
	@FindView(id = R.id.setting_tv_server_enable)
	private TextView setting_tv_server;
	@FindView(id = R.id.setting_lv, itemClick = true)
	private ListView setting_lv;
	// dao
	private AbaseDao dao = AbaseDao.create();

	@FindEngine
	private InterceptEngine interceptEngine;
	private AbaseAdapter<InterceptContact> phoneAdapter;

	private AbaseAdapter<InterceptSms> smsAdapter;
	private AbaseAdapter<BlackContact> blackAdapter;
	private AbaseAdapter<WhiteContact> whiteAdapter;
	private ListViewSettingAdapter settingAdapter;

	private Dialog rosterInputDialog;
	private Dialog contactEditDialog;
	private boolean black = false;

	@FindRes(id = R.array.intercept_setting_lables, type = ResType.STRING_ARRAY)
	private static String[] titles;
	@FindRes(id = R.array.intercept_setting_derscribes, type = ResType.STRING_ARRAY)
	private static String[] describers;

	// 3d animation
	private Rotate3dAnimation rotation;

	@FindView(id = R.id.fl)
	private ViewGroup view;
	private List<InterceptContact> phoneData;
	private List<InterceptSms> smsData;
	private List<BlackContact> blackData;
	private List<WhiteContact> whiteDate;

	private BlackContact blackContact;
	private WhiteContact whiteContact;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		String[] labels = new String[] { getString(R.string.phone), getString(R.string.sms), getString(R.string.black_white_list),
				getString(R.string.setting) };

		initMyTab(R.layout.activity_intercept, R.layout.tab_widget_1, labels, R.id.tab_widget_1_tv, labels, new int[] { R.id.ll_phone, R.id.ll_sms,
				R.id.ll_roster, R.id.ll_setting });

		setViewAdapter();

		roster_bt_select.setOnChangeListener(this);

		view.setPersistentDrawingCache(ViewGroup.PERSISTENT_ANIMATION_CACHE);

	}

	private void setViewAdapter()
	{
		phoneData = dao.findAll(InterceptContact.class);
		smsData = dao.findAll(InterceptSms.class);
		blackData = dao.findAll(BlackContact.class);
		whiteDate = dao.findAll(WhiteContact.class);

		phoneAdapter = new AbaseAdapter<InterceptContact>(this, phoneData, R.layout.lv_item_contact, new String[] { "number", "date" }, new int[] {
				R.id.lv_tv_1, R.id.lv_tv_2 });
		smsAdapter = new AbaseAdapter<InterceptSms>(this, smsData, R.layout.lv_item_contact, new String[] { "number", "content" }, new int[] {
				R.id.lv_tv_1, R.id.lv_tv_2 });

		blackAdapter = new AbaseAdapter<BlackContact>(this, blackData, R.layout.lv_item_contact, new String[] { "name", "number" }, new int[] {
				R.id.lv_tv_1, R.id.lv_tv_2 });
		whiteAdapter = new AbaseAdapter<WhiteContact>(this, whiteDate, R.layout.lv_item_contact, new String[] { "name", "number" }, new int[] {
				R.id.lv_tv_1, R.id.lv_tv_2 });

		settingAdapter = new ListViewSettingAdapter(titles, describers, this);

		phone_lv.setAdapter(phoneAdapter);
		sms_lv.setAdapter(smsAdapter);
		roster_black_lv.setAdapter(blackAdapter);
		roster_white_lv.setAdapter(whiteAdapter);
		setting_lv.setAdapter(settingAdapter);

	}

	@Override
	protected void onResume()
	{
		super.onResume();
		initPhoneView();
	}

	@Override
	public void onTabChanged(String tabId)
	{

		if (tabId.equals(getString(R.string.phone)))
		{
			initPhoneView();
			return;
		}

		if (tabId.equals(getString(R.string.sms)))
		{
			initSmsView();

			return;
		}
		if (tabId.equals(getString(R.string.black_white_list)))
		{
			initBlackView();
			initWhiteView();

			return;
		}

		if (tabId.equals(getString(R.string.setting)))
		{
			// TODO setting
			initSettingView();

			return;
		}

	}

	private void initBlackView()
	{
		if (dao.count(BlackContact.class) == 0)
		{
			roster_ll_black_empty.setVisibility(View.VISIBLE);
			roster_black_lv.setVisibility(View.INVISIBLE);

		}
		else
		{
			roster_ll_black_empty.setVisibility(View.INVISIBLE);
			roster_black_lv.setVisibility(View.VISIBLE);

			blackData = dao.findAll(BlackContact.class);
			blackAdapter.setData(blackData);
			blackAdapter.notifyDataSetChanged();

		}

	}

	private void initWhiteView()
	{
		Log.i(TAG, "init white view");
		if (dao.count(WhiteContact.class) == 0)
		{
			Log.i(TAG, " white is empty");

			roster_ll_white_empty.setVisibility(View.VISIBLE);
			roster_white_lv.setVisibility(View.INVISIBLE);

		}
		else
		{
			roster_ll_white_empty.setVisibility(View.INVISIBLE);
			roster_white_lv.setVisibility(View.VISIBLE);

			whiteDate = dao.findAll(WhiteContact.class);
			whiteAdapter.setData(whiteDate);
			whiteAdapter.notifyDataSetChanged();

		}

	}

	private void initSettingView()
	{
		if (interceptEngine.isOpenService())
		{
			setting_cb_server.setChecked(true);
			setting_tv_server.setText(getString(R.string.already_open));
		}
		else
		{
			setting_cb_server.setChecked(false);
			setting_tv_server.setText(getString(R.string.un_open));

		}

		if (interceptEngine.isNotice())
		{
			setting_cb_nofiti.setChecked(true);
			setting_tv_nofiti.setText(getString(R.string.already_open));
		}
		else
		{
			setting_cb_nofiti.setChecked(false);
			setting_tv_nofiti.setText(getString(R.string.un_open));

		}

		if (interceptEngine.isNightDisturb())
		{
			describers[1] = getString(R.string.already_open);
		}
		else
		{
			describers[1] = getString(R.string.un_open);
		}

		describers[0] = getString(R.string.intercept_lable) + InterceptModel.getName(interceptEngine.getModel());
		describers[2] = InterceptSaveTime.getName(interceptEngine.getLogSaveTime());

		settingAdapter.notifyDataSetChanged();

	}

	private void initSmsView()
	{

		sms_tv_model.setText(getString(R.string.intercept_lable) + InterceptModel.getName(interceptEngine.getModel()));

		if (dao.count(InterceptSms.class) == 0)
		{
			sms_tv_empty.setVisibility(View.VISIBLE);
			sms_lv.setVisibility(View.INVISIBLE);

		}
		else
		{
			sms_tv_empty.setVisibility(View.INVISIBLE);
			sms_lv.setVisibility(View.VISIBLE);
			smsData = dao.findAll(InterceptSms.class);
			smsAdapter.setData(smsData);
			smsAdapter.notifyDataSetChanged();
		}
	}

	private void initPhoneView()
	{
		phone_tv_model.setText(getString(R.string.intercept_lable) + InterceptModel.getName(interceptEngine.getModel()));

		if (dao.count(InterceptContact.class) == 0)
		{
			phone_tv_empty.setVisibility(View.VISIBLE);
			phone_lv.setVisibility(View.INVISIBLE);

		}
		else
		{
			phone_tv_empty.setVisibility(View.INVISIBLE);
			phone_lv.setVisibility(View.VISIBLE);
			phoneData = dao.findAll(InterceptContact.class);
			phoneAdapter.setData(phoneData);
			phoneAdapter.notifyDataSetChanged();

		}

	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.phone_iv_handle:
			break;
		case R.id.sms_iv_handle:
			break;

		case R.id.roster_ll_black_add:

			showInputBlackDialog();

			break;
		case R.id.roster_ll_white_add:
			showInputWhiteDialog();
			break;

		// dialog ---> input
		case R.id.dialog_input_black_call:
			break;
		case R.id.dialog_input_black_sms:
			break;

		case R.id.dialog_input_black_contact:

			break;

		case R.id.dialog_input_black_yourself:

			Intent intent = new Intent(this, RosterAddOrEditActivity.class);
			intent.putExtra("roster", CODE_BLACK);
			startActivityForResult(intent, CODE_BLACK);
			rosterInputDialog.dismiss();
			break;
		case R.id.dialog_input_black_location:
			break;

		case R.id.dialog_input_black_numbers:
			break;

		case R.id.dialog_input_white_call:
			break;

		case R.id.dialog_input_white_sms:
			break;

		case R.id.dialog_input_white_contact:
			break;

		case R.id.dialog_input_white_yourself:
			Intent intent2 = new Intent(this, RosterAddOrEditActivity.class);
			intent2.putExtra("roster", CODE_WHITE);
			startActivityForResult(intent2, CODE_WHITE);

			rosterInputDialog.dismiss();
			break;

		case R.id.dialog_input_black_cancel:
			rosterInputDialog.dismiss();
			break;
		case R.id.dialog_input_white_cancel:
			rosterInputDialog.dismiss();
			break;

		// dialog ---> contact edit

		case R.id.dialog_contact_delete:

			if (black)
			{
				dao.deleteById(BlackContact.class, blackContact.getId() + "");
				initBlackView();
			}
			else
			{
				dao.deleteById(WhiteContact.class, whiteContact.getId() + "");
				initWhiteView();
			}
			destoryEditDialog();
			break;

		case R.id.dialog_contact_edit:

			if (black)
			{
				IntentUtil.startIntentForReuslt(this, RosterAddOrEditActivity.class, "contact", blackContact, "roster", CODE_BLACK, CODE_BLACK);
			}
			else
			{
				IntentUtil.startIntentForReuslt(this, RosterAddOrEditActivity.class, "contact", whiteContact, "roster", CODE_WHITE, CODE_WHITE);
			}

			destoryEditDialog();
			break;

		case R.id.dialog_contact_phone:

			if (black) SysIntentUtil.callPhone(this, blackContact.getNumber());
			else SysIntentUtil.callPhone(this, whiteContact.getNumber());

			destoryEditDialog();
			break;

		case R.id.dialog_contact_sms:

			if (black) SysIntentUtil.sendSms(this, blackContact.getNumber());
			else SysIntentUtil.sendSms(this, whiteContact.getNumber());

			destoryEditDialog();
			break;
		default:
			break;
		}
	}

	private void destoryEditDialog()
	{
		contactEditDialog.dismiss();
		blackContact =null;
		whiteContact=null;
	}

	private void showInputBlackDialog()
	{
		View view = View.inflate(this, R.layout.dialog_input_black, null);

		TextView tv_cantact = (TextView) view.findViewById(R.id.dialog_input_black_contact);
		TextView tv_call = (TextView) view.findViewById(R.id.dialog_input_black_call);
		TextView tv_sms = (TextView) view.findViewById(R.id.dialog_input_black_sms);
		TextView tv_yourself = (TextView) view.findViewById(R.id.dialog_input_black_yourself);
		TextView tv_location = (TextView) view.findViewById(R.id.dialog_input_black_location);
		TextView tv_numbers = (TextView) view.findViewById(R.id.dialog_input_black_numbers);
		Button bt_cancel = (Button) view.findViewById(R.id.dialog_input_black_cancel);

		bt_cancel.setOnClickListener(this);
		tv_call.setOnClickListener(this);
		tv_cantact.setOnClickListener(this);
		tv_location.setOnClickListener(this);
		tv_numbers.setOnClickListener(this);
		tv_sms.setOnClickListener(this);
		tv_yourself.setOnClickListener(this);
		rosterInputDialog = DialogUtil.showDialog(this, R.style.MyDialog, true, view);

	}

	private void showInputWhiteDialog()
	{
		View view = View.inflate(this, R.layout.dialog_input_white, null);
		TextView tv_cantact = (TextView) view.findViewById(R.id.dialog_input_white_contact);
		TextView tv_call = (TextView) view.findViewById(R.id.dialog_input_white_call);
		TextView tv_sms = (TextView) view.findViewById(R.id.dialog_input_white_sms);
		TextView tv_yourself = (TextView) view.findViewById(R.id.dialog_input_white_yourself);
		Button bt_cancel = (Button) view.findViewById(R.id.dialog_input_white_cancel);

		bt_cancel.setOnClickListener(this);
		tv_call.setOnClickListener(this);
		tv_cantact.setOnClickListener(this);
		tv_sms.setOnClickListener(this);
		tv_yourself.setOnClickListener(this);

		rosterInputDialog = DialogUtil.showDialog(this, R.style.MyDialog, true, view);

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id)
	{
		switch (parent.getId())
		{
		case R.id.phone_lv:
			// phone

			break;
		case R.id.sms_lv:
			// sms

			break;
		case R.id.roster_black_lv:
			// black
			Log.i(TAG, "black item click");
			blackContact = (BlackContact) blackAdapter.getItem(position);
			showContactEditDialog();
			black = true;
			break;

		case R.id.roster_white_lv:
			// white
			Log.i(TAG, "white item click");
			whiteContact = (WhiteContact) whiteAdapter.getItem(position);
			showContactEditDialog();
			black = false;
			break;

		case R.id.setting_lv:
			// setting

			break;

		default:
			break;
		}
	}

	private void showContactEditDialog()
	{

		View view = View.inflate(this, R.layout.dialog_edit, null);
		TextView tv_delete = (TextView) view.findViewById(R.id.dialog_contact_delete);
		TextView tv_edit = (TextView) view.findViewById(R.id.dialog_contact_edit);
		TextView tv_phone = (TextView) view.findViewById(R.id.dialog_contact_phone);
		TextView tv_sms = (TextView) view.findViewById(R.id.dialog_contact_sms);
		tv_delete.setOnClickListener(this);
		tv_edit.setOnClickListener(this);
		tv_phone.setOnClickListener(this);
		tv_sms.setOnClickListener(this);
		contactEditDialog = DialogUtil.showDialog(this, R.style.MyDialog, true, view);
	}

	@Override
	public void onCheckedChanged(CompoundButton v, boolean isChecked)
	{
		switch (v.getId())
		{
		case R.id.setting_cb_nofiti_enable:
			if (isChecked)
			{
				interceptEngine.onNotice();
			}
			else
			{
				interceptEngine.offNotice();
			}
			initSettingView();
			break;

		case R.id.setting_cb_server_enable:

			Log.i(TAG, "server is change");

			if (isChecked)
			{
				interceptEngine.onService();
			}
			else
			{
				interceptEngine.offService();
			}

			initSettingView();
			break;

		default:
			break;
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);

		switch (resultCode)
		{
		case CODE_BLACK:

			initBlackView();

			break;
		case CODE_WHITE:

			Log.i(TAG, "code is white");

			initWhiteView();

			break;
		case CODE_SETTING:

			initSettingView();
			break;

		default:
			break;
		}

	}

	// ///////////////------------>Animation

	private void initAnimation(int start, int end, int code)
	{

		final float centerX = view.getWidth() / 2.0f;
		final float centerY = view.getHeight() / 2.0f;

		// revers 开始时候 必需 为 true 这样 不会 先翻转 也不会根据 Deep 缩小了
		rotation = new Rotate3dAnimation(start, end, centerX, centerY, 310.0f, true);
		rotation.setInterpolator(new AccelerateInterpolator());
		rotation.setFillAfter(true);
		rotation.setDuration(400);
		rotation.setAnimationListener(new DisplayNextView(code));

		view.startAnimation(rotation);

	}

	private final class DisplayNextView implements Animation.AnimationListener
	{
		private final int code;

		private DisplayNextView(int code)
		{
			this.code = code;
		}

		public void onAnimationStart(Animation animation)
		{
		}

		public void onAnimationEnd(Animation animation)
		{
			view.post(new SwapViews(code));

		}

		public void onAnimationRepeat(Animation animation)
		{
		}
	}

	/**
	 * This class is responsible for swapping the views and start the second
	 * half of the animation.
	 */
	private final class SwapViews implements Runnable
	{
		private final int code;

		public SwapViews(int code)
		{
			this.code = code;
		}

		public void run()
		{
			final float centerX = view.getWidth() / 2.0f;
			final float centerY = view.getHeight() / 2.0f;

			Rotate3dAnimation rotation;

			if (code == CODE_BLACK)
			{
				roster_ll_black.setVisibility(View.GONE);
				roster_ll_white.setVisibility(View.VISIBLE);
				roster_ll_white.requestFocus();

				rotation = new Rotate3dAnimation(-90, 0, centerX, centerY, 310.0f, false);

			}
			else
			{
				roster_ll_white.setVisibility(View.GONE);
				roster_ll_black.setVisibility(View.VISIBLE);
				roster_ll_black.requestFocus();

				rotation = new Rotate3dAnimation(90, 0, centerX, centerY, 310.0f, false);
			}
			rotation.setDuration(300);

			rotation.setFillAfter(true);
			rotation.setInterpolator(new DecelerateInterpolator());

			view.startAnimation(rotation);
		}
	}

	@Override
	public void OnChanged(boolean changed, View v)
	{

		if (changed)
		{
			initAnimation(0, 90, CODE_BLACK);

		}
		else
		{
			initAnimation(0, -90, CODE_WHITE);

		}
	}

	// //////////////////////////////////////////////////////////////////////
}
