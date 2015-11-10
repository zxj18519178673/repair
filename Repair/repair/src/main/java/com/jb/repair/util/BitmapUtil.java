package com.jb.repair.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Bitmap工具类，支持从Resource和Sd卡加载图片，并按照指定的大小进行缩放
 * 
 * @author dmx
 * 
 */
public class BitmapUtil {
	/**
	 * 计算缩放比例
	 * 
	 * @param options
	 * @param reqWidth
	 * @param reqHeight
	 * @return
	 */
	private static final int caculateInSampleSize(
			BitmapFactory.Options options, int reqWidth, int reqHeight) {
		int height = options.outHeight;
		int width = options.outWidth;
		int inSampleSize = 1;
		if (height > reqHeight || width > reqWidth) {
			int halfHeight = height / 2;
			int halfWidth = width / 2;
			while ((halfHeight / inSampleSize) > reqHeight
					&& (halfWidth / inSampleSize) > reqWidth) {
				inSampleSize *= 2;
			}
		}
		return inSampleSize;
	}

	/**
	 * 创建指定大小的Bitmap
	 * 
	 * @param src
	 *            原始的Bitmap
	 * @param dstWidth
	 *            目标宽度
	 * @param dstHeight
	 *            目标高度
	 * @param filter
	 *            如果是放大图片，filter决定是否平滑，如果是缩小图片，filter无影响
	 * @return
	 */
	public static Bitmap createScaleBitmap(Bitmap src, int dstWidth,
			int dstHeight) {
		Bitmap dst = Bitmap.createScaledBitmap(src, dstWidth, dstHeight, false);
		if (src != dst) {
			src.recycle();
		}
		return dst;
	}

	/**
	 * 从Resource中加载Bitmap
	 * 
	 * @param res
	 *            Resource对象
	 * @param resId
	 *            资源Id
	 * @param reqWidth
	 *            目标宽度
	 * @param reqHeight
	 *            目标高度
	 * @return
	 */
	public static Bitmap decodeSampledBitmapFromResource(Resources res,
			int resId, int reqWidth, int reqHeight) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(res, resId, options);// 读取图片的长宽
		options.inSampleSize = caculateInSampleSize(options, reqWidth,
				reqHeight);// 计算inSampleSize
		options.inJustDecodeBounds = false;
		Bitmap src = BitmapFactory.decodeResource(res, resId, options);// 载入缩略图
		return createScaleBitmap(src, reqWidth, reqHeight);// 得到和目标大小一致的图片
	}

	/**
	 * 从Sd卡加载图片
	 * 
	 * @param pathName
	 *            图片路径
	 * @param reqWidth
	 *            目标宽度
	 * @param reqHeight
	 *            目标高度
	 * @return
	 */
	public static Bitmap decodeSampledBitmapFromSd(String pathName,
			int reqWidth, int reqHeight) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(pathName, options);
		options.inSampleSize = caculateInSampleSize(options, reqWidth,
				reqHeight);
		options.inJustDecodeBounds = false;
		Bitmap src = BitmapFactory.decodeFile(pathName, options);
		return createScaleBitmap(src, reqWidth, reqHeight);
	}
}
