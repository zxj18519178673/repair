package com.jb.repair.ledger.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jb.repair.R;
import com.jb.repair.ledger.db.Db;
import com.jb.repair.ledger.model.JxjlModel;
import com.jb.repair.view.IconFontTextView;

import java.util.List;

/**
 * Created by SLF on 2015/11/5.
 */
public class JxjlAdatper extends BaseAdapter {
    private List<JxjlModel> mdata;
    private Context mContext;
    private LayoutInflater mInflater;

    public JxjlAdatper(Context mContext, List<JxjlModel> mdata) {
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
            tv1.setText("检修时间");
            tv2.setText("检修等级");
            tv3.setText("检修情况简述");
        }else {
            JxjlModel model = mdata.get(position-1);
            tv_no.setText(position+"");
            tv1.setText(model.jxsj);
            String name2 = Db.getInstance(mContext).getName("CODE_NAME", "TB_SYS_CODE", "CODE", model.jxdj);
            tv2.setText(name2);
            tv3.setText(model.jxnr);
        }
        return convertView;
    }
}

