package com.jb.repair.ledger.model;

import java.io.Serializable;

/**
 * Created by SLF on 2015/11/4.异动记录
 */
public class YdjlModel implements Serializable {

    public String  id;//
    public String  sbmc;//设备名称GUID
    public String  ydsj;//动作时间
    public String  ydyy;//动作原因
    public String  ydnr;//事件简述
    public String  zrr;//
    public String  ydbg;//动作分析报告路径
    public String  dw;//
    public String  timepoint;//
}
