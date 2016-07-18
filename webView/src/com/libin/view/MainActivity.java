package com.libin.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity{
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		String url=data.getExtras().getString("url");
		webView.loadUrl(url);
	}
	private final String HOME_PAGE="http://www.2345.com";
	private String currentUrl=HOME_PAGE;
	private TextView textView;
	private Button btnBack,btnForward,btnHome,btnRefresh,btnFinish;
	private WebView webView;
	private WebSettings webSetting;
	private SharedPreferences mShare;
	private RelativeLayout rlview;
	private ProgressBar progressBar;
	private View videoView;
	private long cacheSize=5l*1024l*1024l;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);//���ر�����per.onCreate(savedInstanceState);
		MainActivity.this.getWindow().requestFeature(Window.FEATURE_PROGRESS);
		setContentView(R.layout.activity_main);
		mShare=getSharedPreferences("config", Context.MODE_PRIVATE);
		initView();
		initData();
	}
	
	//�õ������ļ�
	public void initView(){
		rlview=(RelativeLayout) findViewById(R.id.rl_view);
		textView=(TextView) findViewById(R.id.tv_url);
		textView.setText("��������ַ");
		textView.setOnClickListener(new OnClickListener(){
				
			@Override
			public void onClick(View v) {
				//�õ��´�Activity�رպ󷵻ص�����
                //�ڶ�������Ϊ�����룬���Ը���ҵ�������Լ����
				Intent intent=new Intent();
				intent.putExtra("currentUrl",webView.getUrl());
				intent.setClass(MainActivity.this, UrlActivity.class);
                startActivityForResult(intent, 1);
				
			}
			
		});
		webView = (WebView) findViewById(R.id.webView);
		progressBar=(ProgressBar) findViewById(R.id.pb_progress);
		btnBack=(Button) findViewById(R.id.btn_back);
		btnBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				goBack();//������һҳ
			}
		});
		btnForward=(Button) findViewById(R.id.btn_forward);
		btnForward.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
					goForward();
			}
		});
		btnHome=(Button) findViewById(R.id.btn_home);
		btnHome.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				webView.loadUrl(HOME_PAGE);//������ҳ
				System.out.println("������ҳ");
			}
		});
		btnRefresh=(Button) findViewById(R.id.btn_refresh);
		btnRefresh.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				webView.clearCache(true);
				webView.loadUrl(getCurrrentUrl());
			}
		});
		
		btnFinish=(Button) findViewById(R.id.btn_finish);
		btnFinish.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				MainActivity.this.finish();//�˳�����
			}
		});
	}
	//��������
	public void initData(){
		
		webView.setVisibility(View.VISIBLE);
		webView.setWebChromeClient(new WebChromeClient());
		webSetting = webView.getSettings();
		webSetting.setJavaScriptEnabled(true);//�����ܹ�ִ��JavaScript�ű�
		webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
		webSetting.setPluginState(PluginState.ON);
		webSetting.setUseWideViewPort(true);
		//webSetting.setBuiltInZoomControls(true);//֧������
	
		webSetting.setDomStorageEnabled(true);
		webSetting.setDatabasePath(Environment.getExternalStorageDirectory().toString()+"/binViewDatabase");
		webSetting.setAppCacheEnabled(true);//�Ƿ�ʹ�û���
		webSetting.setAppCachePath(Environment.getExternalStorageDirectory().toString()+"/binVieCache");
		webSetting.setAppCacheMaxSize(cacheSize);
		//����֧���Ӵ�meta��ǣ���ʵ��˫�����ţ�
		webSetting.setUseWideViewPort(true);
		//������ͼģʽ����ҳ��
		webSetting.setLoadWithOverviewMode(true);
			
		webSetting.setLoadWithOverviewMode(true);
		webSetting.setUseWideViewPort(true);//���ô����ԣ�������������š�����ͼģʽ
		webSetting.setLoadWithOverviewMode(true);//��setUseWideViewPort(true)һ������ҳ����Ӧ����
		webSetting.setAppCacheEnabled(true);//�Ƿ�ʹ�û���
		webSetting.setDomStorageEnabled(true);//DOM Storage
		webView.loadUrl(currentUrl);//������ҳ
		webView.setWebViewClient(new WebViewClient(){
			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				progressBar.setVisibility(View.VISIBLE);
				currentUrl=url;
				textView.setText(url);
				super.onPageStarted(view, url, favicon);
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				currentUrl=url;
				progressBar.setVisibility(View.INVISIBLE);
				super.onPageFinished(view, url);
				
			}

			@Override
	        public boolean shouldOverrideUrlLoading(WebView view, String url) {
	            // TODO Auto-generated method stub
	               //����ֵ��true��ʱ�����ȥWebView�򿪣�Ϊfalse����ϵͳ�����������������
				currentUrl=url;
	             view.loadUrl(url);
	            return true;
	        }
		});//����web��ͼ
		
		webView.setDownloadListener((DownloadListener) new MyWebViewDownLoadListener()); //���ؼ���
		
		webView.setWebChromeClient(new WebChromeClient(){
			  // һ���ص��ӿ�ʹ�õ�����Ӧ�ó���֪ͨ��ǰҳ����Զ�����ͼ�ѱ���ְ  
	        CustomViewCallback customViewCallback;
			private FrameLayout video;  
	        // ����ȫ����ʱ��  
	        @Override  
	        public void onShowCustomView(View view, CustomViewCallback callback) {  
	            // ��ֵ��callback  
	            customViewCallback = callback;  
	            // ����webView����  
	           // webView.setVisibility(View.INVISIBLE);  
	            video = (FrameLayout) findViewById(R.id.video);  
	            // ��video�ŵ���ǰ��ͼ�� 
	            videoView=view;
	            video.addView(videoView);  
	            // ������ʾ  
	            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);  
	            // ����ȫ��  
	            setFullScreen();  
	        }  
	        // �˳�ȫ����ʱ��  
	        @Override  
	        public void onHideCustomView() {  
	            if (customViewCallback != null) {  
	                // ���ص�  
	            	
	                customViewCallback.onCustomViewHidden();  
	            }  
	            // �û���ǰ����ѡ����  
	            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER);  
	            // �˳�ȫ��  
	            video.removeView(videoView);
	            quitFullScreen();  
	            // ����WebView�ɼ�  
	            //webView.setVisibility(View.VISIBLE); 
	            
	        }  
	  
	        @Override  
	        public void onProgressChanged(WebView view, int newProgress) {  
	            super.onProgressChanged(view, newProgress);  
	        } 
			
		});
	}
	/**
	 * �������ӵļ�����
	 * @author DengLibin
	 *
	 */
	private class MyWebViewDownLoadListener implements DownloadListener{   
		 
		       @Override  
		        public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype,   
		                                    long contentLength) {              
		         
		            Uri uri = Uri.parse(url);   
		            Intent intent = new Intent(Intent.ACTION_VIEW, uri);   
		            startActivity(intent);
		       }
	}  
	
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event){
		switch(keyCode){
		case KeyEvent.KEYCODE_BACK:
			goBack();
			break;
			
		}
		return false;
	}

	 
	 
	 /**
	 * ������һҳ��û�У�����ʾ�˳������
	 */
	public void goBack(){
		 if(webView.canGoBack()){
				webView.goBack();//��WebView���˵���һ����ҳ

			}else{
				new AlertDialog.Builder(MainActivity.this).setTitle("��ʾ").
				setMessage("ȷ���˳��������").setPositiveButton("ȷ��", new DialogInterface.OnClickListener(){

					@Override
					public void onClick(DialogInterface dialog, int which) {
						webView.clearCache(true);
						MainActivity.this.finish();//�˳�����
					}
					
				}).setNegativeButton("ȡ��", null).show();	
			}
	 }
	
	public void goForward(){
		if(webView.canGoForward()){
			webView.goForward();
		}else{
			return;
		}
	}
	/**
	 * ��ַ�����õ�ǰurl
	 */
	public String getCurrrentUrl(){
		String url=webView.getUrl();
		
		return url;
	}
	@Override
	 protected void onDestroy() {
	  webView.destroy();
	  super.onDestroy();
	 }
	 @Override
	 protected void onPause() {//ʧȥ����
	  // TODO Auto-generated method stub
		 webView.reload();//���¼���һ�£����������Ƶ���˳��󻹼������ŵ�����
	  webView.pauseTimers();
	  super.onPause();
	 }
	 @Override
	 public void onResume() {
	  webView.resumeTimers();
	  super.onResume();
	 }
	 
	 /** 
	     * ����ȫ�� 
	     */  
	    private void setFullScreen() {  
	        // ����ȫ����������ԣ���ȡ��ǰ����Ļ״̬��Ȼ������ȫ��  
	        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,  
	                WindowManager.LayoutParams.FLAG_FULLSCREEN);  
	        // ȫ���µ�״̬�룺1098974464  
	        // �����µ�״̬��1098973440  
	    }  
	  
	    /** 
	     * �˳�ȫ�� 
	     */  
	    private void quitFullScreen() {  
	        // ������ǰ��Ļ״̬�Ĳ�������ȡ  
	        final WindowManager.LayoutParams attrs = getWindow().getAttributes();  
	        attrs.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);  
	        getWindow().setAttributes(attrs);  
	        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);  
	    }  
	
}
