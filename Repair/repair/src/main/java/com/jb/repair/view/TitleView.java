package com.jb.repair.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jb.repair.R;
import com.jb.repair.util.StringUtils;


/**
 * 标题控件： 默认布局方式为左侧一个图片和文本；中间为标题；右侧两个图片和两个文本
 * 
 * @author dmx
 * 
 */
public class TitleView extends LinearLayout implements OnClickListener {

	/**
	 * log tag
	 */
	public final static String TAG = "TitleView";
	private TextView txtTitle;
	private TextView txtTip;
	private ImageView leftImageView;
	private ImageView rightImageView1;
	private TextView rightImageView2;
	private LinearLayout leftLayout;
	private TextView txtRight;
	private TextView txtRight2;
	private OnTitleViewClickListener onTitleViewClickListener;

	public TitleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.f1_title_view, this);
		txtTitle = (TextView) this.findViewById(R.id.title_center_text);
		txtTip = (TextView) this.findViewById(R.id.title_left_tip);
		leftImageView = (ImageView) this
				.findViewById(R.id.titile_left_imageBack);
		rightImageView1 = (ImageView) this.findViewById(R.id.title_right_image1);
		rightImageView2 = (TextView) this.findViewById(R.id.title_right_image2);
		leftLayout = (LinearLayout) this.findViewById(R.id.title_left);
		txtRight = (TextView) this.findViewById(R.id.title_right_text);
		txtRight2 = (TextView) this.findViewById(R.id.title_right_text2);
		leftLayout.setOnClickListener(this);
		rightImageView1.setOnClickListener(this);
		rightImageView2.setOnClickListener(this);
		txtRight.setOnClickListener(this);
		txtRight2.setOnClickListener(this);
		// 获取自定义属性集合
		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.TitleView);
		// 设置标题属性
		txtTitle.setText(a.getString(R.styleable.TitleView_title));
//		txtTitle.setTextSize(a.getDimension(styleable.TitleView_title_size, 24));
		txtTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX,
				a.getDimension(R.styleable.TitleView_title_size,
						getResources().getDimensionPixelSize(R.dimen.x28)));
		txtTitle.setTextColor(a.getColor(R.styleable.TitleView_title_color,
				Color.WHITE));
		// 设置左边文本标签
		txtTip.setText(a.getString(R.styleable.TitleView_leftText));
//		txtTip.setTextSize(a
//				.getDimension(styleable.TitleView_leftText_size, 20));
		txtTip.setTextSize(TypedValue.COMPLEX_UNIT_PX,
				a.getDimension(R.styleable.TitleView_leftText_size,
						getResources().getDimensionPixelSize(R.dimen.x24)));
		txtTip.setTextColor(a.getColor(R.styleable.TitleView_leftText_color,
				Color.WHITE));
		// 设置左边图片
		leftImageView.setImageResource(a.getResourceId(R.styleable.TitleView_leftImage, R.mipmap.ic_launcher));
//		leftImageView.setTextSize(a.getDimension(
//				styleable.TitleView_leftImage_size, 20));
//		leftImageView.setTextSize(TypedValue.COMPLEX_UNIT_PX,
//				a.getDimension(R.styleable.TitleView_leftImage_size,
//						getResources().getDimensionPixelSize(R.dimen.x24)));
//		leftImageView.setTextColor(a.getColor(
//				R.styleable.TitleView_leftImage_color, Color.WHITE));

		String rightText = a.getString(R.styleable.TitleView_rightText);
		if (!StringUtils.isNullOrEmpty(rightText)) {
			// 设置右侧文本
			txtRight.setText(rightText);
//			txtRight.setTextSize(a.getDimension(
//					styleable.TitleView_rigthText_size, 20));
			txtRight.setTextSize(TypedValue.COMPLEX_UNIT_PX,
					a.getDimension(R.styleable.TitleView_rigthText_size,
							getResources().getDimensionPixelSize(R.dimen.x24)));
			txtRight.setTextColor(a.getColor(
					R.styleable.TitleView_rigthText_color, Color.WHITE));
			txtRight.setVisibility(View.VISIBLE);
		}

		rightText = a.getString(R.styleable.TitleView_rightText2);
		if (!StringUtils.isNullOrEmpty(rightText)) {
			// 设置右侧文本
			txtRight2.setText(rightText);
			txtRight2.setVisibility(View.VISIBLE);
		}
//		txtRight2.setTextSize(a.getDimension(
//				styleable.TitleView_rigthText_size, 20));
		txtRight2.setTextSize(TypedValue.COMPLEX_UNIT_PX,
				a.getDimension(R.styleable.TitleView_rigthText_size,
						getResources().getDimensionPixelSize(R.dimen.x24)));
		txtRight2.setTextColor(a.getColor(
				R.styleable.TitleView_rigthText_color, Color.WHITE));

		// 设置右侧图片1
		rightImageView1.setImageResource(a.getResourceId(R.styleable.TitleView_rightImage1,0));
//		rightImageView1.setTextSize(a.getDimension(
//				styleable.TitleView_rightImage1_size, 20));
		// 设置右侧图片2
		rightImageView2.setText(a.getString(R.styleable.TitleView_rightImage2));
