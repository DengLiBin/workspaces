package com.jayqqaa12.mobilesafe.engine.traffic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import android.util.Log;

import com.jayqqaa12.abase.core.AbaseDaoEngine;
import com.jayqqaa12.abase.util.common.DateUtil;
import com.jayqqaa12.abase.util.common.TAG;
import com.jayqqaa12.abase.util.sys.ApkInfo;
import com.jayqqaa12.abase.util.sys.ApkInfoUtil;
import com.jayqqaa12.mobilesafe.config.Config;
import com.jayqqaa12.mobilesafe.domain.Traffic;

public class TrafficEngine extends AbaseDaoEngine
{

	public List<ApkInfo<Traffic>> getAppTrafficData()
	{
		List<ApkInfo<Traffic>> data = new ArrayList<ApkInfo<Traffic>>();

		List<ApkInfo> apks = ApkInfoUtil.getAllApps();

		for (ApkInfo<Traffic> info : apks)
		{
			Traffic t = dao.findByWhere(Traffic.class,"uid="+info.uid);

			if (t != null)
			{
				info.obj = t;
				data.add(info);

			}
		}

		return data;
	}

	public void updateTrafficData(Map<Integer, Traffic> temp, List<ApkInfo> apks)
	{

		for (ApkInfo info : apks)
		{
			Traffic t = dao.findByWhere(Traffic.class, "uid="+info.uid);

			Traffic tempTraffic = temp.get(info.uid);

			if (tempTraffic == null || (tempTraffic.getMobileDay() == 0 && tempTraffic.getWifiDay() == 0)) continue;

			if (t == null)
			{
				// 只 记录 有 流量的
				Log.i(TAG.ENGINE, "insert traffic to dao ");
				dao.save(tempTraffic);
				
			}
			else
			{
				

				Date date = t.getDate();
				Date today = new Date();

				if (DateUtil.getYear(date) == DateUtil.getYear(today))
				{
					if (DateUtil.getMonth(date) == DateUtil.getMonth(today))
					{
						if (DateUtil.getDay(date) == DateUtil.getDay(today))
						{
							Log.i(TAG.ENGINE, "update traffic to  same today uid=" + t.getUid());

							t.setMobileDay(t.getMobileDay() + tempTraffic.getMobileDay());
							t.setWifiDay(t.getWifiDay() + tempTraffic.getWifiDay());
							t.setMobileMonth(t.getMobileMonth() + tempTraffic.getMobileDay());
							t.setWifiMonth(t.getWifiMonth() + tempTraffic.getWifiDay());

						}
						else
						{
							Log.i(TAG.ENGINE, "update traffic to same month uid=" + t.getUid());

							t.setMobileDay(tempTraffic.getMobileDay());
							t.setWifiDay(tempTraffic.getWifiDay());

							t.setMobileMonth(t.getMobileMonth() + tempTraffic.getMobileDay());
							t.setWifiMonth(t.getWifiMonth() + tempTraffic.getWifiDay());

						}
					}
					else
					{
						Log.i(TAG.ENGINE, "update traffic to diff month uid=" + t.getUid());

						t.setMobileDay(tempTraffic.getMobileDay());
						t.setMobileMonth(tempTraffic.getMobileDay());

						t.setWifiDay(tempTraffic.getWifiDay());
						t.setWifiMonth(tempTraffic.getWifiDay());
					}

				}
				else
				{
					Log.i(TAG.ENGINE, "update traffic diff yeah uid=" + t.getUid());
					t.setMobileDay(tempTraffic.getMobileDay());
					t.setMobileMonth(tempTraffic.getMobileDay());

					t.setWifiDay(tempTraffic.getWifiDay());
					t.setWifiMonth(tempTraffic.getWifiDay());
				}

				Log.d(TAG.DEBUG, "result = wifi day = " + t.getWifiDay() + "  month= " + t.getWifiMonth());

				dao.update(t);

			}

		}

	}

	public String getTrafficStartDate()
	{
		return sp.getString(Config.TRAFFIC_START_DATE, DateUtil.getDate(new Date()));
	}

	public boolean isOpenService()
	{
		return sp.getBoolean(Config.TRAFFIC_OPEN, true);
	}

}
