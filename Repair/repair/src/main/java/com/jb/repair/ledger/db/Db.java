package com.jb.repair.ledger.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.jb.repair.check.model.DeviceModel;
import com.jb.repair.check.model.ItemModel;
import com.jb.repair.check.model.PositionModel;
import com.jb.repair.common.AppConfig;
import com.jb.repair.ledger.model.DzjlModel;
import com.jb.repair.ledger.model.FssbModel;
import com.jb.repair.ledger.model.JxjlModel;
import com.jb.repair.ledger.model.LedgerModel;
import com.jb.repair.ledger.model.QxjlModel;
import com.jb.repair.ledger.model.SbbjModel;
import com.jb.repair.ledger.model.SbzlModel;
import com.jb.repair.ledger.model.TtjlModel;
import com.jb.repair.ledger.model.YcjlModel;
import com.jb.repair.ledger.model.YdjlModel;
import com.jb.repair.main.model.TaskModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gaobin on 2015/11/3.
 */
public class Db {
    public static final String DB_NAME = "SCGLXT.db";

    //11.4 张进
    private static final String SBBJ_TABLE_NAME = "TB_DEV_HD_SBBJ";   //设备备件表名
    private static final String SBZL_TABLE_NAME = "TB_DEV_SBZL";   //设备资料表名
    private static final String YCGL_TABLE_NAME = "ABNORMAL";   //异常管理表名
    private static final String QXGL_TABLE_NAME = "DEFECT";   //缺陷管理表名
    private static final String JXJL_TABLE_NAME = "TB_DEV_JXJL";   //检修记录表名
    private static final String DZJL_TABLE_NAME = "TB_DEV_HD_DZJL";   //动作记录表名
    private static final String TTJL_TABLE_NAME = "TB_DEV_HD_TTJL";   //投退记录表名
    private static final String YDJL_TABLE_NAME = "TB_DEV_HD_YDJL";   //异动记录表名

    private static final String SBTZ_TABLE_NAME = "TB_DEV_HD_SBTZ";   //设备台账表名
    private static final String FSSB_TABLE_NAME = "TB_DEV_HD_FSSB";   //附属设备表名
    private static final String TASK_TABLE_NAME = "TB_SI_TASK";    //点检任务表名
    private static final String TASK_RESULT_STATISTICAL_TABLE_NAME = "TB_SI_TASK_RESULT_STATISTICAL";  //点检结果统计表名
    private static final String TASK_RESULT_TABLE_NAME = "TB_SI_TASK_RESULT";    //点检任务结果表表名
    private static final String DEV_INS_POSITION_TABLE_NAME = "TB_DEV_INS_POSITION";   //安装位置
    private static final String SI_PLAN_PLACE_TABLE_NAME = "TB_SI_PLAN_PLACE";   //点检计划位置
    private static final String SI_PLAN_DEV_DETAIL_TABLE_NAME = "TB_SI_PLAN_DEV_DETAIL";  //点检计划设备详情
    private static final String SI_PLAN_TASK_DETAIL_TABLE_NAME = "TB_SI_PLAN_TASK_DETAIL";  //计划-任务详情
    private static final String SI_PLAN_TABLE_NAME = "TB_SI_PLAN";   //点检计划
    private static final String SI_PLAN_PLACE_DETAIL_TABLE_NAME = "TB_SI_PLAN_PLACE_DETAIL"; // 点检计划位置详情

    private static String TAG = "db";
    private static SQLiteDatabase db;
    private static Db mydb;

    private Db(Context context) {
        db = context.openOrCreateDatabase(AppConfig.DB_PATh, Context.MODE_PRIVATE, null);
    }

    public static synchronized Db getInstance(Context context) {
        if (mydb == null)
            mydb = new Db(context);
        return mydb;
    }

