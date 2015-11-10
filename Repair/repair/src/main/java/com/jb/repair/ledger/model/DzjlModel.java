package com.jb.repair.ledger.model;

import java.io.Serializable;

/**
 * Created by SLF on 2015/11/4. 动作记录
 */
public class DzjlModel  implements Serializable {

    public String  id;//
    public String  obj_caption;//
    public String  cls_id;//
    public String  flowstatus;//
    public String  obj_dispidx;//
    public String  sbid;//设备id
    public String  sbmc;//设备名称
    public String  dzsj;//动作时间
    public String  dzyy;//不正确动作原因
    public String  dzjg;//动作评价
    public String  sfbzy;//责任单位
    public String  sjjs;//动作情况简述
    public String  ttbglj;//动作报告
    public String  lbtlj;//录波文件
    public String  sfwh;//录波是否完好
    public String  djr;//登记人
    public String  djsj;//登记时间
    public String  dw;//单位
    public String  timepoint;//
}
