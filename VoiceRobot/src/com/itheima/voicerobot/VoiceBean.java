package com.itheima.voicerobot;

import java.util.ArrayList;

/**
 * ������Ϣ��װ
 * 
 * @author Kevin
 * 
 */
public class VoiceBean {

	public ArrayList<WSBean> ws;

	public class WSBean {
		public ArrayList<CWBean> cw;
	}

	public class CWBean {
		public String w;
	}
}
