package com.jb.repair.ledger.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jb.repair.R;
import com.jb.repair.ledger.model.FssbModel;
import com.jb.repair.view.IconFontTextView;

import java.util.List;

/**
 * 附属设备
 * Created by gaobin on 2015/10/26.
 */
public class FssbAdapter extends BaseAdapter {
    private List<FssbModel> mdata;
    private Context mContext;
    private LayoutInflater mInflater;

    public FssbAdapter(Context mContext, List<FssbModel> mdata) {
        this.mdata = mdata;
        this.mContext = mContext;
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
        }else {
            FssbModel model = mdata.get(position -1);
            tv_no.setText(position+"");
            tv1.setText(model.fssb+"/"+model.sbxh);
            tv2.setText(model.sccj);
            tv3.setText(model.sl + "");
            
        }

        return convertView;
    }
}
