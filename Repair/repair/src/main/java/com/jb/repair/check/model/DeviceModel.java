package com.jb.repair.check.model;

import java.util.List;

/**
 * 点检位置
 * Created by gaobin on 2015/11/2.
 */
public class DeviceModel {
    public String id;
    public String equipName;
    public  List<ItemModel> todayCheckList;
    public List<ItemModel> weekCheckList;
    public List<ItemModel> monthCheckList;
}
