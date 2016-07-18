package com.bin.pulldownrefresh;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bin.pulldownrefresh.view.RefreshListView;
import com.bin.pulldownrefresh.view.RefreshListView.OnRefreshListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import java.util.ArrayList;
public class MainActivity extends Activity {

	  private RefreshListView refreshListView;
	  private ArrayList<String>list=new ArrayList<String>();
	  private MyAdapter myAdapter;
	  private Handler handler=new Handler(){
	        public void handleMessage(android.os.Message msg){
	            myAdapter.notifyDataSetChanged();
	            refreshListView.completeRefresh();
	        }
	    };
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        initView();
	        initData();
	    }
	    private void initView(){

	        setContentView(R.layout.activity_main);
	        refreshListView=(RefreshListView)findViewById(R.id.refreshListView);
	    }
	    private void initData(){
	        for(int i=0;i<15;i++){
	            list.add("listViewԭ��������"+i);
	        }
	        myAdapter=new MyAdapter();
	        refreshListView.setAdapter(myAdapter);

	        refreshListView.setOnRefreshListener(new OnRefreshListener() {
	            @Override
	            public void onPullRefresh() {
	                //��Ҫ������������������ݣ�Ȼ�����UI
	                requestDataFromServer(false);
	            }

	            @Override
	            public void onLoadingMore() {
	                requestDataFromServer(true);
	            }
	        });
	    }
	    /**
	     * ģ�����������������
	     */
	    private void requestDataFromServer(final boolean isLoadingMore){
	        new Thread(){
	            public void run() {
	                SystemClock.sleep(3000);//ģ�������������һ��ʱ�䳤��

	                if(isLoadingMore){
	                    list.add("���ظ��������-1");
	                    list.add("���ظ��������-2");
	                    list.add("���ظ��������-3");
	                }else {
	                    list.add(0, "����ˢ�µ�����");// ��0��λ���ϲ���һ������
	                }

	                //��UI�̸߳���UI
	                handler.sendEmptyMessage(0);
	            };
	        }.start();
	    }
	    class MyAdapter extends BaseAdapter{

	        @Override
	        public int getCount() {
	            return list.size();
	        }

	        @Override
	        public long getItemId(int position) {
	            return 0;
	        }

	        @Override
	        public Object getItem(int position) {
	            return null;
	        }

	        @Override
	        public View getView(int position, View convertView, ViewGroup parent) {
	            TextView textView=new TextView(MainActivity.this);
	            textView.setPadding(20, 20, 20, 20);
	            textView.setTextSize(18);
	            textView.setText(list.get(position));
	            return  textView;
	        }
	    }
}
