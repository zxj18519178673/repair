package com.jb.repair.ledger.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;

import com.jb.repair.BaseActivity;
import com.jb.repair.R;
import com.jb.repair.ledger.model.FssbModel;
import com.jb.repair.view.InfoTextView;
import com.jb.repair.view.TitleView;

/**
 * Created by gaobin on 2015/10/27.
 */
public class FssbIntroActivity extends BaseActivity {
    private TitleView titleView;
    private InfoTextView tv_fssb,tv_sbxh,tv_sl,tv_tyrq,tv_img,tv_sccj,tv_zycs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fsxbxq);
        initView();

        FssbModel model = (FssbModel) getIntent().getSerializableExtra("fssb");
        tv_fssb.setText(model.fssb);
        tv_sccj.setText(model.sccj);
        tv_tyrq.setText(model.tyrq);
        tv_sbxh.setText(model.sbxh);
        tv_sl.setText(model.sl+"");
        tv_img.setImagePath(model.sctp);
        tv_zycs.setText(model.zyjscs);
    }



    private void initView(){
        titleView = (TitleView) findViewById(R.id.titleView);
        tv_fssb = (InfoTextView) findViewById(R.id.tv_info);
        tv_sbxh = (InfoTextView) findViewById(R.id.tv_version);
        tv_sl = (InfoTextView) findViewById(R.id.tv_num);
        tv_tyrq = (InfoTextView) findViewById(R.id.tv_run_time);
        tv_img = (InfoTextView) findViewById(R.id.tv_img);
        tv_sccj = (InfoTextView) findViewById(R.id.tv_factory);
        tv_zycs = (InfoTextView) findViewById(R.id.tv_main_param);

        titleView.setOnClickListener(new TitleView.OnTitleViewClickListener(){
            @Override
            public void onLeftImageClick(View view) {
                super.onLeftImageClick(view);
                finish();
            }
        });
    }
}
