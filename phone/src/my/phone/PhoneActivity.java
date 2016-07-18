package my.phone;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PhoneActivity extends Activity {
    /** Called when the activity is first created. */
	EditText mobileText;//�ı���
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);//��ʾ����
       //��ȡ���ı������
	   mobileText=(EditText) findViewById(R.id.mobileNumber);
        //��ȡ�����Ž����Button����
        Button button=(Button) this.findViewById(R.id.button);
        //ΪButton���һ�����������
        button.setOnClickListener(new ButtonClickListener());
    }
    //����һ�� �������ڲ��࣬��дonClick(View v)����
    private final class ButtonClickListener implements View.OnClickListener{

		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			//��ȡ�ı����е�����(�绰����)
			String mobileNumber=mobileText.getText().toString();
			//������ͼ����
			Intent intent=new Intent();
			//ƥ����ͼ������
			intent.setAction("android.intent.action.CALL");
			//intent.addCategory("android.intent.category.DEFAULT");
			intent.setData(Uri.parse("tel:"+mobileNumber));
			//����Activity
			startActivity(intent);//�����ڲ����Զ�ΪIntent������android.intent.category.DEFAULT
		}
    	
    }
}