    //用于表之间的关联
    public String getName(String aim ,String table ,String key ,String value){
        Cursor c = db.rawQuery("SELECT * from " +table +" where "+key+" = '"+value+"'",null);
        if(c.moveToNext()){
            String string = c.getString(c.getColumnIndex(aim));
            return  string;
        }
        return "";
    };
    //用于缺陷登记 异常登记 类型查找
    public Cursor getCursor(String string){
        Cursor cursor = db.rawQuery( string,null );
        return cursor;
    }

    //11.4

    //备用设备 Sbbj
    public List<SbbjModel> getSbbjById(String id){
        List<SbbjModel> list = new ArrayList<SbbjModel>();
        Cursor c = db.rawQuery("SELECT * from " + SBBJ_TABLE_NAME +" where SSSB='"+id+"'",null);
        while (c.moveToNext()){
            SbbjModel model = new SbbjModel();
            model.sssb = id;
            model.id = c.getString(c.getColumnIndex("GUID"));
            model.bjmc = c.getString(c.getColumnIndex("BJMC"));
            model.bjxh = c.getString(c.getColumnIndex("BJXH"));
            model.sccj = c.getString(c.getColumnIndex("SCCJ"));
            model.dw = c.getString(c.getColumnIndex("DW"));
            model.sl = c.getInt(c.getColumnIndex("SL"));
            model.bz = c.getString(c.getColumnIndex("BZ"));
            model.zycs = c.getString(c.getColumnIndex("ZYCS"));
            model.sjc = c.getString(c.getColumnIndex("SJC"));
            model.scrq = c.getString(c.getColumnIndex("SCRQ"));
            model.cfwz = c.getString(c.getColumnIndex("CFWZ"));
            model.ssdw = c.getString(c.getColumnIndex("SSDW"));
            model.timepoint = c.getString(c.getColumnIndex("TIMEPOINT"));
            model.sslb = c.getString(c.getColumnIndex("SSLB"));
            model.wzbm = c.getString(c.getColumnIndex("WZBM"));
            model.ggxh = c.getString(c.getColumnIndex("GGXH"));
            model.dj = c.getString(c.getColumnIndex("DJ"));
            model.crkjl = c.getString(c.getColumnIndex("CRKJL"));
            model.de = c.getString(c.getColumnIndex("DE"));
            list.add(model);
        }
        return list;
    }

    //设备资料 Sbzl
    public List<SbzlModel> getSbzlById(String id){
        List<SbzlModel> list = new ArrayList<SbzlModel>();
        Cursor c = db.rawQuery("SELECT * from " + SBZL_TABLE_NAME +" where SSSB='"+id+"'",null);
        while (c.moveToNext()){
            SbzlModel model = new SbzlModel();
            model.sssb = id;
            model.id = c.getString(c.getColumnIndex("GUID"));
            model.zlmc = c.getString(c.getColumnIndex("ZLMC"));
            model.zllx = c.getString(c.getColumnIndex("ZLLX"));
            model.wdlx = c.getString(c.getColumnIndex("WDLX"));
            model.wddx = c.getString(c.getColumnIndex("WDDX"));
            model.scr = c.getString(c.getColumnIndex("SCR"));
            model.scrq = c.getString(c.getColumnIndex("SCRQ"));
            model.bz = c.getString(c.getColumnIndex("BZ"));
            model.sjc = c.getString(c.getColumnIndex("SJC"));
            model.zl = c.getString(c.getColumnIndex("zl"));
            model.dw = c.getString(c.getColumnIndex("DW"));
            model.sbmc = c.getString(c.getColumnIndex("SBMC"));
            model.zllxmc = c.getString(c.getColumnIndex("ZLLXMC"));
            model.zlbb = c.getString(c.getColumnIndex("ZLBB"));
            model.scrmc = c.getString(c.getColumnIndex("SCRMC"));
            model.fjid = c.getString(c.getColumnIndex("FJID"));
            model.timepoint = c.getString(c.getColumnIndex("timePOINT"));
            list.add(model);
        }
        return list;
    }

