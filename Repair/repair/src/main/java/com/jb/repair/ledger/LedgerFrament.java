package com.jb.repair.ledger;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.jb.repair.R;
import com.jb.repair.ledger.activity.FssbIntroActivity;
import com.jb.repair.ledger.activity.ActionRecordActivtity;
import com.jb.repair.ledger.activity.BeRecordActivtity;
import com.jb.repair.ledger.activity.BugRecordActivtity;
import com.jb.repair.ledger.activity.ChangeRecordActivtity;
import com.jb.repair.ledger.activity.CheckRecordActivtity;
import com.jb.repair.ledger.activity.EquipInfoActivtity;
import com.jb.repair.ledger.activity.ExceptionRecordActivtity;
import com.jb.repair.ledger.activity.SpareEquipActivtity;
import com.jb.repair.ledger.adapter.BjsbAdapter;
import com.jb.repair.ledger.adapter.DzjlAdatper;
import com.jb.repair.ledger.adapter.EquipDataAdapter;
import com.jb.repair.ledger.adapter.FssbAdapter;
import com.jb.repair.ledger.adapter.JxjlAdatper;
import com.jb.repair.ledger.adapter.QxjlAdatper;
import com.jb.repair.ledger.adapter.RecordAdapter;
import com.jb.repair.ledger.adapter.SbzlAdapter;
import com.jb.repair.ledger.adapter.SpareEquipAdapter;
import com.jb.repair.ledger.adapter.TtjlAdatper;
import com.jb.repair.ledger.adapter.YcjlAdatper;
import com.jb.repair.ledger.adapter.YdjlAdatper;
import com.jb.repair.ledger.db.Db;
import com.jb.repair.ledger.model.DzjlModel;
import com.jb.repair.ledger.model.EquipDataModel;
import com.jb.repair.ledger.model.FssbModel;
import com.jb.repair.ledger.model.JxjlModel;
import com.jb.repair.ledger.model.QxjlModel;
import com.jb.repair.ledger.model.RecordModel;
import com.jb.repair.ledger.model.SbbjModel;
import com.jb.repair.ledger.model.SbzlModel;
import com.jb.repair.ledger.model.SpareEquipModel;
import com.jb.repair.ledger.model.TtjlModel;
import com.jb.repair.ledger.model.YcjlModel;
import com.jb.repair.ledger.model.YdjlModel;
import com.jb.repair.view.AttriTextView;
import com.jb.repair.view.ListViewInScroll;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaobin on 2015/10/23.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class LedgerFrament extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {
    private AttriTextView tv_dianchang, tv_zhaugnye, tv_jizu, tv_sbmc, tv_azwz, tv_sszg, tv_sblx, tv_sbxh, tv_sccj, tv_sjsm;
    private AttriTextView tv_scrq, tv_tyrq, tv_yxzt, tv_qbjyzq, tv_bfjyzq, tv_dydj, tv_tdlx, tv_ycsb, tv_kks, tv_fzr;
    private List<TextView> tvs = new ArrayList<TextView>();
    private ListViewInScroll lv_fssb, lv_bpsb, lv_sbzl, lv_ycjl, lv_qxjl, lv_jxjl, lv_dzjl, lv_ttjl, lv_ydjl;
    private ScrollView scrollView;
    private TextView tv0, tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8, tv9;
    private LinearLayout ll_info;

    private List<FssbModel> fssbModelList = new ArrayList<FssbModel>();
    private List<SpareEquipModel> spareEquipModelList = new ArrayList<SpareEquipModel>();
    private List<EquipDataModel> equipDataModelList = new ArrayList<EquipDataModel>();
    private List<RecordModel> recordModelList = new ArrayList<RecordModel>();
    private RecordAdapter recordAdapter;
    private EquipDataAdapter equipDataAdapter;
    private FssbAdapter fssbAdapter;
    private SpareEquipAdapter spareEquipAdapter;
    private Db db;
    private String id = "";

    //11.4 设备备件
    private List<SbbjModel> sbbjModelList = new ArrayList<SbbjModel>();
    private  BjsbAdapter bjsbAdapter;
    private String id1 = "";
    //设备资料
    private List<SbzlModel> sbzlModelList = new ArrayList<SbzlModel>();
    private SbzlAdapter sbzlAdapter;
    private String id2 = "";
    //异常记录
    private List<YcjlModel> ycjlModelList = new ArrayList<YcjlModel>();
    private YcjlAdatper ycjlAdatper;
    private String id3 = "";
    //缺陷记录
    private List<QxjlModel> qxjlModelList = new ArrayList<QxjlModel>();
    private QxjlAdatper qxjlAdatper;
    private String id4 = "";
    //检修记录
    private List<JxjlModel> jxjlModelList = new ArrayList<JxjlModel>();
    private JxjlAdatper jxjlAdatper;
    private String id5 = "";
    //动作记录
    private List<DzjlModel> dzjlModelList = new ArrayList<DzjlModel>();
    private DzjlAdatper dzjlAdatper;
    private String id6 = "";
    //投退记录
    private List<TtjlModel> ttjlModelList = new ArrayList<TtjlModel>();
    private TtjlAdatper ttjlAdatper;
    private String id7 = "";
    //异动记录
    private List<YdjlModel> ydjlModelList = new ArrayList<YdjlModel>();
    private YdjlAdatper ydjlAdatper;
    private String id8 = "";


    private List<Integer> heights = new ArrayList<Integer>();

    public LedgerFrament() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frament_ledger, null);

        // TODO: 2015/11/3 设备id由设备选择界面传入
        //  id = "30DDBC7A-8C78-4F2B-9442-CB38659729B7-00304";
        id = "7B44957E-E2BA-40DB-9F77-B12CD74E2844-00170";
        db = Db.getInstance(getActivity());
        initView(view);
        initData(view);


        fssbModelList = db.getFssbById(id);
        fssbAdapter = new FssbAdapter(getActivity(), fssbModelList);
        lv_fssb.setAdapter(fssbAdapter);


        // 11.4
        //备品设备
        id1 = "6A802EB6-8A66-4338-BAFE-6AB9DA2F6516-00125";
        sbbjModelList = db.getSbbjById(id1);
        bjsbAdapter = new BjsbAdapter(getActivity(),sbbjModelList);
        lv_bpsb.setAdapter(bjsbAdapter);

        //资料设备
        id2 = "c34a7eafb11111e49d5a4439c454643f";
        sbzlModelList = db.getSbzlById(id2);
        sbzlAdapter = new SbzlAdapter(getActivity(),sbzlModelList);
        lv_sbzl.setAdapter(sbzlAdapter);

        //异常记录
        id3 = "c34a7eafb11111e49d5a4439c454643f";
        ycjlModelList = db.getYcjlById(id3);
        ycjlAdatper = new YcjlAdatper(getActivity(),ycjlModelList);
        lv_ycjl.setAdapter(ycjlAdatper);

        //缺陷记录
        id4 = "c34e19c7b11111e49d5a4439c454643f";
        qxjlModelList = db.getQxjlById(id4);
        qxjlAdatper = new QxjlAdatper(getActivity(),qxjlModelList);
        lv_qxjl.setAdapter(qxjlAdatper);
        //检修记录
        id5 = "8e3fd6cdf9a311e4aab9206a8a562037";
        jxjlModelList = db.getJxjlById(id5);
        jxjlAdatper = new JxjlAdatper(getActivity(),jxjlModelList);
        lv_jxjl.setAdapter(jxjlAdatper);
        //动作记录
        id6 = "c7f7e959b11111e49d5a4439c454643f";
        dzjlModelList = db.getDzjlById(id6);
        dzjlAdatper = new DzjlAdatper(getActivity(),dzjlModelList);
        lv_dzjl.setAdapter(dzjlAdatper);
        //投退记录
        id7 = "6A802EB6-8A66-4338-BAFE-6AB9DA2F6516-00132";
        ttjlModelList = db.getTtjlById(id7);
        ttjlAdatper = new TtjlAdatper(getActivity(),ttjlModelList);
        lv_ttjl.setAdapter(ttjlAdatper);
        //异动记录
        id8 = "6A802EB6-8A66-4338-BAFE-6AB9DA2F6516-00199";
        ydjlModelList = db.getYdjlById(id8);
        ydjlAdatper = new YdjlAdatper(getActivity(),ydjlModelList);
        lv_ydjl.setAdapter(ydjlAdatper);

        // TODO: 2015/10/28 测试数据
        SpareEquipModel spareEquipModel = new SpareEquipModel("备品设备", "abc", "abc", 12);
        spareEquipModelList.add(spareEquipModel);
        spareEquipModelList.add(spareEquipModel);
        spareEquipModelList.add(spareEquipModel);
        spareEquipAdapter = new SpareEquipAdapter(getActivity(), spareEquipModelList);
        //lv_bpsb.setAdapter(spareEquipAdapter);

        EquipDataModel equipDataModel = new EquipDataModel("图纸", "hhhh", "2015.12.12");
        equipDataModelList.add(equipDataModel);
        equipDataModelList.add(equipDataModel);
        equipDataModelList.add(equipDataModel);
        equipDataAdapter = new EquipDataAdapter(getActivity(), equipDataModelList);
        //lv_sbzl.setAdapter(equipDataAdapter);

        RecordModel recordModel1 = new RecordModel("异常发生时间", "处理方式", "异常说明");
        RecordModel recordModel2 = new RecordModel("2015.01.01", "无需处理", "abcb啊不错的等待的的");
        recordModelList.add(recordModel1);
        recordModelList.add(recordModel2);
        recordModelList.add(recordModel2);
        recordModelList.add(recordModel2);
        recordAdapter = new RecordAdapter(getActivity(), recordModelList);
