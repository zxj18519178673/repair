package com.jb.repair.common;

import android.content.Context;
import android.os.Environment;

import com.jb.repair.R;
import com.jb.repair.util.SharePreferenceUtil;

import java.io.File;


/**
 * 系统配置项工具类
 *
 * @author dmx
 */
public class AppConfig {

    private static final String SYSTEMCONFIG = "SYSTEMCONFIG";

    public static final String ACTION_MAIN = "com.jb.f1.action.main";


    //本地数据库存储路径
    public static final String DB_PATh = Environment.getExternalStorageDirectory() + File.separator + "Repair" + File.separator+ "db"+File.separator+"SCGLXT.db";

    /**
     * 写入到SharedPreference有关配置的key
     */
    /**
     * 服务器地址列表
     */
    public static final String SERVER_ADDRESS = "SERVERADDRES";
    /**
     * 默认的服务器节点名称
     */
    public static final String DEFAULT_SERVER_NODENAME = "DEFAULT_SERVER_NODENAME";
    /**
     * 默认的服务器地址
     */
    public static final String DEFAULT_SERVER_ADDRESS = "DEFAULT_SERVER_ADDRESS";
    /**
     * 用户名
     */
    public static final String USERNAME = "USERNAME";
    /**
     * 密码
     */
    public static final String PASSWORD = "PASSWORD";
    /**
     * 登录成功的用户信息列表
     */
    public static final String CONFIG_USER_LIST = "CONFIG_USER_LIST";
    /**
     * 是否启用自动登录
     */
    public static final String IS_AUTO_LOGIN = "IS_AUTO_LOGIN";


    protected SharePreferenceUtil spu;
    protected Context context;
    private static AppConfig appConfig;

    public synchronized static AppConfig getInstance(Context context) {
        if (appConfig == null) {
            appConfig = new AppConfig(context);
        }

        return appConfig;
    }

    protected AppConfig(Context context) {
        this.context = context;
        spu = SharePreferenceUtil.getInstance(context, SYSTEMCONFIG);
    }

    /**
     * 获取默认的服务地址
     *
     * @return
     */
    public String getDefaultServerAddress() {
        return spu.getString(DEFAULT_SERVER_ADDRESS,
                context.getString(R.string.server_address));
    }

    /**
     * 设置默认的服务器地址
     *
     * @param address
     */
    public void setDefaultServerAddress(String address) {
        spu.setString(DEFAULT_SERVER_ADDRESS, address);
    }

    /**
     * 获取默认的服务节点名称
     *
     * @return
     */
    public String getDefaultServerNodeName() {
        return spu.getString(DEFAULT_SERVER_NODENAME, "");
    }

    /**
     * 设置默认的服务器节点名称
     *
     * @param name
     */
    public void setDefaultServerNodeName(String name) {
        spu.setString(DEFAULT_SERVER_NODENAME, name);
    }

    /**
     * 获取服务地址列表
     *
     * @return
     */
    public String getServerAddress() {
        return spu.getString(SERVER_ADDRESS, "");
    }

    /**
     * 设置服务地址列表
     *
     * @return
     */
    public void setServerAddress(String address) {
        spu.setString(SERVER_ADDRESS, address);
    }

    /**
     * 设置用户列表
     *
     * @param value
     */
    public void setUserList(String value) {
        spu.setString(CONFIG_USER_LIST, value);
    }

    /**
     * 获取配置项中的用户列表
     *
     * @return
     */
    public String getUserList() {
        return spu.getString(CONFIG_USER_LIST, "");
    }

    /**
     * 读取配置项
     *
     * @param key
     * @return
     */
    public String getConfig(String key, String defaultValue) {
        return spu.getString(key, defaultValue);
    }

    /**
     * 设置配置项
     *
     * @param key
     * @param value
     */
    public void setConfig(String key, String value) {
        spu.setString(key, value);
    }

    /**
     * 设置是否自动登录
     *
     * @param isAutoLogin
     */
    public void setIsAutoLogin(boolean isAutoLogin) {
        spu.setBoolean(IS_AUTO_LOGIN, isAutoLogin);
    }

    /**
     * 读取是否自动登录
     * @return
     */
    public boolean getIsAutoLogin(){
       return spu.getBoolean(IS_AUTO_LOGIN,false);
    }

}
