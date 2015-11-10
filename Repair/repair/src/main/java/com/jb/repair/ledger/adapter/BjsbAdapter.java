package com.jb.repair.ledger.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jb.repair.R;
import com.jb.repair.ledger.model.SbbjModel;
import com.jb.repair.view.IconFontTextView;

import java.util.List;

/**
 * Created by SLF on 2015/11/4.  设备备件
 */
public class BjsbAdapter extends BaseAdapter {
    private List<SbbjModel> mdata;
    private Context mContext;
    private LayoutInflater mInflater;

    public BjsbAdapter(Context mContext, List<SbbjModel> mdata) {
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
            tv1.setText("备品备件名称/型号");
            iconFontTextView.setVisibility(View.INVISIBLE);
        }else {
            SbbjModel model = mdata.get(position -1);
            tv_no.setText(position+"");
            tv1.setText(model.bjmc+"/"+model.bjxh);
            tv2.setText(model.sccj);
            tv3.setText(model.sl + "");
        }
        return convertView;
    }
}
