package com.jb.repair.ledger.activity;

import android.os.Bundle;
import android.view.View;

import com.jb.repair.BaseActivity;
import com.jb.repair.R;
import com.jb.repair.ledger.db.Db;
import com.jb.repair.ledger.model.JxjlModel;
import com.jb.repair.view.InfoTextView;
import com.jb.repair.view.TitleView;

public class CheckRecordActivtity extends BaseActivity {

    private TitleView titleView;
    private Db db;

    private InfoTextView tv_check_time,tv_check_type,tv_check_grade,
            tv_people,tv_check_descri,tv_instuctor,tv_check_info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jxjlxq);
        initviews();
        db = Db.getInstance(this);
        JxjlModel model = (JxjlModel) getIntent().getSerializableExtra("jxjl");
        tv_check_time.setText(model.jxsj);
        String name1 = db.getName("CODE_NAME","TB_SYS_CODE","CODE",model.jxlx);
        tv_check_type.setText(name1);
        String name2 = db.getName("CODE_NAME","TB_SYS_CODE","CODE",model.jxdj);
        tv_check_grade.setText(name2);

        String name = db.getName("PERS_NAME","TB_SYS_PERSON","GUID",model.zrr);
        tv_people.setText(name);
        tv_check_descri.setText(model.jxnr);
        tv_instuctor.setText(model.jxdj);
        String name3 = db.getName("WJMC","TB_FILE_LB","OBJ_ID",model.jxzl);
        tv_check_info.setText(name3);
    }

    private void initviews(){
        titleView = (TitleView) this.findViewById(R.id.titleView);
        tv_check_time = (InfoTextView) findViewById(R.id.tv_check_time);
        tv_check_type = (InfoTextView) findViewById(R.id.tv_check_type);
        tv_check_grade = (InfoTextView) findViewById(R.id.tv_check_grade);
        tv_people = (InfoTextView) findViewById(R.id.tv_people);
        tv_check_descri = (InfoTextView) findViewById(R.id.tv_check_descri);
        tv_instuctor = (InfoTextView) findViewById(R.id.tv_instuctor);
        tv_check_info = (InfoTextView) findViewById(R.id.tv_check_info);

        titleView.setOnClickListener(new TitleView.OnTitleViewClickListener(){
            @Override
            public void onLeftImageClick(View view) {
                super.onLeftImageClick(view);
                finish();
            }
        });
    }
}
