package com.jb.repair.ledger.model;

/**
 * 备品设备 model
 * Created by gaobin on 2015/10/28.
 */
public class SpareEquipModel {
    public String id;
    public String name;
    public String goodNum;  //物资编号
    public String normNum;  //规格编号
    public String manufacturer;  //生产厂家
    public float price;  //单价
    public int quota;    //定额
    public int stockAmount;   //库存数量
    public String produceDate;   //生产日期
    public String mainParam;   //主要参数
    public String storeLoc;    //存放位置
    public String remark;    // 备注

    public SpareEquipModel(String name, String normNum, String manufacturer, int stockAmount) {
        this.name = name;
        this.normNum = normNum;
        this.manufacturer = manufacturer;
        this.stockAmount = stockAmount;
    }
}
