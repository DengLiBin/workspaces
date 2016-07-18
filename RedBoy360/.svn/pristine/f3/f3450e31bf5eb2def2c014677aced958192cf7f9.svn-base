package com.shopping.redboy;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.RelativeLayout;

import com.shopping.redboy.ViewManager.BottomManager;
import com.shopping.redboy.ViewManager.TitleManager;
import com.shopping.redboy.ViewManager.TitleManager.ButtonClickListener;
import com.shopping.redboy.ViewManager.UIManager;
import com.shopping.redboy.util.PromptManager;
import com.shopping.redboy.view.AccountView;
import com.shopping.redboy.view.BaseView;
import com.shopping.redboy.view.FirstView;
import com.shopping.redboy.view.HomeView;
import com.shopping.redboy.view.MyFavoriteView;
import com.shopping.redboy.view.categoryDetail.ProductDetailView;

public class MainActivity extends Activity {
	private RelativeLayout middlecontainer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.il_main);
		middlecontainer = (RelativeLayout) findViewById(R.id.ii_middle);
		init();
	}

	private void init() {
		TitleManager.getInstance().init(this);
		BottomManager.getInstance().init(this);
		UIManager.getInstance().init(this);
		UIManager.getInstance().setMiddleContainer(middlecontainer);
		UIManager.getInstance().addObserver(TitleManager.getInstance());
		UIManager.getInstance().addObserver(BottomManager.getInstance());
		UIManager.getInstance().changeView(HomeView.class);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			
			BaseView myCurrentview = UIManager.getInstance().getCurrentview();
			if(myCurrentview instanceof ProductDetailView){
				boolean showing = ((ProductDetailView)myCurrentview).getPop().isShowing();
				if(showing){
					((ProductDetailView) myCurrentview).getPop().dismiss();
					return false;
				}
			}
			
			if(myCurrentview instanceof ButtonClickListener){
				((ButtonClickListener)myCurrentview).onLeftButtonClicked();
				return false;
			}
			boolean goback = UIManager.getInstance().goback();
			if (!goback) {
				PromptManager.showExitSystem(this);
			}
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}
}
