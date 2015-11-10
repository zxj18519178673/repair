package com.jb.repair;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jb.repair.common.AppConfig;
import com.jb.repair.ledger.db.Db;
import com.jb.repair.setting.ConfigActivity;
import com.jb.repair.util.MD5;
import com.jb.repair.util.SharePreferenceUtil;
import com.jb.repair.util.StringUtils;
import com.jb.repair.view.TitleView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;


public class LoginActivity extends BaseActivity {
    TitleView titleView;
    EditText tv_name,tv_pass;
    CheckBox ck_auto,ck_pass;
    TextView tv_login;
    private RequestQueue mQueue;
    Db db;
    SharePreferenceUtil sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //判断数据库是否存在


        File file = new File(AppConfig.DB_PATh);
        if(!file.exists()){
            Log.e("Tag","数据库不存在");
            Toast.makeText(this, "数据库不存在，跳转配置页面", Toast.LENGTH_SHORT);
            Intent intent = new Intent(LoginActivity.this,ConfigActivity.class);
            startActivity(intent);
            finish();
        }
        Log.e("Tag","数据库存在");
        sp = SharePreferenceUtil.getInstance(this,"pass.txt");
        db = Db.getInstance(this);
        mQueue = Volley.newRequestQueue(this);
        initviews();
    }

    private void initviews() {
        titleView = (TitleView) findViewById(R.id.titleView);
        tv_name = (EditText) findViewById(R.id.tv_name);
        tv_pass = (EditText) findViewById(R.id.tv_pass);
        ck_auto = (CheckBox) findViewById(R.id.ck_auto);
        ck_pass = (CheckBox) findViewById(R.id.ck_pass);
        tv_login = (TextView) findViewById(R.id.tv_login);

        String name1 = sp.getString("name", "");
        tv_name.setText(name1);

        boolean ck_pass1 = sp.getBoolean("ck_pass",false);
        boolean ck_auto1 = sp.getBoolean("ck_auto",false);
        if(ck_pass1){
            ck_pass.setChecked(true);
            String pass1 = sp.getString("pass","");
            tv_pass.setText(pass1);
            if(ck_auto1){
                ck_auto.setChecked(true);
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
            }
        }



        tv_login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String name = tv_name.getText().toString();
                String pass = tv_pass.getText().toString();
                if(StringUtils.isNullOrEmpty(name) || StringUtils.isNullOrEmpty(pass)){
                    Toast.makeText(LoginActivity.this,"用户名和密码不能为空", Toast.LENGTH_SHORT).show();
                }
                else {
                    String md5_pass = MD5.parse(pass);
                    Log.e("Tag", md5_pass);
                    String db_pass = db.getName("LOGO_PASS","TB_SYS_PERSON","LOGO_ID",name);
                    Log.e("Tag",db_pass);
                    if(md5_pass.equals(db_pass)){
                        Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                        sp.setString("name", name);
                        if(ck_pass.isChecked()){
                            sp.setBoolean("ck_pass", true);
                            sp.setString("pass",pass);
                            if(ck_auto.isChecked()){
                                sp.setBoolean("ck_auto",true);
                            }else {
                                sp.setBoolean("ck_auto",false);
                            }
                        }
                        else {
                            if(ck_auto.isChecked()){
                                Toast.makeText(LoginActivity.this,"自动登录需要记住密码",Toast.LENGTH_SHORT).show();
                                return;
                            }
                            sp.setBoolean("ck_pass",false);
                        }
                        //判断是否需要数据同步
                       // invokeInterfaceDemo();


                        //跳转页面
                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Toast.makeText(LoginActivity.this,"用户名或密码错误",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        titleView.setOnClickListener(new TitleView.OnTitleViewClickListener() {
            @Override
            public void onRightImage1Click(View view) {
                super.onRightImage1Click(view);
                Intent intent = new Intent(LoginActivity.this, ConfigActivity.class);
                startActivity(intent);
            }
        });
    }
    private void invokeInterfaceDemo() {
        String url = "192.168.1.1/xxx/xxx";//接口地址

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {//s为从服务器端获取的数据
                try {
                    JSONObject object = new JSONObject(s);
                    if(object.has("code")&&object.getInt("code")==0) {//code=0表示正常返回数据
					/* 在这里对接收到的数据进行处理 */
                        // 判断 需要同步数据库 同步 跳转

                        //不需要同步数据库 跳转


                    } else {
                        Log.i("TAG","返回结果中没有code或者code不为0");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.i("TAG","返回结果不是JSON格式");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
			/* 在这里进行错误处理 */


            }
        }) {
            /* 该方法用于发送客户端参数 */
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("param1", "");
                map.put("param2", "");
                return map;
            }
            /* 该方法用于转换编码格式，不要修改 */
            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                try {
                    String s = new String(response.data,"utf-8");
                    return Response.success(s, HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError());
                }
            }
        };
        mQueue.add(request);
    }


}
