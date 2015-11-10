package com.jb.repair.ledger.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jb.repair.R;
import com.jb.repair.ledger.db.Db;
import com.jb.repair.ledger.model.SbzlModel;
import com.jb.repair.view.IconFontTextView;

import java.util.List;

/**
 * Created by SLF on 2015/11/4.
 */
public class SbzlAdapter extends BaseAdapter {
    private List<SbzlModel> mdata;
    private Context mContext;
    private LayoutInflater mInflater;

    public SbzlAdapter(Context mContext, List<SbzlModel> mdata) {
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
            tv1.setText("设备资料类型");
            tv2.setText("文件名称");
            tv3.setText("上传时间");
            iconFontTextView.setVisibility(View.INVISIBLE);
        }else {
            SbzlModel model = mdata.get(position -1);
            tv_no.setText(position + "");
            String name1 = Db.getInstance(mContext).getName("CODE_NAME", "TB_SYS_CODE", "CODE", model.zllx);
            tv1.setText(name1);
            tv2.setText(model.zlmc);
            tv3.setText(model.scrq );
        }
        return convertView;
    }
}
