package com.jayqqaa12.mobilesafe.engine.comm.intercept;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.wltea.analyzer.IKSegmentation;
import org.wltea.analyzer.Lexeme;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.text.TextUtils;
import android.util.Log;

import com.jayqqaa12.abase.core.AbaseDao;
import com.jayqqaa12.abase.core.AbaseDaoEngine;
import com.jayqqaa12.abase.util.ConfigSpUtil;
import com.jayqqaa12.abase.util.comm.ContactUtil;
import com.jayqqaa12.abase.util.comm.PhoneUtil;
import com.jayqqaa12.abase.util.common.DateUtil;
import com.jayqqaa12.abase.util.ui.NotificationUtil;
import com.jayqqaa12.mobilesafe.R;
import com.jayqqaa12.mobilesafe.config.Config;
import com.jayqqaa12.mobilesafe.config.InterceptModel;
import com.jayqqaa12.mobilesafe.config.InterceptSaveTime;
import com.jayqqaa12.mobilesafe.domain.BlackContact;
import com.jayqqaa12.mobilesafe.domain.Contact;
import com.jayqqaa12.mobilesafe.domain.InterceptContact;
import com.jayqqaa12.mobilesafe.domain.InterceptSms;
import com.jayqqaa12.mobilesafe.domain.RuleContact;
import com.jayqqaa12.mobilesafe.domain.Sms;
import com.jayqqaa12.mobilesafe.domain.WhiteContact;
import com.jayqqaa12.mobilesafe.ui.comm.intercept.InterceptMainActivity;
import com.jayqqaa12.mobilesafe.ui.comm.intercept.RosterAddOrEditActivity;

public class InterceptEngine extends AbaseDaoEngine
{
	private static final String TAG = "InterceptService";
	private AbaseDao ruleDao = AbaseDao.open(Config.DB_DIR, "rule");

	private static final String FIND_BLACK = "SELECT * FROM rules where matcher =? and desc !='白' and type=0";
	private static final String FIND_WHITE = "SELECT * FROM rules where matcher =? and (type!=0 or desc='白')";
	private static final String FIND = "SELECT * FROM rules where matcher=?";
	private static final String RULEFIND_MATCHER = "select matcher from rules where matcher+0!=matcher and desc !='白' and type=0";

	public boolean isOpenService()
	{
		return sp.getBoolean(Config.INTERCEPT_SERVICE, true);
	}

	public void onService()
	{
		ConfigSpUtil.setValue(Config.INTERCEPT_SERVICE, true);
	}

	public void offService()
	{
		ConfigSpUtil.setValue(Config.INTERCEPT_SERVICE, false);
	}

	public boolean isNotice()
	{
		return sp.getBoolean(Config.INTERCEPT_NOTICE, true);
	}

	public void onNotice()
	{
		ConfigSpUtil.setValue(Config.INTERCEPT_NOTICE, true);
	}

	public void offNotice()
	{
		ConfigSpUtil.setValue(Config.INTERCEPT_NOTICE, false);
	}

	public boolean isNightDisturb()
	{
		return sp.getBoolean(Config.INTERCEPT_NIGHT_DISTURB, false);
	}

	public void onNightDisturb()
	{
		ConfigSpUtil.setValue(Config.INTERCEPT_NIGHT_DISTURB, true);
	}

	public void offNightDisturb()
	{
		ConfigSpUtil.setValue(Config.INTERCEPT_NIGHT_DISTURB, false);
	}

	public void setLogSaveTime(InterceptSaveTime time)
	{
		ConfigSpUtil.setValue(Config.INTERCEPT_LOG_SAVE_TIME, time.ordinal());

	}

	public InterceptSaveTime getLogSaveTime()
	{
		return InterceptSaveTime.getTime(sp.getInt(Config.INTERCEPT_LOG_SAVE_TIME, 0));
	}

	public InterceptModel getModel()
	{
		return InterceptModel.getModel(sp.getInt(Config.INTERCEPT_MODEL, 0));
	}

	public void setModel(InterceptModel model)
	{
		ConfigSpUtil.setValue(Config.INTERCEPT_MODEL, model.ordinal());
	}

