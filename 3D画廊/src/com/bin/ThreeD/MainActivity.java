package com.bin.ThreeD;

import com.bin.ThreeD.view.CustomGallery;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery.LayoutParams;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private int[] imagesIds={
			R.drawable.pic_1,
			R.drawable.pic_2,
			R.drawable.pic_3,
			R.drawable.pic_4,
			R.drawable.pic_5,
			R.drawable.pic_6,
			R.drawable.pic_7,
			R.drawable.pic_8,
	};
	private CustomGallery myGallery;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		myGallery = (CustomGallery) findViewById(R.id.galley);
		
		myGallery.setAdapter(new MyAdapter());
	}
	
	private final class MyAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return imagesIds.length;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView iv=null;
			if(convertView==null){
				iv=new ImageView(MainActivity.this);
			}else{
				iv=(ImageView)convertView;
			}
			Bitmap bitmap=ImageUtils.getInvertImage(MainActivity.this.getResources(),imagesIds[position]);
			iv.setImageBitmap(bitmap);
			LayoutParams params=new LayoutParams(600,800);
			iv.setLayoutParams(params);
			return iv;
		}
		
	}
}
