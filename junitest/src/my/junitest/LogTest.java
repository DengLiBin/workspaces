package my.junitest;

import android.test.AndroidTestCase;
import android.util.Log;

public class LogTest extends AndroidTestCase {
	private static final String TAG="LogTest";
	public void testOutLog()throws Throwable{
		Log.i(TAG, "www.itcast.cn");
		Log.e(TAG,"itcast.cn");//���������־��Ϣ
		Log.w(TAG,"www.cn" );//���������־��Ϣ
	}

}
