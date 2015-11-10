package com.jb.repair.ledger.activity;

import android.os.Bundle;
import android.view.View;

import com.jb.repair.BaseActivity;
import com.jb.repair.R;
import com.jb.repair.ledger.db.Db;
import com.jb.repair.ledger.model.YdjlModel;
import com.jb.repair.view.InfoTextView;
import com.jb.repair.view.TitleView;

public class ChangeRecordActivtity extends BaseActivity {

    private TitleView titleView;
    private InfoTextView tv_change_time,tv_change_reason,tv_change_content,
            tv_people,tv_change_report;
    private Db db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ydjlxq);
        initviews();
        db = Db.getInstance(this);

        YdjlModel model = (YdjlModel) getIntent().getSerializableExtra("ydjl");
        tv_change_time.setText(model.ydsj);
        tv_change_reason.setText(model.ydyy);
        tv_change_content.setText(model.ydnr);

        String name = db.getName("PERS_NAME","TB_SYS_PERSON","GUID",model.zrr);
        tv_people.setText(name);
        String name1 = db.getName("WJMC","TB_FILE_LB","OBJ_ID",model.ydbg);
        tv_change_report.setText(name1);

    }

    private void initviews(){
        titleView = (TitleView) this.findViewById(R.id.titleView);
        tv_change_time = (InfoTextView) findViewById(R.id.tv_change_time);
        tv_change_reason = (InfoTextView) findViewById(R.id.tv_change_reason);
        tv_change_content = (InfoTextView) findViewById(R.id.tv_change_content);
        tv_people = (InfoTextView) findViewById(R.id.tv_people);
        tv_change_report = (InfoTextView) findViewById(R.id.tv_change_report);

        titleView.setOnClickListener(new TitleView.OnTitleViewClickListener(){
            @Override
            public void onLeftImageClick(View view) {
                super.onLeftImageClick(view);
                finish();
            }
        });
    }
}
