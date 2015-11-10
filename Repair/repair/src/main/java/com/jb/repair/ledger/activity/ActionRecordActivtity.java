package com.jb.repair.ledger.activity;

import android.os.Bundle;
import android.view.View;

import com.jb.repair.BaseActivity;
import com.jb.repair.R;
import com.jb.repair.ledger.db.Db;
import com.jb.repair.ledger.model.DzjlModel;
import com.jb.repair.view.InfoTextView;
import com.jb.repair.view.TitleView;

public class ActionRecordActivtity extends BaseActivity {

    private TitleView titleView;
    private InfoTextView tv_action_time,tv_action_evaluate,tv_reason,
            tv_duty_org,tv_action_descri,tv_action_report,tv_file,tv_boolean;
    private Db db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dzjlxq);
        initviews();
        db = Db.getInstance(this);

        DzjlModel model = (DzjlModel) getIntent().getSerializableExtra("dzjl");
        tv_action_time.setText(model.dzsj);
        String name1 = db.getName("CODE_NAME","TB_SYS_CODE","CODE",model.dzjg);
        tv_action_evaluate.setText(name1);

        String name = db.getName("CODE_NAME","TB_SYS_CODE","CODE",model.dzyy);
        tv_reason.setText(name);

        tv_duty_org.setText(model.sfbzy);
        tv_action_descri.setText(model.sjjs);

        String name2 = db.getName("WJMC","TB_FILE_LB","OBJ_ID",model.ttbglj);
        tv_action_report.setText(name2);
        String name3 = db.getName("WJMC","TB_FILE_LB","OBJ_ID",model.lbtlj);
        tv_file.setText(name3);

        tv_boolean.setText(model.sfwh);
    }

    private void initviews(){
        titleView = (TitleView) this.findViewById(R.id.titleView);
        tv_action_time = (InfoTextView) findViewById(R.id.tv_action_time);
        tv_action_evaluate = (InfoTextView) findViewById(R.id.tv_action_evaluate);
        tv_reason = (InfoTextView) findViewById(R.id.tv_reason);
        tv_duty_org = (InfoTextView) findViewById(R.id.tv_duty_org);
        tv_action_report = (InfoTextView) findViewById(R.id.tv_action_report);
        tv_file = (InfoTextView) findViewById(R.id.tv_file);
        tv_boolean = (InfoTextView) findViewById(R.id.tv_boolean);
        tv_action_descri = (InfoTextView) findViewById(R.id.tv_action_descri);

        titleView.setOnClickListener(new TitleView.OnTitleViewClickListener(){
            @Override
            public void onLeftImageClick(View view) {
                super.onLeftImageClick(view);
                finish();
            }
        });
    }
}
