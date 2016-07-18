package com.bin.clock;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TimePicker;

public class AlarmView extends LinearLayout {

	private ListView lv;
	private Button btn;
	private ArrayAdapter<AlarmData> adapter;
	private static final String KEY_ALARM_LIST="alarmlsit";
	private AlarmManager alarmManager;
	private AlarmData data;
	public AlarmView(Context context) {
		super(context);
		init();
	}
	
	public AlarmView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}


	public AlarmView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}
	
	private void init(){
		alarmManager=(AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);//拿到闹钟服务
	}
	//不可见与可见状态切换时掉用
	@Override
	protected void onVisibilityChanged(View changedView, int visibility) {
		super.onVisibilityChanged(changedView, visibility);
		
	}
	//界面加载完成时调用
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		lv=(ListView) findViewById(R.id.lv_alarmlist);
		
		adapter=new ArrayAdapter<AlarmView.AlarmData>(getContext(),android.R.layout.simple_list_item_1);
		lv.setAdapter(adapter);//adapter没有添加任何数据，为空
		readSaveAlarmList();//加载保存的数据
		//adapter.add(new AlarmData(System.currentTimeMillis()));//添加的是一个对象,显示出来的是该对象toString（）方法返回值
		btn=(Button) findViewById(R.id.btn_addalarm);
		btn.setOnClickListener(new View.OnClickListener() {		
			@Override
			public void onClick(View v) {
				addAlarm();
			}
		});
		
		//item的长按监听
		lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
				
				AlertDialog.Builder builder=new AlertDialog.Builder(getContext()).setTitle("操作选项")
						.setItems(new String[]{"删除"}, new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								switch(which){
								case 0:
									removeAlarm(position);
									break;
								default:
									break;
								}
							}
						}).setNegativeButton("取消",null);
					builder.show();
				
				return true;
			}
		
			
		
		});
	}
	
	/**
	 * 添加闹钟数据
	 */
	public void addAlarm(){
		Calendar c=Calendar.getInstance();
		//显示设置时间的对话框
		new TimePickerDialog(getContext(),new TimePickerDialog.OnTimeSetListener() {
			

			//设置时间时调用该方法
			@Override
			public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
				//设置的日期
				Calendar calendar=Calendar.getInstance();
				calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
				calendar.set(Calendar.MINUTE, minute);
				calendar.set(Calendar.SECOND,0);//秒数清零
				calendar.set(Calendar.MILLISECOND, 0);
				//当前日期
				Calendar currentTime=Calendar.getInstance();
				if(calendar.getTimeInMillis()<=currentTime.getTimeInMillis()){//设置的时间比当前时间小 
					calendar.setTimeInMillis(calendar.getTimeInMillis()+24*60*60*1000);//将设置的时间向后推一天
				}
				data = new AlarmData(calendar.getTimeInMillis());
				adapter.add(data);//将设置的时间添加到adapter,并没有保存，要用sharedPreference
				//闹钟，到AlarmReceiver广播接受者中处理
				alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),5*60*1000,
										PendingIntent.getBroadcast(getContext(), data.getId(), //请求码(用id作为请求码)
												new Intent(getContext(),AlarmReceiver.class),
												0));//标记
				saveAlarmList();//保存到sharedPreference
			}
		},c.get(Calendar.HOUR_OF_DAY),c.get(Calendar.MINUTE),true).show();
	}
	
	/**
	 * 将添加的闹钟数据保存到本地
	 */
	private void saveAlarmList(){
		Editor editor=getContext().getSharedPreferences(AlarmView.class.getName(), Context.MODE_PRIVATE).edit();
		
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<adapter.getCount();i++){//拿到adapter中的对象，再拿到对象的time添加到sb中
			sb.append(adapter.getItem(i).getTime()).append(",");
		}
		if(sb.length()>1){
			editor.putString(KEY_ALARM_LIST, sb.toString().substring(0, sb.length()-1));//将最后的“,”去掉
			editor.commit();//提交
		}
			
		editor.commit();//提交
	}
	
	/**
	 * 从本地读取保存的闹钟数据
	 */
	private void readSaveAlarmList(){
		SharedPreferences sp=getContext().getSharedPreferences(AlarmView.class.getName(),Context.MODE_PRIVATE);
		
		String content=sp.getString(KEY_ALARM_LIST,null);//没有就返回null
		if(content!=null){
			String[] timeString=content.split(",");//数据的切割
			for(String string : timeString){
				adapter.add(new AlarmData(Long.parseLong(string)));//将数据格式话后添加到adapter中
			}
		}
	}
	
	/**删除一条 闹钟数据
	 * @param position
	 */
	private void removeAlarm(int position){
		adapter.remove(adapter.getItem(position));
		//删除后保存一下
		saveAlarmList();
		//取消闹钟
		alarmManager.cancel(PendingIntent.getBroadcast(getContext(),data.getId(),//设置闹钟时的请求码
				new Intent(getContext(),AlarmReceiver.class),0));
	}
	/**
	 * 闹钟数据的封装
	 * @author DengLibin
	 *
	 */
	private static class AlarmData{
		private Calendar date;
		private int id;
		private long time=0;
		private String timeLable="";
		public AlarmData(long time){
			this.time=time;
			date=Calendar.getInstance();
			date.setTimeInMillis(time);
			timeLable=date.get(Calendar.HOUR_OF_DAY)+":"+date.get(Calendar.MINUTE);
			//对字符串进行格式化，月份从0开始的，所以要加1
			timeLable=String.format("%d月%d日 %d:%d",date.get(Calendar.MONTH)+1,
									date.get(Calendar.DAY_OF_MONTH),date.get(Calendar.HOUR_OF_DAY),date.get(Calendar.MINUTE));
		}
		public int getId(){
			return (int) time/(1000*60);
		}
		public long getTime(){
			return this.time;
		}
		public String getTimeLable(){
			return timeLable;
		}
		@Override
		public String toString() {
			return  timeLable;
		}
		
	}
}
