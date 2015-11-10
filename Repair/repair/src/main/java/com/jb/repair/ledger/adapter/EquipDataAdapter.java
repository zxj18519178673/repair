package com.jb.repair.ledger.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jb.repair.R;
import com.jb.repair.ledger.model.EquipDataModel;
import com.jb.repair.view.IconFontTextView;

import java.util.List;

/**
 * 设备资料
 * Created by gaobin on 2015/10/28.
 */
public class EquipDataAdapter extends BaseAdapter{
    private List<EquipDataModel> mdata;
    private Context mContext;
    private LayoutInflater mInflater;

    public EquipDataAdapter(Context mContext, List<EquipDataModel> mdata) {
        this.mContext = mContext;
        this.mdata = mdata;
        mInflater = LayoutInflater.from(mContext);
    }
    @Override
    public int getCount() {
        return mdata.size() + 1;
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
        convertView = mInflater.inflate(R.layout.item_ledger,null);
        TextView tv_no = (TextView) convertView.findViewById(R.id.tv_no);
        TextView tv1 = (TextView) convertView.findViewById(R.id.tv1);
        TextView tv2 = (TextView) convertView.findViewById(R.id.tv2);
        TextView tv3 = (TextView) convertView.findViewById(R.id.tv3);
        IconFontTextView iconFontTextView = (IconFontTextView) convertView.findViewById(R.id.icon);

        if(position == 0){
            iconFontTextView.setVisibility(View.INVISIBLE);
            tv1.setText("设备资料类型");
            tv2.setText("文件名称");
            tv3.setText("上传时间");
        }else {
            EquipDataModel model = mdata.get(position -1);
            tv_no.setText(position+"");
            tv1.setText(model.type);
            tv2.setText(model.docName);
            tv3.setText(model.uplodeDate);
        }

        return convertView;
    }
}
