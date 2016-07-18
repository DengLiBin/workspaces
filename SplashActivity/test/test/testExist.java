package test;

import java.io.IOException;

import com.jayqqaa12.abase.util.io.FileUtil;
import com.jayqqaa12.mobilesafe.config.Config;

import android.test.AndroidTestCase;

public class testExist extends AndroidTestCase
{

	public void test()
	{

		try
		{
			
			boolean result = FileUtil.isFileDecompression(FileUtil.getAllAssertFileName(getContext()), Config.DB_DIR,".12");
			assertEquals(false, result);

		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

	}

}
