package com.jb.repair;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jb.repair.check.CheckFragment;
import com.jb.repair.ledger.LedgerFrament;
import com.jb.repair.ledger.db.Db;
import com.jb.repair.main.MainFragment;
import com.jb.repair.view.TitleView;
import com.slidingmenu.lib.SlidingMenu;


public class MainActivity extends BaseActivity implements View.OnClickListener {
    private TextView tv_username;
    private ImageView iv_userhead;
    private TitleView titleView;
    private SlidingMenu slideMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initMenu();
        initView();
        showFragment(new MainFragment());
    }

    private void initView() {

        titleView = (TitleView) findViewById(R.id.titleView);
        titleView.setOnClickListener(new TitleView.OnTitleViewClickListener() {
            @Override
            public void onLeftTextClick(View view) {
                super.onLeftTextClick(view);
                slideMenu.showMenu();
            }

            @Override
            public void onLeftImageClick(View view) {
                super.onLeftImageClick(view);
                slideMenu.showMenu();
            }

            @Override
            public void onRightImage1Click(View view) {
                super.onRightImage1Click(view);
                slideMenu.showSecondaryMenu();
            }

            @Override
            public void onRightImage2Click(View view) {
                super.onRightImage2Click(view);
                slideMenu.showSecondaryMenu();
            }

            @Override
            public void onRightTextClick(View view) {
                super.onRightTextClick(view);
            }

            @Override
            public void onRightText2Click(View view) {
                super.onRightText2Click(view);
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void showFragment(Fragment f) {
        FragmentTransaction ft = this.getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_content, f);
        ft.commit();
        slideMenu.setStatic(false);
    }

    private void initMenu() {
        View leftMenuView = LayoutInflater.from(this).inflate(R.layout.menu_left, null);
        View leftRightView = LayoutInflater.from(this).inflate(R.layout.menu_right, null);
        slideMenu = new SlidingMenu(this);
        slideMenu.setMode(SlidingMenu.LEFT_RIGHT);
        slideMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        slideMenu.setBehindOffsetRes(R.dimen.x200);
        slideMenu.setFadeDegree(0.35f);
        slideMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        slideMenu.setMenu(leftMenuView);
        slideMenu.setSecondaryMenu(leftRightView);

        tv_username = (TextView) findViewById(R.id.tv_username);
        //iv_userhead = (ImageView) findViewById(R.id.iv_head);
        leftMenuView.findViewById(R.id.ll_check).setOnClickListener(this);
        leftMenuView.findViewById(R.id.ll_task).setOnClickListener(this);
        leftMenuView.findViewById(R.id.ll_exit).setOnClickListener(this);
        leftMenuView.findViewById(R.id.ll_ledger).setOnClickListener(this);
        leftMenuView.findViewById(R.id.ll_setting).setOnClickListener(this);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_task:
                showFragment(new MainFragment());
                titleView.setTitle("我的任务");
                break;
            case R.id.ll_ledger:
                showFragment(new LedgerFrament());
                titleView.setTitle("设备台账");
                break;
            case R.id.ll_check:
                showFragment(new CheckFragment());
                titleView.setTitle("点检管理");
                break;

            case R.id.ll_setting:

                break;
            case R.id.ll_exit:

                break;
        }
    }
}
