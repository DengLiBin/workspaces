package com.bin.zhbj;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.TextSize;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.onekeyshare.OnekeyShareTheme;

/**
 * ��������ҳ
 * @author Administrator
 *
 */
public class NewsDetailActivity extends Activity implements OnClickListener {
	private ImageButton btnBack;
	private ImageButton btnSize;
	private ImageButton btnShare;
	private WebView mWebView;
	
	private ProgressBar mProgress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_news_detail);
		
		mWebView = (WebView) findViewById(R.id.wv_web);
		btnBack = (ImageButton) findViewById(R.id.btn_back);
		btnSize = (ImageButton) findViewById(R.id.btn_size);
		btnShare = (ImageButton) findViewById(R.id.btn_share);
		mProgress=(ProgressBar) findViewById(R.id.pb_progress);
		
		btnBack.setOnClickListener(this);
		btnSize.setOnClickListener(this);
		btnShare.setOnClickListener(this);
		
		String url=getIntent().getStringExtra("url");
		WebSettings setting=mWebView.getSettings();
		setting.setJavaScriptEnabled(true);//��ʾ֧��js��Ĭ����false
		setting.setBuiltInZoomControls(true);//��ʾ�Ŵ����ΰ°�ť
		setting.setUseWideViewPort(true);//˫������
		
		mWebView.setWebViewClient(new WebViewClient(){
			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				super.onPageStarted(view, url, favicon);
				System.out.println("��ʼ������ҳ");
				mProgress.setVisibility(View.VISIBLE);
			}
			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
				System.out.println("��ҳ���ؽ���");
				mProgress.setVisibility(View.GONE);
			}
			
			//������ת�����Ӷ����ڴ˷����лص�
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				System.out.println("��תURL:"+url);
				view.loadUrl(url);//ǿ�����Լ���WebView������ҳ�����������������
				return true;
				//return super.shouldOverrideUrlLoading(view, url);
			}
		});
		
		mWebView.setWebChromeClient(new WebChromeClient(){
			
			//���ȱ仯
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				System.out.println("���ؽ��ȣ�"+newProgress);
				super.onProgressChanged(view, newProgress);
			}
			
			
			//��ȡ��ҳ����
			@Override
			public void onReceivedTitle(WebView view, String title) {
				System.out.println("���⣺"+title);
				super.onReceivedTitle(view, title);
			}
		});
		mWebView.loadUrl(url);		
		//mWebView.loadUrl("http://www.itheima.com/");//������ҳ
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.btn_back:
			finish();
			break;
		case R.id.btn_share:
			showShare();
			break;
		case R.id.btn_size:
			showChooseDialog();
			break;
		default:
			break;
		}
	}

	private int mCurrentChooseItem;// ��¼��ǰѡ�е�item, ���ȷ��ǰ
	private int mCurrentItem=2;// ��¼��ǰѡ�е�item, ���ȷ����
	/**
	 * ��ʾѡ�������С�Ի���
	 */
	private void showChooseDialog() {
		AlertDialog.Builder builder=new AlertDialog.Builder(this);
		
		String[] items=new String[]{"���������","�������","��������","С������","��С������"};
		builder.setTitle("��������");
		builder.setSingleChoiceItems(items,mCurrentItem,new DialogInterface.OnClickListener() {//Ĭ��ѡ����������
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				System.out.println("ѡ�У�"+which);
				mCurrentChooseItem=which;
			}
		});
		builder.setPositiveButton("ȷ��",new DialogInterface.OnClickListener(){

			@Override
			public void onClick(DialogInterface dialog, int which) {
				WebSettings setting=mWebView.getSettings();
				switch(mCurrentChooseItem){
				case 0:
					setting.setTextSize(TextSize.LARGEST);
					//setting.setTextZoom(20);
					break;
				case 1:
					setting.setTextSize(TextSize.LARGER);
					break;
				case 2:
					setting.setTextSize(TextSize.NORMAL);
					break;
				case 3:
					setting.setTextSize(TextSize.SMALLER);
					break;
				case 4:
					setting.setTextSize(TextSize.SMALLEST);
					break;
				}
				mCurrentItem=mCurrentChooseItem;
			}
			
		});
		builder.setNegativeButton("ȡ��", null);
		
		builder.show();
	}


	private void showShare() {
	 ShareSDK.initSDK(this);
	 OnekeyShare oks = new OnekeyShare();
	 
	 oks.setTheme(OnekeyShareTheme.SKYBLUE);//���
	 //�ر�sso��Ȩ
	 oks.disableSSOWhenAuthorize(); 

	// ����ʱNotification��ͼ�������  2.5.9�Ժ�İ汾�����ô˷���
	 //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
	 // title���⣬ӡ��ʼǡ����䡢��Ϣ��΢�š���������QQ�ռ�ʹ��
	 oks.setTitle("����");
	 // titleUrl�Ǳ�����������ӣ�������������QQ�ռ�ʹ��
	 oks.setTitleUrl("http://sharesdk.cn");
	 // text�Ƿ����ı�������ƽ̨����Ҫ����ֶ�
	 oks.setText("���Ƿ����ı�");
	 // imagePath��ͼƬ�ı���·����Linked-In�����ƽ̨��֧�ִ˲���
	 oks.setImagePath("/sdcard/test.jpg");//ȷ��SDcard������ڴ���ͼƬ
	 // url����΢�ţ��������Ѻ�����Ȧ����ʹ��
	 oks.setUrl("http://sharesdk.cn");
	 // comment���Ҷ�������������ۣ�������������QQ�ռ�ʹ��
	 oks.setComment("���ǲ��������ı�");
	 // site�Ƿ�������ݵ���վ���ƣ�����QQ�ռ�ʹ��
	 oks.setSite(getString(R.string.app_name));
	 // siteUrl�Ƿ�������ݵ���վ��ַ������QQ�ռ�ʹ��
	 oks.setSiteUrl("http://sharesdk.cn");

	// ��������GUI
	 oks.show(this);
	 }
	
	
}
