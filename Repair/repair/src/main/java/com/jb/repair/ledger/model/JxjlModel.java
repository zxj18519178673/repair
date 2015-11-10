package com.jb.repair.ledger.model;

import java.io.Serializable;

/**
 * Created by SLF on 2015/11/4. 检修记录
 */
public class JxjlModel implements Serializable {

    public String  id;//
    public String  jxsj;//项目进度
    public String  jxlx;//检修类型
    public String  jxdj;//指导书清单表 TB_OVH_LIST
    public String  zrr;//同上
    public String  zyzds;//同上
    public String  jxzl;//手动上传
    public String  sssb;//所属设备
    public String  dw;//
    public String  sjly;//数据来源
    public String  timepoint;//
    public String  jxnr;//
}
