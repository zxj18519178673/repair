package com.jb.repair.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jb.repair.R;
import com.jb.repair.main.model.TaskModel;
import com.jb.repair.view.IconFontTextView;

import java.util.List;

/**
 * 主界面历史点击任务
 * Created by gaobin on 2015/10/28.
 */
public class HisTaskAdapter extends BaseAdapter {
    private List<TaskModel> mdata;
    private Context mContext;
    private LayoutInflater mInflater;

    public HisTaskAdapter(Context mContext, List<TaskModel> mdata) {
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
        convertView = mInflater.inflate(R.layout.item_his_task,null);
        TextView tv_no = (TextView) convertView.findViewById(R.id.tv_no);
        TextView tv_name = (TextView) convertView.findViewById(R.id.tv_task_name);
        TextView tv_date = (TextView) convertView.findViewById(R.id.tv_date);
        TextView tv_abnormal_num = (TextView) convertView.findViewById(R.id.tv_abnormal_num);
        TextView tv_inplace_rate = (TextView) convertView.findViewById(R.id.tv_inplace_rate);
        IconFontTextView iconFontTextView = (IconFontTextView) convertView.findViewById(R.id.icon);

        if(0 == position)
            iconFontTextView.setVisibility(View.INVISIBLE);
        else {
            TaskModel model = mdata.get(position - 1);
            tv_no.setText(position + "");
//            tv_name.setText(model.name);
//            tv_date.setText(model.date + "");
//            tv_abnormal_num.setText(model.abnormalNum + "");
//            tv_inplace_rate.setText(model.inPlaceRate + "%");
        }


        return convertView;
    }

}
