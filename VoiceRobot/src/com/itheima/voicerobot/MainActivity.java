package com.itheima.voicerobot;

import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;
import com.itheima.voicerobot.VoiceBean.WSBean;

public class MainActivity extends Activity {

	private ListView lvList;

	private ArrayList<ChatBean> mChatList = new ArrayList<ChatBean>();

	private ChatAdapter mAdapter;

	private String[] mMMAnswers = new String[] { "Լ��?", "����!", "��Ҫ��Ҫ��!",
			"�������һ����!", "Ư����?" };

	private int[] mMMImageIDs = new int[] { R.drawable.p1, R.drawable.p2,
			R.drawable.p3, R.drawable.p4 };


	StringBuffer mTextBuffer = new StringBuffer();
	private RecognizerDialogListener recognizerDialogListener = new RecognizerDialogListener() {

		@Override
		public void onResult(RecognizerResult results, boolean isLast) {
			// System.out.println(results.getResultString());
			// System.out.println("isLast=" + isLast);
			
			//�������������json��ʽ���ַ�����
			String text = parseData(results.getResultString());
			mTextBuffer.append(text);

			if (isLast) {// �Ự����
				String finalText = mTextBuffer.toString();
				mTextBuffer = new StringBuffer();// ����buffer
				System.out.println("���ս��:" + finalText);
				mChatList.add(new ChatBean(finalText, true, -1));//������������ݵ�������
				//Ĭ�ϵĻش�����
				String answer = "û����";
				int imageId = -1;
				
				//�������������������ûش�����
				if (finalText.contains("���")) {
					answer = "��Һ�,������ĺ�!";
				} else if (finalText.contains("����˭")) {
					answer = "�������С����!";
				} else if (finalText.contains("�����ǵػ�")) {
					answer = "С����Ģ��";
					imageId = R.drawable.m;
				}else if(finalText.contains("����Ϸ")){
					answer="��Ҫ��������Ϸ��Ҫ�ú�ѧϰ��";
				} else if(finalText.contains("�Ĵ�")){
					answer="�Ĵ��ˣ��۾��⣬�����ϰ�ߣ���������";
				} else if (finalText.contains("��Ů")) {
					Random random = new Random();
					int i = random.nextInt(mMMAnswers.length);
					int j = random.nextInt(mMMImageIDs.length);
					answer = mMMAnswers[i];
					imageId = mMMImageIDs[j];
				}
			

				mChatList.add(new ChatBean(answer, false, imageId));// ��ӻش�����
				mAdapter.notifyDataSetChanged();// ˢ��listview
				
				lvList.setSelection(mChatList.size() - 1);// ��λ�����һ��

				read(answer);
			}

		}

		@Override
		public void onError(SpeechError arg0) {

		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		lvList = (ListView) findViewById(R.id.lv_list);
		mAdapter = new ChatAdapter();

		lvList.setAdapter(mAdapter);

		// ��ʼ����������
		SpeechUtility.createUtility(this, SpeechConstant.APPID + "=54b8bca3");
	}


	/**
	 * ��������
	 */
	public void read(String text) {
		SpeechSynthesizer mTts = SpeechSynthesizer
				.createSynthesizer(this, null);

		mTts.setParameter(SpeechConstant.VOICE_NAME, "vixr");
		mTts.setParameter(SpeechConstant.SPEED, "50");
		mTts.setParameter(SpeechConstant.VOLUME, "80");
		mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD);

		mTts.startSpeaking(text, null);
	}

	/**
	 * �����ť����ʼ����ʶ��
	 * 
	 * @param view
	 */
	public void startListen(View view) {
		RecognizerDialog iatDialog = new RecognizerDialog(this, null);

		// 2.������д������������ƴ�Ѷ��MSC API�ֲ�(Android)��SpeechConstant��
		iatDialog.setParameter(SpeechConstant.DOMAIN, "iat");
		iatDialog.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
		iatDialog.setParameter(SpeechConstant.ACCENT, "mandarin");

		iatDialog.setListener(recognizerDialogListener);

		iatDialog.show();
	}

	class ChatAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return mChatList.size();
		}

		@Override
		public ChatBean getItem(int position) {
			return mChatList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = View.inflate(MainActivity.this,
						R.layout.list_item, null);

				holder.tvAsk = (TextView) convertView.findViewById(R.id.tv_ask);
				holder.tvAnswer = (TextView) convertView
						.findViewById(R.id.tv_answer);
				holder.llAnswer = (LinearLayout) convertView
						.findViewById(R.id.ll_answer);
				holder.ivPic = (ImageView) convertView
						.findViewById(R.id.iv_pic);

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			ChatBean item = getItem(position);

			if (item.isAsker) {// ��������
				holder.tvAsk.setVisibility(View.VISIBLE);
				holder.llAnswer.setVisibility(View.GONE);

				holder.tvAsk.setText(item.text);
			} else {
				holder.tvAsk.setVisibility(View.GONE);
				holder.llAnswer.setVisibility(View.VISIBLE);
				holder.tvAnswer.setText(item.text);
				if (item.imageId != -1) {// ��ͼƬ
					holder.ivPic.setVisibility(View.VISIBLE);
					holder.ivPic.setImageResource(item.imageId);
				} else {
					holder.ivPic.setVisibility(View.GONE);
				}
			}

			return convertView;
		}

	}

	static class ViewHolder {
		public TextView tvAsk;
		public TextView tvAnswer;
		public LinearLayout llAnswer;
		public ImageView ivPic;
	}

	/**
	 * ������������
	 * 
	 * @param resultString
	 */
	protected String parseData(String resultString) {
		Gson gson = new Gson();
		VoiceBean bean = gson.fromJson(resultString, VoiceBean.class);
		ArrayList<WSBean> ws = bean.ws;

		StringBuffer sb = new StringBuffer();
		for (WSBean wsBean : ws) {
			String text = wsBean.cw.get(0).w;
			sb.append(text);
		}

		return sb.toString();
	}

}
