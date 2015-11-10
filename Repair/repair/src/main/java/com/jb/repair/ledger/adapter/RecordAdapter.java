package com.jb.repair.ledger.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jb.repair.R;
import com.jb.repair.ledger.model.RecordModel;
import com.jb.repair.view.IconFontTextView;

import java.util.List;

/**
 * Created by gaobin on 2015/10/28.
 */
public class RecordAdapter extends BaseAdapter {
    private List<RecordModel> mdata;
    private Context mContext;
    private LayoutInflater mInflater;

    public RecordAdapter(Context mContext, List<RecordModel> mdata) {
        this.mContext = mContext;
        this.mdata = mdata;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mdata.size();
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
        convertView = mInflater.inflate(R.layout.item_record,null);
        TextView tv_no = (TextView) convertView.findViewById(R.id.tv_no);
        TextView tv1 = (TextView) convertView.findViewById(R.id.tv1);
        TextView tv2 = (TextView) convertView.findViewById(R.id.tv2);
        TextView tv3 = (TextView) convertView.findViewById(R.id.tv3);
        IconFontTextView iconFontTextView = (IconFontTextView) convertView.findViewById(R.id.icon);

        if(position == 0){
            iconFontTextView.setVisibility(View.INVISIBLE);
        }else {
            RecordModel model = mdata.get(position-1);
            tv_no.setText(position+"");
            tv1.setText(model.time);
            tv2.setText(model.rank);
            tv3.setText(model.explain);
        }
        return convertView;
    }
}
