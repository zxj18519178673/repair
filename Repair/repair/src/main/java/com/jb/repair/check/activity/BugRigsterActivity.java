package com.jb.repair.check.activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.Toast;

import com.jb.repair.BaseActivity;
import com.jb.repair.R;
import com.jb.repair.ledger.db.Db;
import com.jb.repair.util.TimeUtil;
import com.jb.repair.view.TitleView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BugRigsterActivity extends BaseActivity {


    private TitleView titleView;
    private Spinner sp1,sp2;
    private ImageView imageView;
    private Db db;
    private PopupWindow window;
    HashMap<String ,String> map1;
   // HashMap<String ,String> map2;
    List<String> list1;
    List<String> list2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bug_rigster);

        db= Db.getInstance(this);
        list1 = new ArrayList<String>();
        list2 = new ArrayList<String>();
        map1 = new HashMap<String,String>();
     //   map2 = new HashMap<String,String>();

        initview();
    }

    public void initview() {
        titleView = (TitleView) findViewById(R.id.titleView);
        //缺陷等级
        sp1 = (Spinner) findViewById(R.id.sp1);
        //缺陷类型
        sp2 = (Spinner) findViewById(R.id.sp2);
        //附件
        imageView = (ImageView) findViewById(R.id.iv_iv);

        //缺陷等级
        Cursor cursor = db.getCursor("Select * from DEFECT_LEVEL");
        while (cursor.moveToNext()){
            String key = cursor.getString(cursor.getColumnIndex("defect_level"));
            String value = cursor.getString(cursor.getColumnIndex("GUID"));
            list1.add(key);
            map1.put(key,value);
        }
        Log.e("Tag", list1.toString());
        Log.e("Tag", String.valueOf(map1.entrySet()));
        ArrayAdapter arrayAdapter1 = new ArrayAdapter(this, R.layout.list_item,list1);
        arrayAdapter1.setDropDownViewResource(android.R.layout.select_dialog_item);
        sp1.setAdapter(arrayAdapter1);
        //缺陷类型

        Cursor cursor1 = db.getCursor("Select distinct QXLX from TB_DEV_HD_QXLXWH");
        while (cursor1.moveToNext()){
//            String key = cursor1.getString(cursor1.getColumnIndex("QXLX"));
            String key = cursor1.getString(0);
           // String value = cursor.getString(cursor.getColumnIndex("GUID"));
            list2.add(key);
            Log.e("Tag","执行了");
          //  map2.put(key,value);
        }
        Log.e("Tag", list2.toString());
        //Log.e("Tag", String.valueOf(map2.entrySet()));
        ArrayAdapter arrayAdapter2 = new ArrayAdapter(this, R.layout.list_item,list2);
        arrayAdapter2.setDropDownViewResource(android.R.layout.select_dialog_item);
        sp2.setAdapter(arrayAdapter2);


        titleView.setOnClickListener(new TitleView.OnTitleViewClickListener() {
            @Override
            public void onLeftImageClick(View view) {
                super.onLeftImageClick(view);
                finish();
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(BugRigsterActivity.this, "点击成功", Toast.LENGTH_SHORT).show();

                View popView = LayoutInflater.from(BugRigsterActivity.this).inflate(R.layout.layout, null);
                window = new PopupWindow(popView, 200, LinearLayout.LayoutParams.WRAP_CONTENT, true);
                window.setFocusable(true);
                ColorDrawable dw = new ColorDrawable(0x00000000);
                window.setBackgroundDrawable(dw);
                window.showAsDropDown(v);

                popView.findViewById(R.id.tv_local).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        window.dismiss();

                        Intent intentGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(intentGallery, 1);//这里不一定是1，可以是任意int型的数据
                    }
                });
                popView.findViewById(R.id.tv_camera).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        window.dismiss();

                        /* 选择拍照 核心代码*/
                        Intent intentCamera = new Intent();
                        intentCamera.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                        intentCamera.addCategory(Intent.CATEGORY_DEFAULT);
                        String path = "/sdcard/Repair/upload/";
                        File file = new File(path); //path代表拍照结束后照片要保存到的目录，如/sdcard/upload/image/
                        if (!file.exists()) {
                            file.mkdirs();
                        }
                        // String uploadImageFileName = System.currentTimeMillis() + ".jpg";
                        String uploadImageFileName = TimeUtil.generateFilenameString() + ".jpg";
                        Log.e("Tag", uploadImageFileName);
                        //uploadImageFileName 代表照片文件的名称
                        String uploadImagePath = path + uploadImageFileName;
                        file = new File(uploadImagePath);
                        intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                        startActivityForResult(intentCamera, 0);//这里不一定是0，可以是任意int型的数据

                    }
                });

            }
        });
    }
    /* 拍照返回后在 onActivityResult()方法中进行处理 */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 0:
                /* 在这里对拍照的图片进行处理，如上传*/

                break;

            case 1:
                /* 在这里对选取的图片进行处理，如上传*/
                if (data != null) {
                    Uri uri = data.getData();//这个uri指向刚才选中的图片
                    if (uri != null) {
                        //获取该图片的具体路径
                        Cursor cursor = BugRigsterActivity.this.getContentResolver().query(uri, null, null, null, null);
                        if (cursor.moveToFirst()) {
                            String url = cursor.getString(1);//url会指向该图片
                            Log.e("Tag",url);

                            //未对照片进行处理

                            /* 接下来就可以对该图片进行处理了，如显示到指定位置 */
                            // BitmapFactory.Options option = new BitmapFactory.Options();
                            //此处需要一个ImageTools
                            // option.inSampleSize = ImageTools.getImageScale(url);

                            // Bitmap bm = BitmapFactory.decodeFile(url, option);
                            Bitmap bm = BitmapFactory.decodeFile(url);
                            if (bm != null) {
                                imageView.setImageBitmap(bm);//imageView代表要显示图片的view
                            }
                        }

                    }
                    break;
                }
        }

    }
}
