package com.jb.repair.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jb.repair.R;

import java.io.File;

/**
 * Created by gaobin on 2015/10/27.
 */
public class InfoTextView extends LinearLayout implements View.OnClickListener {
    private TextView tv_key;
    private TextView tv_value;
    private ImageView iv;
    private Context mContext;
    private OnClickListener clickListener;

    public InfoTextView(Context context) {
        super(context);
        init(context, null);
    }

    public InfoTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public InfoTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public InfoTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attr) {
        LayoutInflater.from(context).inflate(R.layout.info_textview, this, true);
        mContext = context;
        tv_key = (TextView) findViewById(R.id.tv1);
        tv_value = (TextView) findViewById(R.id.tv2);
        iv = (ImageView) findViewById(R.id.iv);

        TypedArray a = context.obtainStyledAttributes(attr, R.styleable.InfoTextView);
        tv_key.setText(a.getString(R.styleable.InfoTextView_text1) + ":");

        if (a.getBoolean(R.styleable.InfoTextView_isImg, false)) {
            iv.setVisibility(VISIBLE);
            tv_value.setVisibility(GONE);
        } else {
            tv_value.setVisibility(VISIBLE);
            iv.setVisibility(GONE);
            tv_value.setText(a.getString(R.styleable.InfoTextView_text2));
        }

        if(a.getBoolean(R.styleable.InfoTextView_isdoc,false)){
            tv_value.setTextColor(context.getResources().getColor(R.color.title_bg));
            tv_value.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG ); //下划线
            tv_value.getPaint().setAntiAlias(true);//抗锯齿
            tv_value.setBackgroundColor(context.getResources().getColor(R.color.gray_bg));
        }

        a.recycle();
    }

    public void setText(String value) {
        tv_value.setText(value);
    }

    public void setImagePath(String path) {
        iv.setVisibility(VISIBLE);
        tv_value.setVisibility(GONE);
        if(path == null||path.equals(""))
            return;
        File f = new File(path);
        if (f.exists()) {
            Bitmap bm = BitmapFactory.decodeFile(path);
            iv.setImageBitmap(bm);
        } else {
        }
    }


    public void setOnClickListener(
            OnClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public void onClick(View v) {
        if (clickListener == null) {
            return;
        }
        if (v.getId() == R.id.titile_left_imageBack
                || v.getId() == R.id.title_left) {
            clickListener.onTextClick(this.tv_value);
        }
    }

    public static class OnClickListener {
        /**
         */
        public void onTextClick(View view) {
        }
    }
}
