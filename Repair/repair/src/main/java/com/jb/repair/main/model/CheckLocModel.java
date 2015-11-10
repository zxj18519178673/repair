package com.jb.repair.main.model;

/**
 * 点检位置
 * Created by gaobin on 2015/10/29.
 */
public class CheckLocModel {
    public String id;
    public String name;
    public int equipTotalNum;  //设备总数
    public int equipCheckedNum;   //已点检设备数
    public int equipAbnormalNum;   //异常相关设备数
    public int itemTotalNum;  //项目总数
    public int itemCheckedNum;   //已点检项目数
    public int itemAbnormalNum;   //异常项目数
}
