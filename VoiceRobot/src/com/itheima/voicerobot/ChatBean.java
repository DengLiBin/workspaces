package com.itheima.voicerobot;

/**
 * ������Ϣ����
 * 
 * @author Kevin
 * 
 */
public class ChatBean {

	public String text;// ����
	public boolean isAsker;// true��ʾ������,�����ǻش���

	public int imageId = -1;// ͼƬid

	public ChatBean(String text, boolean isAsker, int imageId) {
		this.text = text;
		this.isAsker = isAsker;
		this.imageId = imageId;
	}

}
