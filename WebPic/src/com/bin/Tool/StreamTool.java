package com.bin.Tool;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
/**
 *��ȡ�������е����� 
 */
public class StreamTool {
	public static byte[] read(InputStream inStream)throws Exception {
		ByteArrayOutputStream outStream=new ByteArrayOutputStream();
		byte[] buffer=new byte[1024];
		int len=0;
		while((len=inStream.read(buffer))!=-1){
			outStream.write(buffer, 0, len);//ǿ�ֽ������е����� д�뵽�ڴ���
		}
		inStream.close();
		return outStream.toByteArray();
		
	}
}
