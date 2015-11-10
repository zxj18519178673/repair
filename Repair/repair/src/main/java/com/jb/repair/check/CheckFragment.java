package com.jb.repair.check;

import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.jb.repair.R;
import com.jb.repair.check.model.News;

import java.util.ArrayList;
import java.util.List;

public class CheckFragment extends Fragment implements View.OnClickListener {

    private TextView tvCheck;
    private TextView tvChange;
    private TextView tvBug;
    private ListView lvNow;
    private ListView lvBefore;
    List<News> list;
    List<News> list2;
    List<News> list3;
    public CheckFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view =  inflater.inflate(R.layout.fragment_check2, null);
        initview(view);

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
        return view;
    }

    private void initview(View view) {

        tvCheck = (TextView) view.findViewById(R.id.tv_bef_check);
        tvChange = (TextView) view.findViewById(R.id.tv_change);
        tvBug = (TextView) view.findViewById(R.id.tv_bug);
        lvNow = (ListView) view.findViewById(R.id.lv_now);
        lvBefore = (ListView) view.findViewById(R.id.lv_before);
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
//                lvNow.setAdapter(new Lv2NowAdapter());
                lvBefore.setAdapter(new Lv3BeforAdapter());
                break;
            case R.id.tv_bug:
                tvCheck.setBackgroundResource(R.drawable.textview_unselet);
                tvBug.setBackgroundResource(R.drawable.textview_selected);
                tvChange.setBackgroundResource(R.drawable.textview_unselet);
//                lvNow.setAdapter(new Lv2NowAdapter());
                lvBefore.setAdapter(new Lv2BeforAdapter());
                break;

        }


    }
    class LvNowAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return (list.size()+1);
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = null;
            ViewHolder viewHolder;
            if( convertView == null){
                v = View.inflate(getActivity(), R.layout.lv_check,null);
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
                v = View.inflate(getActivity(), R.layout.lv_check2,null);
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
                v = View.inflate(getActivity(), R.layout.lv_check3,null);
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
                v = View.inflate(getActivity(), R.layout.lv_check3,null);
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
