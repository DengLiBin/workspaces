package com.jayqqaa12.abase.util.sys;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.concurrent.TimeoutException;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.jayqqaa12.abase.util.MsgUtil;
import com.jayqqaa12.abase.util.SysIntentUtil;
import com.stericson.RootTools.RootTools;
import com.stericson.RootTools.RootToolsException;

/**
 * 部分方法 需要 加入 RootTool.jar 方可使用
 * 
 * @author jayqqaa12
 * @date 2013-5-17
 */
public class RootUtil
{

	/**
	 * 是否 已经 获得 root 权限
	 * 
	 * 
	 * @return 是否成功
	 */
	public static synchronized boolean getRootAhth()
	{
		Process process = null;
		DataOutputStream os = null;
		try
		{
			process = Runtime.getRuntime().exec("su");
			os = new DataOutputStream(process.getOutputStream());
			os.writeBytes("exit\n");
			os.flush();
			int exitValue = process.waitFor();
			if (exitValue == 0)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch (Exception e)
		{
			Log.d("*** DEBUG ***", "Unexpected error - Here is what I know: " + e.getMessage());
			return false;
		}
		finally
		{
			try
			{
				if (os != null)
				{
					os.close();
				}
				process.destroy();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	/**
	 * 获得 root 权限 的输出流
	 * 
	 * @return 是否成功
	 */
	public static synchronized DataOutputStream getRootOutputStream()
	{
		Process process = null;
		DataOutputStream os = null;
		try
		{
			process = Runtime.getRuntime().exec("su");
			os = new DataOutputStream(process.getOutputStream());
			os.writeBytes("exit\n");
			os.flush();
			int exitValue = process.waitFor();
			if (exitValue == 0) { return os; }

		}
		catch (Exception e)
		{
			Log.d("*** DEBUG ***", "Unexpected error - Here is what I know: " + e.getMessage());
		}
		return null;
	}

	/**
	 * 启动 任何的activity
	 * 
	 * 包名/包名＋类名
	 * 
	 * @param packname
	 *            包名
	 * @param actvityName
	 *            包名＋类名
	 */
	public static void startIntent(final String packname, final String actvityName)
	{
		new Thread()
		{

			public void run()
			{
				Process process = null;
				OutputStream out = null;
				try
				{
					process = Runtime.getRuntime().exec("su");
					out = process.getOutputStream();
					// 调用安装
					out.write(("am start -n " + packname + "/" + actvityName + "\n").getBytes());

				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
				finally
				{
					try
					{
						process.destroy();
						out.close();
					}
					catch (IOException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			};

		}.start();

	}

	/***
	 * 
	 * 调用 Root 失败 会使用 intent 方式
	 * 
	 * @param context
	 * @param path
	 * @param msgwhat
	 * @param callback
	 */
	public static void install(final Context context, final List<String> paths, final Object msgobj, final Handler callback)
	{
		new Thread()
		{
			public void run()
			{
				Process process = null;
				OutputStream out = null;
				InputStream in = null;
				try
				{
					for (String path : paths)
					{
						// 请求root
						process = Runtime.getRuntime().exec("su");
						if (callback != null) MsgUtil.sendMessage(callback, MsgUtil.MSG_START, msgobj);
						out = process.getOutputStream();
						// 调用安装
						out.write(("pm install -r " + path + "\n").getBytes());
						in = process.getInputStream();
						byte[] bs = new byte[256];
						int len = in.read(bs);
						// root fail
						if (-1 == len) SysIntentUtil.install(context, path);
					}

					if (callback != null) MsgUtil.sendMessage(callback, MsgUtil.MSG_FINISH, null);

				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
				finally
				{
					try
					{
						if (out != null)
						{
							out.flush();
							out.close();
						}
						if (in != null)
						{
							in.close();
						}
					}
					catch (IOException e)
					{
						e.printStackTrace();
					}
				}
			}
		}.start();
	}

	
	/***
	 * Root uninstall
	 * 
	 * 调用 Root 失败 会使用 intent 方式
	 * 
	 * 如果不是 root 方式的 可以通过 receiver 来 监听
	 * 
	 * @param context
	 * @param pkg
	 * @param msgwhat
	 * @param callback
	 */
	public static void uninstall(final Context context, final List<String> packageNames, final Object msgobj, final Handler callback)
	{
		new Thread()
		{
			public void run()
			{
				Process process = null;
				OutputStream out = null;
				InputStream in = null;
				try
				{
					for (String pkg : packageNames)
					{// 请求root
						process = Runtime.getRuntime().exec("su");
						if (callback != null) MsgUtil.sendMessage(callback, MsgUtil.MSG_START, msgobj);
						out = process.getOutputStream();
						// 调用卸载
						out.write(("pm uninstall " + pkg + "\n").getBytes());
						in = process.getInputStream();
						byte[] bs = new byte[256];
						int len = in.read(bs);
						// 取得root失败
						if (-1 == len) SysIntentUtil.uninstall(context, pkg);
					}
					if (callback != null) MsgUtil.sendMessage(callback, MsgUtil.MSG_FINISH, -1);

				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
				finally
				{
					try
					{
						if (out != null)
						{
							out.flush();
							out.close();
						}
						if (in != null)
						{
							in.close();
						}
					}
					catch (IOException e)
					{
						e.printStackTrace();
					}
				}
			}
		}.start();
	}

	
	/////////////////////////--------------------ROOT TOOLS .jar 封装 //////////////////////////////////////////////
	
	
	
	
	/**
	 * 直接 发送 指令
	 * @param name
	 */
	public static void sendShell(String cmd)
	{
		try
		{
			RootTools.sendShell(cmd, 2);
		
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (RootToolsException e)
		{
			e.printStackTrace();
		}
		catch (TimeoutException e)
		{
			e.printStackTrace();
		}
		
		
	}
	
	
	/**
	 * 杀死 进程 
	 * 
	 * @param pid
	 */
	public static void killProcess(final int pid)
	{
		new Thread()
		{
			public void run()
			{
				try
				{
					RootTools.sendShell("kill -9 " + pid, 2);

				}
				catch (Exception e)
				{
					e.printStackTrace();
				}

			};
		}.start();
	}


}
