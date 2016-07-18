package com.bin.service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import android.util.Log;

/**
 * @author Administrator
 *
 */
public class NewService {
	/**
	 *��������
	 *@param title ����
	 *@param timelength ʱ�� 
	 * 
	 */
	public static boolean save(String title, String timelength) {
		String path="http://192.168.2.104:8080/getdatafromAndroid/GetData";
		Map<String,String> params=new HashMap<String,String>();
		params.put("title", title);
		params.put("timelength", timelength);
		try{
			return sendGetRequest(path,params);
		}catch(Exception e){
			e.printStackTrace();
			Log.i("werfw", "sendGetRequest�����쳣");
			
		}
		return false;
		
	}
	
	/**
	 * ����GET����
	 * @param path ����·��
	 * @param params �������
	 * @return �����Ƿ�ɹ�
	 * @throws Exception
	 */
	private static boolean sendGetRequest(String path,
			Map<String, String> params) throws Exception  {
		StringBuilder url=new StringBuilder(path);
		url.append("?");
		for(Map.Entry<String, String> entry : params.entrySet()){
			url.append(entry.getKey()).append("=");
			url.append(entry.getValue());
			url.append("&");
		}
		//������ɺ�url������һ��&���ţ�ɾ������
		url.deleteCharAt(url.length()-1);
		Log.i("bingaer", "url�ǣ�"+url.toString());
		//String uri="https://www.baidu.com";
		HttpURLConnection conn=(HttpURLConnection) new URL(url.toString()).openConnection();
		
		conn.setConnectTimeout(5000);
		conn.setRequestMethod("GET");
		
		if(conn.getResponseCode()==200){
			return true;
		}else
		{
			return false;
		}
		
	}
	
}
