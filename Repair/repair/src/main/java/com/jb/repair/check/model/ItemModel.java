package com.jb.repair.check.model;

/**
 * Created by gaobin on 2015/11/2.
 */
public class ItemModel {

    public String planId;   //计划任务详情id
    public String resultId;  //点检任务结果id

    public Boolean isMust;  //是否为必检
    public String project;  //项目
    public String content;  //内容
    public String standard;  //标准
    public String preCheckDate;   //上次点检时间
    public int checkRes;   //点检结果   0:为点检  1：正常  2：异常  3：
    public String checkResult;

    public String checkPerson;
    public Boolean isAbnormal;
    public String unit;  //数值类标准单位
    public int num_result;   //数值类点检结果
    public String desc_result;  //描述类点检结果
    public String max;   //数值类上限
    public String min;   //数值类下限

    public String cycle;  //周期
}
