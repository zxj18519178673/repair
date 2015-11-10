package com.jb.repair.main.model;

/**
 * Created by gaobin on 2015/10/28.
 */
public class TaskModel {
    public String id;
    public String task;   //任务名称
    public String plan_id;//计划id
    public String plan;  //计划名称
    public String eq_dept_id;//专业id
    public String eq_dept;  //专业名称
    public String si_person_a_id;  //点检员A角id
    public String si_person_a;  //点检员A角姓名
    public String si_person_b_id;  //
    public String si_person_b;  //
    public String si_new_person_a_id;  //新点检员A角id
    public String si_new_person_a;  //
    public String si_new_person_b_id;  //
    public String si_new_person_b;  //
    public String task_date;  //任务日期
    public String start_type;  //派发方式
    public String is_holiday;  //节假日否
    public String generating_unit_state;  //机组状态
    public String task_status;  //任务状态
    public String upload_date;  //上传时间
    public String is_start_task;  //是否派发任务
    public String dek_mark;  //删除标记
    public String who_change_it;  //变更人id
    public String note;  //备注
    public String power_plant_id;  //发电厂id
    public String creat_time;  //创建时间
    public String update_time;  //更新时间


    public int total_position_num;  //项目总数量
    public int inPlaceRate;  //到位率
    public int total_pos_num;  //位置总数量
    public int total_si_num;  //已点检项目数
    public int total_unsi_num;  //待点检项目数
    public int abnormal_num;   //项目异常数

}
