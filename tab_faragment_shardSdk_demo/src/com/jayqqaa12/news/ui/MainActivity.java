package com.jayqqaa12.news.ui;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import android.support.v4.widget.DrawerLayout;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.jayqqaa12.abase.core.fragment.AFragmentActivity;
import com.jayqqaa12.news.R;

@EActivity(R.layout.activity_main)
public class MainActivity extends AFragmentActivity
{
	@ViewById
	DrawerLayout dl;

	@ViewById
	ListView lv_dl;

	private static int mImageViewArray[] = { R.drawable.tab_home_btn, R.drawable.tab_message_btn, R.drawable.tab_selfinfo_btn,
			R.drawable.tab_square_btn, R.drawable.tab_more_btn };

	private static int mTextviewArray[] = { R.string.tab_1, R.string.tab_2, R.string.tab_3, R.string.tab_4, R.string.tab_5 };

	@AfterViews
	public void init()
	{

		initMyTab(getTabHost(R.id.realtabcontent), R.layout.tab_item_view, new int[][] { mImageViewArray, mTextviewArray }, new int[] {
				R.id.imageview, R.id.textview }, new Class[] { TabFragment_.class, TabFragment_.class, TabFragment_.class,
				TabFragment_.class, TabFragment_.class });

//		ShareSDK.initSDK(this);

		lv_dl.setAdapter(new ArrayAdapter<String>(this, R.layout.lv_dl_item, R.id.lv_tv,
				new String[] { "分享到微博", "分享到QQ空间", "一键分享", "第三方登录" }));

	}



//	@ItemClick({ R.id.lv_dl })
//	public void onItemClick(int position)
//	{
//
//		switch (position)
//		{
//		case 0:
//			L.i(" shard to sina weibo ");
//
//			Platform weibo = ShareSDK.getPlatform(this, SinaWeibo.NAME);
//
//			weibo.setPlatformActionListener(new PlatformActionListener()
//			{
//
//				public void onError(Platform platform, int action, Throwable t)
//				{
//					L.i("onError");
//				}
//
//				public void onComplete(Platform platform, int action, HashMap<String, Object> res)
//				{
//					L.i("onComplete");
//				}
//
//				public void onCancel(Platform platform, int action)
//				{
//					L.i("onCancel");
//				}
//			});
//
//			Platform.ShareParams sp = new SinaWeibo.ShareParams();
//			sp.text = "测试分享的文本  FROM 12 shardSdk ";
//			weibo.share(sp);
//
//			break;
//
//		case 1:
//
//			L.i(" shard to qzone");
//
//			Platform qzone = ShareSDK.getPlatform(this, QZone.NAME);
//			QZone.ShareParams sp2 = new QZone.ShareParams();
//			sp2.title = "测试分享的标题";
//			sp2.titleUrl = "http://sharesdk.cn"; // 标题的超链接
//			sp2.text = "测试分享的文本";
//			sp2.imageUrl = "http://img.appgo.cn/imgs/sharesdk/content/2013/07/25/1374723172663.jpg";
//			sp2.comment = "我对此分享内容的评论";
//			sp2.site = "发布分享的网站名称";
//			sp2.siteUrl = "发布分享网站的地址";
//
//			qzone.setPlatformActionListener(new PlatformActionListener()
//			{
//
//				public void onError(Platform platform, int action, Throwable t)
//				{
//					L.i("onError");
//				}
//
//				public void onComplete(Platform platform, int action, HashMap<String, Object> res)
//				{
//					L.i("onComplete");
//				}
//
//				public void onCancel(Platform platform, int action)
//				{
//					L.i("onCancel");
//				}
//			});
//			qzone.share(sp2);
//
//			break;
//
//		case 2:
//
//			L.i(" one key share");
//			OnekeyShare oks = new OnekeyShare();
//
//			// 分享时Notification的图标和文字
//			oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
//			// address是接收人地址，仅在信息和邮件使用
//			oks.setAddress("12345678901");
//			// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
//			oks.setTitle(getString(R.string.share));
//			// titleUrl是标题的网络链接，仅在人人网和QQ空间使用
//			oks.setTitleUrl("http://sharesdk.cn");
//			// text是分享文本，所有平台都需要这个字段
//			oks.setText("分享");
//			// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//			// oks.setImagePath(MainActivity.TEST_IMAGE);
//			// imageUrl是图片的网络路径，新浪微博、人人网、QQ空间、
//			// 微信的两个平台、Linked-In支持此字段
//			oks.setImageUrl("http://img.appgo.cn/imgs/sharesdk/content/2013/07/25/1374723172663.jpg");
//			// url仅在微信（包括好友和朋友圈）中使用
//			oks.setUrl("http://sharesdk.cn");
//			// appPath是待分享应用程序的本地路劲，仅在微信中使用
//			// oks.setAppPath(MainActivity.TEST_IMAGE);
//			// comment是我对这条分享的评论，仅在人人网和QQ空间使用
//			oks.setComment(getString(R.string.share));
//			// site是分享此内容的网站名称，仅在QQ空间使用
//			oks.setSite(getString(R.string.app_name));
//			// siteUrl是分享此内容的网站地址，仅在QQ空间使用
//			oks.setSiteUrl("http://sharesdk.cn");
//			// venueName是分享社区名称，仅在Foursquare使用
//			oks.setVenueName("Southeast in China");
//			// venueDescription是分享社区描述，仅在Foursquare使用
//			oks.setVenueDescription("This is a beautiful place!");
//			// latitude是维度数据，仅在新浪微博、腾讯微博和Foursquare使用
//			oks.setLatitude(23.122619f);
//			// longitude是经度数据，仅在新浪微博、腾讯微博和Foursquare使用
//			oks.setLongitude(113.372338f);
//			// 是否直接分享（true则直接分享）
//
//			// oks.setSilent(silent);
//			// // 指定分享平台，和slient一起使用可以直接分享到指定的平台
//			// if (platform != null)
//			// {
//			// oks.setPlatform(platform);
//			// }
//			// 去除注释可通过OneKeyShareCallback来捕获快捷分享的处理结果
//			// oks.setCallback(new OneKeyShareCallback());
//
//			// 通过OneKeyShareCallback来修改不同平台分享的内容
//			// oks.setShareContentCustomizeCallback(new
//			// ShareContentCustomizeDemo());
//
//			oks.show(this);
//
//			break;
//
//		case 3:
//			L.i("thrid party login");
//			IntentKit.startIntent(this, LoginActivity_.class);
//
//			break;
//		}
//	}
//
//	@Override
//	protected void onDestroy()
//	{
//		super.onDestroy();
//		ShareSDK.stopSDK(this);
//	}

}
