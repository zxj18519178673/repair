package com.jb.repair.ledger.model;

import java.io.Serializable;

/**
 * 附属设备 model
 * Created by gaobin on 2015/10/26.
 */
public class FssbModel implements Serializable{
    public String id;
    public String sssb;  //所属设备
    public String tyrq; //投运日期
    public String fssb;
    public String sbxh;//设备型号
    /**生产厂家*/
    public String sccj;
    /**数量*/
    public int sl;
    /**投运日期*/
    public String operateDate;
    /**主要参数*/
    public String zyjscs;
    /**上传附件*/
    public String sctp;

    public String sjc;  //时间戳
    public String ssdw;   //所属单位

}
