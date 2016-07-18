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
		alarmManager=(AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);//�õ����ӷ���
	}
	//���ɼ���ɼ�״̬�л�ʱ����
	@Override
	protected void onVisibilityChanged(View changedView, int visibility) {
		super.onVisibilityChanged(changedView, visibility);
		
	}
	//����������ʱ����
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		lv=(ListView) findViewById(R.id.lv_alarmlist);
		
		adapter=new ArrayAdapter<AlarmView.AlarmData>(getContext(),android.R.layout.simple_list_item_1);
		lv.setAdapter(adapter);//adapterû������κ����ݣ�Ϊ��
		readSaveAlarmList();//���ر��������
		//adapter.add(new AlarmData(System.currentTimeMillis()));//��ӵ���һ������,��ʾ�������Ǹö���toString������������ֵ
		btn=(Button) findViewById(R.id.btn_addalarm);
		btn.setOnClickListener(new View.OnClickListener() {		
			@Override
			public void onClick(View v) {
				addAlarm();
			}
		});
		
		//item�ĳ�������
		lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
				
				AlertDialog.Builder builder=new AlertDialog.Builder(getContext()).setTitle("����ѡ��")
						.setItems(new String[]{"ɾ��"}, new DialogInterface.OnClickListener() {
							
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
						}).setNegativeButton("ȡ��",null);
					builder.show();
				
				return true;
			}
		
			
		
		});
	}
	
	/**
	 * �����������
	 */
	public void addAlarm(){
		Calendar c=Calendar.getInstance();
		//��ʾ����ʱ��ĶԻ���
		new TimePickerDialog(getContext(),new TimePickerDialog.OnTimeSetListener() {
			

			//����ʱ��ʱ���ø÷���
			@Override
			public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
				//���õ�����
				Calendar calendar=Calendar.getInstance();
				calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
				calendar.set(Calendar.MINUTE, minute);
				calendar.set(Calendar.SECOND,0);//��������
				calendar.set(Calendar.MILLISECOND, 0);
				//��ǰ����
				Calendar currentTime=Calendar.getInstance();
				if(calendar.getTimeInMillis()<=currentTime.getTimeInMillis()){//���õ�ʱ��ȵ�ǰʱ��С 
					calendar.setTimeInMillis(calendar.getTimeInMillis()+24*60*60*1000);//�����õ�ʱ�������һ��
				}
				data = new AlarmData(calendar.getTimeInMillis());
				adapter.add(data);//�����õ�ʱ����ӵ�adapter,��û�б��棬Ҫ��sharedPreference
				//���ӣ���AlarmReceiver�㲥�������д���
				alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),5*60*1000,
										PendingIntent.getBroadcast(getContext(), data.getId(), //������(��id��Ϊ������)
												new Intent(getContext(),AlarmReceiver.class),
												0));//���
				saveAlarmList();//���浽sharedPreference
			}
		},c.get(Calendar.HOUR_OF_DAY),c.get(Calendar.MINUTE),true).show();
	}
	
	/**
	 * ����ӵ��������ݱ��浽����
	 */
	private void saveAlarmList(){
		Editor editor=getContext().getSharedPreferences(AlarmView.class.getName(), Context.MODE_PRIVATE).edit();
		
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<adapter.getCount();i++){//�õ�adapter�еĶ������õ������time��ӵ�sb��
			sb.append(adapter.getItem(i).getTime()).append(",");
		}
		if(sb.length()>1){
			editor.putString(KEY_ALARM_LIST, sb.toString().substring(0, sb.length()-1));//�����ġ�,��ȥ��
			editor.commit();//�ύ
		}
			
		editor.commit();//�ύ
	}
	
	/**
	 * �ӱ��ض�ȡ�������������
	 */
	private void readSaveAlarmList(){
		SharedPreferences sp=getContext().getSharedPreferences(AlarmView.class.getName(),Context.MODE_PRIVATE);
		
		String content=sp.getString(KEY_ALARM_LIST,null);//û�оͷ���null
		if(content!=null){
			String[] timeString=content.split(",");//���ݵ��и�
			for(String string : timeString){
				adapter.add(new AlarmData(Long.parseLong(string)));//�����ݸ�ʽ������ӵ�adapter��
			}
		}
	}
	
	/**ɾ��һ�� ��������
	 * @param position
	 */
	private void removeAlarm(int position){
		adapter.remove(adapter.getItem(position));
		//ɾ���󱣴�һ��
		saveAlarmList();
		//ȡ������
		alarmManager.cancel(PendingIntent.getBroadcast(getContext(),data.getId(),//��������ʱ��������
				new Intent(getContext(),AlarmReceiver.class),0));
	}
	/**
	 * �������ݵķ�װ
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
			//���ַ������и�ʽ�����·ݴ�0��ʼ�ģ�����Ҫ��1
			timeLable=String.format("%d��%d�� %d:%d",date.get(Calendar.MONTH)+1,
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
