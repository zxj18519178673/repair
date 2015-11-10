package com.jb.repair.check.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.jb.repair.R;
import com.jb.repair.check.model.News;
import com.jb.repair.view.TitleView;

import java.util.ArrayList;
import java.util.List;

/*
无用的Activity
 */
public class CheckManActivity extends Activity implements View.OnClickListener {

    private TextView tvCheck;
    private TextView tvChange;
    private TextView tvBug;
    private TextView tvTitle;
    private TitleView titleView;
    private ListView lvNow;
    private ListView lvBefore;
    List<News> list;
    List<News> list2;
    List<News> list3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_manager);
        tvCheck = (TextView) findViewById(R.id.tv_bef_check);
        tvChange = (TextView) findViewById(R.id.tv_change);
        tvBug = (TextView) findViewById(R.id.tv_bug);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        lvNow = (ListView) findViewById(R.id.lv_now);
        lvBefore = (ListView) findViewById(R.id.lv_before);
        titleView = (TitleView) findViewById(R.id.titleView);
        tvCheck.setOnClickListener(this);
        tvChange.setOnClickListener(this);
        tvBug.setOnClickListener(this);
        list = new ArrayList<News>();
        list2 = new ArrayList<News>();
        list3 = new ArrayList<News>();
        for (int i = 0; i < 5; i++) {
            News news = new News(i,"2015.11.11",i+"",i,i,i+"");
            list.add(news);
            list2.add(news);
            list3.add(news);
        }
        lvNow.setAdapter(new LvNowAdapter());
        lvBefore.setAdapter(new LvBeforAdapter());
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.tv_bef_check:
                tvCheck.setBackgroundResource(R.drawable.textview_selected);
                tvBug.setBackgroundResource(R.drawable.textview_unselet);
                tvChange.setBackgroundResource(R.drawable.textview_unselet);
//                lvNow.setAdapter(new LvNowAdapter());
                lvBefore.setAdapter(new LvBeforAdapter());
                break;
            case R.id.tv_change:
                tvCheck.setBackgroundResource(R.drawable.textview_unselet);
                tvBug.setBackgroundResource(R.drawable.textview_unselet);
                tvChange.setBackgroundResource(R.drawable.textview_selected);
                tvTitle.setText("2015.11.11,王小明点检任务");
//                lvNow.setAdapter(new LvNowAdapter());
                lvBefore.setAdapter(new Lv3BeforAdapter());
                break;
            case R.id.tv_bug:
                tvCheck.setBackgroundResource(R.drawable.textview_unselet);
                tvBug.setBackgroundResource(R.drawable.textview_unselet);
                tvChange.setBackgroundResource(R.drawable.textview_selected);
                tvTitle.setText("2015.11.11,王小明点检任务");