//        lv_ycjl.setAdapter(recordAdapter);
//
//        lv_qxjl.setAdapter(recordAdapter);
//        lv_jxjl.setAdapter(recordAdapter);
//        lv_dzjl.setAdapter(recordAdapter);
//        lv_ttjl.setAdapter(recordAdapter);
//        lv_ydjl.setAdapter(recordAdapter);

        lv_fssb.setOnItemClickListener(this);
        lv_bpsb.setOnItemClickListener(this);
        lv_sbzl.setOnItemClickListener(this);
        lv_ycjl.setOnItemClickListener(this);
        lv_qxjl.setOnItemClickListener(this);
        lv_jxjl.setOnItemClickListener(this);
        lv_dzjl.setOnItemClickListener(this);
        lv_ttjl.setOnItemClickListener(this);
        lv_ydjl.setOnItemClickListener(this);

        return view;
    }


    private void initData(View v) {

//        LedgerModel ledgerModel = db.getLedgerbyId(id);
//        tv_dianchang.setValue(ledgerModel.ssdc);
    }

    private void initView(View v) {
        lv_fssb = (ListViewInScroll) v.findViewById(R.id.lv_fushushebei);
        lv_bpsb = (ListViewInScroll) v.findViewById(R.id.lv_beiyongshebei);
        lv_sbzl = (ListViewInScroll) v.findViewById(R.id.lv_shebeiziliao);
        lv_ycjl = (ListViewInScroll) v.findViewById(R.id.lv_yichangjilu);
        lv_qxjl = (ListViewInScroll) v.findViewById(R.id.lv_quexianjilu);
        lv_jxjl = (ListViewInScroll) v.findViewById(R.id.lv_jianxiujilu);
        lv_dzjl = (ListViewInScroll) v.findViewById(R.id.lv_dongzuojilu);
        lv_ttjl = (ListViewInScroll) v.findViewById(R.id.lv_toutuijilu);
        lv_ydjl = (ListViewInScroll) v.findViewById(R.id.lv_yidongjilu);
        scrollView = (ScrollView) v.findViewById(R.id.scrollView);
        ll_info = (LinearLayout) v.findViewById(R.id.ll_equip_info);

        tv_dianchang = (AttriTextView) v.findViewById(R.id.tv_dianchang);
        ininMenuBar(v);

        //scrollview滑动停止监听，
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            private int lastY = 0;
            private int touchEventId = -9983761;

            Handler handler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    View scroller = (View) msg.obj;
                    if (msg.what == touchEventId) {
                        if (lastY == scroller.getScrollY()) {
                            handleStop(scroller);
                        } else {
                            handler.sendMessageDelayed(handler.obtainMessage(touchEventId, scroller), 5);
                            lastY = scroller.getScrollY();
                        }
                    }
                }
            };

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    handler.sendMessageDelayed(handler.obtainMessage(touchEventId, v), 5);
                }
                return false;
            }

            //这里写真正的事件
            private void handleStop(Object view) {
                setMenuBar(scrollView.getScrollY());
            }
        });
    }

    private void setMenuBar(int height) {
        measureHeight();
        if (height == 0) {
            menuSelecte(0);
            return;
        }
        for (int i = 0; i < heights.size(); i++) {
            if (height < heights.get(i)) {
                menuSelecte(i + 1);
                return;
            }
        }
    }

    private void ininMenuBar(View v) {
        tv0 = (TextView) v.findViewById(R.id.tv_info);
        tv1 = (TextView) v.findViewById(R.id.tv_belong);
        tv2 = (TextView) v.findViewById(R.id.tv_reserve);
        tv3 = (TextView) v.findViewById(R.id.tv_data);
        tv4 = (TextView) v.findViewById(R.id.tv_abnormal);
        tv5 = (TextView) v.findViewById(R.id.tv_defect);
        tv6 = (TextView) v.findViewById(R.id.tv_repair);
        tv7 = (TextView) v.findViewById(R.id.tv_action);
        tv8 = (TextView) v.findViewById(R.id.tv_import);
        tv9 = (TextView) v.findViewById(R.id.tv_yidong);
        tv0.setOnClickListener(this);
        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);
        tv4.setOnClickListener(this);
        tv5.setOnClickListener(this);
        tv6.setOnClickListener(this);
        tv7.setOnClickListener(this);
        tv8.setOnClickListener(this);
        tv9.setOnClickListener(this);
        tvs.add(tv0);
        tvs.add(tv1);
        tvs.add(tv2);
        tvs.add(tv3);
        tvs.add(tv4);
        tvs.add(tv5);
        tvs.add(tv6);
        tvs.add(tv7);
        tvs.add(tv8);
        tvs.add(tv9);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void menuSelecte(int m) {
        for (int i = 0; i < 10; i++) {
            TextView tv = tvs.get(i);
            if (i == m) {
                tv.setBackground(getActivity().getResources().getDrawable(R.drawable.textview_selected));
                tv.setTextColor(getActivity().getResources().getColor(R.color.white_font));
            } else {
                tv.setBackground(getActivity().getResources().getDrawable(R.drawable.textview_unselet));
                tv.setTextColor(getActivity().getResources().getColor(R.color.black_text));
            }
        }
    }

    private void measureHeight() {
        if (heights == null || heights.size() == 0) {
            heights.add(ll_info.getHeight());
            heights.add(ll_info.getHeight() + lv_fssb.getHeight());
            heights.add(ll_info.getHeight() + lv_fssb.getHeight() + lv_bpsb.getHeight());
            heights.add(ll_info.getHeight() + lv_fssb.getHeight() + lv_bpsb.getHeight() + lv_sbzl.getHeight());
            heights.add(ll_info.getHeight() + lv_fssb.getHeight() + lv_bpsb.getHeight() + lv_sbzl.getHeight() + lv_ycjl.getHeight());
            heights.add(ll_info.getHeight() + lv_fssb.getHeight() + lv_bpsb.getHeight() + lv_sbzl.getHeight() + lv_ycjl.getHeight() + lv_qxjl.getHeight());
            heights.add(ll_info.getHeight() + lv_fssb.getHeight() + lv_bpsb.getHeight() + lv_sbzl.getHeight() + lv_ycjl.getHeight() + lv_qxjl.getHeight() + lv_jxjl.getHeight());
            heights.add(ll_info.getHeight() + lv_fssb.getHeight() + lv_bpsb.getHeight() + lv_sbzl.getHeight() + lv_ycjl.getHeight() + lv_qxjl.getHeight() + lv_jxjl.getHeight());
            heights.add(ll_info.getHeight() + lv_fssb.getHeight() + lv_bpsb.getHeight() + lv_sbzl.getHeight() + lv_ycjl.getHeight() + lv_qxjl.getHeight() + lv_jxjl.getHeight() + lv_ttjl.getHeight());
        }
    }

    @Override
    public void onClick(View v) {
        measureHeight();
        switch (v.getId()) {
            case R.id.tv_info:
                menuSelecte(0);
                scrollView.smoothScrollTo(0, 0);
                break;
            case R.id.tv_belong:
                menuSelecte(1);
                scrollView.smoothScrollTo(0, heights.get(0));
                break;
            case R.id.tv_reserve:
                scrollView.smoothScrollTo(0, heights.get(1));
                menuSelecte(2);
                break;
            case R.id.tv_data:
                scrollView.smoothScrollTo(0, heights.get(2));
                menuSelecte(3);
                break;
            case R.id.tv_abnormal:
                scrollView.smoothScrollTo(0, heights.get(3));
                menuSelecte(4);
                break;
            case R.id.tv_defect:
                scrollView.smoothScrollTo(0, heights.get(4));
                menuSelecte(5);
                break;
            case R.id.tv_repair:
                scrollView.smoothScrollTo(0, heights.get(5));
                menuSelecte(6);
                break;
            case R.id.tv_action:
                scrollView.smoothScrollTo(0, heights.get(6));
                menuSelecte(7);
                break;
            case R.id.tv_import:
                scrollView.smoothScrollTo(0, heights.get(7));
                menuSelecte(8);
                break;
            case R.id.tv_yidong:
                scrollView.smoothScrollTo(0, heights.get(8));
                menuSelecte(9);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position > 0) {

            switch (parent.getId()) {
                case R.id.lv_fushushebei:
                    Intent intent = new Intent(getActivity(), FssbIntroActivity.class);
                    intent.putExtra("fssb",fssbModelList.get(position - 1));
                    startActivity(intent);
                    break;
                case R.id.lv_beiyongshebei:
                    Intent spareintent = new Intent(getActivity(), SpareEquipActivtity.class);
                    spareintent.putExtra("bjsb",sbbjModelList.get(position-1));
                    startActivity(spareintent);
                    break;
                case R.id.lv_shebeiziliao:
                    Intent ziliaointent = new Intent(getActivity(), EquipInfoActivtity.class);
                    ziliaointent.putExtra("sbzl",sbzlModelList.get(position-1));
                    startActivity(ziliaointent);
                    break;
                case R.id.lv_yichangjilu:
                    Intent excepintent = new Intent(getActivity(), ExceptionRecordActivtity.class);
                    excepintent.putExtra("ycjl", ycjlModelList.get(position - 1));
                    startActivity(excepintent);
                    break;
                case R.id.lv_quexianjilu:
                    Intent bugintent = new Intent(getActivity(), BugRecordActivtity.class);
                    bugintent.putExtra("qxjl", qxjlModelList.get(position - 1));
                    startActivity(bugintent);
                    break;
                case R.id.lv_jianxiujilu:
                    Intent repairintent = new Intent(getActivity(), CheckRecordActivtity.class);
                    repairintent.putExtra("jxjl", jxjlModelList.get(position - 1));
                    startActivity(repairintent);
                    break;
                case R.id.lv_dongzuojilu:
                    Intent actionintent = new Intent(getActivity(), ActionRecordActivtity.class);
                    actionintent.putExtra("dzjl", dzjlModelList.get(position - 1));
                    startActivity(actionintent);
                    break;
                case R.id.lv_toutuijilu:
                    Intent exitintent = new Intent(getActivity(), BeRecordActivtity.class);
                    exitintent.putExtra("ttjl", ttjlModelList.get(position - 1));
                    startActivity(exitintent);
                    break;
                case R.id.lv_yidongjilu:
                    Intent yiintent = new Intent(getActivity(), ChangeRecordActivtity.class);
                    yiintent.putExtra("ydjl",ydjlModelList.get(position-1));
                    startActivity(yiintent);
                    break;

            }
        }
    }
}
