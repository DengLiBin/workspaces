package com.shopping.redboy.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.accounts.NetworkErrorException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapUtil {
	/**
	 * 获取bitmap
	 * 
	 * @param pic
	 * @return
	 * @throws NetworkErrorException
	 * @throws IOException
	 */
	public static Bitmap getBitMapFormURL(String pic) throws NetworkErrorException,
			IOException {
		URL picUrl = new URL(pic);
		HttpURLConnection conn = (HttpURLConnection) picUrl.openConnection();

		conn.setConnectTimeout(500);
		conn.setReadTimeout(500);
		// 获取返回码
		int code = conn.getResponseCode();
		if (code == 200) {
			InputStream in = conn.getInputStream();
			Bitmap bm = BitmapFactory.decodeStream(in);
			return bm;
		} else {
			throw new NetworkErrorException("网络连接错误");
		}
	}
}
