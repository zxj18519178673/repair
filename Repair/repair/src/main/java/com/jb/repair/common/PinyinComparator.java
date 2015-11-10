package com.jb.repair.common;

import java.util.Comparator;
import java.util.Map;

/**
 * 字典序排序规则
 * 
 * @author dmx
 * 
 */
public class PinyinComparator implements Comparator<Map<String, ?>> {
	// 实例化汉字转拼音类
	private CharacterParser characterParser = CharacterParser.getInstance();

	@Override
	public int compare(Map<String, ?> lhs, Map<String, ?> rhs) {
		String lchar = (String) lhs.get("text");
		String rchar = (String) rhs.get("text");
		lchar = getFirstChar(lchar);
		rchar = getFirstChar(rchar);
		if (lchar.equals("@") || rchar.equals("#")) {
			return -1;
		} else if (lchar.equals("#") || rchar.equals("@")) {
			return 1;
		} else {
			return lchar.compareTo(rchar);
		}
	}

	/**
	 * 获取字符串的首字母拼音
	 * 
	 * @param str
	 * @return
	 */
	private String getFirstChar(String str) {
		// 汉字转换成拼音
		String pinyin = characterParser.getSelling(str);
		String sortString = pinyin.substring(0, 1).toUpperCase();

		// 正则表达式，判断首字母是否是英文字母
		if (sortString.matches("[A-Z]")) {
			return sortString;
		} else {
			return "#";
		}
	}

}
