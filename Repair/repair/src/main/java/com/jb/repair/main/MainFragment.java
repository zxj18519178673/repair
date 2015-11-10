package com.jb.repair.main;


import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.jb.repair.R;
import com.jb.repair.check.activity.CheckLocActivity;
import com.jb.repair.ledger.db.Db;
import com.jb.repair.main.adapter.HisTaskAdapter;
import com.jb.repair.main.adapter.TodayTaskAdapter;
import com.jb.repair.main.model.TaskModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaobin on 2015/10/23.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MainFragment extends Fragment implements AdapterView.OnItemClickListener {
    private TextView tv_today_date, tv_update_date;
    private TextView tv_username, tv_username2;
    private ListView lv_today_task, lv_his_task;

    private String userName;
    private List<TaskModel> todayTaskList = new ArrayList<TaskModel>();
    private List<TaskModel> hisTaskList = new ArrayList<TaskModel>();
    private TodayTaskAdapter todayTaskAdapter;
    private HisTaskAdapter hisTaskAdapter;

    public MainFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frament_main, null);
        initView(view);

        todayTaskList = Db.getInstance(getActivity()).getTodayTask();
        hisTaskList = Db.getInstance(getActivity()).getHisTask();

        todayTaskAdapter = new TodayTaskAdapter(getActivity(), todayTaskList);
        hisTaskAdapter = new HisTaskAdapter(getActivity(), hisTaskList);
        lv_today_task.setAdapter(todayTaskAdapter);
        lv_his_task.setAdapter(hisTaskAdapter);

        return view;
    }

    private void initView(View view) {
        tv_today_date = (TextView) view.findViewById(R.id.tv_today_date);
        tv_update_date = (TextView) view.findViewById(R.id.tv_update_date);
        tv_username = (TextView) view.findViewById(R.id.tv_username);
        tv_username2 = (TextView) view.findViewById(R.id.tv_username2);
        lv_today_task = (ListView) view.findViewById(R.id.lv_today_task);
        lv_his_task = (ListView) view.findViewById(R.id.lv_his_task);
        lv_today_task.setOnItemClickListener(this);
        lv_his_task.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position > 0) {
            Intent intent = new Intent(getActivity(),CheckLocActivity.class);
            if (parent.getId() == R.id.lv_today_task) {
                intent.putExtra("id",todayTaskList.get(position - 1).id);
            }else if (parent.getId() == R.id.lv_his_task){
                intent.putExtra("id",hisTaskList.get(position -1).id);
            }
            startActivity(intent);
        }
    }
}
