package com.jb.repair.ledger.activity;

import android.os.Bundle;
import android.view.View;

import com.jb.repair.BaseActivity;
import com.jb.repair.R;
import com.jb.repair.ledger.db.Db;
import com.jb.repair.ledger.model.SbzlModel;
import com.jb.repair.view.InfoTextView;
import com.jb.repair.view.TitleView;

public class EquipInfoActivtity extends BaseActivity {

    private TitleView titleView;
    private InfoTextView tv_info,tv_name,tv_type,tv_version,tv_upload,tv_upload_data,tv_remark,tv_attachment;
    private Db db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sbzlxq);
        initviews();
        db = Db.getInstance(this);
        SbzlModel model = (SbzlModel) getIntent().getSerializableExtra("sbzl");

        String name1 = db.getName("CODE_NAME","TB_SYS_CODE","CODE",model.zllx);
        tv_info.setText(name1);
        tv_name.setText(model.zlmc);
        tv_type.setText(model.wdlx);

        String name = db.getName("PERS_NAME","TB_SYS_PERSON","GUID",model.scr);
        tv_upload.setText(name);
        tv_upload_data.setText(model.scrq);
        tv_remark.setText(model.bz);
        tv_version.setText(model.zlbb);

        String name2 = db.getName("WJMC","TB_FILE_LB","OBJ_ID",model.zl);
        tv_attachment.setText(name2);
    }

    private void initviews(){
        titleView = (TitleView) this.findViewById(R.id.titleView);
        tv_info = (InfoTextView) this.findViewById(R.id.tv_info);
        tv_name = (InfoTextView) this.findViewById(R.id.tv_name);
        tv_type = (InfoTextView) this.findViewById(R.id.tv_type);
        tv_version = (InfoTextView) this.findViewById(R.id.tv_version);
        tv_upload = (InfoTextView) this.findViewById(R.id.tv_upload);
        tv_upload_data = (InfoTextView) this.findViewById(R.id.tv_upload_data);
        tv_remark = (InfoTextView) this.findViewById(R.id.tv_remark);
        tv_attachment = (InfoTextView) this.findViewById(R.id.tv_attachment);

        titleView.setOnClickListener(new TitleView.OnTitleViewClickListener(){
            @Override
            public void onLeftImageClick(View view) {
                super.onLeftImageClick(view);
                finish();
            }
        });
    }
}
