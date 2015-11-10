package com.jb.repair.ledger.activity;

import android.os.Bundle;
import android.view.View;

import com.jb.repair.BaseActivity;
import com.jb.repair.R;
import com.jb.repair.ledger.db.Db;
import com.jb.repair.ledger.model.TtjlModel;
import com.jb.repair.view.InfoTextView;
import com.jb.repair.view.TitleView;

public class BeRecordActivtity extends BaseActivity {

    private TitleView titleView;
    private InfoTextView tv_end_time,tv_begin_time,tv_function_range,tv_function_descri,
            tv_be_report,tv_boolean;
    private Db db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ttjlxq);
        initviews();
        db = Db.getInstance(this);

        TtjlModel model= (TtjlModel) getIntent().getSerializableExtra("ttjl");
        tv_end_time.setText(model.tcsj);
        tv_begin_time.setText(model.trsj);
        tv_function_range.setText(model.tcgnfw);
        tv_function_descri.setText(model.tcyy);
        String name = db.getName("WJMC","TB_FILE_LB","OBJ_ID",model.ttbg);
        tv_be_report.setText(name);
        tv_boolean.setText(model.sfgztc);
    }

    private void initviews(){
        titleView = (TitleView) this.findViewById(R.id.titleView);

        tv_end_time = (InfoTextView) findViewById(R.id.tv_end_time);
        tv_begin_time = (InfoTextView) findViewById(R.id.tv_begin_time);
        tv_function_range = (InfoTextView) findViewById(R.id.tv_function_range);
        tv_function_descri = (InfoTextView) findViewById(R.id.tv_function_descri);
        tv_be_report = (InfoTextView) findViewById(R.id.tv_be_report);
        tv_boolean = (InfoTextView) findViewById(R.id.tv_boolean);

        titleView.setOnClickListener(new TitleView.OnTitleViewClickListener(){
            @Override
            public void onLeftImageClick(View view) {
                super.onLeftImageClick(view);
                finish();
            }
        });
    }
}
