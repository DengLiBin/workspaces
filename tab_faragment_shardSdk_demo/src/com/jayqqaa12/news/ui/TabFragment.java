package com.jayqqaa12.news.ui;

import java.util.ArrayList;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.jayqqaa12.abase.core.fragment.AFragment;
import com.jayqqaa12.abase.core.fragment.AFragmentPagerAdapter;
import com.jayqqaa12.news.R;

@EFragment(R.layout.fragment)
public class TabFragment extends AFragment  
{
	@ViewById
	ViewPager vp;

	@ViewById(R.id.iv_bottom_line)
	ImageView iv_line;

	private ArrayList<Fragment> fragmentsList = new ArrayList<Fragment>();

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		Fragment activityfragment = PageFragment.newInstance(R.drawable.xianjian01);
		Fragment groupFragment = PageFragment.newInstance(R.drawable.xianjian02);
		Fragment friendsFragment = PageFragment.newInstance(R.drawable.xianjian03);
		Fragment f4 = PageFragment.newInstance(R.drawable.xianjian03);

		fragmentsList.add(activityfragment);
		fragmentsList.add(groupFragment);
		fragmentsList.add(friendsFragment);
		fragmentsList.add(f4);
		
	}

	@AfterViews
	public void init()
	{
		vp.setAdapter(new AFragmentPagerAdapter(getChildFragmentManager(), fragmentsList));
		vp.setOnPageChangeListener( new APageChangeListener(getActivity(), fragmentsList.size(), iv_line));
		
	}

 

	
}
