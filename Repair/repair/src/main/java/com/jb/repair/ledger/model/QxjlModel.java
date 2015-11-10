package com.jb.repair.ledger.model;

import java.io.Serializable;

/**
 * Created by SLF on 2015/11/4. 缺陷记录
 */
public class QxjlModel implements Serializable {

    public String  id;//
    public String  defect_time;//缺陷发生时间
    public String  level_guid;//缺陷等级guid
    public String  source_guid;//缺陷来源guid
    public String  equip_id;//设备gid
    public String  type_guid;//缺陷类型guid
    public String  defect_describe;//缺陷说明
    public String  chargeman;//负责人id
    public String  process_time;//处理时间
    public String  defect_report;//缺陷分析报告
    public String  inspect_guid;//点检记录guid
    public String  patrol_guid;//巡检记录guid
    public String  related_id;//关联id
    public String  def_str1;//
    public String  def_str2;//
    public String  def_str3;//
    public String  createtime;//创建时间
    public String  updatetime;//更新时间

}