    // 11.5 异常记录
    public List<YcjlModel> getYcjlById(String id){
        List<YcjlModel> list = new ArrayList<YcjlModel>();
        //需修改外键
        Cursor c = db.rawQuery("SELECT * from " + YCGL_TABLE_NAME +" where equip_id='"+id+"'",null);
        while (c.moveToNext()){
            YcjlModel model = new YcjlModel();
            //缺一栏外键
            model.id = c.getString(c.getColumnIndex("GUID"));
            model.abn_time = c.getString(c.getColumnIndex("abn_time"));
            model.abn_source = c.getString(c.getColumnIndex("abn_source"));
            model.abn_describe = c.getString(c.getColumnIndex("abn_describe"));
            model.dealtype = c.getString(c.getColumnIndex("dealtype"));
            model.chargeman = c.getString(c.getColumnIndex("chargeman"));
            model.equip_id = id;
            model.process_time = c.getString(c.getColumnIndex("process_time"));
            model.abn_report = c.getString(c.getColumnIndex("abn_report"));
            model.inspect_guid = c.getString(c.getColumnIndex("inspect_guid"));
            model.patrol_guid = c.getString(c.getColumnIndex("patrol_guid"));
            model.related_id = c.getString(c.getColumnIndex("related_id"));
            model.def_str1 = c.getString(c.getColumnIndex("def_str1"));
            model.def_str2 = c.getString(c.getColumnIndex("def_str2"));
            model.def_str3 = c.getString(c.getColumnIndex("def_str3"));
            model.createtime = c.getString(c.getColumnIndex("createtime"));
            model.updatetime = c.getString(c.getColumnIndex("updatetime"));
            list.add(model);
        }
        return list;
    }

    //缺陷记录
    public List<QxjlModel> getQxjlById(String id){
        List<QxjlModel> list = new ArrayList<QxjlModel>();
        //需修改外键
        Cursor c = db.rawQuery("SELECT * from " + QXGL_TABLE_NAME +" where equip_id='"+id+"'",null);
        while (c.moveToNext()){
            QxjlModel model = new QxjlModel();
            //缺一栏外键
            model.id = c.getString(c.getColumnIndex("GUID"));
            model.defect_time = c.getString(c.getColumnIndex("defect_time"));
            model.level_guid = c.getString(c.getColumnIndex("level_guid"));
            model.source_guid = c.getString(c.getColumnIndex("source_guid"));
            model.equip_id = id;
            model.type_guid = c.getString(c.getColumnIndex("type_guid"));
            model.defect_describe = c.getString(c.getColumnIndex("defect_describe"));
            model.chargeman = c.getString(c.getColumnIndex("chargeman"));
            model.process_time = c.getString(c.getColumnIndex("process_time"));
            model.defect_report = c.getString(c.getColumnIndex("defect_report"));
            model.inspect_guid = c.getString(c.getColumnIndex("inspect_guid"));
            model.patrol_guid = c.getString(c.getColumnIndex("patrol_guid"));
            model.related_id = c.getString(c.getColumnIndex("related_id"));
            model.def_str1 = c.getString(c.getColumnIndex("def_str1"));
            model.def_str2 = c.getString(c.getColumnIndex("def_str2"));
            model.def_str3 = c.getString(c.getColumnIndex("def_str3"));
            model.createtime = c.getString(c.getColumnIndex("createtime"));
            model.updatetime = c.getString(c.getColumnIndex("updatetime"));
            list.add(model);
        }
        return list;
    }
    //检修记录
    public List<JxjlModel> getJxjlById(String id){
        List<JxjlModel> list = new ArrayList<JxjlModel>();
        //需修改外键
        Cursor c = db.rawQuery("SELECT * from " + JXJL_TABLE_NAME +" where SSSB='"+id+"'",null);
        while (c.moveToNext()){
            JxjlModel model = new JxjlModel();
            //缺一栏外键
            model.id = c.getString(c.getColumnIndex("GUID"));
            model.jxsj = c.getString(c.getColumnIndex("JXSJ"));
            model.jxlx = c.getString(c.getColumnIndex("JXLX"));
            model.jxdj = c.getString(c.getColumnIndex("JXDJ"));
            model.zrr = c.getString(c.getColumnIndex("ZRR"));
            model.zyzds = c.getString(c.getColumnIndex("ZYZDS"));
            model.jxzl = c.getString(c.getColumnIndex("JXZL"));
            model.sssb = id;
            model.dw = c.getString(c.getColumnIndex("DW"));
            model.sjly = c.getString(c.getColumnIndex("SJLY"));
            model.timepoint = c.getString(c.getColumnIndex("timepoint"));
            model.jxnr = c.getString(c.getColumnIndex("JXNR"));
            list.add(model);
        }
        return list;
    }

