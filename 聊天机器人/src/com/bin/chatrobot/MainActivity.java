package com.bin.chatrobot;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.bin.chatrobot.domain.Chat;
import com.bin.chatrobot.service.ChatService;
import com.bin.chatrobot.service.DBOpenHelper;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {
	private TextView tv;
	private ProgressBar bar;
	public int i=1;
    public int j=1;
	Handler handler=new Handler(){
		public void handleMessage(android.os.Message msg) {
			bar.setProgress(msg.what);
			if(msg.what==bar.getMax()){
				tv.setText("数据加载完成，开始体验吧！");
			}
		};
	};

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);	
		tv=(TextView) findViewById(R.id.tv);
		bar=(ProgressBar) findViewById(R.id.bar);
		//DBOpenHelper dbOpenHelper=new DBOpenHelper(this);
		//dbOpenHelper.getWritableDatabase();//创建数据库文件chat.db
		//this.deleteDatabase("chat1.db");
		//this.deleteDatabase("chat2.db");
		
		
		final Context context=getApplicationContext();
		
		new Thread(){
			public void run() {
				ChatService service=new ChatService(context);
				try {
				        String fileNames[] = context.getAssets().list("chat.txt");//获取assets目录下的所有文件及目录名
				         
				        	InputStream is = context.getAssets().open("chat.txt");
				            BufferedReader br=new BufferedReader(new InputStreamReader(is));
				            String line=null;
				            String question=null;
				            String answer=null;		            
				            while((line=br.readLine())!=null){	            	
				            	if(i%3==1){		            		
				            		question=line;
				            	}else if(i%3==2){
				            		answer=line;
				            		Chat chat=new Chat(j++,question,answer);
				            		service.save(chat);
				            	}				            	
				            	Message msg=new Message();
								
								msg.what=i;
								handler.sendMessage(msg);
								i++;
				            }
				        
				    } catch (Exception e) {
				        // TODO Auto-generated catch block
				        e.printStackTrace();
				        
				    }                           
			}
							
		}.start();
		
	}
	
	
}




