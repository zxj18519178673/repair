package com.jb.repair.ledger.activity;

import android.os.Bundle;
import android.view.View;

import com.jb.repair.BaseActivity;
import com.jb.repair.R;
import com.jb.repair.ledger.model.SbbjModel;
import com.jb.repair.view.InfoTextView;
import com.jb.repair.view.TitleView;

public class SpareEquipActivtity extends BaseActivity {

    private TitleView titleView;

    private InfoTextView tv_name,tv_id,tv_version,tv_factory,tv_price,tv_ration,tv_stock,tv_data,tv_main_param,tv_location,tv_remark;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bpbjxq);
        initviews();

        SbbjModel sbbjModel = (SbbjModel) getIntent().getSerializableExtra("bjsb");
        tv_name.setText(sbbjModel.bjmc);
        tv_id.setText(sbbjModel.wzbm);
        tv_version.setText(sbbjModel.bjxh);
        tv_factory.setText(sbbjModel.sccj);
        tv_price.setText(sbbjModel.dj);
        tv_ration.setText(sbbjModel.de);
        tv_stock.setText(sbbjModel.sl+"");
        tv_data.setText(sbbjModel.scrq);
        tv_main_param.setText(sbbjModel.zycs);
        tv_location.setText(sbbjModel.cfwz);
        tv_remark.setText(sbbjModel.bz);
    }

    private void initviews(){
        titleView = (TitleView) this.findViewById(R.id.titleView);

        tv_name = (InfoTextView) findViewById(R.id.tv_name);
        tv_id = (InfoTextView) findViewById(R.id.tv_id);
        tv_version = (InfoTextView) findViewById(R.id.tv_version);
        tv_factory = (InfoTextView) findViewById(R.id.tv_factory);
        tv_price = (InfoTextView) findViewById(R.id.tv_price);
        tv_ration = (InfoTextView) findViewById(R.id.tv_ration);
        tv_stock = (InfoTextView) findViewById(R.id.tv_stock);
        tv_data = (InfoTextView) findViewById(R.id.tv_data);
        tv_main_param = (InfoTextView) findViewById(R.id.tv_main_param);
        tv_location = (InfoTextView) findViewById(R.id.tv_location);
        tv_remark = (InfoTextView) findViewById(R.id.tv_remark);

        titleView.setOnClickListener(new TitleView.OnTitleViewClickListener(){
            @Override
            public void onLeftImageClick(View view) {
                super.onLeftImageClick(view);
                finish();
            }
        });
    }
}
