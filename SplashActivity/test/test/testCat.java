package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.jayqqaa12.abase.util.common.LogcatUtil;
import com.jayqqaa12.abase.util.common.TAG;

import android.test.AndroidTestCase;

public class testCat extends AndroidTestCase
{

	public void test()
	{

		try
		{
			
			System.out.println(" start get log ");
			Process process = Runtime.getRuntime().exec("logcat -s " + TAG.ActivityManager);

			BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String s = null;
			while ((s = br.readLine()) != null)
			{
				System.out.println(s);
				
				
				if (s.contains("dat=package:" +getContext().getPackageName()+ " cmp=com.android.settings/.applications.InstalledAppDetails"))
					
					System.out.println("ento my settingss 1!!!");
					
			}
			System.out.println("提前关闭了 说明不好使");
			br.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

	}

}
