package com.bin.zhbj.base;

import java.util.ArrayList;

import com.bin.zhbj.NewsDetailActivity;
import com.bin.zhbj.R;
import com.bin.zhbj.domain.NewsData;
import com.bin.zhbj.domain.NewsData.NewsTabData;
import com.bin.zhbj.domain.NewsDetailData;
import com.bin.zhbj.domain.NewsDetailData.TabNewsData;
import com.bin.zhbj.domain.NewsDetailData.TopNewsData;
import com.bin.zhbj.global.GlobalContacts;
import com.bin.zhbj.utils.CacheUtils;
import com.bin.zhbj.utils.PrefUtils;
import com.bin.zhbj.utils.bitmap.MyBitmapUtils;
import com.bin.zhbj.view.RefreshListView;
import com.bin.zhbj.view.RefreshListView.OnRefreshListener;
import com.bin.zhbj.view.TopNewsViewPager;
import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.viewpagerindicator.CirclePageIndicator;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * ҳǩ����ҳ
 * @author Administrator
 *
 */
public class TabDetailPager extends BaseMenuDetailPager implements OnPageChangeListener {
	private NewsTabData mTabData;
	private TextView text;
	private String mUrl;
	private NewsDetailData mNewsDetailData;
	private TopNewsViewPager mViewPager;
	private BitmapUtils utils;
	private CirclePageIndicator mIndicator;//viewpager�л�ʱ��λ��ָʾ����ָʾ��ǰҳ��
	private TextView tvTitle;//ͷ������ҳ����
	private RefreshListView lvList;//����ҳ�б�
	ArrayList<TabNewsData> mNewsList;//����ҳ�б���
	private String mMoreUrl;//����ҳ���ַ
	private NewsAdapter mNewsAdapter;
	
	private Handler mHandler;
	public TabDetailPager(Activity activity,NewsTabData newsTabData) {
		super(activity);
		mTabData=newsTabData;
		mUrl=GlobalContacts.URL+mTabData.url;
		System.out.println("URL:"+mUrl);
	}

	@Override
	public View initView() {
		View view=View.inflate(mActivity, R.layout.tab_detail_pager, null);
		//����ͷ����
		View headView=View.inflate(mActivity, R.layout.list_header_topnews, null);
		mViewPager=(TopNewsViewPager) headView.findViewById(R.id.vp_news);
		tvTitle=(TextView) headView.findViewById(R.id.tv_title);
		mIndicator=(CirclePageIndicator) headView.findViewById(R.id.indicator);//ViewPager��ǰҳ��ָʾ�����൱������ҳ���Ǽ���С�㣩
		lvList=(RefreshListView) view.findViewById(R.id.lv_list);
		lvList.addHeaderView(headView);//�ڸ�listViewǰ�����һ��ͷView,���»���ListViewʱ��ͷViewҲ�Ử��
		//���ü��������÷����Ѿ���RefreshListView������ж����ˣ�������Ҳ�����ˣ���Ҫ��д�������ķ�����
		lvList.setOnRefreshListener(new OnRefreshListener(){

			@Override
			public void onRefresh() {
				getDataFromServer();//�ٴδӷ�������ȡ����
			}

			@Override
			public void onLoadingMore() {
				if(mMoreUrl==null){
					Toast.makeText(mActivity, "���һҳ��", Toast.LENGTH_SHORT).show();
					lvList.onRefreshComplete(); //���ؽŲ���
					
					
				}else{
					getMoreDataFromServer();
				}
			}
			
		});
		
		lvList.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				System.out.println("�������"+position);
				//���������Item��id��¼������
				String ids=PrefUtils.getString(mActivity, "read_ids", "");//�����ھͷ��ؿ��ַ���
				String readId=mNewsList.get(position).id;
				System.out.println("readId:"+readId);
				System.out.println("ids:"+ids);
				if(!ids.contains(readId)){//Ϊ�˱����ظ���ӣ���Ҫ�ж�һ��
					ids=ids+readId+",";
					PrefUtils.setString(mActivity, "read_ids", ids);
				}
				TextView tvTitle=(TextView) view.findViewById(R.id.tv_title);//�����View���Ǳ�����Ĳ��ֽ���
				tvTitle.setTextColor(Color.GRAY);//�������Item��title��ɫ��Ϊ��ɫ������Ҫˢ������adapter���������
				//mNewsAdapter.notifyDataSetChanged();//ˢ��Adapter,�����µ���Adapter�ļ�������
				
				Intent intent=new Intent();
				intent.setClass(mActivity, NewsDetailActivity.class);
				intent.putExtra("url", mNewsList.get(position).url);
				mActivity.startActivity(intent);//��ת����������ҳ
			}
			
		});
		return view;
	}
	
	
