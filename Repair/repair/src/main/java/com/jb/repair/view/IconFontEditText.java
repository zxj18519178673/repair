package com.jb.repair.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * 字体图标显示控件
 * 
 * @author dmx
 * 
 */
public class IconFontEditText extends EditText {

	private static Typeface iconFont;

	private static synchronized void initIconFont(Context context) {
		if (iconFont == null) {
			iconFont = Typeface.createFromAsset(context.getAssets(),
					"iconfont/icomoon.ttf");
		}
	}

	public IconFontEditText(Context context) {
		super(context);
		initIconFont(context);
		this.setTypeface(iconFont);
	}

	public IconFontEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		initIconFont(context);
		this.setTypeface(iconFont);
	}

	public IconFontEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initIconFont(context);
		this.setTypeface(iconFont);
	}

}
