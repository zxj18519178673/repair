package com.jb.repair.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jb.repair.R;

/**
 * Created by gaobin on 2015/10/26.
 */
public class AttriTextView extends LinearLayout {
    private TextView tv_key;
    private TextView tv_value;
    private ImageView iv_imp;
    private View line;
    private Context mContext;

    public AttriTextView(Context context) {
        super(context);
        init(context, null);
    }

    public AttriTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public AttriTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attr) {
        LayoutInflater.from(context).inflate(R.layout.attri_textview, this, true);
        mContext = context;
        tv_key = (TextView) findViewById(R.id.tv_key);
        tv_value = (TextView) findViewById(R.id.tv_value);
        iv_imp = (ImageView) findViewById(R.id.iv_imp);
        line = findViewById(R.id.line);

        TypedArray a = context.obtainStyledAttributes(attr, R.styleable.AttriTextView);
        tv_key.setText(a.getString(R.styleable.AttriTextView_key) + ":");
        tv_value.setText(a.getString(R.styleable.AttriTextView_value));
        if (a.getBoolean(R.styleable.AttriTextView_isImp, true))
            iv_imp.setVisibility(VISIBLE);
        else
            iv_imp.setVisibility(GONE);
        if(!a.getBoolean(R.styleable.AttriTextView_hasline,true))
            line.setVisibility(GONE);
        a.recycle();
    }

    public void setValue(String value){
        tv_value.setText(value);
    }

    public void setIsImp(Boolean b){
        if(b){
            iv_imp.setVisibility(VISIBLE);
        }else {
            iv_imp.setVisibility(GONE);
        }
    }

}
