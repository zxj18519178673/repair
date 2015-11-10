package com.jb.repair.ledger.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jb.repair.MainActivity;
import com.jb.repair.R;
import com.jb.repair.ledger.db.Db;
import com.jb.repair.ledger.model.DzjlModel;
import com.jb.repair.view.IconFontTextView;

import java.util.List;

/**
 * Created by SLF on 2015/11/5.
 */
public class DzjlAdatper extends BaseAdapter {
    private List<DzjlModel> mdata;
    private Context mContext;
    private LayoutInflater mInflater;


    public DzjlAdatper(Context mContext, List<DzjlModel> mdata) {
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
            tv1.setText("动作时间");
            tv2.setText("动作评价");
            tv3.setText("动作情况简述");
        }else {
            DzjlModel model = mdata.get(position-1);
            tv_no.setText(position + "");
            String name1 = Db.getInstance(mContext).getName("CODE_NAME", "TB_SYS_CODE", "CODE", model.dzjg);
            tv1.setText(model.dzsj);
            tv2.setText(name1);
            tv3.setText(model.sjjs);
        }
        return convertView;
    }
}

