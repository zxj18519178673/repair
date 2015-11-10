package com.jb.repair.ledger.activity;

import android.os.Bundle;
import android.view.View;

import com.jb.repair.BaseActivity;
import com.jb.repair.R;
import com.jb.repair.ledger.db.Db;
import com.jb.repair.ledger.model.QxjlModel;
import com.jb.repair.view.InfoTextView;
import com.jb.repair.view.TitleView;

public class BugRecordActivtity extends BaseActivity {

    private TitleView titleView;
    private InfoTextView tv_data,tv_grade,tv_type,tv_people,tv_bug_info,
            tv_report;
    private Db db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qxjlxq);
        initviews();
        db = Db.getInstance(this);

        QxjlModel model = (QxjlModel) getIntent().getSerializableExtra("qxjl");

        tv_data.setText(model.defect_time);

        String level = db.getName("defect_level","DEFECT_LEVEL","GUID",model.level_guid);
        tv_grade.setText(level);

        String type = db.getName("QXLX","TB_DEV_HD_QXLXWH","GUID",model.type_guid);
        tv_type.setText(type);

        String name = db.getName("PERS_NAME","TB_SYS_PERSON","GUID",model.chargeman);
        tv_people.setText(name);

        tv_bug_info.setText(model.defect_describe);
        String name1 = db.getName("WJMC","TB_FILE_LB","OBJ_ID",model.defect_report);
        tv_report.setText(name1);
//        tv_report.setText(model.defect_report);
    }

    private void initviews(){
        titleView = (TitleView) this.findViewById(R.id.titleView);
        tv_data = (InfoTextView) findViewById(R.id.tv_data);
        tv_grade = (InfoTextView) findViewById(R.id.tv_grade);
        tv_people = (InfoTextView) findViewById(R.id.tv_people);
        tv_bug_info = (InfoTextView) findViewById(R.id.tv_bug_info);
        tv_type = (InfoTextView) findViewById(R.id.tv_type);
        tv_report = (InfoTextView) findViewById(R.id.tv_report);

        titleView.setOnClickListener(new TitleView.OnTitleViewClickListener(){
            @Override
            public void onLeftImageClick(View view) {
                super.onLeftImageClick(view);
                finish();
            }
        });
    }
}