    //动作记录
    public List<DzjlModel> getDzjlById(String id){
        List<DzjlModel> list = new ArrayList<DzjlModel>();
        //需修改外键
        Cursor c = db.rawQuery("SELECT * from " + DZJL_TABLE_NAME +" where SBMC='"+id+"'",null);
        while (c.moveToNext()){
            DzjlModel model = new DzjlModel();
            //缺一栏外键
            model.id = c.getString(c.getColumnIndex("GUID"));
            model.obj_caption = c.getString(c.getColumnIndex("OBJ_CAPTION"));
            model.cls_id = c.getString(c.getColumnIndex("CLS_ID"));
            model.flowstatus = c.getString(c.getColumnIndex("FLOWSTATUS"));
            model.obj_dispidx = c.getString(c.getColumnIndex("OBJ_DISPIDX"));
            model.sbid = c.getString(c.getColumnIndex("SBID"));
            model.sbmc = id;
            model.dzsj = c.getString(c.getColumnIndex("DZSJ"));
            model.dzyy = c.getString(c.getColumnIndex("DZYY"));
            model.dzjg = c.getString(c.getColumnIndex("DZJG"));
            model.sfbzy = c.getString(c.getColumnIndex("SFBZY"));
            model.sjjs = c.getString(c.getColumnIndex("SJJS"));
            model.ttbglj = c.getString(c.getColumnIndex("TTBGLJ"));
            model.lbtlj = c.getString(c.getColumnIndex("LBTLJ"));
            model.sfwh = c.getString(c.getColumnIndex("SFWH"));
            model.djr = c.getString(c.getColumnIndex("DJR"));
            model.djsj = c.getString(c.getColumnIndex("DJSJ"));
            model.dw = c.getString(c.getColumnIndex("DW"));
            model.timepoint = c.getString(c.getColumnIndex("timepoint"));
            list.add(model);
        }
        return list;
    }
    //投退记录

    public List<TtjlModel> getTtjlById(String id){
        List<TtjlModel> list = new ArrayList<TtjlModel>();
        //需修改外键
        Cursor c = db.rawQuery("SELECT * from " + TTJL_TABLE_NAME +" where SBMC='"+id+"'",null);
        while (c.moveToNext()){
            TtjlModel model = new TtjlModel();
            //缺一栏外键
            model.id = c.getString(c.getColumnIndex("GUID"));
            model.sscz = c.getString(c.getColumnIndex("SSCZ"));
            model.sbxhguid = c.getString(c.getColumnIndex("SBXHGUID"));
            model.sbmc = id;
            model.bbhsbmc = c.getString(c.getColumnIndex("BBHSBMC"));
            model.tcsj = c.getString(c.getColumnIndex("TCSJ"));
            model.trsj = c.getString(c.getColumnIndex("TRSJ"));
            model.tczsj = c.getString(c.getColumnIndex("TCZSJ"));
            model.sfgztc = c.getString(c.getColumnIndex("SFGZTC"));
            model.tcyy = c.getString(c.getColumnIndex("TCYY"));
            model.tcgnfw = c.getString(c.getColumnIndex("TCGNFW"));
            model.ttbg = c.getString(c.getColumnIndex("TTBG"));
            model.djr = c.getString(c.getColumnIndex("DJR"));
            model.djsj = c.getString(c.getColumnIndex("DJSJ"));
            model.sjc = c.getString(c.getColumnIndex("SJC"));
            model.dw = c.getString(c.getColumnIndex("DW"));
            model.timepoint = c.getString(c.getColumnIndex("timepoint"));
            list.add(model);
        }
        return list;
    }

