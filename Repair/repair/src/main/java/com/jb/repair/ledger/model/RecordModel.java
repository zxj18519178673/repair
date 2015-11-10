package com.jb.repair.ledger.model;

/**
 * Created by gaobin on 2015/10/28.
 */
public class RecordModel {
    public String id;
    public String time;     //时间
    public String rank;     //等级
    public String explain;  //说明

    public RecordModel(String time, String rank, String explain) {
        this.time = time;
        this.rank = rank;
        this.explain = explain;
    }
}
