/*
 * Copyright (C), 北京中恒博瑞数字电力技术有限公司，保留所有权利.
 * FileName: MD5.java
 * History：
 * <author>         <time>             <version>      <desc>
 *   许策     2013-11-9下午02:55:39        V1.0          MD5算法
 */
package com.jb.repair.util;

import java.security.MessageDigest;

/**
 * @Package: com.jb.sys.util<br>
 * @ClassName: MD5<br>
 * @Description: MD5<br>
 */
public class MD5 {
	/**
	 * @Title: parse
	 * @Description: MD5计算方法
	 * @param
	 * @return String
	 * @throws
	 */
	public final static String parse(String value) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };
		try {
			byte[] btInput = value.getBytes();
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
