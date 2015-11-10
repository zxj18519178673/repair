package com.jb.repair;

import android.app.Activity;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.HandlerThread;
import android.os.Looper;

import com.jb.model.UserModel;
import com.jb.util.json.JsonBinder;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 自定义Application，用于数据共享和异常捕获
 * 
 * @author dmx
 * 
 */
public class F1Application extends Application {

	private static HandlerThread handlerThread;
	private static List<Activity> activities;
	private static UserModel userModel;
	private static Context applicationContext;

	/**
	 * 登录模式枚举
	 * 
	 * @author dmx
	 * 
	 */
	public static enum LoginMode {
		/**
		 * 在线登录模式
		 */
		online,
		/**
		 * 离线登录模式
		 */
		offline
	}

	private static LoginMode loginMode = LoginMode.online;

	/**
	 * 获取当前登录模式
	 * 
	 * @return
	 */
	public static LoginMode getLoginMode() {
		return loginMode;
	}

	/**
	 * 设置当前的登录模式
	 */
	public static void setLoginMode(LoginMode loginMode) {
		F1Application.loginMode = loginMode;
	}

	/**
	 * 网络连接状态
	 */
	private static boolean wifiState;

	/**
	 * 获取wifi状态
	 * 
	 * @return
	 */
	public static boolean getWifiState() {
		return wifiState;
	}

	/**
	 * 设置wifi状态
	 * 
	 * @param wifiState
	 */
	public static void setWifiState(boolean wifiState) {
		F1Application.wifiState = wifiState;
	}

	/** 判断wifi网络状态 */
	class WifiConnectChangedReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			if (intent.getAction().equals(
					WifiManager.NETWORK_STATE_CHANGED_ACTION)) {
				NetworkInfo info = intent
						.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);

				switch (info.getState()) {

				case DISCONNECTED:
					wifiState = false;
					break;
				case CONNECTING:
					wifiState = true;
					break;
				case DISCONNECTING:
					wifiState = false;
					break;
				case CONNECTED:
					wifiState = true;
					break;
				case SUSPENDED:
					wifiState = true;
					break;
				default:
					break;

				}

			}
		}
	}

	/**
	 * 获取工作线程的消息循环
	 * 
	 * @return
	 */
	public static Looper getLooper() {
		return handlerThread.getLooper();
	}

	/**
	 * 获取当前登录用户的UserModel
	 * 
	 * @return
	 */
	public static UserModel getUserModel() {
		try {
			if(userModel!=null){
				return userModel;
			}
			BufferedReader br=new BufferedReader(new InputStreamReader(getAppConext().openFileInput("f1_usermodel.txt")));
			StringBuilder sb=new StringBuilder();
			String line = null;
			while((line=br.readLine())!=null){
				sb.append(line);
			}
			br.close();
			line = sb.toString();
			 userModel = JsonBinder.getJsonBinder().toBean(line, UserModel.class);
			return userModel;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void setUserModel(UserModel userModel) {
		F1Application.userModel = userModel;
		try {
			FileOutputStream fos = getAppConext().openFileOutput("f1_usermodel.txt", MODE_PRIVATE);
			String user = JsonBinder.getJsonBinder().toJson(userModel);
			fos.write(user.getBytes());
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Context getAppConext() {
		return applicationContext;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		applicationContext = getApplicationContext();
		if (getResources().getBoolean(R.bool.debug_mode)) {
			// 异常日志捕获
			CrashHandler crashHandler = CrashHandler.getInstance();
			crashHandler.init(getApplicationContext());
		}
		activities = new ArrayList<Activity>();
		handlerThread = new HandlerThread("F1ApplicationHandlerThread");
		handlerThread.start();

		// 未收到广播之前 判断wifi是否连接上
		try {
			ConnectivityManager connManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
			NetworkInfo mWifi = null;
			mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
			NetworkInfo gprs=connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
			if (mWifi != null && mWifi.isConnected()) {
				wifiState = true;
			}

			if (gprs != null && gprs.isConnected()) {
				wifiState = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 动态注册wifi广播接收器，监听网络变化
		WifiConnectChangedReceiver wifiReceiver = new WifiConnectChangedReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
		this.registerReceiver(wifiReceiver, filter);
	}

	public static void addActivity(Activity activity) {
		activities.add(activity);
	}

	public static void removeActivity(Activity activity) {
		activities.remove(activity);
	}

	/**
	 * 退出应用程序
	 */
	public static void exit() {
		handlerThread.getLooper().quit();
		for (Activity activity : activities) {
			activity.finish();
		}
		System.exit(0);
	}

}
