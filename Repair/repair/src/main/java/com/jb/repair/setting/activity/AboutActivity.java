package com.jb.repair.setting.activity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.jb.repair.BaseActivity;
import com.jb.repair.R;
import com.jb.repair.view.TitleView;

public class AboutActivity extends BaseActivity {
    private TitleView titleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_about);
        initView();
    }
    private void initView(){
        titleView = (TitleView) this.findViewById(R.id.titleView);
      //  titleView.setRightImage1ViewVisable(false);

        titleView.setOnClickListener(new TitleView.OnTitleViewClickListener() {
            @Override
            public void onLeftImageClick(View view) {
                super.onLeftImageClick(view);
                finish();
            }
        });
    }

}
