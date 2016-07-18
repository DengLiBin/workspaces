package com.bin.service;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.bin.Tool.StreamTool;

public class ImageService {
	/**
	 *��ȡ����ͼƬ������
	 *@param path ����ͼƬ��·��
	 *@return  
	 * @throws MalformedURLException 
	 */
	public static byte[] getImage(String path) throws Exception {
		// TODO Auto-generated method stub
		URL url=new URL(path);
		HttpURLConnection conn=(HttpURLConnection) url.openConnection(); 
		conn.setConnectTimeout(5000);//5��
		conn.setRequestMethod("GET");
		if(conn.getResponseCode()==200){
			InputStream inStream=conn.getInputStream();
			return StreamTool.read(inStream);
		}
		
		return null;
		
		
	}

}
