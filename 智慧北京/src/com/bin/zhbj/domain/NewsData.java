package com.bin.zhbj.domain;

import java.util.ArrayList;

/**
 * ���������Ϣ�ķ�װ
 * �ֶ�����Ҫ�ͷ��������ص��ֶ���һ�£�����Gson����
 * @author Administrator
 *
 */
public class NewsData {
	
	public int retcode;
	public ArrayList<NewMenuData> data;//�����NewsMenuData����
	
	//���������
	public class NewMenuData{
		
		public String id;
		public String title;
		public String url;
		public int type;
		public ArrayList<NewsTabData> children;//�����NewsTabData����
		@Override
		public String toString() {
			return "NewMenuData [title=" + title + ", children=" + children + "]";
		}
	}
	//����ҳ��11����ҳǩ�����ݶ���
	public class NewsTabData{
		
		public String id;
		public String title;
		public String url;
		public int type;
		@Override
		public String toString() {
			return "NewsTabData [title=" + title + "]";
		}
	}
	@Override
	public String toString() {
		return "NewsData [Data=" + data + "]";
	}
}