//		rightImageView2.setTextSize(a.getDimension(
//				styleable.TitleView_rightImage2_size, 20));
		rightImageView2.setTextSize(TypedValue.COMPLEX_UNIT_PX,
				a.getDimension(R.styleable.TitleView_rightImage2_size,
						getResources().getDimensionPixelSize(R.dimen.x24)));
		rightImageView2.setTextColor(a.getColor(
				R.styleable.TitleView_rightImage2_color, Color.WHITE));
		a.recycle();
	}

	/**
	 * 设置标题
	 * 
	 * @param title
	 */
	public void setTitle(String title) {

		txtTitle.setText(title);
	}

	/**
	 * 设置左侧文本，默认为“返回”
	 * 
	 * @param tip
	 */
	public void setLeftTip(String tip) {
		txtTip.setText(tip);
	}

	/**
	 * 设置左侧的图片
	 * 
	 * @param resId
	 */
	public void setLeftImage(int resId) {
		leftImageView.setImageResource(resId);
	}

	/**
	 * 设置右侧的图片 右侧存在两个图片 position为1代表的是最右侧的图片
	 * 
	 * @param position
	 * @param resId
	 *            图片资源Id
	 */
	public void setRightImage(int position, int resId) {
		if (position == 1) {
			rightImageView1.setImageResource(resId);
		} else {
			rightImageView2.setText(resId);
		}

	}

	/**
	 * 设置右侧的文本
	 * 
	 * @param text
	 */
	public void setRightTextView(String text) {
		txtRight.setVisibility(View.VISIBLE);
		txtRight.setText(text);
	}

	/**
	 * 设置右侧的文本2
	 *
	 * @param text
	 */
	public void setRightTextView2(String text) {
		txtRight2.setVisibility(View.VISIBLE);
		txtRight2.setText(text);
	}

	/**
	 * 设置右侧的文本是否显示
	 * 
	 * @param show
	 */
	public void setRightTextViewVisable(boolean show) {
		if (show) {
			txtRight.setVisibility(View.VISIBLE);
		} else {
			txtRight.setVisibility(View.GONE);
		}
	}

	/**
	 * 设置右侧的文本2是否显示
	 *
	 * @param show
	 */
	public void setRightTextView2Visable(boolean show) {
		if (show) {
			txtRight2.setVisibility(View.VISIBLE);
		} else {
			txtRight2.setVisibility(View.GONE);
		}
	}

	/**
	 * 设置右侧的文本是否可用
	 * 
	 * @param activated
	 */
	public void setRightTextViewActivated(boolean activated) {
		if (activated) {
			txtRight.setClickable(true);
			txtRight.setTextColor(getResources().getColor(R.color.white_font));
		} else {
			txtRight.setClickable(false);
			txtRight.setTextColor(getResources().getColor(R.color.gravy_font));
		}
	}

	/**
	 * 设置右侧的文本2是否可用
	 *
	 * @param activated
	 */
	public void setRightTextView2Activated(boolean activated) {
		if (activated) {
			txtRight2.setClickable(true);
			txtRight2.setTextColor(getResources().getColor(R.color.white_font));
		} else {
			txtRight2.setClickable(false);
			txtRight2.setTextColor(getResources().getColor(R.color.gravy_font));
		}
	}

	/**
	 * 设置监听器 参数值为对应的ImageView
	 *
	 */
	public void setOnClickListener(
			OnTitleViewClickListener onTitleViewClickListener) {
		this.onTitleViewClickListener = onTitleViewClickListener;
	}

	/**
	 * 请使用setOnClickListener(OnTitleViewClickListener
	 * onTitleViewClickListener)方法
	 */
	@Deprecated
	@Override
	public void setOnClickListener(OnClickListener listener) {
		super.setOnClickListener(listener);
	}

	@Override
	public void onClick(View v) {
		if (onTitleViewClickListener == null) {
			return;
		}
		if (v.getId() == R.id.titile_left_imageBack
				|| v.getId() == R.id.title_left) {
			onTitleViewClickListener.onLeftImageClick(this.leftImageView);
		} else if (v.getId() == R.id.title_left_tip) {
			onTitleViewClickListener.onLeftTextClick(v);
		} else if (v.getId() == R.id.title_right_image1) {
			onTitleViewClickListener.onRightImage1Click(v);
		} else if (v.getId() == R.id.title_right_image2) {
			onTitleViewClickListener.onRightImage2Click(v);
		} else if (v.getId() == R.id.title_right_text) {
			onTitleViewClickListener.onRightTextClick(v);
		} else if (v.getId() == R.id.title_right_text2) {
			onTitleViewClickListener.onRightText2Click(v);
		}
	}

	/**
	 * 标题控件点击监听器
	 * 
	 * @author dmx
	 * 
	 */
	public static class OnTitleViewClickListener {
		/**
		 * 当点击左边文字触发的事件
		 * 
		 * @param view
		 */
		public void onLeftTextClick(View view) {
		}

		/**
		 * 当点击左边图标触发的事件
		 * 
		 * @param view
		 */
		public void onLeftImageClick(View view) {
		}

		/**
		 * 点击右侧第一个图片触发
		 * 
		 * @param view
		 */
		public void onRightImage1Click(View view) {
		}

		/**
		 * 点击右侧第二个图片触发
		 * 
		 * @param view
		 */
		public void onRightImage2Click(View view) {
		}

		/**
		 * 点击右侧文本触发
		 * 
		 * @param view
		 */
		public void onRightTextClick(View view) {
		}

		/**
		 * 点击右侧文本2触发
		 *
		 * @param view
		 */
		public void onRightText2Click(View view) {
		}
	}
}
