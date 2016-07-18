package com.shopping.redboy.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import android.util.Log;

import com.shopping.redboy.ConstantValue;
import com.shopping.redboy.GlobalParams;

public class HttpClientUtil {
	private HttpClient client;

	private HttpPost post;
	private HttpGet get;
	private HttpResponse response;

	private String TAG = "HttpClientUtil";

	private static Header[] headers;
	static {
		headers = new BasicHeader[10];
		headers[0] = new BasicHeader("Appkey", "12343");
		headers[1] = new BasicHeader("Udid", "");// 手机串号
		headers[2] = new BasicHeader("Os", "android");//
		headers[3] = new BasicHeader("Osversion", "");//
		headers[4] = new BasicHeader("Appversion", "");// 1.0
		headers[5] = new BasicHeader("Sourceid", "");//
		headers[6] = new BasicHeader("Ver", "");
		headers[7] = new BasicHeader("Userid", "");
		headers[8] = new BasicHeader("Usersession", "");
		headers[9] = new BasicHeader("Unique", "");
	}

	public HttpClientUtil() {
		client = new DefaultHttpClient();
		// 判断是否需要设置代理信息
		if (StringUtils.isNotBlank(GlobalParams.PROXY)) {
			// 设置代理信息
			HttpHost host = new HttpHost(GlobalParams.PROXY, GlobalParams.PORT);
			client.getParams()
					.setParameter(ConnRoutePNames.DEFAULT_PROXY, host);
		}
	}

	public String SendGet(String mapping, Map<String, String> params) {
		StringBuffer sbuf = new StringBuffer(ConstantValue.URL + mapping);
		if (params != null) {
			Set<Entry<String, String>> entrySet = params.entrySet();
			sbuf.append("?");
			for (Entry<String, String> item : entrySet) {
				sbuf.append(item.getKey()).append("=").append(item.getValue())
						.append("&");
			}
		}
		get = new HttpGet(sbuf.toString());
		get.setHeaders(headers);
		try {
			response = client.execute(get);
			if (response.getStatusLine().getStatusCode() == 200) {
				return EntityUtils.toString(response.getEntity(),
						ConstantValue.ENCONDING);
			} else {
				Log.i(TAG, "" + response.getStatusLine().getStatusCode());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String sendPost(String mapping, Map<String, String> params) {
		post = new HttpPost(ConstantValue.URL + mapping);
		post.setHeaders(headers);
		// System.out.println(ConstantValue.URL + mapping);
		// 处理超时
		HttpParams httpParams = new BasicHttpParams();//
		HttpConnectionParams.setConnectionTimeout(httpParams, 3000);
		HttpConnectionParams.setSoTimeout(httpParams, 3000);
		post.setParams(httpParams);

		// 设置参数
		if (params != null && params.size() > 0) {
			List<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();
			for (Map.Entry<String, String> item : params.entrySet()) {
				BasicNameValuePair pair = new BasicNameValuePair(item.getKey(),
						item.getValue());
				parameters.add(pair);
			}
			try {
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(
						parameters, ConstantValue.ENCONDING);
				post.setEntity(entity);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		try {
			response = client.execute(post);
			if (response.getStatusLine().getStatusCode() == 200) {
				return EntityUtils.toString(response.getEntity(),
						ConstantValue.ENCONDING);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