	public boolean interceptBlackNumber(String number, String content, BroadcastReceiver receiver)
	{
		BlackContact c = dao.findByWhere(BlackContact.class, number);

		String desc = "黑名单用户";

		// sms
		if (receiver != null && c != null)
		{
			Log.i(TAG, "black sms intercept");
			receiver.abortBroadcast();
			InterceptSms sms = new InterceptSms(c.getName(), number, content, desc);
			dao.save(sms);
			showSmsInterceptNotification("拦截到黑名单短信", number, content);
			return true;
		}

		// phone
		else if (c != null)
		{
			Log.i(TAG, "black phone intercept");
			PhoneUtil.endPhone();
			// add number log into db

			dao.save(new InterceptContact(c.getName(), c.getNumber(), desc));

			showPhoneInterceptNotification("拦截到黑名单电话", number);

			return true;
		}

		return false;
	}

	private void showPhoneInterceptNotification(String title, String number)
	{
		if (isNotice())
		{
			showInterceptNotification(title, number);

		}
	}

	private void showSmsInterceptNotification(String title, String number, String content)
	{
		if (isNotice())
		{
			showInterceptNotification(title, number + ":" + content);
		}
	}

	/**
	 * 判断 是否 是 垃圾 电话。。 是否在 危险电话中
	 * 
	 * @param number
	 *            电话
	 * @param receive
	 * @param content
	 * @return
	 */
	public boolean interceptRubbishNumber(String number, String content, BroadcastReceiver receiver)
	{

		RuleContact c = ruleDao.findByWhere(RuleContact.class, "matcher = " + number + " and desc !='白' and type=0");

		// sms 判断 是否 为垃圾 短信
		if (c == null && content != null)
		{
			Date startTime = new Date();
			List<String> words = participle(content);
			List<String> matcher = ruleDao.findAllStringBySql(RULEFIND_MATCHER, "matcher");
			int i = 0;
			for (String w : words)
			{
				for (String m : matcher)
				{
					if (m.contains(w))
					{

						Log.i(TAG, "拦截到 的信息为" + w);

						i++;
					}
				}
			}

			Date endTime = new Date();

			Log.i(TAG, DateUtil.diffDate(startTime, endTime) + "");

			if (i > 5)
			{
				Log.i(TAG, "rubbish sms intercept");
				receiver.abortBroadcast();
				InterceptSms sms = new InterceptSms("", number, content, "垃圾短信");
				dao.save(sms);

				showSmsInterceptNotification("拦截到垃圾短信", number, content);
			}

		}
		// sms
		if (receiver != null && c != null)
		{
			Log.i(TAG, "rubbish sms intercept");
			receiver.abortBroadcast();
			InterceptSms sms = new InterceptSms(c.getName(), number, content, c.getDesc());
			dao.save(sms);
			showSmsInterceptNotification("拦截到垃圾短信", number, content);
			return true;
		}

		if (c != null)
		{
			Log.i(TAG, "rubbish phone intercept");
			PhoneUtil.endPhone();
			dao.save(new InterceptContact(c.getName(), c.getNumber(), c.getDesc()));
			showPhoneInterceptNotification("拦截到垃圾电话", number);
			return true;
		}

		return false;
	}

