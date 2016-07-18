package com.shopping.redboy.ViewManager;

import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import org.apache.commons.lang3.StringUtils;

import com.shopping.redboy.R;
import com.shopping.redboy.annotation.Category;
import com.shopping.redboy.view.BaseView;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class TitleManager implements Observer{
	protected static final String TAG = "TitleManager";
	private TextView title;
	
	public TextView getTitle() {
		return title;
	}

	private Button leftbutton;
	private Button rightbutton;
	
	private static TitleManager manager = new TitleManager();
	private TitleManager(){}
	public static TitleManager getInstance(){
		return manager;
	}
	
	public void init(Activity activity){
		title = (TextView) activity.findViewById(R.id.ii_title_content);
		leftbutton = (Button) activity.findViewById(R.id.ii_title_leftbutton);
		rightbutton = (Button) activity.findViewById(R.id.ii_title_rightbutton);
		leftbutton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				BaseView currentview = UIManager.getInstance().getCurrentview();
				if(currentview instanceof ButtonClickListener){
					((ButtonClickListener)currentview).onLeftButtonClicked();
				}
			}
		});
		rightbutton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				BaseView currentview = UIManager.getInstance().getCurrentview();
				if(currentview instanceof ButtonClickListener){
					((ButtonClickListener)currentview).onRightButtonClicked();
				}
			}
		});
	}
	
	@Override
	public void update(Observable observable, Object data) {
		BaseView currentview = UIManager.getInstance().getCurrentview();
		Category annotation = currentview.getClass().getAnnotation(Category.class);
		String temptitle = annotation.title();
		String templeftbutton = annotation.leftbutton();
		String temprightbutton = annotation.rightbutton();
		
		if(StringUtils.isBlank(temptitle)){
			title.setVisibility(View.INVISIBLE);
		}else{
			title.setVisibility(View.VISIBLE);
			title.setText(temptitle);
		}
		if(StringUtils.isBlank(templeftbutton)){
			leftbutton.setVisibility(View.INVISIBLE);
		}else{
			leftbutton.setVisibility(View.VISIBLE);
			leftbutton.setText(templeftbutton);
		}
		if(StringUtils.isBlank(temprightbutton)){
			rightbutton.setVisibility(View.INVISIBLE);
		}else{
			rightbutton.setVisibility(View.VISIBLE);
			rightbutton.setText(temprightbutton);
		}
	}
	
	public interface ButtonClickListener{
		void onLeftButtonClicked();
		void onRightButtonClicked();
	}
	
}
