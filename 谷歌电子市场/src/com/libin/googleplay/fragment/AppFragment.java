package com.libin.googleplay.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

//�̳�v4���µ�Fragment�����ܼ��ݵͰ汾
public class AppFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		TextView view=new TextView(getActivity());
		view.setText("����AppFragment");
		return view;
	}
}
