package com.shopping.redboy.view.categoryDetail;

import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.loopj.android.image.SmartImageView;
import com.shopping.redboy.R;
import com.shopping.redboy.ViewManager.TitleManager.ButtonClickListener;
import com.shopping.redboy.ViewManager.UIManager;
import com.shopping.redboy.annotation.Category;
import com.shopping.redboy.annotation.ResID;
import com.shopping.redboy.domain.Product;
import com.shopping.redboy.engine.CategoryDetailEngine;
import com.shopping.redboy.util.BeanFactory;
import com.shopping.redboy.util.DensityUtil;
import com.shopping.redboy.util.MyAsynTask;
import com.shopping.redboy.view.BaseView;

@ResID(id=R.layout.product_detail_activity)
@Category(id=R.id.imgClassify,title="商品详情",leftbutton="返回",rightbutton="加入购物车")
public class ProductDetailView extends BaseView implements ButtonClickListener{
	@ResID(id=R.id.textProductNameValue)
	private TextView productName ;		//商品名
	
	@ResID(id=R.id.textProductIdValue)
	private TextView productId;  	//商品编号
	
	@ResID(id=R.id.textOriginalPriceValue)
	private TextView productMarketPrice;		//商品市场价
	
	@ResID(id=R.id.textPriceValue)
	private TextView productMemberPrice;	//会员价格
	
	@ResID(id=R.id.textProdGradeValue)
	private ImageView productScore;			//商品评分
	
	@ResID(id=R.id.prodNumValue)
	private EditText buyNumber;		//购买数量
	
	@ResID(id=R.id.prod_property)
	private RelativeLayout productProperties ;		//商品属性
	
	@ResID(id=R.id.textColorValue)
	private TextView productColor;		//商品颜色
	
	@ResID(id=R.id.textSizeValue)
	private TextView productSize;		//商品尺码
	
	@ResID(id=R.id.textPutIntoShopcar)
	private TextView addShoppingCart;		//加入购物车
	
	@ResID(id=R.id.textProdToCollect)
	private TextView 	collect;		//收藏
	
	@ResID(id=R.id.relDescription)
	private RelativeLayout productDescription ; 		//商品描述
	
	@ResID(id=R.id.relProdStock)
	private RelativeLayout productNumber;		//库存数量
	
	@ResID(id=R.id.textProdIsStock)
	private TextView isStock;		//是否有货
	
	@ResID(id=R.id.relProductComment)
	private RelativeLayout productComment;		//购买评论
	
	@ResID(id=R.id.textProductCommentNum)
	private TextView commentCount; 		//评论数量
	
	@ResID(id=R.id.orderTelTv)
	private TextView orderPhoneNumber ;		//订购电话
	
	@ResID(id=R.id.categoryEmptyListTv)
	private TextView loading;			//正在加载
	
	@ResID(id=R.id.textProductInfoIsNull)
	private TextView productInfoIsNull;		//商品描述为空
	
	@ResID(id=R.id.imgPoint)
	private LinearLayout ll_pointGroup ;			//viewPager上的点的父容器
	
	@ResID(id=R.id.productViewPager)
	private MyViewPager viewPager;		//展示商品图片的viewPager
	
	private RelativeLayout bigImageView;	//大图的view
	
	private ViewPager bigImageViewPager;		//大图界面的viewPager
	
	private PopupWindow pop; 		//大图的婆婆窗口
	
	private Product product;		//该界面显示的商品的javaBean
	
	private String[] ImgUrl;			//展示图片的Url数组
	
	private int[] scoreImageRes;		//评分的Image资源id的数组
	
	private int lastPoint;		//上一个viewpager的指引点的位置
	
	private BigImageAdapter bigAdapter ;	//大图界面的viewPager适配器
	
	private int[] bigImageResId ;	//大图界面的图片资源id
	/*========================================*/
	public ProductDetailView(Context context) {
		super(context);
	}

