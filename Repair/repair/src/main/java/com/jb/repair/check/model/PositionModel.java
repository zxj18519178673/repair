package com.jb.repair.check.model;

/**
 * 位置model
 * Created by gaobin on 2015/11/6.
 */
public class PositionModel {
    public String id;
    public String eq_dept_id;   //专业id
    public String place_type_id;  //机组id
    public String ins_position_name;  //安装位置名称
    public int pos_dev_num;  //该位置设备总数
    public String note;  //备注
    public String tranfer_fieid;  //迁移字段id
    public String power_plant_id; //发电厂id
    public String creat_time;   //创建日期
    public String update_time;   //更新日期

    public int dev_num_today;    //该位置今天所要点检的设备总数
    public int item_num_today;   //今天所要点检的项目总数
    public int dev_checked_num;  //已点检项目数
    public int item_abnormal_num;   //异常项目数

}
