package com.jb.repair.ledger.activity;

import android.os.Bundle;
import android.view.View;

import com.jb.repair.BaseActivity;
import com.jb.repair.R;
import com.jb.repair.ledger.db.Db;
import com.jb.repair.ledger.model.YcjlModel;
import com.jb.repair.view.InfoTextView;
import com.jb.repair.view.TitleView;

public class ExceptionRecordActivtity extends BaseActivity {

    private TitleView titleView;
    private InfoTextView tv_begin_time,tv_deal,tv_people,
            tv_deal_time,tv_exception_info,tv_report;
    private Db db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ycjlxq);
        initviews();
        YcjlModel model = (YcjlModel) getIntent().getSerializableExtra("ycjl");
        tv_begin_time.setText(model.abn_time);
        db = Db.getInstance(this);
        String dealtype = db.getName("dealtype","ABN_DEALTYPE","GUID",model.dealtype);
        tv_deal.setText(dealtype);

        String name = db.getName("PERS_NAME","TB_SYS_PERSON","GUID",model.chargeman);

        tv_people.setText(name);
        tv_deal_time.setText(model.process_time);
        tv_exception_info.setText(model.abn_describe);

        String name2 = db.getName("WJMC","TB_FILE_LB","OBJ_ID",model.abn_report);
        tv_report.setText(name2);
    }

    private void initviews(){
        titleView = (TitleView) this.findViewById(R.id.titleView);
        tv_begin_time = (InfoTextView) this.findViewById(R.id.tv_begin_time);
        tv_deal = (InfoTextView) this.findViewById(R.id.tv_deal);
        tv_people = (InfoTextView) this.findViewById(R.id.tv_people);
        tv_deal_time = (InfoTextView) this.findViewById(R.id.tv_deal_time);
        tv_exception_info = (InfoTextView) this.findViewById(R.id.tv_exception_info);
        tv_report = (InfoTextView) this.findViewById(R.id.tv_report);

        titleView.setOnClickListener(new TitleView.OnTitleViewClickListener(){
            @Override
            public void onLeftImageClick(View view) {
                super.onLeftImageClick(view);
                finish();
            }
        });
    }
}
