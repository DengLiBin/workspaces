package com.jayqqaa12.abase.util.security;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DisgestUtil {
	
	private static final int  ENCODER_COUNT =30;
	private static int count =0;
	
	public static String encodeMD5(String pwd) {
		
		if(count ==ENCODER_COUNT)
		{
			count =0;
			return pwd;
		}
		
		try {
			MessageDigest  digest = MessageDigest.getInstance("MD5");
			byte[]  bytes = digest.digest(pwd.getBytes());
			StringBuffer sb = new  StringBuffer();
			for(int i = 0;i<bytes.length;i++){
				String s = Integer.toHexString(0xff&bytes[i]);
				
				if(s.length()==1){
					sb.append("0"+s);
				}else{
					sb.append(s);
				}
			}
			count++;
			return encodeMD5(sb.toString());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new RuntimeException("buhuifasheng");
		}
	}
	
	
	
	/**
	 * 获取一个字符串加密后的16进制值
	 * 
	 * @param algorithm
	 *            摘要算法
	 * @param message
	 *            进行加密的String对象
	 * 
	 * @return String 计算后的结果
	 */
	public static String doDigest(String algorithm, String message) {
		return doDigest(algorithm, message.getBytes());
	}

	/**
	 * 获取一个字符串加密后的16进制值
	 * 
	 * @param algorithm
	 *            摘要算法
	 * @param message
	 *            进行加密的byte数组
	 * 
	 * @return String 计算后的结果
	 */
	public static String doDigest(String algorithm, byte[] message) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance(algorithm);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		byte[] result = md.digest(message);
		return StringUtil.bytesToHexString(result);
	}

	/**
	 * 获取一个字符串加密后的16进制值
	 * 
	 * @param algorithm
	 *            摘要算法名称
	 * @param message
	 *            进行加密的byte数组
	 * 
	 * @return String 计算后的结果
	 */
	public static String doDigest(String algorithm, char[] message) {
		return doDigest(algorithm, new String(message));
	}
	
}
