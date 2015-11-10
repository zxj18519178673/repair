package com.jb.repair.setting;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jb.repair.BaseActivity;
import com.jb.repair.R;
import com.jb.repair.view.TitleView;

public class ConfigActivity extends BaseActivity implements View.OnClickListener {
    private EditText ed_test;
    private TextView tv_test,tv_download,tv_sync;
    private TitleView titleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        ed_test = (EditText) findViewById(R.id.ed_test);
        tv_test = (TextView) findViewById(R.id.tv_test);
        tv_download = (TextView) findViewById(R.id.tv_download);
        tv_sync = (TextView) findViewById(R.id.tv_sync);
        titleView = (TitleView) findViewById(R.id.titleView);

        tv_test.setOnClickListener(this);
        tv_download.setOnClickListener(this);
        tv_sync.setOnClickListener(this);
        titleView.setOnClickListener(new TitleView.OnTitleViewClickListener(){
            @Override
            public void onLeftImageClick(View view) {
                super.onLeftImageClick(view);
                finish();
            }

            @Override
            public void onRightTextClick(View view) {
                super.onRightTextClick(view);
                Toast.makeText(ConfigActivity.this,"点击了确认",Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_test:
                Toast.makeText(this,"测试中",Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_download:
                Toast.makeText(this,"下载数据库",Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_sync:
                Toast.makeText(this,"同步数据库",Toast.LENGTH_SHORT).show();
                break;
        }

    }

}
