package com.jayqqaa12.mobilesafe.ui.comm.intercept;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jayqqaa12.abase.annotation.view.FindView;
import com.jayqqaa12.abase.core.AbaseDao;
import com.jayqqaa12.abase.core.activity.AbaseActivity;
import com.jayqqaa12.abase.util.ui.ToastUtil;
import com.jayqqaa12.mobilesafe.R;
import com.jayqqaa12.mobilesafe.config.Config;
import com.jayqqaa12.mobilesafe.domain.BlackContact;
import com.jayqqaa12.mobilesafe.domain.Contact;
import com.jayqqaa12.mobilesafe.domain.WhiteContact;

public class RosterAddOrEditActivity extends AbaseActivity
{
	private AbaseDao dao = AbaseDao.create();

	@FindView(id = R.id.et_number)
	private EditText et_number;
	@FindView(id = R.id.et_name)
	private EditText et_name;
	@FindView(id = R.id.bt_save, click = true)
	private Button bt_save;
	@FindView(id = R.id.bt_cancel, click = true)
	private Button bt_cancel;
	@FindView(id = R.id.title_1_tv, textId = R.string.addOrEdit)
	private TextView tv_title;

	private int code;


	private Contact contact;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_intercept_roster_add);

		Intent intent = getIntent();
		code = intent.getIntExtra("roster", -1);

		contact = (Contact) intent.getSerializableExtra("contact");

		if (contact != null) initView();

	}

	private void initView()
	{

		et_name.setText(contact.getName());
		et_number.setText(contact.getNumber());
	}

	@Override
	public void onClick(View v)
	{

		switch (v.getId())
		{
		case R.id.bt_save:

			if (et_number.getText().toString().equals("") || !et_number.getText().toString().matches(Config.NUMBER_PATTERN))
			{
				ToastUtil.ShortToast("号码为空或输入错误");

				return;
			}
			Contact c = contact;

			if (c == null)
			{
				if (code == InterceptMainActivity.CODE_BLACK) c = new BlackContact();
				else c= new WhiteContact();
			}

			c.setName(et_name.getText().toString());
			c.setNumber(et_number.getText().toString());
			
			if(c.getName().equals("")) c.setName("未知联系人");

			if (code == InterceptMainActivity.CODE_BLACK)
			{
				isExist(c);

				if (contact == null || c.getId() == 0)
				{
					dao.save((BlackContact) c);
				}
				else dao.update((BlackContact) c);

				setResult(InterceptMainActivity.CODE_BLACK);
			}

			else if (code == InterceptMainActivity.CODE_WHITE)
			{
				Log.i(InterceptMainActivity.TAG, "save white");

				isExist(c);

				if (contact == null || c.getId() == 0)
				{
					dao.save((WhiteContact) c);
				}
				else
				{
					dao.update((WhiteContact) c);
				}

				setResult(InterceptMainActivity.CODE_WHITE);

			}
			ToastUtil.ShortToast("添加或修改成功");

		case R.id.bt_cancel:
			finish();

			break;
		default:
			break;
		}
	}

	private void isExist(Contact c)
	{
		isFindBlack(c);
		isFindWhite(c);

	}

	private void isFindBlack(Contact c)
	{
		if (dao.isFindByWhere(BlackContact.class, "number=" + c.getNumber()))
		{
			ToastUtil.ShortToast("号码存在黑名单，请删除后再试");

			return;
		}
	}

	private void isFindWhite(Contact c)
	{
		if (dao.isFindByWhere(WhiteContact.class, "number=" + c.getNumber()))
		{
			ToastUtil.ShortToast("号码存在白名单，请删除后再试");

			return;
		}
	}

}
