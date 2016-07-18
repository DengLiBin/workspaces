package my.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import android.content.Context;
import android.os.Environment;

public class FileService {
	private Context context;
	public FileService(Context context) {
		
		this.context = context;
	}
	/**
	 * �����ļ�
	 * @param filename �ļ�����
	 * @param filecontent �ļ�����
	 * @throws FileNotFoundException 
	 * 
	 */public void saveToSDCard(String filename, String content)throws Exception {
			File file = new File(Environment.getExternalStorageDirectory(), filename);
			FileOutputStream outStream = new FileOutputStream(file);
			outStream.write(content.getBytes());
			outStream.close();
		}
	public void save(String filename, String filecontent) throws Exception{
		//��ȡ���������
		/*
		 * Context.MODE_PRIVATE��ΪĬ�ϲ���ģʽ��������ļ���˽�����ݣ�ֻ�ܱ�Ӧ�ñ�����ʣ��ڸ�ģʽ�£�д������ݻḲ��ԭ�ļ������ݣ�
		 * ��������д�������׷�ӵ�ԭ�ļ��С�����ʹ��Context.MODE_APPEND
		Context.MODE_APPEND��ģʽ�����ļ��Ƿ���ڣ����ھ����ļ�׷�����ݣ�����ʹ������ļ���
		Context.MODE_WORLD_READABLE��Context.MODE_WORLD_WRITEABLE������������Ӧ���Ƿ���Ȩ�޶�д���ļ���
		MODE_WORLD_READABLE����ʾ��ǰ�ļ����Ա�����Ӧ�ö�ȡ��MODE_WORLD_WRITEABLE����ʾ��ǰ�ļ����Ա�����Ӧ��д�롣
		���ϣ���ļ�������Ӧ�ö���д�����Դ��룺 
		openFileOutput("itcast.txt", Context.MODE_WORLD_READABLE + Context.MODE_WORLD_WRITEABLE);
		 */
		FileOutputStream outStream=context.openFileOutput(filename, Context.MODE_PRIVATE);
		outStream.write(filecontent.getBytes());//���ļ����ݣ��ȱ�����ֽ����飩д�뵽�������
		outStream.close();//��������е�����ˢ���ļ��У����ر������,�����·����data/data/my.file/files/filename
	}
	public String read(String filename)throws Exception{
		FileInputStream inStream=context.openFileInput(filename);
		ByteArrayOutputStream outStream=new ByteArrayOutputStream();
		byte[] buffer=new byte[1024];
		int len=0;
		while((len=inStream.read(buffer))!=-1){
			outStream.write(buffer,0,len);//���ֽ������е�����д�뵽�������
		}
		byte[] data=outStream.toByteArray();//��������е��ֽ����ݴ��뵽�ֽ�������
		return new String(data);//���ֽ�����ת�����ַ���
	}
}