//                lvNow.setAdapter(new LvNowAdapter());
                lvBefore.setAdapter(new Lv2BeforAdapter());
                break;

        }

    }
    class LvNowAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return (list.size()+1);
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = null;
            ViewHolder viewHolder;
            if( convertView == null){
                v = View.inflate(CheckManActivity.this, R.layout.lv_check,null);
                viewHolder = new ViewHolder();
                viewHolder.tv1 = (TextView) v.findViewById(R.id.tv1);
                viewHolder.tv2 = (TextView) v.findViewById(R.id.tv2);
                viewHolder.tv3 = (TextView) v.findViewById(R.id.tv3);
                viewHolder.tv4 = (TextView) v.findViewById(R.id.tv4);
                viewHolder.tv5 = (TextView) v.findViewById(R.id.tv5);
                viewHolder.tv6 = (TextView) v.findViewById(R.id.tv6);
                v.setTag(viewHolder);
            }
            else {
                v = convertView;
                viewHolder = (ViewHolder) v.getTag();
            }
            if(position ==0){
                viewHolder.tv1.setText("");
                viewHolder.tv2.setText("日期");
                viewHolder.tv3.setText("任务");
                viewHolder.tv4.setText("待点检设备总数");
                viewHolder.tv5.setText("待点检项目总数");
                viewHolder.tv6.setVisibility(View.INVISIBLE);

            }

            else {
                News news = list.get(position-1);
                viewHolder.tv1.setText(news.getTv1()+"");
                viewHolder.tv2.setText(news.getTv2());
                viewHolder.tv3.setText(news.getTv3());
                viewHolder.tv4.setText(news.getTv4()+"");
                viewHolder.tv5.setText(news.getTv5()+"");
            }
            return v;
        }
        class ViewHolder{
            TextView tv1;
            TextView tv2;
            TextView tv3;
            TextView tv4;
            TextView tv5;
            TextView tv6;
        }
        @Override
        public Object getItem(int position) {
            return null;
        }
        @Override
        public long getItemId(int position) {
            return 0;
        }
    }

    class LvBeforAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return (list2.size()+1);
        }
        @Override
        public Object getItem(int position) {
            return null;
        }
        @Override
        public long getItemId(int position) {
            return 0;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = null;
            ViewHolder viewHolder;
            if( convertView == null){
                v = View.inflate(CheckManActivity.this, R.layout.lv_check2,null);
                viewHolder = new ViewHolder();
                viewHolder.tv1 = (TextView) v.findViewById(R.id.tv1);
                viewHolder.tv2 = (TextView) v.findViewById(R.id.tv2);
                viewHolder.tv3 = (TextView) v.findViewById(R.id.tv3);
                viewHolder.tv4 = (TextView) v.findViewById(R.id.tv4);
                viewHolder.tv5 = (TextView) v.findViewById(R.id.tv5);
                viewHolder.tv6 = (TextView) v.findViewById(R.id.tv6);
                v.setTag(viewHolder);
            }
            else {
                v = convertView;
                viewHolder = (ViewHolder) v.getTag();
            }
            if(position ==0){
                viewHolder.tv1.setText("");
                viewHolder.tv2.setText("点检日期");
                viewHolder.tv3.setText("点检任务");
                viewHolder.tv4.setText("异常项目数");
                viewHolder.tv5.setText("点检到位率");
                viewHolder.tv6.setVisibility(View.INVISIBLE);
            }

            else {
                News news = list2.get(position-1);
                viewHolder.tv1.setText(news.getTv1()+"");
                viewHolder.tv2.setText(news.getTv2());
                viewHolder.tv3.setText(news.getTv3());
                viewHolder.tv4.setText(news.getTv4()+"");
                viewHolder.tv5.setText(news.getTv5()+"");
            }
            return v;
        }
        class ViewHolder{
            TextView tv1;
            TextView tv2;
            TextView tv3;
            TextView tv4;
            TextView tv5;
            TextView tv6;
        }

    }
    class Lv2BeforAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return (list3.size()+1);
        }
        @Override
        public Object getItem(int position) {
            return null;
        }
        @Override
        public long getItemId(int position) {
            return 0;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = null;
            ViewHolder viewHolder;
            if( convertView == null){
                v = View.inflate(CheckManActivity.this, R.layout.lv_check3,null);
                viewHolder = new ViewHolder();
                viewHolder.tv1 = (TextView) v.findViewById(R.id.tv1);
                viewHolder.tv2 = (TextView) v.findViewById(R.id.tv2);
                viewHolder.tv3 = (TextView) v.findViewById(R.id.tv3);
                viewHolder.tv4 = (TextView) v.findViewById(R.id.tv4);
                viewHolder.tv5 = (TextView) v.findViewById(R.id.tv5);
                v.setTag(viewHolder);
            }
            else {
                v = convertView;
                viewHolder = (ViewHolder) v.getTag();
            }
            if(position ==0){
                viewHolder.tv1.setText("");
                viewHolder.tv2.setText("缺陷发生时间");
                viewHolder.tv3.setText("缺陷等级");
                viewHolder.tv4.setText("缺陷说明");
                viewHolder.tv5.setVisibility(View.INVISIBLE);
            }

            else {
                News news = list2.get(position-1);
                viewHolder.tv1.setText(news.getTv1()+"");
                viewHolder.tv2.setText(news.getTv2());
                viewHolder.tv3.setText(news.getTv3());
                viewHolder.tv4.setText(news.getTv4() + "");

            }
            return v;
        }
        class ViewHolder{
            TextView tv1;
            TextView tv2;
            TextView tv3;
            TextView tv4;
            TextView tv5;
        }

    }
    class Lv3BeforAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return (list3.size()+1);
        }
        @Override
        public Object getItem(int position) {
            return null;
        }
        @Override
        public long getItemId(int position) {
            return 0;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = null;
            ViewHolder viewHolder;
            if( convertView == null){
                v = View.inflate(CheckManActivity.this, R.layout.lv_check3,null);
                viewHolder = new ViewHolder();
                viewHolder.tv1 = (TextView) v.findViewById(R.id.tv1);
                viewHolder.tv2 = (TextView) v.findViewById(R.id.tv2);
                viewHolder.tv3 = (TextView) v.findViewById(R.id.tv3);
                viewHolder.tv4 = (TextView) v.findViewById(R.id.tv4);
                viewHolder.tv5 = (TextView) v.findViewById(R.id.tv5);
                v.setTag(viewHolder);
            }
            else {
                v = convertView;
                viewHolder = (ViewHolder) v.getTag();
            }
            if(position ==0){
                viewHolder.tv1.setText("");
                viewHolder.tv2.setText("异常发生时间");
                viewHolder.tv3.setText("处理方式");
                viewHolder.tv4.setText("异常说明");
                viewHolder.tv5.setVisibility(View.INVISIBLE);
            }

            else {
                News news = list2.get(position-1);
                viewHolder.tv1.setText(news.getTv1()+"");
                viewHolder.tv2.setText(news.getTv2());
                viewHolder.tv3.setText(news.getTv3());
                viewHolder.tv4.setText(news.getTv4()+"");
            }
            return v;
        }
        class ViewHolder{
            TextView tv1;
            TextView tv2;
            TextView tv3;
            TextView tv4;
            TextView tv5;
        }

    }


}
