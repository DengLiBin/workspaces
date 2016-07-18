package com.jayqqaa12.abase.util.comm;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.ProgressDialog;

public class DownLoadUtil
{

	public static File getFile(String srcPath, String dirPath, ProgressDialog pd) throws Exception
	{
		URL url = new URL(srcPath);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setConnectTimeout(3000);
		if (conn.getResponseCode() == 200)
		{
			int total = conn.getContentLength();
			pd.setMax(total);
			InputStream is = conn.getInputStream();
			File file = new File(dirPath);
			FileOutputStream fos = new FileOutputStream(file);
			byte[] buffer = new byte[1024];
			int len = 0;
			int process = 0;
			while ((len = is.read(buffer)) != -1)
			{
				fos.write(buffer, 0, len);
				process += len;
				pd.setProgress(process);
			}
			fos.flush();
			fos.close();
			is.close();

			return file;
		}
		return null;

	}
	
	
	/**
	 * 下载指定的文本内容
	 * 
	 * @param url
	 *            请求下载的地址。
	 */
	public static String downloadString(String url) {
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet request = new HttpGet(url);
			HttpResponse response = httpClient.execute(request);
			return EntityUtils.toString(response.getEntity());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