    //异动记录
    public List<YdjlModel> getYdjlById(String id){
        List<YdjlModel> list = new ArrayList<YdjlModel>();
        //需修改外键
        Cursor c = db.rawQuery("SELECT * from " + YDJL_TABLE_NAME +" where SBMC='"+id+"'",null);
        while (c.moveToNext()){
            YdjlModel model = new YdjlModel();
            //缺一栏外键
            model.id = c.getString(c.getColumnIndex("GUID"));
            model.sbmc = id;
            model.ydsj = c.getString(c.getColumnIndex("YDSJ"));
            model.ydyy = c.getString(c.getColumnIndex("YDYY"));
            model.ydnr = c.getString(c.getColumnIndex("YDNR"));
            model.zrr = c.getString(c.getColumnIndex("ZRR"));
            model.ydbg = c.getString(c.getColumnIndex("YDBG"));
            model.dw = c.getString(c.getColumnIndex("DW"));
            model.timepoint = c.getString(c.getColumnIndex("timepoint"));
            list.add(model);
        }
        return list;
    }

    public LedgerModel getLedgerbyId(String id) {
        LedgerModel model = new LedgerModel();
        Cursor c = db.rawQuery("SELECT * from " + SBTZ_TABLE_NAME + " where GUID='" + id + "'", null);
        if (c.moveToNext()) {
            model.sbmc = c.getString(c.getColumnIndex("SBMC"));
            model.guid = id;
            model.ssdc = c.getString(c.getColumnIndex("SSDC"));
            model.zy = c.getString(c.getColumnIndex("ZY"));
            model.jz = c.getString(c.getColumnIndex("JZ"));
            model.xt = c.getString(c.getColumnIndex("XT"));
            model.sbmc = c.getString(c.getColumnIndex("SBMC"));
            model.sblx = c.getString(c.getColumnIndex("SBLX"));
            model.sbxh = c.getString(c.getColumnIndex("SBXH"));
            model.sccj = c.getString(c.getColumnIndex("SCCJ"));
            model.ycsb = c.getString(c.getColumnIndex("YCSB"));
            model.azwz = c.getString(c.getColumnIndex("AZWZ"));
            model.dydj = c.getString(c.getColumnIndex("DYDJ"));
            model.scrq = c.getString(c.getColumnIndex("SCRQ"));
            model.tyrq = c.getString(c.getColumnIndex("TYRQ"));
            model.sjsm = c.getString(c.getColumnIndex("SJSM"));
//            model.jyzq = c.getString(c.getColumnIndex("JYZQ"));
            model.kksbm = c.getString(c.getColumnIndex("KKSBM"));
            model.zrr = c.getString(c.getColumnIndex("ZRR"));
            model.yxzt = c.getString(c.getColumnIndex("YXZT"));
            model.tdlx = c.getString(c.getColumnIndex("TDLX"));
            model.sfgd = c.getString(c.getColumnIndex("SFGD"));
            model.gdr = c.getString(c.getColumnIndex("GDR"));
            model.gdrq = c.getString(c.getColumnIndex("GDRQ"));
            model.gdrdw = c.getString(c.getColumnIndex("GDRDW"));
//            model.sfsgyybz = c.getString(c.getColumnIndex("SFSJYYBZ"));
            model.timepoint = c.getString(c.getColumnIndex("TIMEPOINT"));
            model.zxt = c.getString(c.getColumnIndex("ZXT"));
            model.sjc = c.getString(c.getColumnIndex("SJC"));
            model.sspg = c.getString(c.getColumnIndex("SSPG"));
            model.qbjjzq = c.getString(c.getColumnIndex("QBJYZQ"));
            model.bfjjzq = c.getString(c.getColumnIndex("BFJYZQ"));
            return model;
        }
        return null;
    }