	@Override
	protected void init() {
		bigImageResId = new int[]{R.drawable.dota1,R.drawable.dota2,R.drawable.dota3,R.drawable.dota4,R.drawable.dota5};
		
		bigImageView = (RelativeLayout) View.inflate(context, R.layout.bigimageview, null);
		bigImageViewPager = (ViewPager) bigImageView.findViewById(R.id.bigImageViewPager);
		pop = new PopupWindow(context);
		pop.setContentView(bigImageView);
		pop.setBackgroundDrawable(null);
		pop.setAnimationStyle(android.R.anim.fade_in);
		pop.setWidth(-1);
		pop.setHeight(-1);
		bigAdapter = new BigImageAdapter();
		bigImageViewPager.setAdapter(bigAdapter);
		
		scoreImageRes = new int[]{R.drawable.level_3,R.drawable.level_2,R.drawable.level_1,R.drawable.level};
		ImgUrl = new String[5];
		for(int i = 0; i<5;i++){
			ImageView point = new ImageView(context);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-2,-2);
			params.rightMargin = 10;
			point.setLayoutParams(params);
			point.setImageResource(R.drawable.point_bg);
			if(i == 2){
				point.setEnabled(true);
				lastPoint = 2;
			}else{
				point.setEnabled(false);
			}
			ll_pointGroup.addView(point);
		}
		viewPager.setAdapter(new PagerAdapter() {
			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0==arg1;
			}
			@Override
			public int getCount() {
				return ImgUrl.length;
			}
			@Override
			public void destroyItem(ViewGroup container, int position,
					Object object) {
				container.removeView((View)object);
				object = null;
			}
			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				SmartImageView smart = new SmartImageView(context);
				LayoutParams params = new LayoutParams(-1,-1);
				smart.setLayoutParams(params);
//				smart.setImageUrl(ImgUrl[position]);
				smart.setImageResource(R.drawable.my1249002);
				container.addView(smart);
				return smart;
			}
			
		});
	}

	private int firstDownX = 0;
	@Override
	protected void setListener() {
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				ll_pointGroup.getChildAt(lastPoint).setEnabled(false);
				ll_pointGroup.getChildAt(position).setEnabled(true);
				lastPoint = position;
			}
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}
			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});

		viewPager.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					firstDownX = (int) event.getX();
					break;
				case MotionEvent.ACTION_UP:
					float dx = Math.abs((event.getX()-firstDownX));
					if(dx<5){
						System.out.println("viewPager的点击被触发了");
						pop.showAtLocation(showview, Gravity.CENTER, 0, 0);
					}
					break;
				}
				return viewPager.onTouchEvent(event);
			}
		});
		collect.setOnClickListener(this);
		addShoppingCart.setOnClickListener(this);
		orderPhoneNumber.setOnClickListener(this);
		productComment.setOnClickListener(this);
		productDescription.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.textProdToCollect:
			Toast.makeText(context, "收藏成功", 0).show();
			collect.setText("已收藏");
			collect.setEnabled(false);
			break;
		case R.id.textPutIntoShopcar:
			//加入购物车
			
			break;
		case R.id.orderTelTv:
			Intent intent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+"10086"));
			context.startActivity(intent);
			break;
		case R.id.relProductComment:
			UIManager.getInstance().getMap().put("productName", product.getName());
			UIManager.getInstance().changeView(ProductCommentView.class);
			break;
		case R.id.relDescription:
			UIManager.getInstance().changeView(ProductDescriptionView.class);
			break;
		}
		super.onClick(v);
	}
	
	
	public PopupWindow getPop() {
		return pop;
	}

	@Override
	public void onResume() {
		loading.setVisibility(View.VISIBLE);
		Map<String, Object> map = UIManager.getInstance().getMap();
		if(map.get("product")!=null){
			product= (Product) map.get("product");
			for(int i = 0 ;i<5;i++){
				ImgUrl[i] = product.getPics()[i%2];
			}
			//小图片的展示要使用动态获得的话在这里通知小图的viewPager的适配器
			initProductView();
		}else if(map.get("isGoback") != null){
			loading.setVisibility(View.INVISIBLE);
		}else{
			getProduct();
		}
		map.clear();
		super.onResume();
	}
	
	private void getProduct(){
		new MyAsynTask() {
			@Override
			public void onPreExecute() {
			}
			@Override
			public void onPostExecute() {
				for(int i = 0 ;i<5;i++){
					ImgUrl[i] = product.getPics()[i%2];
				}
				//小图片的展示要使用动态获得的话在这里通知小图的viewPager的适配器
				initProductView();
				
			}
			@Override
			public void doInBackground() {
				CategoryDetailEngine engine = BeanFactory.getImpl(CategoryDetailEngine.class);
				product = engine.getProductDetail();
			}
		}.execute();
	}
	
	private void initProductView(){
		collect.setText("收藏");
		collect.setEnabled(true);
		loading.setVisibility(View.INVISIBLE);
		productName.setText(product.getName());  //设置商品名
		productId.setText(String.valueOf(product.getId()));//设置商品编号
		productMarketPrice.setText(String.valueOf("渣"+product.getMarketprice()));//设置商品市场价格
		productMemberPrice.setText(String.valueOf("￥"+product.getPrice()));	//设置会员价格
		productScore.setImageResource(scoreImageRes[(int)(product.getScore()>3?3:product.getScore())]);	//设置商品评分
		if(product.getNumber()==0){	//设置商品库存数量
			isStock.setText("(无货)");
		}else{
			isStock.setText("(有货)");
		}
		commentCount.setText(String.valueOf(product.getComment_count()));	//设置商品评论数量
	}

	@Override
	public void onLeftButtonClicked() {
		UIManager.getInstance().goback();
	}

	@Override
	public void onRightButtonClicked() {
		//加入购物车
	}
	
	
	private class BigImageAdapter extends android.support.v4.view.PagerAdapter{
		@Override
		public int getCount() {
			return 5;
		}
		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View)object);
			object = null;
		}
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			SmartImageView smart = new SmartImageView(context);
			
//			LayoutParams params = new LayoutParams(-1,-1);
			ViewPager.LayoutParams params = new ViewPager.LayoutParams();
			params.width = -1;
			int height = DensityUtil.dip2px(context, 300);
			params.height = height;
			smart.setLayoutParams(params);
//			smart.setImageUrl(ImgUrl[position]);
			smart.setImageResource(bigImageResId[position]);
			container.addView(smart);
			return smart;
		}
		
	}
}