//NewsMenuDetailPager�е���
	@Override
	public void initData() {
		String cache =CacheUtils.getCache(mActivity, mUrl);
		if(!TextUtils.isEmpty(cache)){//���������ڣ�ֱ�ӽ������ݣ�����Ҫ��������
			System.out.println("�ӱ��ػ����������");
			parseData(cache, false);
		}
			getDataFromServer();//�������ȡ���ݣ����ܱ������޻��涼�������ȡ���ݣ�	
    }
   
		
	/**
	 * �ӷ�������ȡ����
	 */
	private void getDataFromServer(){
		HttpUtils utils=new HttpUtils();
		utils.send(HttpMethod.GET, mUrl, new RequestCallBack<String>() {
			//���ʳɹ�
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String result=(String)responseInfo.result;
				System.out.println("ҳǩ����ҳ���ؽ����"+result);
				CacheUtils.setCache(mActivity,mUrl,result);
				parseData(result,false);//���Ǽ��ظ�������
				
				lvList.onRefreshComplete();//��ȡ������ɣ�����ˢ�¿ؼ�
			}
			//����ʧ��
			@Override
			public void onFailure(HttpException error, String msg) {
				Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT).show();
				error.printStackTrace();
				
				lvList.onRefreshComplete();
			}
				
		});
	}
	/**
	 * ���ظ�������
	 */
	private void getMoreDataFromServer(){
		HttpUtils utils=new HttpUtils();
		utils.send(HttpMethod.GET, mMoreUrl, new RequestCallBack<String>() {
			//���ʳɹ�
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String result=(String)responseInfo.result;
				System.out.println("ҳǩ����ҳ���ؽ����"+result);
				parseData(result,true);//���ظ�������
				
				lvList.onRefreshComplete();//��ȡ������ɣ�����ˢ�¿ؼ�
			}
			//����ʧ��
			@Override 
			public void onFailure(HttpException error, String msg) {
				Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT).show();
				error.printStackTrace();
				
				lvList.onRefreshComplete();
			}
				
		});
	}
	/**
	 * ��Gson��������
	 * @param string
	 */
	protected void parseData(String result,boolean isMore) {//isMore��ʾ�Ƿ��Ǽ�����һҳ
		Gson gson=new Gson();
		mNewsDetailData = gson.fromJson(result, NewsDetailData.class);
		
		
		System.out.println("ҳǩ����ҳ�������:"+mNewsDetailData);
		System.out.println("ҳǩ����:"+mNewsList);
		
		//mViewPager.setOnPageChangeListener(this);
		
		String more=mNewsDetailData.data.more;//����ҳ���ַ
		if(!TextUtils.isEmpty(more)){//�ж��ַ����Ƿ��ǿպͿ��ַ���
			mMoreUrl = GlobalContacts.URL+more;
		}else{
			mMoreUrl=null;
		}
		if(isMore==false){
			mNewsList=mNewsDetailData.data.news;
			if(mNewsDetailData.data.topnews!=null){
				mViewPager.setAdapter(new TopNewsAdapter());
				mIndicator.setViewPager(mViewPager);
				mIndicator.setSnap(true);//������ʾ
				mIndicator.setOnPageChangeListener(this);
				mIndicator.onPageSelected(0);//��ָʾ�����¶�λ����һ����
			}
			if(mNewsList!=null){
				mNewsAdapter = new NewsAdapter();
				lvList.setAdapter(mNewsAdapter);
			}
			
			//ViewPager�Զ��ֲ�
			if(mHandler==null){
				mHandler=new Handler(){
					public void handleMessage(android.os.Message msg){//������Ϣ
						System.out.println("handler.....");
						int currentItem=mViewPager.getCurrentItem();
						if(currentItem<mNewsDetailData.data.topnews.size()-1){
							currentItem++;
						}else{
							currentItem=0;
						}
						
						mViewPager.setCurrentItem(currentItem);
						mHandler.sendEmptyMessageDelayed(0, 3000);//�ӳ�3���ٷ���һ����Ϣ��ѭ����
					}
				};
				mHandler.sendEmptyMessageDelayed(0, 3000);//�ӳ�3�뷢��һ����Ϣ
				
			}
		}else{//������һҳ�������� ׷�ӵ�ԭ���ļ�����
			ArrayList<TabNewsData> moreNews=mNewsDetailData.data.news;
			mNewsList.addAll(moreNews);
			mNewsAdapter.notifyDataSetChanged();//ˢ��adapter
		}
		
		
		
	}
	
	class TopNewsAdapter extends PagerAdapter{
		public TopNewsAdapter(){
			utils = new BitmapUtils(mActivity);
		}
		@Override
		public int getCount() {
			return mNewsDetailData.data.topnews.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0==arg1;
		}
		
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			ImageView image=new ImageView(mActivity);
			//image.setImageResource(R.drawable.topnews_item_default);//Ĭ�ϵ�ͼƬ
			image.setScaleType(ScaleType.FIT_XY);//���ڿռ��С���ͼƬ
			TopNewsData topNewsData = mNewsDetailData.data.topnews.get(position);
			if(position==0){
				tvTitle.setText(topNewsData.title);
			}
			
			utils.display(image, topNewsData.topimage);//��BitmapUtils��imageView���ͼƬ��topNewsData.topimage��ͼƬ��URI
			container.addView(image);
			
			//��סViewPagerʱ���ֲ���ͣ������һ������������һ�������������õ��������Ϊ�˱����ͻ���������ֻ����һ��
			image.setOnTouchListener(new OnTouchListener(){

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					switch(event.getAction()){
					case MotionEvent.ACTION_DOWN:
						mHandler.removeCallbacksAndMessages(null);//ɾ��Handler��������Ϣ
						/*
						 * mHandler.postDelayed(new Runnable(){

							@Override
							public void run() {
								System.out.println("�ӳ�3��Ҫ�������飨�����߳��У�");
							}
							
						}, 3000);
						*/
						break;
					case MotionEvent.ACTION_CANCEL://����ȡ�����磺����ʱ������һ�£�
						mHandler.sendEmptyMessageDelayed(0, 3000);//��������Ϣ
						break;
					case MotionEvent.ACTION_UP:
						mHandler.sendEmptyMessageDelayed(0, 3000);//��������Ϣ
						break;
					
					}
					return true;
				}
				
			});
			return image;
		}
		
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View)object);
		}
		
	}
	
	/**
	 * �����б��������
	 * @author Administrator
	 *
	 */
	class NewsAdapter extends BaseAdapter{
		//private BitmapUtils utils;
		private MyBitmapUtils utils;//���Լ���BitmapUtils��ʵ�ʿ���ʱ������BitmapUtils
		public NewsAdapter(){
			//utils=new BitmapUtils(mActivity);
			utils=new MyBitmapUtils();
			//utils.configDefaultLoadingImage(R.drawable.pic_item_list_default);//����ͼƬʱ��ʾ��Ĭ��ͼƬ
		}
		@Override
		public int getCount() {
			return mNewsList.size();
		}

		@Override
		public TabNewsData getItem(int position) {
			return mNewsList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if(convertView==null){
				convertView=View.inflate(mActivity, R.layout.list_news_item,null);
				holder=new ViewHolder();
				holder.ivPic=(ImageView) convertView.findViewById(R.id.iv_pic);
				holder.tvTitle=(TextView) convertView.findViewById(R.id.tv_title);
				holder.tvDate=(TextView) convertView.findViewById(R.id.tv_date);
				
				convertView.setTag(holder);
			}else{
				holder=(ViewHolder) convertView.getTag();
			}
			TabNewsData item=getItem(position);
			holder.tvTitle.setText(item.title);
			holder.tvDate.setText(item.pubdate);
			utils.display(holder.ivPic, item.listimage);//��ImageView���ͼƬ
			
			String ids=PrefUtils.getString(mActivity, "read_ids", "");
			if(ids.contains(getItem(position).id)){//�жϸ�id��Item�Ƿ����ѱ������
				holder.tvTitle.setTextColor(Color.GRAY);
			}else{
				holder.tvTitle.setTextColor(Color.BLACK);
			}
			return convertView;
		}
		
	}
	static class ViewHolder{
		public TextView tvTitle;
		public TextView tvDate;
		public ImageView ivPic;
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		
	}

	@Override
	public void onPageSelected(int arg0) {
		TopNewsData topNewsData=mNewsDetailData.data.topnews.get(arg0);
		tvTitle.setText(topNewsData.title);
	}
}
