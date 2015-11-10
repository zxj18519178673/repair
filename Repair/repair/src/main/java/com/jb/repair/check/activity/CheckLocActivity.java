package com.jb.repair.check.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jb.repair.BaseActivity;
import com.jb.repair.R;
import com.jb.repair.demo.CaptureActivity;
import com.jb.repair.ledger.db.Db;
import com.jb.repair.main.adapter.CheckLocAdapter;
import com.jb.repair.main.model.CheckLocModel;
import com.jb.repair.view.TitleView;

import java.util.ArrayList;
import java.util.List;

/**
 * 点检位置
 * Created by gaobin on 2015/10/29.
 */
public class CheckLocActivity extends BaseActivity {
    private TextView tv_name;
    private ListView lv_data;
    private TitleView titleView;

    private List<CheckLocModel> checkLocModelList = new ArrayList<CheckLocModel>();
    private CheckLocAdapter checkLocAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_loc);
        tv_name = (TextView) this.findViewById(R.id.tv_name);
        lv_data = (ListView) this.findViewById(R.id.lv_data);
        titleView = (TitleView) this.findViewById(R.id.titleView);

        // TODO: 2015/10/30 测试用数据
        CheckLocModel model = new CheckLocModel();
        model.name = "xxx电厂王小明点检路线";
        model.equipAbnormalNum = 12;
        model.equipCheckedNum = 11;
        model.equipTotalNum = 15;
        model.itemAbnormalNum = 1;
        model.itemCheckedNum = 14;
        model.itemTotalNum = 15;
        checkLocModelList.add(model);
        checkLocModelList.add(model);
        checkLocModelList.add(model);
        checkLocAdapter = new CheckLocAdapter(this,checkLocModelList);
        lv_data.setAdapter(checkLocAdapter);

        titleView.setTitle(model.name);
        titleView.setOnClickListener(new TitleView.OnTitleViewClickListener(){
            @Override
            public void onLeftImageClick(View view) {
                super.onLeftImageClick(view);
                finish();
            }

            @Override
            public void onRightImage1Click(View view) {
                super.onRightImage1Click(view);
                Intent intent = new Intent(getApplicationContext(), CaptureActivity.class);
                startActivityForResult(intent,1);
            }
        });

        lv_data.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), CheckRegisActivity.class);
                intent.putExtra("posId",checkLocModelList.get(position).id);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            String localId = data.getStringExtra("data");
            Toast.makeText(this,"二维码为"+localId,Toast.LENGTH_SHORT).show();
            String local = Db.getInstance(this).getName("place_id", "TB_SI_PLACE", "qrcode_id", localId);
            Toast.makeText(this, "得到位置为" + local, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(),CheckRegisActivity.class);
            intent.putExtra("posId",local);
            startActivity(intent);
        }
    }
}
