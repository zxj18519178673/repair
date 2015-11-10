package com.jb.repair.setting;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jb.repair.R;
import com.jb.repair.setting.activity.AboutActivity;


@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class SettingFragment extends Fragment implements View.OnClickListener {


    private TextView tvClean, tvAbout;

    public SettingFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting,null);
        initView(view);

        tvClean.setOnClickListener(this);
        tvAbout.setOnClickListener(this);
        return view;
    }

    private void initView(View view) {
        tvClean = (TextView) view.findViewById(R.id.tv_clean);
        tvAbout = (TextView) view.findViewById(R.id.tv_about);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_clean:
                //清除缓存
               AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("确定清除缓存吗？");
                builder.setNegativeButton("取消",null);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //清除
                    }
                });

                builder.show();
                break;
            case R.id.tv_about:
                //跳转到关于页面
                Intent intent = new Intent(getActivity(), AboutActivity.class);
                startActivity(intent);
                break;
        }

    }
}
