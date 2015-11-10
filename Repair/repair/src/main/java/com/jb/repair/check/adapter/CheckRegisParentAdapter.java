package com.jb.repair.check.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jb.repair.R;
import com.jb.repair.check.model.DeviceModel;
import com.jb.repair.view.ListViewInScroll;

import java.util.List;

/**
 * Created by gaobin on 2015/11/2.
 */
public class CheckRegisParentAdapter extends BaseAdapter {
    private Context mContext;
    private List<DeviceModel> mData;
    private LayoutInflater mInflater;

    public CheckRegisParentAdapter(Context mContext, List<DeviceModel> mData) {
        this.mContext = mContext;
        this.mData = mData;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(R.layout.item_check_info_parent, null);
        TextView tv_name = (TextView) convertView.findViewById(R.id.tv_name);
        ListViewInScroll lv_today = (ListViewInScroll) convertView.findViewById(R.id.lv_today_check);
        ListViewInScroll lv_week = (ListViewInScroll) convertView.findViewById(R.id.lv_week_check_check);
        ListViewInScroll lv_month = (ListViewInScroll) convertView.findViewById(R.id.lv_month_check);

        DeviceModel model = mData.get(position);
        tv_name.setText(model.equipName);
        CheckRegisChildAdapter todayAdapter = new CheckRegisChildAdapter(mContext, model.todayCheckList);
        lv_today.setAdapter(todayAdapter);
        CheckRegisChildAdapter weekAdapter = new CheckRegisChildAdapter(mContext, model.weekCheckList);
        lv_week.setAdapter(weekAdapter);
        CheckRegisChildAdapter monthAdapter = new CheckRegisChildAdapter(mContext, model.monthCheckList);
        lv_month.setAdapter(monthAdapter);


        return convertView;
    }
}