    /**
     * 根据设备id 返回其附属设备列表
     */
    public List<FssbModel> getFssbById(String id) {
        List<FssbModel> list = new ArrayList<FssbModel>();
        Cursor c = db.rawQuery("SELECT * from " + FSSB_TABLE_NAME + " where SSSB='" + id + "'", null);
        while (c.moveToNext()) {
            FssbModel model = new FssbModel();
            model.sssb = id;
            model.id = c.getString(c.getColumnIndex("GUID"));
            model.fssb = c.getString(c.getColumnIndex("FSSB"));
            model.sbxh = c.getString(c.getColumnIndex("SBXH"));
            model.sccj = c.getString(c.getColumnIndex("SCCJ"));
            model.tyrq = c.getString(c.getColumnIndex("TYRQ"));
            model.sl = c.getInt(c.getColumnIndex("SL"));
            model.ssdw = c.getString(c.getColumnIndex("DW"));
            model.zyjscs = c.getString(c.getColumnIndex("ZYJSCS"));
//            model.sctp = c.getString(c.getColumnIndex("SCTP"));

            model.sjc = c.getString(c.getColumnIndex("SJC"));
            model.ssdw = c.getString(c.getColumnIndex("SSDW"));
            list.add(model);
        }
        return list;
    }

    /**
     * 获取用户历史任务列表
     *
     * @return
     */
    public List<TaskModel> getHisTask() {
        List<TaskModel> list = new ArrayList<TaskModel>();
        // TODO: 2015/11/4 姓名应该为当前登录人姓名
        String name = "aa";
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        Long nowTime = calendar.getTimeInMillis();
        try {
            calendar.setTime(formatter.parse(str));
        } catch (ParseException e) {
            e.printStackTrace();
        }


        String sql = "SELECT * from " + TASK_TABLE_NAME + " where si_person_a='" + name + "' " + "or " + "si_person_b='" + name + "'";
        Log.i(TAG, sql);
        Cursor c = db.rawQuery(sql, null);
        while (c.moveToNext()) {
            String taskDate = c.getString(c.getColumnIndex("task_date")).replace("-", ".").substring(0, 8);
            try {
                Log.i(TAG, taskDate);
                calendar.setTime(formatter.parse(taskDate));
                Long taskTime = calendar.getTimeInMillis();
                if (taskTime < nowTime) {
                    TaskModel model = new TaskModel();
                    model.id = c.getString(c.getColumnIndex("GUID"));
                    model.si_new_person_a = name;
                    model.task = c.getString(c.getColumnIndex("task"));
                    model.abnormal_num = getTaskAbnormalNum(model.id);
                    list.add(model);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return list;
    }

    /**
     * 获取用户今日任务列表
     *
     * @return
     */
    public List<TaskModel> getTodayTask() {
        List<TaskModel> list = new ArrayList<TaskModel>();
        // TODO: 2015/11/4 姓名应该为当前登录人姓名，日期应该为系统当前日期
        String name = "aa";
        String date = "2015-10-15";

        String sql = "SELECT * from " + TASK_TABLE_NAME + " where (si_person_a='" + name + "' " + "or " + "si_person_b='" + name + "') and task_date LIKE " + "'" + date + "%'";
        Log.i(TAG, sql);
        Cursor c = db.rawQuery(sql, null);
        while (c.moveToNext()) {
            TaskModel model = new TaskModel();
            model.id = c.getString(c.getColumnIndex("GUID"));
            model.task = c.getString(c.getColumnIndex("task"));
            model.task_status = c.getString(c.getColumnIndex("task_status"));
            model.si_person_a = name;
            String b_name = c.getString(c.getColumnIndex("si_person_b"));
            model.si_person_b = name == b_name ? c.getString(c.getColumnIndex("si_person_a")) : b_name;
            model.total_pos_num = getTaskTotalPosNum(model.id);
            model.total_si_num = getTaskCheckedItemNum(model.id);
            model.total_unsi_num = model.total_position_num - model.total_si_num;
            model.abnormal_num = getTaskAbnormalNum(model.id);
            list.add(model);
        }
        return list;
    }

    /**
     * 根据任务id获取该任务的位置总数
     *
     * @param taskId 任务id
     * @return 项目总数
     */
    public int getTaskTotalPosNum(String taskId) {
        int num = 0;
        String sql = "SELECT total_pos_num from " + TASK_RESULT_STATISTICAL_TABLE_NAME + " where task_id ='" + taskId + "'";
        Log.i(TAG, sql);
        Cursor c = db.rawQuery(sql, null);
        if (c.moveToNext()) {
            num = c.getInt(0);
        }
        return num;
    }

    /**
     * 根据任务id获取该任务已点检项目数
     *
     * @param taskId
     * @return
     */
    public int getTaskCheckedItemNum(String taskId) {
        int num = 0;
        String sql = "SELECT si_person from " + TASK_RESULT_TABLE_NAME + " where task_id='" + taskId + "'";
        Log.i(TAG, sql);
        Cursor c = db.rawQuery(sql, null);
        while (c.moveToNext()) {
            String name = c.getString(0);
            if (!name.equals("") && name != null)
                num++;
        }
        return num;
    }

    /**
     * 根据任务id获取该任务已异常项目数
     *
     * @param taskId
     * @return
     */
    public int getTaskAbnormalNum(String taskId) {
        int num = 0;
        String sql = "SELECT is_abnormal from " + TASK_RESULT_TABLE_NAME + " where task_id='" + taskId + "'";
        Log.i(TAG, sql);
        Cursor c = db.rawQuery(sql, null);
        while (c.moveToNext()) {
            String isAbnormal = c.getString(0);
            if (isAbnormal != null && isAbnormal.equals("T"))
                num++;
        }
        return num;
    }

    /**
     * 获取历史点检任务的点检到位率
     *
     * @param taskId
     * @return
     */
    public float getHisTaskInplaceTate(String taskId) {
        float rate = 0;
        // TODO: 2015/11/6 待开发

        return rate;
    }

    /**
     * 获取某个人物的位置列表
     *
     * @param planId
     * @return
     */
    public List<PositionModel> getPositionList(String planId) {
        List<PositionModel> list = new ArrayList<PositionModel>();
        String sql = "SELECT * from TB_SI_PLAN_PLACE_DETAIL where plan_id='" + planId + "'";
        Log.i(TAG, sql);
        Cursor c = db.rawQuery(sql, null);
        while (c.moveToFirst()) {
            PositionModel model = new PositionModel();
            model.id = c.getString(c.getColumnIndex("place_id"));
            model.ins_position_name = c.getString(c.getColumnIndex("place"));
            model.dev_num_today = getPosTotalDevNum(model.id);
        }
        return list;
    }

    /**
     * 获取指定位置今天点检设备数
     *
     * @return
     */
    public int getPosTotalDevNum(String placeId) {
        int result = 0;
        String sql = "SELECT count(*) from " + SI_PLAN_DEV_DETAIL_TABLE_NAME + " where place_id='" + placeId + "'";
        Log.i(TAG, sql);
        Cursor c = db.rawQuery(sql, null);
        result = c.getInt(0);
        return result;
    }


    public int getPosTotalItemNum(String placeId) {
        // TODO: 2015/11/6  待开发
        int result = 0;
//        String sql =  "SELECT GUID from " + SI_PLAN_TASK_DETAIL_TABLE_NAME + " where place_id='" + placeId + "'";
//        Log.i(TAG,sql);
//        Cursor c = db.rawQuery(sql,null);
//        result = c.getInt(0);
        return result;
    }


    public List<DeviceModel> getPosDeviceModel(String placeId) {
        List<DeviceModel> list = new ArrayList<DeviceModel>();
        String sql = "SELECT DISTINCT dev_id,dev_name from " + SI_PLAN_DEV_DETAIL_TABLE_NAME + " where place_id='" + placeId + "'";
        Log.i(TAG, sql);
        Cursor c = db.rawQuery(sql, null);
        while (c.moveToNext()) {
            DeviceModel model = new DeviceModel();
            model.equipName = c.getString(c.getColumnIndex("dev_name"));
            model.id = c.getString(c.getColumnIndex("dev_id"));
            Map<Integer,List<ItemModel>> map = new HashMap<>();
            model.todayCheckList = map.get(1);
            model.weekCheckList = map.get(2);
            model.monthCheckList = map.get(3);
        }
        return list;
    }

    public Map<Integer, List<ItemModel>> getAllDevItem(String devId) {
        Map<Integer, List<ItemModel>> map = new HashMap<Integer, List<ItemModel>>();
        List<ItemModel> dayItemList = new ArrayList<>();
        List<ItemModel> weekItemList = new ArrayList<>();
        List<ItemModel> monthItemList = new ArrayList<>();

        String sql = "SELECT * from " + SI_PLAN_TASK_DETAIL_TABLE_NAME + "," + TASK_RESULT_TABLE_NAME + " where TB_SI_TASK_RESULT.dev_id='" + devId + "'";
        Log.i(TAG, sql);
        Cursor c = db.rawQuery(sql, null);
        while (c.moveToNext()) {
            ItemModel model = new ItemModel();
            model.planId = c.getString(c.getColumnIndex("TB_SI_PLAN_TASK_DETAIL.GUID"));
            model.resultId = c.getString(c.getColumnIndex("TB_SI_TASK_RESULT.GUID"));
            model.project = c.getString(c.getColumnIndex("TB_SI_PLAN_TASK_DETAIL.position"));
            model.content = c.getString(c.getColumnIndex("TB_SI_PLAN_TASK_DETAIL.content"));
            model.standard = c.getString(c.getColumnIndex("TB_SI_PLAN_TASK_DETAIL.standard"));
            String ismust = c.getString(c.getColumnIndex("TB_SI_TASK_RESULT.is_must_today")).trim();
            model.isMust = ismust.equals("T");
            // TODO: 2015/11/6 上次点检时间取值待确定
            model.preCheckDate = "";

            model.unit = c.getString(c.getColumnIndex("TB_SI_PLAN_TASK_DETAIL.unit"));
            model.min = c.getString(c.getColumnIndex("TB_SI_PLAN_TASK_DETAIL.standard_min"));
            model.max = c.getString(c.getColumnIndex("TB_SI_PLAN_TASK_DETAIL.standard_max"));
            model.num_result = c.getInt(c.getColumnIndex("TB_SI_TASK_RESULT.num_result"));
            model.desc_result = c.getString(c.getColumnIndex("TB_SI_TASK_RESULT.desc_result"));
            model.checkPerson = c.getString(c.getColumnIndex("TB_SI_TASK_RESULT.si_person"));
            model.isAbnormal = c.getString(c.getColumnIndex("TB_SI_TASK_RESULT.is_abnormal")).trim().equals("T");
            model.cycle = c.getString(c.getColumnIndex("TB_SI_PLAN_TASK_DETAIL.cycle"));
            if (model.checkPerson.equals("") || model.checkPerson == null)
                model.checkResult = "未检查";
            else if (!model.isAbnormal)
                model.checkResult = "正常";
            else
                model.checkResult = "异常";

            // TODO: 2015/11/6  数据库中周期存方式不明确，
            if(model.cycle.equals("每天一次")){
                dayItemList.add(model);
            }else if(model.cycle.equals("每周一次")){
                weekItemList.add(model);
            }else if(model.cycle.equals("每月一次")){
                monthItemList.add(model);
            }

            map.put(1,dayItemList);
            map.put(2,weekItemList);
            map.put(3,monthItemList);

        }
        return map;
    }
}