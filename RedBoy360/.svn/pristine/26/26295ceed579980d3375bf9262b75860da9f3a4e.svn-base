package com.shopping.redboy.ViewManager;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Observer;

import android.R.integer;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.shopping.redboy.R;
import com.shopping.redboy.annotation.Category;
import com.shopping.redboy.view.BaseView;
import com.shopping.redboy.view.FirstView;
import com.shopping.redboy.view.HomeView;
import com.shopping.redboy.view.MoreView;
import com.shopping.redboy.view.SearchView;
import com.shopping.redboy.view.SecondView;
import com.shopping.redboy.view.SsuccessView;
import com.shopping.redboy.view.SubmitTallyView;
import com.shopping.redboy.view.categoryDetail.ClassificationListView1;

public class BottomManager implements OnClickListener, Observer {
	private Map<Integer, myHolder> map = new HashMap<Integer, BottomManager.myHolder>();

	private static BottomManager manager = new BottomManager();
	private BottomManager() {}
	public static BottomManager getInstance() {
		return manager;
	}

	public void init(Activity activity) {
		initMap(activity);
		setlistener();
	}
	
	private void initMap(Activity activity) {
		map.put(R.id.imgHome,
				new myHolder((ImageView) activity.findViewById(R.id.imgHome),
						HomeView.class, R.drawable.bar_home_normal,
						R.drawable.bar_home_selected));
		map.put(R.id.imgClassify,
				new myHolder((ImageView) activity.findViewById(R.id.imgClassify),
						ClassificationListView1.class, R.drawable.bar_class_normal,
						R.drawable.bar_class_selected));
		map.put(R.id.imgSearch,
				new myHolder((ImageView) activity.findViewById(R.id.imgSearch),
						SearchView.class, R.drawable.bar_search_normal,
						R.drawable.bar_search_selected));
		map.put(R.id.imgShoppingCar,
				new myHolder((ImageView) activity.findViewById(R.id.imgShoppingCar),
						SubmitTallyView.class, R.drawable.bar_shopping_normal,
						R.drawable.bar_shopping_selected));
		map.put(R.id.imgMore,
				new myHolder((ImageView) activity.findViewById(R.id.imgMore),
						MoreView.class, R.drawable.bar_more_normal,
						R.drawable.bar_more_selected));
	}

	private void setlistener() {
		for (Entry<Integer, myHolder> item : map.entrySet()) {
			item.getValue().view.setOnClickListener(this);
		}
	}

	@Override
	public void onClick(View v) {
		System.out.println(map.get(v.getId()).clazz);
		UIManager.getInstance().changeView(map.get(v.getId()).clazz);
	}

	private void changeImageViewStatus() {
		for (Entry<Integer, myHolder> item : map.entrySet()) {
			item.getValue().view.setBackgroundResource(item.getValue().normalID);
		}
	}

	@Override
	public void update(Observable observable, Object data) {
		BaseView currentview = UIManager.getInstance().getCurrentview();
		Category category = currentview.getClass().getAnnotation(Category.class);
		int id = category.id();
		changeImageViewStatus();
		map.get(id).view.setBackgroundResource(map.get(id).selectedID);
	}

	private class myHolder {
		public myHolder(ImageView view, Class<? extends BaseView> clazz,
				int normalID, int selectedID) {
			this.view = view;
			this.clazz = clazz;
			this.normalID = normalID;
			this.selectedID = selectedID;
		}

		ImageView view;
		Class<? extends BaseView> clazz;
		int normalID;
		int selectedID;
	}

}
