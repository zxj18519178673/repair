package com.jb.repair.ledger.model;

import java.io.Serializable;

/**
 * Created by SLF on 2015/11/4.投退记录
 */
public class TtjlModel implements Serializable {

    public String  id;//
    public String  sscz;//单位
    public String  sbxhguid;//设备型号
    public String  sbmc;//设备名称GUID
    public String  bbhsbmc;//被保护设备名称
    public String  tcsj;//退出保护时间
    public String  trsj;//投入保护时间
    public String  tczsj;//退出时长：时间相减，非负数字
    public String  sfgztc;//是否故障退出 T为完好 F为不完好
    public String  tcyy;//退出原因
    public String  tcgnfw;//退出功能范围
    public String  ttbg;//退出报告
    public String  djr;//登记人
    public String  djsj;//登记时间
    public String  sjc;//
    public String  timepoint;//
    public String  dw;//
}