	/**
	 * IK 分词 词库为自定义 拦截词库
	 * 
	 */
	private List<String> participle(String content)
	{
		// 去掉 字符 留下 汉字
		content = content.replaceAll("[^\u4E00-\u9FA5]", "");
		List<String> words = new ArrayList<String>();

		try
		{
			StringReader reader = new StringReader(content);
			IKSegmentation ik = new IKSegmentation(reader, true);

			Lexeme lexeme = null;
			while ((lexeme = ik.next()) != null)
			{

				words.add(lexeme.getLexemeText());
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		return words;
	}

	/**
	 * @param receiver
	 * @param content
	 * @return true phone is end
	 * @return false phone is pass
	 */
	public boolean interceptNotInWhiteNumber(String number, String content, BroadcastReceiver receiver)
	{
		if (!dao.isFindByWhere(WhiteContact.class, number)
				&& !ruleDao.isFindByWhere(RuleContact.class, " matcher =" + number + " and (type!=0 or desc='白')"))
		{
			String desc = "非白名单 拦截";
			// find name from System contact
			String name = ContactUtil.getContactName(number);

			if (TextUtils.isEmpty(name))
			{
				RuleContact c = ruleDao.findByWhere(RuleContact.class, "numner =" + number);

				if (c != null)
				{
					name = c.getName();
				}
			}

			// sms
			if (receiver != null)
			{
				Log.i(TAG, "拦截到非白名单短信   intercept");
				receiver.abortBroadcast();
				InterceptSms sms = new InterceptSms(name, number, content, desc);

				dao.save(sms);

				showSmsInterceptNotification("拦截到非白名单短信", number, content);
				return true;
			}
			else
			{
				Log.i(TAG, "拦截到非白名单电话  intercept");
				PhoneUtil.endPhone();
				dao.save(new InterceptContact(name, number, desc));
				showPhoneInterceptNotification("拦截到非白名单电话", number);

				return true;

			}
		}
		return false;

	}

	public boolean interceptStrangerNumber(String number, String content, BroadcastReceiver receiver)
	{

		if (!ContactUtil.isFindPhoneFromContact(number)
				&& !ruleDao.isFindByWhere(RuleContact.class, " matcher =" + number + " and (type!=0 or desc='白')"))
		{
			String name = null;
			String desc = "陌生人来电";

			RuleContact c = ruleDao.findByWhere(RuleContact.class, "number=" + number);

			if (c != null)
			{
				name = c.getName();
			}

			// sms
			if (receiver != null)
			{
				Log.i(TAG, "拦截到陌生人短信  intercept");
				receiver.abortBroadcast();
				InterceptSms sms = new InterceptSms(name, number, content, desc);
				dao.save(sms);
				showSmsInterceptNotification("拦截到陌生人短信", number, content);
				return true;
			}
			else
			{
				Log.i(TAG, "拦截到陌生人电话  intercept");
				PhoneUtil.endPhone();
				dao.save(new InterceptContact(name, number, desc));

				showPhoneInterceptNotification("拦截到陌生人电话", number);
				return true;

			}
		}

		return false;
	}

	// TODO user-defined intercept
	public boolean interceptUserDefined(String number, String content, BroadcastReceiver receiver)
	{

		return false;
	}

	public boolean interceptNumber(String number, String content, BroadcastReceiver receiver)
	{
		boolean result = false;
		if (isOpenService())
		{
			if (receiver == null) Log.i(TAG, "拦截 电话");
			else Log.i(TAG, "拦截 短信");

			// 获得 拦截 模式
			InterceptModel model = getModel();

			switch (model.ordinal())
			{
			// 智能拦截 模式 拦截 黑名单 智能判断 垃圾电话
			case InterceptModel.INTELLIGENT_MODEL:

				result = interceptBlackNumber(number, content, receiver);
				if (!result) result = interceptRubbishNumber(number, content, receiver);
				break;
			// 只拦截 黑名单
			case InterceptModel.BLACK_MODEL:
				result = interceptBlackNumber(number, content, receiver);
				break;
			// 只允许白名单
			case InterceptModel.WHITE_MODEL:
				result = interceptNotInWhiteNumber(number, content, receiver);
				break;

			// 拦截 所有
			case InterceptModel.REJECT_MODEL:
				PhoneUtil.endPhone();
				result = true;
				break;
			// 拦截黑名单 和陌生人
			case InterceptModel.UNDISTURB_MODEL:
				result = interceptBlackNumber(number, content, receiver);
				if (!result) result = interceptStrangerNumber(number, content, receiver);
				break;

			// user defined
			case InterceptModel.USER_DEFINED_MODEL:
				result = interceptUserDefined(number, content, receiver);

				break;
			default:
				break;
			}
		}

		return result;
	}

	public void showInterceptOnePhoneNotification(Context context, String incomingNumber, String title)
	{

		Contact contact = new Contact();
		contact.setNumber(incomingNumber);

		Intent intent = new Intent(context, RosterAddOrEditActivity.class);

		// TODO 点击 查看 详情

		intent.putExtra("roster", InterceptMainActivity.CODE_BLACK);
		intent.putExtra("contact", contact);

		PendingIntent contentIntent = PendingIntent.getActivity(context, 0, intent, 0);
		NotificationUtil.showNotification(R.drawable.icon, title, System.currentTimeMillis(), title, "点击添加到黑名单", contentIntent, context);

	}

	public void showInterceptNotification(String title, String content)
	{
		Intent intent = new Intent(getContext(), InterceptMainActivity.class);

		PendingIntent contentIntent = PendingIntent.getActivity(getContext(), 0, intent, 0);
		NotificationUtil.showNotification(R.drawable.icon, title, System.currentTimeMillis(), title, content, contentIntent, getContext());

	}

	public boolean interceptSms(SmsMessage sms, BroadcastReceiver receiver)
	{
		return interceptNumber(sms.getOriginatingAddress(), sms.getMessageBody(), receiver);

	}

	public boolean isWhite(String number)
	{
		if (dao.isFindByWhere(WhiteContact.class, "number=" + number)
				|| ruleDao.isFindByWhere(RuleContact.class, " matcher =" + number + " and (type!=0 or desc='白')")) { return true; }

		return false;
	}

}
