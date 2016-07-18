package com.jayqqaa12.news.ui;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import android.os.Bundle;
import android.widget.ImageView;

import com.jayqqaa12.abase.core.fragment.AFragment;
import com.jayqqaa12.news.R;

@EFragment(R.layout.image)
public class PageFragment extends AFragment
{
	@ViewById
	ImageView imageview;

	public static PageFragment_ newInstance(int resId)
	{
		PageFragment_ newFragment = new PageFragment_();
		Bundle bundle = new Bundle();
		bundle.putInt("icon", resId);
		newFragment.setArguments(bundle);

		return newFragment;
	}

	
	
	@AfterViews
	public void init(){
		
		imageview.setImageResource(getArguments().getInt("icon"));

		
	}

}
