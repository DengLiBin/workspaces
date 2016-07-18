import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.print.attribute.standard.Finishings;

public class MultiDownload {

	static int ThreadCount = 3;
	static int finishedThread = 0;
	//ȷ�����ص�ַ
	static String path = "http://192.168.13.13:8080/QQPlayer.exe";
	public static void main(String[] args) {
		
		//����get�������������ַ����Դ
		try {
			URL url = new URL(path);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(5000);
			
			if(conn.getResponseCode() == 200){
				//�õ���������Դ�ļ��ĳ���
				int length = conn.getContentLength();
				
				File file = new File("QQPlayer.exe");
				//������ʱ�ļ�
				RandomAccessFile raf = new RandomAccessFile(file, "rwd");
				//������ʱ�ļ��Ĵ�С
				raf.setLength(length);
				raf.close();
				//�����ÿ���߳�Ӧ�����ض����ֽ�
				int size = length / ThreadCount;
				
				for (int i = 0; i < ThreadCount; i++) {//��ThreadCount���߳�
					//�����߳����صĿ�ʼλ�úͽ���λ��
					int startIndex = i * size;
					int endIndex = (i + 1) * size - 1;
					//��������һ���̣߳���ô����λ��д��
					if(i == ThreadCount - 1){
						endIndex = length - 1;
					}
//					System.out.println("�߳�" + i + "�����������ǣ�" + startIndex + "---" + endIndex);
					new DownLoadThread(startIndex, endIndex, i).start();
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
class DownLoadThread extends Thread{
	int startIndex;
	int endIndex;
	int threadId;
	
	public DownLoadThread(int startIndex, int endIndex, int threadId) {
		super();
		this.startIndex = startIndex;
		this.endIndex = endIndex;
		this.threadId = threadId;
	}

	@Override
	public void run() {
		//�ٴη���http��������ԭ�ļ�
		try {
			File progressFile = new File(threadId + ".txt");
			//�жϽ�����ʱ�ļ��Ƿ����
			if(progressFile.exists()){
				FileInputStream fis = new FileInputStream(progressFile);
				BufferedReader br = new BufferedReader(new InputStreamReader(fis));
				//�ӽ�����ʱ�ļ��ж�ȡ����һ�����ص��ܽ��ȣ�Ȼ����ԭ���Ŀ�ʼλ����ӣ��õ��µĿ�ʼλ��
				startIndex += Integer.parseInt(br.readLine());
				fis.close();
			}
			System.out.println("�߳�" + threadId + "�����������ǣ�" + startIndex + "---" + endIndex);
			HttpURLConnection conn;
			URL url = new URL(MultiDownload.path);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(5000);
			//���ñ���http��������������ݵ�����
			conn.setRequestProperty("Range", "bytes=" + startIndex + "-" + endIndex);
			
			//���󲿷����ݣ���Ӧ����206
			if(conn.getResponseCode() == 206){
				//�����ʱֻ��1/3ԭ�ļ�������
				InputStream is = conn.getInputStream();
				byte[] b = new byte[1024];
				int len = 0;
				int total = 0;
				//�õ���ʱ�ļ��������
				File file = new File("QQPlayer.exe");
				RandomAccessFile raf = new RandomAccessFile(file, "rwd");
				//���ļ���д��λ���ƶ���startIndex
				raf.seek(startIndex);
				while((len = is.read(b)) != -1){
					//ÿ�ζ�ȡ��������֮��ͬ��������д����ʱ�ļ�
					raf.write(b, 0, len);
					total += len;
//					System.out.println("�߳�" + threadId + "������" + total);
					
					//����һ��ר��������¼���ؽ��ȵ���ʱ�ļ�
					RandomAccessFile progressRaf = new RandomAccessFile(progressFile, "rwd");
					//ÿ�ζ�ȡ��������֮��ͬ���ѵ�ǰ�߳����ص��ܽ���д�������ʱ�ļ���
					progressRaf.write((total + "").getBytes());
					progressRaf.close();
				}
				System.out.println("�߳�" + threadId + "�������-------------------С־���ϣ�");
				raf.close();
				
				MultiDownload.finishedThread++;
				synchronized (MultiDownload.path) {
					if(MultiDownload.finishedThread == MultiDownload.ThreadCount){
						for (int i = 0; i < MultiDownload.ThreadCount; i++) {
							File f = new File(i + ".txt");
							f.delete();
						}
						MultiDownload.finishedThread = 0;
					}
				}
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
