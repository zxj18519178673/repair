package com.jb.repair.ledger.model;

/**
 * 设备资料model
 * Created by gaobin on 2015/10/28.
 */
public class EquipDataModel {
    public String type;  //设备资料类型
    public String docName;  //资料名称
    public String uplodeDate;  //上传时间

    public EquipDataModel(String type, String docName, String uplodeDate) {
        this.type = type;
        this.docName = docName;
        this.uplodeDate = uplodeDate;
    }
}
