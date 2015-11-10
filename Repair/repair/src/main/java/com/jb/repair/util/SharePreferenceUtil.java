package com.jb.repair.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Set;

/**
 * SharedPreferences 工具类
 * 
 * @author dmx
 * 
 */
public class SharePreferenceUtil {
	/**
	 * SharedPreference 默认实例
	 */
	private SharedPreferences sp;

	/**
	 * 获取工具类的一个实例
	 * 
	 * @param context
	 *            应用程序上下文
	 * @return
	 */
	public static SharePreferenceUtil getInstance(Context context) {
		return new SharePreferenceUtil(context);
	}

	/**
	 * 
	 * 获取工具类的一个实例
	 * 
	 * @param context
	 *            应用程序上下文
	 * @param fileName
	 *            文件名称
	 * @return
	 */
	public static SharePreferenceUtil getInstance(Context context,
			String fileName) {
		return new SharePreferenceUtil(context, fileName);
	}

	private SharePreferenceUtil(Context context, String fileName) {
		sp = context.getSharedPreferences(fileName, context.MODE_PRIVATE);
	}

	private SharePreferenceUtil(Context context) {
		sp = PreferenceManager.getDefaultSharedPreferences(context);
	}

	public boolean hasKey(final String key) {
		return sp.contains(key);
	}

	public String getString(String key, final String defaultValue) {
		return sp.getString(key, defaultValue);
	}

	public void setString(final String key, final String value) {
		sp.edit().putString(key, value).commit();
	}

	public boolean getBoolean(final String key, final boolean defaultValue) {
		return sp.getBoolean(key, defaultValue);
	}

	public void setBoolean(final String key, final boolean value) {
		sp.edit().putBoolean(key, value).commit();
	}

	public void setInt(final String key, final int value) {
		sp.edit().putInt(key, value).commit();
	}

	public int getInt(final String key, final int defaultValue) {
		return sp.getInt(key, defaultValue);
	}

	public void setFloat(final String key, final float value) {
		sp.edit().putFloat(key, value).commit();
	}

	public float getFloat(final String key, final float defaultValue) {
		return sp.getFloat(key, defaultValue);
	}

	public void setLong(final String key, final long value) {
		sp.edit().putLong(key, value).commit();
	}

	public long getLong(final String key, final long defaultValue) {
		return sp.getLong(key, defaultValue);
	}

	public void setStringSet(final String key, final Set<String> values) {
		sp.edit().putStringSet(key, values).commit();
	}

	public Set<String> getStringSet(final String key,
			final Set<String> defValues) {
		return sp.getStringSet(key, defValues);
	}
}