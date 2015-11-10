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
 * 主界面今日点检任务
 * Created by gaobin on 2015/10/28.
 */
public class TodayTaskAdapter extends BaseAdapter {
    private List<TaskModel> mdata;
    private Context mContext;
    private LayoutInflater mInflater;

    public TodayTaskAdapter(Context mContext, List<TaskModel> mdata) {
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
        convertView = mInflater.inflate(R.layout.item_today_task,null);
        TextView tv_no = (TextView) convertView.findViewById(R.id.tv_no);
        TextView tv_name = (TextView) convertView.findViewById(R.id.tv_task_name);
        TextView tv_total = (TextView) convertView.findViewById(R.id.tv_total);
        TextView tv_check = (TextView) convertView.findViewById(R.id.tv_check_num);
        TextView tv_checked = (TextView) convertView.findViewById(R.id.tv_checked_num);
        TextView tv_abnormal_num = (TextView) convertView.findViewById(R.id.tv_abnormal_num);
        TextView tv_state = (TextView) convertView.findViewById(R.id.tv_state);
        IconFontTextView iconFontTextView = (IconFontTextView) convertView.findViewById(R.id.icon);

        if(0 == position)
            iconFontTextView.setVisibility(View.INVISIBLE);
        else {
            TaskModel model = mdata.get(position - 1);
            tv_no.setText(position + "");
            tv_name.setText(model.task);
            tv_total.setText(model.total_pos_num + "");
            tv_check.setText(model.total_unsi_num + "");
            tv_checked.setText(model.total_si_num + "");
            tv_abnormal_num.setText(model.abnormal_num + "");
            tv_state.setText(model.task_status);
        }


        return convertView;
    }
}
