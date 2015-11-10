package com.jb.repair.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jb.repair.R;
import com.jb.repair.main.model.CheckLocModel;
import com.jb.repair.view.AttriTextView;

import java.util.List;

/**
 * 点检位置
 * Created by gaobin on 2015/10/29.
 */
public class CheckLocAdapter extends BaseAdapter{
    private Context mContext;
    private List<CheckLocModel> mData;
    private LayoutInflater mInflater;

    public CheckLocAdapter(Context mContext, List<CheckLocModel> mData) {
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
        convertView = mInflater.inflate(R.layout.item_check_loc,null);
        TextView tv_name = (TextView) convertView.findViewById(R.id.tv_equip_name);
        AttriTextView tv_equip_total = (AttriTextView) convertView.findViewById(R.id.tv_equip_checked_num);
       // AttriTextView tv_equip_checked_num = (AttriTextView) convertView.findViewById(R.id.tv_equip_checked_num);
       // AttriTextView tv_equip_abnormal_num = (AttriTextView) convertView.findViewById(R.id.tv_equip_abnormal_num);
        AttriTextView tv_item_total = (AttriTextView) convertView.findViewById(R.id.tv_equip_checked_num);
        AttriTextView tv_item_checked_num = (AttriTextView) convertView.findViewById(R.id.tv_equip_checked_num);
        AttriTextView tv_item_abnormal_num = (AttriTextView) convertView.findViewById(R.id.tv_equip_abnormal_num);

        CheckLocModel model = mData.get(position);
        tv_name.setText(model.name);
        tv_equip_total.setValue(model.equipTotalNum+"");
//        tv_equip_checked_num.setValue(model.equipCheckedNum+"");
//        tv_equip_abnormal_num.setValue(model.equipAbnormalNum+"");
        tv_item_total.setValue(model.itemTotalNum+"");
        tv_item_checked_num.setValue(model.itemCheckedNum+"");
        tv_item_abnormal_num.setValue(model.itemAbnormalNum+"");

        return convertView;
    }
}
