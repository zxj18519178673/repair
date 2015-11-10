package com.jb.repair.check.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.jb.repair.R;
import com.jb.repair.check.activity.BugRigsterActivity;
import com.jb.repair.check.activity.ChangeRigsterActivity;
import com.jb.repair.check.model.ItemModel;

import java.util.List;

/**
 * Created by gaobin on 2015/11/2.
 */
public class CheckRegisChildAdapter extends BaseAdapter {
    private Context mContext;
    private List<ItemModel> mData;
    private LayoutInflater mInflater;

    public CheckRegisChildAdapter(Context mContext, List<ItemModel> mData) {
        this.mContext = mContext;
        this.mData = mData;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(R.layout.item_check_info, null);
        TextView tv_project = (TextView) convertView.findViewById(R.id.tv_project_name);
        TextView tv_content = (TextView) convertView.findViewById(R.id.tv_content);
        TextView tv_standard = (TextView) convertView.findViewById(R.id.tv_stardand);
        final TextView tv_res = (TextView) convertView.findViewById(R.id.tv_result);
        TextView tv_no = (TextView) convertView.findViewById(R.id.tv_no);
        ImageView iv_imp = (ImageView) convertView.findViewById(R.id.iv_imp);
        TextView tv_predate = (TextView) convertView.findViewById(R.id.tv_pre_check_date);

        final ItemModel model = mData.get(position);

        if (model.isMust)
            iv_imp.setVisibility(View.VISIBLE);
        else
            iv_imp.setVisibility(View.INVISIBLE);
        tv_project.setText(model.project);
        tv_content.setText(model.content);
        if (model.checkRes == 0)
            tv_res.setText("未检查");
        tv_no.setText((position + 1) + "");
        tv_predate.setText(model.preCheckDate);
        tv_standard.setText(model.standard);

        final View finalConvertView = convertView;
        tv_res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (model.checkRes == 1) {
                    return;
                } else if (model.checkRes == 2) {
                    showRegisPop(finalConvertView);
                } else if (model.checkRes == 0) {
                    showSelectPop(finalConvertView, tv_res, position);
                }


            }
        });

        return convertView;
    }

    private void showSelectPop(View v, final View v2, final int position) {
        final TextView tv = (TextView) v2;
        View popView = mInflater.inflate(R.layout.pop_abnormal, null);
        final PopupWindow pop = new PopupWindow(popView, (int) mContext.getResources().getDimension(R.dimen.x60), (int) mContext.getResources().getDimension(R.dimen.y100), true);
        pop.setFocusable(true);
        ColorDrawable dw = new ColorDrawable(0x00000000);
        pop.setBackgroundDrawable(dw);
        pop.showAsDropDown(tv);
        popView.findViewById(R.id.tv_normal).setOnClickListener(new View.OnClickListener() { //异常登记
            @Override
            public void onClick(View v) {
                ItemModel model = mData.get(position);
                // TODO: 2015/11/2 修改数据库中数据
                tv.setText("正常");
                tv.setOnClickListener(null);
                pop.dismiss();
            }
        });

        popView.findViewById(R.id.tv_abnormal).setOnClickListener(new View.OnClickListener() { //异常登记
            @Override
            public void onClick(View v) {
                // TODO: 2015/11/2  修改数据库中数据
                tv.setText("异常");
                tv.setTextColor(mContext.getResources().getColor(R.color.chart_red));
                tv.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
                tv.getPaint().setAntiAlias(true);//抗锯齿
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showRegisPop(v);
                    }
                });
                pop.dismiss();
            }
        });
    }

    private void showRegisPop(View v) {
        View popView = mInflater.inflate(R.layout.pop_exception_regis, null);
        final PopupWindow pop = new PopupWindow(popView, (int) mContext.getResources().getDimension(R.dimen.x400), (int) mContext.getResources().getDimension(R.dimen.y250), true);
        pop.setFocusable(true);
        ColorDrawable dw = new ColorDrawable(0x00000000);
        pop.setBackgroundDrawable(dw);
        pop.showAtLocation(v, Gravity.CENTER, 0, 0);
        popView.findViewById(R.id.iv_close).setOnClickListener(new View.OnClickListener() { //关闭popview
            @Override
            public void onClick(View v) {
                pop.dismiss();
            }
        });

        popView.findViewById(R.id.tv_abnormal_regis).setOnClickListener(new View.OnClickListener() { //异常登记
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ChangeRigsterActivity.class);
                mContext.startActivity(intent);
            }
        });

        popView.findViewById(R.id.tv_error_regis).setOnClickListener(new View.OnClickListener() { //异常登记
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, BugRigsterActivity.class);
                mContext.startActivity(intent);
            }
        });
    }
}
