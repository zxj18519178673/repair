package com.jb.repair.check.activity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.jb.repair.BaseActivity;
import com.jb.repair.R;
import com.jb.repair.check.adapter.CheckRegisParentAdapter;
import com.jb.repair.check.model.DeviceModel;
import com.jb.repair.check.model.ItemModel;
import com.jb.repair.ledger.db.Db;
import com.jb.repair.view.TitleView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaobin on 2015/10/30.
 */
public class CheckRegisActivity extends BaseActivity implements View.OnClickListener {
    private TitleView titleView;
    private ListView lv_data;

    private CheckRegisParentAdapter parentAdapter;
    private List<DeviceModel> mData = new ArrayList<DeviceModel>();
    private List<String> equipNameList = new ArrayList<String>();  //存放所有设备的名称
    private String posId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_regis);

        posId = getIntent().getStringExtra("posId");

        initView();

        mData = Db.getInstance(this).getPosDeviceModel(posId);
        if (mData == null || mData.size() == 0) {
            // TODO: 2015/11/2 测试数据
            DeviceModel model = new DeviceModel();
            model.equipName = "测试数据";
            ItemModel model1 = new ItemModel();
            model1.project = "测试数据";
            model1.content = "外观和性能检测";
            model1.standard = "1.卫生清洁，无灰尘。";
            model1.preCheckDate = "2015.10.12";
            model1.checkRes = 0;
            model1.isMust = true;
            List<ItemModel> childList = new ArrayList<ItemModel>();
            childList.add(model1);
            childList.add(model1);
            model.todayCheckList = childList;
            model.weekCheckList = childList;
            model.monthCheckList = childList;
            mData.add(model);
            mData.add(model);
        }
        parentAdapter = new CheckRegisParentAdapter(this, mData);
        lv_data.setAdapter(parentAdapter);
    }

    private void initView() {
        titleView = (TitleView) findViewById(R.id.titleView);
        lv_data = (ListView) findViewById(R.id.lv_data);

        titleView.setOnClickListener(new TitleView.OnTitleViewClickListener() {
            @Override
            public void onLeftImageClick(View view) {
                super.onLeftImageClick(view);
                finish();
            }

            @Override
            public void onRightImage1Click(View view) {
                super.onRightImage1Click(view);
                showPop(view);

            }
        });
    }

    private void showPop2(View v) {
        ListView lv_equip = new ListView(this);
        if (equipNameList.size() == 0)
            for (DeviceModel model : mData) {
                equipNameList.add(model.equipName);
            }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.textview_pop_list, equipNameList);
        lv_equip.setAdapter(adapter);

        PopupWindow popupWindow = new PopupWindow(lv_equip, 200, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setFocusable(true);
        ColorDrawable dw = new ColorDrawable(0x00000000);
        popupWindow.setBackgroundDrawable(dw);
        popupWindow.showAsDropDown(v);
    }

    private void showPop(final View view) {
        View popView = LayoutInflater.from(this).inflate(R.layout.pop_check_regis, null);
        final PopupWindow popupWindow = new PopupWindow(popView, 200, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setFocusable(true);
        ColorDrawable dw = new ColorDrawable(0x00000000);
        popupWindow.setBackgroundDrawable(dw);
        popupWindow.showAsDropDown(view);
        popView.findViewById(R.id.ll_loc_equip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                showPop2(view);
            }
        });
        popView.findViewById(R.id.ll_batch_regis).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_loc_equip:   //快速定位设备
                break;
            case R.id.ll_batch_regis:   //批量处理

                break;
        }
    }
}
