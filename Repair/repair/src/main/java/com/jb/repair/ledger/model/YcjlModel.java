package com.jb.repair.ledger.model;

import java.io.Serializable;

/**
 * Created by SLF on 2015/11/4. 异常记录
 */
public class YcjlModel implements Serializable {

    public String  id;//
    public String  abn_time;//异常发生时间
    public String  abn_source;// 异常来源
    public String  abn_describe;//异常说明
    public String  dealtype;//处理方式
    public String  chargeman;//负责人id
    public String  equip_id;//设备id
    public String  process_time;//处理时间
    public String  abn_report;//异常分析报告
    public String  inspect_guid;//点检记录GUID
    public String  patrol_guid;//巡检记录
    public String  related_id;//关联id
    public String  def_str1;//
    public String  def_str2;//
    public String  def_str3;//
    public String  createtime;//创建时间
    public String  updatetime;//更新时间
}