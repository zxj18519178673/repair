package com.jb.repair.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * 字符串工具类
 */
public class StringUtils {

	private StringUtils() {
	}

	/** 空字符串。 */
	public static final String EMPTY_STRING = "";

	/**
	 * 判断一个字符串是否为null或空字符串.
	 * <p>
	 * <b>注意:</b>字符串"null"将被认为是空.
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNullOrEmpty(String str) {
		if (str == null)
			return true;
		if (str.equalsIgnoreCase("null"))
			return true;
		return str.trim().length() == 0;
	}

	/**
	 * 随机生成指定位数且不重复的字符串.去除了部分容易混淆的字符，如1和l，o和0等， 随机范围1-9 a-z A-Z
	 * 
	 * @param length
	 *            指定字符串长度
	 * @return 返回指定位数且不重复的字符串
	 */
	public static String getRandomString(int length) {
		StringBuffer bu = new StringBuffer();
		while (bu.length() < length) {
			String temp = getRandomChar();
			if (bu.indexOf(temp) == -1) {
				bu.append(temp);
			}
		}
		return bu.toString();
	}

	/**
	 * 生成一个随机的字符,主要用于显示在验证码中,出去了容易混淆的字符,如1和l,0和o.
	 * 
	 * @return
	 */
	public static String getRandomChar() {
		String[] arr = { "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c",
				"d", "e", "f", "g", "h", "i", "j", "k", "m", "n", "p", "q",
				"r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C",
				"D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "P",
				"Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };

		Random random = new Random();
		return arr[random.nextInt(57)];
	}

	/**
	 * 获取某个范围内的随机整数
	 * 
	 * @param sek
	 *            随机种子
	 * @param start
	 *            最小范围
	 * @param max
	 *            最大范围
	 * @return 整数
	 */
	public static int getRandomInt(int sek, int min, int max) {

		Random random = new Random();

		int temp = 0;

		do {
			temp = random.nextInt(sek);
		} while (temp < min || temp > max);

		return temp;
	}

	/**
	 * 把date按指定格式转化成字符串
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String formatDate(Date date, String format) {
		if (date == null) {
			return null;
		}

		SimpleDateFormat fmt = new SimpleDateFormat(format);
		return fmt.format(date);
	}

	/**
	 * 检查字符串中是否包含指定的字符。如果字符串为<code>null</code>，将返回<code>false</code>。
	 * 
	 * <pre>
	 *                                 StringUtil.contains(null, *)    = false
	 *                                 StringUtil.contains(&quot;&quot;, *)      = false
	 *                                 StringUtil.contains(&quot;abc&quot;, 'a') = true
	 *                                 StringUtil.contains(&quot;abc&quot;, 'z') = false
	 * </pre>
	 * 
	 * @param str
	 *            要扫描的字符串
	 * @param searchChar
	 *            要查找的字符
	 * @return 如果找到，则返回<code>true</code>
	 */
	public static boolean contains(String str, char searchChar) {
		if ((str == null) || (str.length() == 0)) {
			return false;
		}

		return str.indexOf(searchChar) >= 0;
	}

	/**
	 * 检查字符串中是否包含指定的字符串。如果字符串为<code>null</code>，将返回<code>false</code>。
	 * 
	 * <pre>
	 *                                 StringUtil.contains(null, *)     = false
	 *                                 StringUtil.contains(*, null)     = false
	 *                                 StringUtil.contains(&quot;&quot;, &quot;&quot;)      = true
	 *                                 StringUtil.contains(&quot;abc&quot;, &quot;&quot;)   = true
	 *                                 StringUtil.contains(&quot;abc&quot;, &quot;a&quot;)  = true
	 *                                 StringUtil.contains(&quot;abc&quot;, &quot;z&quot;)  = false
	 * </pre>
	 * 
	 * @param str
	 *            要扫描的字符串
	 * @param searchStr
	 *            要查找的字符串
	 * @return 如果找到，则返回<code>true</code>
	 */
	public static boolean contains(String str, String searchStr) {
		if ((str == null) || (searchStr == null)) {
			return false;
		}

		return str.indexOf(searchStr) >= 0;
	}

	/*
	 * ==========================================================================
	 * ==
	 */
	/* 字符串连接函数。 */
	/*                                                                              */
	/* 将多个对象按指定分隔符连接成字符串。 */
	/*
	 * ==========================================================================
	 * ==
	 */

	/**
	 * 将数组中的元素连接成一个字符串。
	 * 
	 * <pre>
	 *                                 StringUtil.join(null)            = null
	 *                                 StringUtil.join([])              = &quot;&quot;
	 *                                 StringUtil.join([null])          = &quot;&quot;
	 *                                 StringUtil.join([&quot;a&quot;, &quot;b&quot;, &quot;c&quot;]) = &quot;abc&quot;
	 *                                 StringUtil.join([null, &quot;&quot;, &quot;a&quot;]) = &quot;a&quot;
	 * </pre>
	 * 
	 * @param array
	 *            要连接的数组
	 * @return 连接后的字符串，如果原数组为<code>null</code>，则返回<code>null</code>
	 */
	public static String join(Object[] array) {
		return join(array, null);
	}

	/**
	 * 将数组中的元素连接成一个字符串。
	 * 
	 * <pre>
	 *                                 StringUtil.join(null, *)               = null
	 *                                 StringUtil.join([], *)                 = &quot;&quot;
	 *                                 StringUtil.join([null], *)             = &quot;&quot;
	 *                                 StringUtil.join([&quot;a&quot;, &quot;b&quot;, &quot;c&quot;], ';')  = &quot;a;b;c&quot;
	 *                                 StringUtil.join([&quot;a&quot;, &quot;b&quot;, &quot;c&quot;], null) = &quot;abc&quot;
	 *                                 StringUtil.join([null, &quot;&quot;, &quot;a&quot;], ';')  = &quot;;;a&quot;
	 * </pre>
	 * 
	 * @param array
	 *            要连接的数组
	 * @param separator
	 *            分隔符
	 * @return 连接后的字符串，如果原数组为<code>null</code>，则返回<code>null</code>
	 */
	public static String join(Object[] array, char separator) {
		if (array == null) {
			return null;
		}

		int arraySize = array.length;
		int bufSize = (arraySize == 0) ? 0 : ((((array[0] == null) ? 16
				: array[0].toString().length()) + 1) * arraySize);
		StringBuffer buf = new StringBuffer(bufSize);

		for (int i = 0; i < arraySize; i++) {
			if (i > 0) {
				buf.append(separator);
			}

			if (array[i] != null) {
				buf.append(array[i]);
			}
		}

		return buf.toString();
	}

	/**
	 * 将数组中的元素连接成一个字符串。
	 * 
	 * <pre>
	 *                                 StringUtil.join(null, *)                = null
	 *                                 StringUtil.join([], *)                  = &quot;&quot;
	 *                                 StringUtil.join([null], *)              = &quot;&quot;
	 *                                 StringUtil.join([&quot;a&quot;, &quot;b&quot;, &quot;c&quot;], &quot;--&quot;)  = &quot;a--b--c&quot;
	 *                                 StringUtil.join([&quot;a&quot;, &quot;b&quot;, &quot;c&quot;], null)  = &quot;abc&quot;
	 *                                 StringUtil.join([&quot;a&quot;, &quot;b&quot;, &quot;c&quot;], &quot;&quot;)    = &quot;abc&quot;
	 *                                 StringUtil.join([null, &quot;&quot;, &quot;a&quot;], ',')   = &quot;,,a&quot;
	 * </pre>
	 * 
	 * @param array
	 *            要连接的数组
	 * @param separator
	 *            分隔符
	 * @return 连接后的字符串，如果原数组为<code>null</code>，则返回<code>null</code>
	 */
	public static String join(Object[] array, String separator) {
		if (array == null) {
			return null;
		}

		if (separator == null) {
			separator = EMPTY_STRING;
		}

		int arraySize = array.length;

		// ArraySize == 0: Len = 0
		// ArraySize > 0: Len = NofStrings *(len(firstString) + len(separator))
		// (估计大约所有的字符串都一样长)
		int bufSize = (arraySize == 0) ? 0
				: (arraySize * (((array[0] == null) ? 16 : array[0].toString()
						.length()) + ((separator != null) ? separator.length()
						: 0)));

		StringBuffer buf = new StringBuffer(bufSize);

		for (int i = 0; i < arraySize; i++) {
			if ((separator != null) && (i > 0)) {
				buf.append(separator);
			}

			if (array[i] != null) {
				buf.append(array[i]);
			}
		}

		return buf.toString();
	}

	/**
	 * 合并数组元素，对null或""元素不合并 <br>
	 * "a","","b" --> a,b
	 * 
	 * @param array
	 * @param sep
	 * @return
	 */
	public static String joinWithoutSpace(String[] array, String sep) {
		if (array == null || array.length == 0)
			return null;
		if (isNullOrEmpty(sep)) {
			sep = EMPTY_STRING;
		}
		StringBuffer buf = new StringBuffer(256);
		for (int i = 0; i < array.length; i++) {
			if (!isNullOrEmpty(array[i])) {
				if (i == 0) {
					buf.append(array[i]);
				} else {
					buf.append(sep).append(array[i]);
				}
			}
		}
		return buf.toString();
	}

	/**
	 * 将<code>Iterator</code>中的元素连接成一个字符串。
	 * 
	 * <pre>
	 *                                 StringUtil.join(null, *)                = null
	 *                                 StringUtil.join([], *)                  = &quot;&quot;
	 *                                 StringUtil.join([null], *)              = &quot;&quot;
	 *                                 StringUtil.join([&quot;a&quot;, &quot;b&quot;, &quot;c&quot;], &quot;--&quot;)  = &quot;a--b--c&quot;
	 *                                 StringUtil.join([&quot;a&quot;, &quot;b&quot;, &quot;c&quot;], null)  = &quot;abc&quot;
	 *                                 StringUtil.join([&quot;a&quot;, &quot;b&quot;, &quot;c&quot;], &quot;&quot;)    = &quot;abc&quot;
	 *                                 StringUtil.join([null, &quot;&quot;, &quot;a&quot;], ',')   = &quot;,,a&quot;
	 * </pre>
	 * 
	 * @param iterator
	 *            要连接的<code>Iterator</code>
	 * @param separator
	 *            分隔符
	 * @return 连接后的字符串，如果原数组为<code>null</code>，则返回<code>null</code>
	 */
	public static String join(Iterator iterator, char separator) {
		if (iterator == null) {
			return null;
		}

		StringBuffer buf = new StringBuffer(256); // Java默认值是16, 可能偏小

		while (iterator.hasNext()) {
			Object obj = iterator.next();

			if (obj != null) {
				buf.append(obj);
			}

			if (iterator.hasNext()) {
				buf.append(separator);
			}
		}

		return buf.toString();
	}

	/**
	 * 将<code>Iterator</code>中的元素连接成一个字符串。
	 * 
	 * <pre>
	 *                                 StringUtil.join(null, *)                = null
	 *                                 StringUtil.join([], *)                  = &quot;&quot;
	 *                                 StringUtil.join([null], *)              = &quot;&quot;
	 *                                 StringUtil.join([&quot;a&quot;, &quot;b&quot;, &quot;c&quot;], &quot;--&quot;)  = &quot;a--b--c&quot;
	 *                                 StringUtil.join([&quot;a&quot;, &quot;b&quot;, &quot;c&quot;], null)  = &quot;abc&quot;
	 *                                 StringUtil.join([&quot;a&quot;, &quot;b&quot;, &quot;c&quot;], &quot;&quot;)    = &quot;abc&quot;
	 *                                 StringUtil.join([null, &quot;&quot;, &quot;a&quot;], ',')   = &quot;,,a&quot;
	 * </pre>
	 * 
	 * @param iterator
	 *            要连接的<code>Iterator</code>
	 * @param separator
	 *            分隔符
	 * @return 连接后的字符串，如果原数组为<code>null</code>，则返回<code>null</code>
	 */
	public static String join(Iterator iterator, String separator) {
		if (iterator == null) {
			return null;
		}

		StringBuffer buf = new StringBuffer(256); // Java默认值是16, 可能偏小

		while (iterator.hasNext()) {
			Object obj = iterator.next();

			if (obj != null) {
				buf.append(obj);
			}

			if ((separator != null) && iterator.hasNext()) {
				buf.append(separator);
			}
		}

		return buf.toString();
	}

	/**
	 * 判定两个字符串是否相等。
	 * 
	 * @param s1
	 *            待判定的字符串1。
	 * @param s2
	 *            待判定的字符串2。
	 * @param p_ignoreCase
	 *            比较过程是否忽略大小写。true表示忽略大小写。
	 * @return 相等则返回true。
	 */
	public static boolean equal(String s1, String s2, boolean p_ignoreCase) {
		if ((s1 == null) || (s2 == null))
			return false;
		if (p_ignoreCase)
			return s1.equalsIgnoreCase(s2);
		return s1.equals(s2);
	}

	/**
	 * 将字符串转换成大写。
	 * <p>
	 * 如果字符串是<code>null</code>则返回<code>null</code>。
	 * 
	 * <pre>
	 *                                 StringUtil.toUpperCase(null)  = null
	 *                                 StringUtil.toUpperCase(&quot;&quot;)    = &quot;&quot;
	 *                                 StringUtil.toUpperCase(&quot;aBc&quot;) = &quot;ABC&quot;
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param str
	 *            要转换的字符串
	 * @return 大写字符串，如果原字符串为<code>null</code>，则返回<code>null</code>
	 */
	public static String toUpperCase(String str) {
		if (str == null) {
			return null;
		}

		return str.toUpperCase();
	}

	/**
	 * 比较两个字符串（大小写敏感）。
	 * 
	 * <pre>
	 *                                 StringUtil.equals(null, null)   = true
	 *                                 StringUtil.equals(null, &quot;abc&quot;)  = false
	 *                                 StringUtil.equals(&quot;abc&quot;, null)  = false
	 *                                 StringUtil.equals(&quot;abc&quot;, &quot;abc&quot;) = true
	 *                                 StringUtil.equals(&quot;abc&quot;, &quot;ABC&quot;) = false
	 * </pre>
	 * 
	 * @param str1
	 *            要比较的字符串1
	 * @param str2
	 *            要比较的字符串2
	 * @return 如果两个字符串相同，或者都是<code>null</code>，则返回<code>true</code>
	 */
	public static boolean equals(String str1, String str2) {
		if (str1 == null) {
			return str2 == null;
		}

		return str1.equals(str2);
	}

	/**
	 * 比较两个字符串（大小写不敏感）。
	 * 
	 * <pre>
	 *                                 StringUtil.equalsIgnoreCase(null, null)   = true
	 *                                 StringUtil.equalsIgnoreCase(null, &quot;abc&quot;)  = false
	 *                                 StringUtil.equalsIgnoreCase(&quot;abc&quot;, null)  = false
	 *                                 StringUtil.equalsIgnoreCase(&quot;abc&quot;, &quot;abc&quot;) = true
	 *                                 StringUtil.equalsIgnoreCase(&quot;abc&quot;, &quot;ABC&quot;) = true
	 * </pre>
	 * 
	 * @param str1
	 *            要比较的字符串1
	 * @param str2
	 *            要比较的字符串2
	 * @return 如果两个字符串相同，或者都是<code>null</code>，则返回<code>true</code>
	 */
	public static boolean equalsIgnoreCase(String str1, String str2) {
		if (str1 == null) {
			return str2 == null;
		}

		return str1.equalsIgnoreCase(str2);
	}

	/*
	 * ==========================================================================
	 * ==
	 */
	/* 字符串分割函数。 */
	/*                                                                              */
	/* 将字符串按指定分隔符分割。 */
	/*
	 * ==========================================================================
	 * ==
	 */

	/**
	 * 将字符串按空白字符分割。
	 * <p>
	 * 分隔符不会出现在目标数组中，连续的分隔符就被看作一个。如果字符串为<code>null</code>，则返回<code>null</code>。
	 * 
	 * <pre>
	 *                                 StringUtil.split(null)       = null
	 *                                 StringUtil.split(&quot;&quot;)         = []
	 *                                 StringUtil.split(&quot;abc def&quot;)  = [&quot;abc&quot;, &quot;def&quot;]
	 *                                 StringUtil.split(&quot;abc  def&quot;) = [&quot;abc&quot;, &quot;def&quot;]
	 *                                 StringUtil.split(&quot; abc &quot;)    = [&quot;abc&quot;]
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param str
	 *            要分割的字符串
	 * @return 分割后的字符串数组，如果原字符串为<code>null</code>，则返回<code>null</code>
	 */
	public static String[] split(String str) {
		return split(str, null, -1);
	}

	/**
	 * 将字符串按指定字符分割。
	 * <p>
	 * 分隔符不会出现在目标数组中，连续的分隔符就被看作一个。如果字符串为<code>null</code>，则返回<code>null</code>。
	 * 
	 * <pre>
	 *                                 StringUtil.split(null, *)         = null
	 *                                 StringUtil.split(&quot;&quot;, *)           = []
	 *                                 StringUtil.split(&quot;a.b.c&quot;, '.')    = [&quot;a&quot;, &quot;b&quot;, &quot;c&quot;]
	 *                                 StringUtil.split(&quot;a..b.c&quot;, '.')   = [&quot;a&quot;, &quot;b&quot;, &quot;c&quot;]
	 *                                 StringUtil.split(&quot;a:b:c&quot;, '.')    = [&quot;a:b:c&quot;]
	 *                                 StringUtil.split(&quot;a b c&quot;, ' ')    = [&quot;a&quot;, &quot;b&quot;, &quot;c&quot;]
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param str
	 *            要分割的字符串
	 * @param separatorChar
	 *            分隔符
	 * @return 分割后的字符串数组，如果原字符串为<code>null</code>，则返回<code>null</code>
	 */
	@SuppressWarnings("unchecked")
	public static String[] split(String str, char separatorChar) {
		if (str == null) {
			return null;
		}

		int length = str.length();

		if (length == 0) {
			return new String[0];
		}

		List list = new ArrayList();
		int i = 0;
		int start = 0;
		boolean match = false;

		while (i < length) {
			if (str.charAt(i) == separatorChar) {
				if (match) {
					list.add(str.substring(start, i));
					match = false;
				}

				start = ++i;
				continue;
			}

			match = true;
			i++;
		}

		if (match) {
			list.add(str.substring(start, i));
		}

		return (String[]) list.toArray(new String[list.size()]);
	}

	/**
	 * 将字符串按指定字符分割。
	 * <p>
	 * 分隔符不会出现在目标数组中，连续的分隔符就被看作一个。如果字符串为<code>null</code>，则返回<code>null</code>。
	 * 
	 * <pre>
	 *                                 StringUtil.split(null, *)                = null
	 *                                 StringUtil.split(&quot;&quot;, *)                  = []
	 *                                 StringUtil.split(&quot;abc def&quot;, null)        = [&quot;abc&quot;, &quot;def&quot;]
	 *                                 StringUtil.split(&quot;abc def&quot;, &quot; &quot;)         = [&quot;abc&quot;, &quot;def&quot;]
	 *                                 StringUtil.split(&quot;abc  def&quot;, &quot; &quot;)        = [&quot;abc&quot;, &quot;def&quot;]
	 *                                 StringUtil.split(&quot; ab:  cd::ef  &quot;, &quot;:&quot;)  = [&quot;ab&quot;, &quot;cd&quot;, &quot;ef&quot;]
	 *                                 StringUtil.split(&quot;abc.def&quot;, &quot;&quot;)          = [&quot;abc.def&quot;]
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param str
	 *            要分割的字符串
	 * @param separatorChars
	 *            分隔符
	 * @return 分割后的字符串数组，如果原字符串为<code>null</code>，则返回<code>null</code>
	 */
	public static String[] split(String str, String separatorChars) {
		return split(str, separatorChars, -1);
	}

	/**
	 * 将字符串按指定字符分割。
	 * <p>
	 * 分隔符不会出现在目标数组中，连续的分隔符就被看作一个。如果字符串为<code>null</code>，则返回<code>null</code>。
	 * 
	 * <pre>
	 *                                 StringUtil.split(null, *, *)                 = null
	 *                                 StringUtil.split(&quot;&quot;, *, *)                   = []
	 *                                 StringUtil.split(&quot;ab cd ef&quot;, null, 0)        = [&quot;ab&quot;, &quot;cd&quot;, &quot;ef&quot;]
	 *                                 StringUtil.split(&quot;  ab   cd ef  &quot;, null, 0)  = [&quot;ab&quot;, &quot;cd&quot;, &quot;ef&quot;]
	 *                                 StringUtil.split(&quot;ab:cd::ef&quot;, &quot;:&quot;, 0)        = [&quot;ab&quot;, &quot;cd&quot;, &quot;ef&quot;]
	 *                                 StringUtil.split(&quot;ab:cd:ef&quot;, &quot;:&quot;, 2)         = [&quot;ab&quot;, &quot;cdef&quot;]
	 *                                 StringUtil.split(&quot;abc.def&quot;, &quot;&quot;, 2)           = [&quot;abc.def&quot;]
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param str
	 *            要分割的字符串
	 * @param separatorChars
	 *            分隔符
	 * @param max
	 *            返回的数组的最大个数，如果小于等于0，则表示无限制
	 * @return 分割后的字符串数组，如果原字符串为<code>null</code>，则返回<code>null</code>
	 */
	@SuppressWarnings("unchecked")
	public static String[] split(String str, String separatorChars, int max) {
		if (str == null) {
			return null;
		}

		int length = str.length();

		if (length == 0) {
			return new String[0];
		}

		List list = new ArrayList();
		int sizePlus1 = 1;
		int i = 0;
		int start = 0;
		boolean match = false;

		if (separatorChars == null) {
			// null表示使用空白作为分隔符
			while (i < length) {
				if (Character.isWhitespace(str.charAt(i))) {
					if (match) {
						if (sizePlus1++ == max) {
							i = length;
						}

						list.add(str.substring(start, i));
						match = false;
					}

					start = ++i;
					continue;
				}

				match = true;
				i++;
			}
		} else if (separatorChars.length() == 1) {
			// 优化分隔符长度为1的情形
			char sep = separatorChars.charAt(0);

			while (i < length) {
				if (str.charAt(i) == sep) {
					if (match) {
						if (sizePlus1++ == max) {
							i = length;
						}

						list.add(str.substring(start, i));
						match = false;
					}

					start = ++i;
					continue;
				}

				match = true;
				i++;
			}
		} else {
			// 一般情形
			while (i < length) {
				if (separatorChars.indexOf(str.charAt(i)) >= 0) {
					if (match) {
						if (sizePlus1++ == max) {
							i = length;
						}

						list.add(str.substring(start, i));
						match = false;
					}

					start = ++i;
					continue;
				}

				match = true;
				i++;
			}
		}

		if (match) {
			list.add(str.substring(start, i));
		}

		return (String[]) list.toArray(new String[list.size()]);
	}

	/*
	 * ==========================================================================
	 * ==
	 */

	/**
	 * 取得第一个出现的分隔子串之前的子串。
	 * <p>
	 * 如果字符串为<code>null</code>，则返回<code>null</code>。 如果分隔子串为<code>null</code>
	 * 或未找到该子串，则返回原字符串。
	 * 
	 * <pre>
	 *                                 StringUtil.substringBefore(null, *)      = null
	 *                                 StringUtil.substringBefore(&quot;&quot;, *)        = &quot;&quot;
	 *                                 StringUtil.substringBefore(&quot;abc&quot;, &quot;a&quot;)   = &quot;&quot;
	 *                                 StringUtil.substringBefore(&quot;abcba&quot;, &quot;b&quot;) = &quot;a&quot;
	 *                                 StringUtil.substringBefore(&quot;abc&quot;, &quot;c&quot;)   = &quot;ab&quot;
	 *                                 StringUtil.substringBefore(&quot;abc&quot;, &quot;d&quot;)   = &quot;abc&quot;
	 *                                 StringUtil.substringBefore(&quot;abc&quot;, &quot;&quot;)    = &quot;&quot;
	 *                                 StringUtil.substringBefore(&quot;abc&quot;, null)  = &quot;abc&quot;
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param str
	 *            字符串
	 * @param separator
	 *            要搜索的分隔子串
	 * @return 子串，如果原始串为<code>null</code>，则返回<code>null</code>
	 */
	public static String substringBefore(String str, String separator) {
		if ((str == null) || (separator == null) || (str.length() == 0)) {
			return str;
		}

		if (separator.length() == 0) {
			return EMPTY_STRING;
		}

		int pos = str.indexOf(separator);

		if (pos == -1) {
			return str;
		}

		return str.substring(0, pos);
	}

	/**
	 * 取得第一个出现的分隔子串之后的子串。
	 * <p>
	 * 如果字符串为<code>null</code>，则返回<code>null</code>。 如果分隔子串为<code>null</code>
	 * 或未找到该子串，则返回原字符串。
	 * 
	 * <pre>
	 *                                 StringUtil.substringAfter(null, *)      = null
	 *                                 StringUtil.substringAfter(&quot;&quot;, *)        = &quot;&quot;
	 *                                 StringUtil.substringAfter(*, null)      = &quot;&quot;
	 *                                 StringUtil.substringAfter(&quot;abc&quot;, &quot;a&quot;)   = &quot;bc&quot;
	 *                                 StringUtil.substringAfter(&quot;abcba&quot;, &quot;b&quot;) = &quot;cba&quot;
	 *                                 StringUtil.substringAfter(&quot;abc&quot;, &quot;c&quot;)   = &quot;&quot;
	 *                                 StringUtil.substringAfter(&quot;abc&quot;, &quot;d&quot;)   = &quot;&quot;
	 *                                 StringUtil.substringAfter(&quot;abc&quot;, &quot;&quot;)    = &quot;abc&quot;
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param str
	 *            字符串
	 * @param separator
	 *            要搜索的分隔子串
	 * @return 子串，如果原始串为<code>null</code>，则返回<code>null</code>
	 */
	public static String substringAfter(String str, String separator) {
		if ((str == null) || (str.length() == 0)) {
			return str;
		}

		if (separator == null) {
			return EMPTY_STRING;
		}

		int pos = str.indexOf(separator);

		if (pos == -1) {
			return EMPTY_STRING;
		}

		return str.substring(pos + separator.length());
	}

	/**
	 * 取得最后一个的分隔子串之前的子串。
	 * <p>
	 * 如果字符串为<code>null</code>，则返回<code>null</code>。 如果分隔子串为<code>null</code>
	 * 或未找到该子串，则返回原字符串。
	 * 
	 * <pre>
	 *                                 StringUtil.substringBeforeLast(null, *)      = null
	 *                                 StringUtil.substringBeforeLast(&quot;&quot;, *)        = &quot;&quot;
	 *                                 StringUtil.substringBeforeLast(&quot;abcba&quot;, &quot;b&quot;) = &quot;abc&quot;
	 *                                 StringUtil.substringBeforeLast(&quot;abc&quot;, &quot;c&quot;)   = &quot;ab&quot;
	 *                                 StringUtil.substringBeforeLast(&quot;a&quot;, &quot;a&quot;)     = &quot;&quot;
	 *                                 StringUtil.substringBeforeLast(&quot;a&quot;, &quot;z&quot;)     = &quot;a&quot;
	 *                                 StringUtil.substringBeforeLast(&quot;a&quot;, null)    = &quot;a&quot;
	 *                                 StringUtil.substringBeforeLast(&quot;a&quot;, &quot;&quot;)      = &quot;a&quot;
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param str
	 *            字符串
	 * @param separator
	 *            要搜索的分隔子串
	 * @return 子串，如果原始串为<code>null</code>，则返回<code>null</code>
	 */
	public static String substringBeforeLast(String str, String separator) {
		if ((str == null) || (separator == null) || (str.length() == 0)
				|| (separator.length() == 0)) {
			return str;
		}

		int pos = str.lastIndexOf(separator);

		if (pos == -1) {
			return str;
		}

		return str.substring(0, pos);
	}

	/**
	 * 取得最后一个的分隔子串之后的子串。
	 * <p>
	 * 如果字符串为<code>null</code>，则返回<code>null</code>。 如果分隔子串为<code>null</code>
	 * 或未找到该子串，则返回原字符串。
	 * 
	 * <pre>
	 *                                 StringUtil.substringAfterLast(null, *)      = null
	 *                                 StringUtil.substringAfterLast(&quot;&quot;, *)        = &quot;&quot;
	 *                                 StringUtil.substringAfterLast(*, &quot;&quot;)        = &quot;&quot;
	 *                                 StringUtil.substringAfterLast(*, null)      = &quot;&quot;
	 *                                 StringUtil.substringAfterLast(&quot;abc&quot;, &quot;a&quot;)   = &quot;bc&quot;
	 *                                 StringUtil.substringAfterLast(&quot;abcba&quot;, &quot;b&quot;) = &quot;a&quot;
	 *                                 StringUtil.substringAfterLast(&quot;abc&quot;, &quot;c&quot;)   = &quot;&quot;
	 *                                 StringUtil.substringAfterLast(&quot;a&quot;, &quot;a&quot;)     = &quot;&quot;
	 *                                 StringUtil.substringAfterLast(&quot;a&quot;, &quot;z&quot;)     = &quot;&quot;
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param str
	 *            字符串
	 * @param separator
	 *            要搜索的分隔子串
	 * @return 子串，如果原始串为<code>null</code>，则返回<code>null</code>
	 */
	public static String substringAfterLast(String str, String separator) {
		if ((str == null) || (str.length() == 0)) {
			return str;
		}

		if ((separator == null) || (separator.length() == 0)) {
			return EMPTY_STRING;
		}

		int pos = str.lastIndexOf(separator);

		if ((pos == -1) || (pos == (str.length() - separator.length()))) {
			return EMPTY_STRING;
		}

		return str.substring(pos + separator.length());
	}

	/**
	 * 取得指定分隔符的前两次出现之间的子串。
	 * <p>
	 * 如果字符串为<code>null</code>，则返回<code>null</code>。 如果分隔子串为<code>null</code>
	 * ，则返回<code>null</code>。
	 * 
	 * <pre>
	 *                                 StringUtil.substringBetween(null, *)            = null
	 *                                 StringUtil.substringBetween(&quot;&quot;, &quot;&quot;)             = &quot;&quot;
	 *                                 StringUtil.substringBetween(&quot;&quot;, &quot;tag&quot;)          = null
	 *                                 StringUtil.substringBetween(&quot;tagabctag&quot;, null)  = null
	 *                                 StringUtil.substringBetween(&quot;tagabctag&quot;, &quot;&quot;)    = &quot;&quot;
	 *                                 StringUtil.substringBetween(&quot;tagabctag&quot;, &quot;tag&quot;) = &quot;abc&quot;
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param str
	 *            字符串
	 * @param tag
	 *            要搜索的分隔子串
	 * @return 子串，如果原始串为<code>null</code>或未找到分隔子串，则返回<code>null</code>
	 */
	public static String substringBetween(String str, String tag) {
		return substringBetween(str, tag, tag, 0);
	}

	/**
	 * 取得两个分隔符之间的子串。
	 * <p>
	 * 如果字符串为<code>null</code>，则返回<code>null</code>。 如果分隔子串为<code>null</code>
	 * ，则返回<code>null</code>。
	 * 
	 * <pre>
	 *                                 StringUtil.substringBetween(null, *, *)          = null
	 *                                 StringUtil.substringBetween(&quot;&quot;, &quot;&quot;, &quot;&quot;)          = &quot;&quot;
	 *                                 StringUtil.substringBetween(&quot;&quot;, &quot;&quot;, &quot;tag&quot;)       = null
	 *                                 StringUtil.substringBetween(&quot;&quot;, &quot;tag&quot;, &quot;tag&quot;)    = null
	 *                                 StringUtil.substringBetween(&quot;yabcz&quot;, null, null) = null
	 *                                 StringUtil.substringBetween(&quot;yabcz&quot;, &quot;&quot;, &quot;&quot;)     = &quot;&quot;
	 *                                 StringUtil.substringBetween(&quot;yabcz&quot;, &quot;y&quot;, &quot;z&quot;)   = &quot;abc&quot;
	 *                                 StringUtil.substringBetween(&quot;yabczyabcz&quot;, &quot;y&quot;, &quot;z&quot;)   = &quot;abc&quot;
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param str
	 *            字符串
	 * @param open
	 *            要搜索的分隔子串1
	 * @param close
	 *            要搜索的分隔子串2
	 * @return 子串，如果原始串为<code>null</code>或未找到分隔子串，则返回<code>null</code>
	 */
	public static String substringBetween(String str, String open, String close) {
		return substringBetween(str, open, close, 0);
	}

	/**
	 * 取得两个分隔符之间的子串。
	 * <p>
	 * 如果字符串为<code>null</code>，则返回<code>null</code>。 如果分隔子串为<code>null</code>
	 * ，则返回<code>null</code>。
	 * 
	 * <pre>
	 *                                 StringUtil.substringBetween(null, *, *)          = null
	 *                                 StringUtil.substringBetween(&quot;&quot;, &quot;&quot;, &quot;&quot;)          = &quot;&quot;
	 *                                 StringUtil.substringBetween(&quot;&quot;, &quot;&quot;, &quot;tag&quot;)       = null
	 *                                 StringUtil.substringBetween(&quot;&quot;, &quot;tag&quot;, &quot;tag&quot;)    = null
	 *                                 StringUtil.substringBetween(&quot;yabcz&quot;, null, null) = null
	 *                                 StringUtil.substringBetween(&quot;yabcz&quot;, &quot;&quot;, &quot;&quot;)     = &quot;&quot;
	 *                                 StringUtil.substringBetween(&quot;yabcz&quot;, &quot;y&quot;, &quot;z&quot;)   = &quot;abc&quot;
	 *                                 StringUtil.substringBetween(&quot;yabczyabcz&quot;, &quot;y&quot;, &quot;z&quot;)   = &quot;abc&quot;
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param str
	 *            字符串
	 * @param open
	 *            要搜索的分隔子串1
	 * @param close
	 *            要搜索的分隔子串2
	 * @param fromIndex
	 *            从指定index处搜索
	 * @return 子串，如果原始串为<code>null</code>或未找到分隔子串，则返回<code>null</code>
	 */
	public static String substringBetween(String str, String open,
			String close, int fromIndex) {
		if ((str == null) || (open == null) || (close == null)) {
			return null;
		}

		int start = str.indexOf(open, fromIndex);

		if (start != -1) {
			int end = str.indexOf(close, start + open.length());

			if (end != -1) {
				return str.substring(start + open.length(), end);
			}
		}

		return null;
	}

	/**
	 * 除去字符串头尾部的指定字符，如果字符串是<code>null</code>，依然返回<code>null</code>。
	 * 
	 * @param str
	 *            要处理的字符串
	 * @param stripChars
	 *            要除去的字符，如果为<code>null</code>表示除去空白字符
	 * @param mode
	 *            <code>-1</code>表示trimStart，<code>0</code>表示trim全部，
	 *            <code>1</code>表示trimEnd
	 * @return 除去指定字符后的的字符串，如果原字串为<code>null</code>，则返回<code>null</code>
	 */
	private static String trim(String str, String stripChars, int mode) {
		if (str == null) {
			return null;
		}

		int length = str.length();
		int start = 0;
		int end = length;

		// 扫描字符串头部
		if (mode <= 0) {
			if (stripChars == null) {
				while ((start < end)
						&& (Character.isWhitespace(str.charAt(start)))) {
					start++;
				}
			} else if (stripChars.length() == 0) {
				return str;
			} else {
				while ((start < end)
						&& (stripChars.indexOf(str.charAt(start)) != -1)) {
					start++;
				}
			}
		}

		// 扫描字符串尾部
		if (mode >= 0) {
			if (stripChars == null) {
				while ((start < end)
						&& (Character.isWhitespace(str.charAt(end - 1)))) {
					end--;
				}
			} else if (stripChars.length() == 0) {
				return str;
			} else {
				while ((start < end)
						&& (stripChars.indexOf(str.charAt(end - 1)) != -1)) {
					end--;
				}
			}
		}

		if ((start > 0) || (end < length)) {
			return str.substring(start, end);
		}

		return str;
	}

	/**
	 * 除去字符串头尾部的空白，如果字符串是<code>null</code>，依然返回<code>null</code>。
	 * <p>
	 * 注意，和<code>String.trim</code>不同，此方法使用<code>Character.isWhitespace</code>
	 * 来判定空白， 因而可以除去英文字符集之外的其它空白，如中文空格。
	 * 
	 * @param str
	 *            要处理的字符串
	 * @return 除去空白的字符串，如果原字串为<code>null</code>，则返回<code>null</code>
	 */
	public static String trim(String str) {
		return trim(str, null, 0);
	}

	/**
	 * 去除str中的全部空格
	 */
	public static String trimAll(String str) {
		if (StringUtils.isNullOrEmpty(str))
			return "";
		str = trim(str);
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			String ch = substring(str, i, i + 1);
			if (isNotBlank(ch)) {
				buf.append(ch);
			}
		}
		return buf.toString();

	}

	/**
	 * 检查字符串是否不是空白：<code>null</code>、空字符串<code>""</code>或只有空白字符。
	 * 
	 * @param str
	 *            要检查的字符串
	 * @return 如果为空白, 则返回<code>true</code>
	 */
	public static boolean isNotBlank(String str) {
		int length;

		if ((str == null) || ((length = str.length()) == 0)) {
			return false;
		}

		for (int i = 0; i < length; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return true;
			}
		}

		return false;
	}

	/**
	 * 判断字符串是否只包含unicode数字。
	 * <p>
	 * <code>null</code>将返回<code>false</code>，空字符串<code>""</code>将返回
	 * <code>true</code>。
	 * </p>
	 * 
	 * <pre>
	 *                                 StringUtil.isNumeric(null)   = false
	 *                                 StringUtil.isNumeric(&quot;&quot;)     = true
	 *                                 StringUtil.isNumeric(&quot;  &quot;)   = false
	 *                                 StringUtil.isNumeric(&quot;123&quot;)  = true
	 *                                 StringUtil.isNumeric(&quot;12 3&quot;) = false
	 *                                 StringUtil.isNumeric(&quot;ab2c&quot;) = false
	 *                                 StringUtil.isNumeric(&quot;12-3&quot;) = false
	 *                                 StringUtil.isNumeric(&quot;12.3&quot;) = false
	 * </pre>
	 * 
	 * @param str
	 *            要检查的字符串
	 * @return 如果字符串非<code>null</code>并且全由unicode数字组成，则返回<code>true</code>
	 */
	public static boolean isNumeric(String str) {
		if (str == null) {
			return false;
		}

		int length = str.length();

		for (int i = 0; i < length; i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}

		return true;
	}

	/**
	 * 取指定字符串的子串。
	 * <p>
	 * 负的索引代表从尾部开始计算。如果字符串为<code>null</code>，则返回<code>null</code>。
	 * 
	 * <pre>
	 *                                 StringUtil.substring(null, *)   = null
	 *                                 StringUtil.substring(&quot;&quot;, *)     = &quot;&quot;
	 *                                 StringUtil.substring(&quot;abc&quot;, 0)  = &quot;abc&quot;
	 *                                 StringUtil.substring(&quot;abc&quot;, 2)  = &quot;c&quot;
	 *                                 StringUtil.substring(&quot;abc&quot;, 4)  = &quot;&quot;
	 *                                 StringUtil.substring(&quot;abc&quot;, -2) = &quot;bc&quot;
	 *                                 StringUtil.substring(&quot;abc&quot;, -4) = &quot;abc&quot;
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param str
	 *            字符串
	 * @param start
	 *            起始索引，如果为负数，表示从尾部查找
	 * @return 子串，如果原始串为<code>null</code>，则返回<code>null</code>
	 */
	public static String substring(String str, int start) {
		if (str == null) {
			return null;
		}

		if (start < 0) {
			start = str.length() + start;
		}

		if (start < 0) {
			start = 0;
		}

		if (start > str.length()) {
			return EMPTY_STRING;
		}

		return str.substring(start);
	}

	/**
	 * 取指定字符串的子串。
	 * <p>
	 * 负的索引代表从尾部开始计算。如果字符串为<code>null</code>，则返回<code>null</code>。
	 * 
	 * @param str
	 *            字符串
	 * @param start
	 *            起始索引，如果为负数，表示从尾部计算
	 * @param end
	 *            结束索引（不含），如果为负数，表示从尾部计算
	 * @return 子串，如果原始串为<code>null</code>，则返回<code>null</code>
	 */
	public static String substring(String str, int start, int end) {
		if (str == null) {
			return null;
		}

		if (end < 0) {
			end = str.length() + end;
		}

		if (start < 0) {
			start = str.length() + start;
		}

		if (end > str.length()) {
			end = str.length();
		}

		if (start > end) {
			return EMPTY_STRING;
		}

		if (start < 0) {
			start = 0;
		}

		if (end < 0) {
			end = 0;
		}

		return str.substring(start, end);
	}

	public static String replace(String text, String repl, String with) {
		return replace(text, repl, with, -1);
	}

	/*
	 * ==========================================================================
	 * ==
	 */
	/* 替换子串。 */
	/*
	 * ==========================================================================
	 * ==
	 */

	/**
	 * 替换指定的子串，只替换第一个出现的子串。
	 * 
	 * <p>
	 * 如果字符串为<code>null</code>则返回<code>null</code>，如果指定子串为<code>null</code>
	 * ，则返回原字符串。
	 * 
	 * <pre>
	 *                                 StringUtil.replaceOnce(null, *, *)        = null
	 *                                 StringUtil.replaceOnce(&quot;&quot;, *, *)          = &quot;&quot;
	 *                                 StringUtil.replaceOnce(&quot;aba&quot;, null, null) = &quot;aba&quot;
	 *                                 StringUtil.replaceOnce(&quot;aba&quot;, null, null) = &quot;aba&quot;
	 *                                 StringUtil.replaceOnce(&quot;aba&quot;, &quot;a&quot;, null)  = &quot;aba&quot;
	 *                                 StringUtil.replaceOnce(&quot;aba&quot;, &quot;a&quot;, &quot;&quot;)    = &quot;ba&quot;
	 *                                 StringUtil.replaceOnce(&quot;aba&quot;, &quot;a&quot;, &quot;z&quot;)   = &quot;zba&quot;
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param text
	 *            要扫描的字符串
	 * @param repl
	 *            要搜索的子串
	 * @param with
	 *            替换字符串
	 * 
	 * @return 被替换后的字符串，如果原始字符串为<code>null</code>，则返回<code>null</code>
	 */
	public static String replaceOnce(String text, String repl, String with) {
		return replace(text, repl, with, 1);
	}

	/**
	 * 替换指定的子串，替换指定的次数。
	 * 
	 * <p>
	 * 如果字符串为<code>null</code>则返回<code>null</code>，如果指定子串为<code>null</code>
	 * ，则返回原字符串。
	 * 
	 * <pre>
	 *                                 StringUtil.replace(null, *, *, *)         = null
	 *                                 StringUtil.replace(&quot;&quot;, *, *, *)           = &quot;&quot;
	 *                                 StringUtil.replace(&quot;abaa&quot;, null, null, 1) = &quot;abaa&quot;
	 *                                 StringUtil.replace(&quot;abaa&quot;, null, null, 1) = &quot;abaa&quot;
	 *                                 StringUtil.replace(&quot;abaa&quot;, &quot;a&quot;, null, 1)  = &quot;abaa&quot;
	 *                                 StringUtil.replace(&quot;abaa&quot;, &quot;a&quot;, &quot;&quot;, 1)    = &quot;baa&quot;
	 *                                 StringUtil.replace(&quot;abaa&quot;, &quot;a&quot;, &quot;z&quot;, 0)   = &quot;abaa&quot;
	 *                                 StringUtil.replace(&quot;abaa&quot;, &quot;a&quot;, &quot;z&quot;, 1)   = &quot;zbaa&quot;
	 *                                 StringUtil.replace(&quot;abaa&quot;, &quot;a&quot;, &quot;z&quot;, 2)   = &quot;zbza&quot;
	 *                                 StringUtil.replace(&quot;abaa&quot;, &quot;a&quot;, &quot;z&quot;, -1)  = &quot;zbzz&quot;
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param text
	 *            要扫描的字符串
	 * @param repl
	 *            要搜索的子串
	 * @param with
	 *            替换字符串
	 * @param max
	 *            maximum number of values to replace, or <code>-1</code> if no
	 *            maximum
	 * 
	 * @return 被替换后的字符串，如果原始字符串为<code>null</code>，则返回<code>null</code>
	 */
	public static String replace(String text, String repl, String with, int max) {
		if ((text == null) || (repl == null) || (with == null)
				|| (repl.length() == 0) || (max == 0)) {
			return text;
		}

		StringBuffer buf = new StringBuffer(text.length());
		int start = 0;
		int end = 0;

		while ((end = text.indexOf(repl, start)) != -1) {
			buf.append(text.substring(start, end)).append(with);
			start = end + repl.length();

			if (--max == 0) {
				break;
			}
		}

		buf.append(text.substring(start));
		return buf.toString();
	}

	/**
	 * 将字符串中所有指定的字符，替换成另一个。
	 * 
	 * <p>
	 * 如果字符串为<code>null</code>则返回<code>null</code>。
	 * 
	 * <pre>
	 *                                 StringUtil.replaceChars(null, *, *)        = null
	 *                                 StringUtil.replaceChars(&quot;&quot;, *, *)          = &quot;&quot;
	 *                                 StringUtil.replaceChars(&quot;abcba&quot;, 'b', 'y') = &quot;aycya&quot;
	 *                                 StringUtil.replaceChars(&quot;abcba&quot;, 'z', 'y') = &quot;abcba&quot;
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param str
	 *            要扫描的字符串
	 * @param searchChar
	 *            要搜索的字符
	 * @param replaceChar
	 *            替换字符
	 * 
	 * @return 被替换后的字符串，如果原始字符串为<code>null</code>，则返回<code>null</code>
	 */
	public static String replaceChars(String str, char searchChar,
			char replaceChar) {
		if (str == null) {
			return null;
		}

		return str.replace(searchChar, replaceChar);
	}

	/**
	 * 将字符串中所有指定的字符，替换成另一个。
	 * 
	 * <p>
	 * 如果字符串为<code>null</code>则返回<code>null</code>。如果搜索字符串为<code>null</code>
	 * 或空，则返回原字符串。
	 * </p>
	 * 
	 * <p>
	 * 例如：
	 * <code>replaceChars(&quot;hello&quot;, &quot;ho&quot;, &quot;jy&quot;) = jelly</code>
	 * 。
	 * </p>
	 * 
	 * <p>
	 * 通常搜索字符串和替换字符串是等长的，如果搜索字符串比替换字符串长，则多余的字符将被删除。 如果搜索字符串比替换字符串短，则缺少的字符将被忽略。
	 * 
	 * <pre>
	 *                                 StringUtil.replaceChars(null, *, *)           = null
	 *                                 StringUtil.replaceChars(&quot;&quot;, *, *)             = &quot;&quot;
	 *                                 StringUtil.replaceChars(&quot;abc&quot;, null, *)       = &quot;abc&quot;
	 *                                 StringUtil.replaceChars(&quot;abc&quot;, &quot;&quot;, *)         = &quot;abc&quot;
	 *                                 StringUtil.replaceChars(&quot;abc&quot;, &quot;b&quot;, null)     = &quot;ac&quot;
	 *                                 StringUtil.replaceChars(&quot;abc&quot;, &quot;b&quot;, &quot;&quot;)       = &quot;ac&quot;
	 *                                 StringUtil.replaceChars(&quot;abcba&quot;, &quot;bc&quot;, &quot;yz&quot;)  = &quot;ayzya&quot;
	 *                                 StringUtil.replaceChars(&quot;abcba&quot;, &quot;bc&quot;, &quot;y&quot;)   = &quot;ayya&quot;
	 *                                 StringUtil.replaceChars(&quot;abcba&quot;, &quot;bc&quot;, &quot;yzx&quot;) = &quot;ayzya&quot;
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param str
	 *            要扫描的字符串
	 * @param searchChars
	 *            要搜索的字符串
	 * @param replaceChars
	 *            替换字符串
	 * 
	 * @return 被替换后的字符串，如果原始字符串为<code>null</code>，则返回<code>null</code>
	 */
	public static String replaceChars(String str, String searchChars,
			String replaceChars) {
		if ((str == null) || (str.length() == 0) || (searchChars == null)
				|| (searchChars.length() == 0)) {
			return str;
		}

		char[] chars = str.toCharArray();
		int len = chars.length;
		boolean modified = false;

		for (int i = 0, isize = searchChars.length(); i < isize; i++) {
			char searchChar = searchChars.charAt(i);

			if ((replaceChars == null) || (i >= replaceChars.length())) {
				// 删除
				int pos = 0;

				for (int j = 0; j < len; j++) {
					if (chars[j] != searchChar) {
						chars[pos++] = chars[j];
					} else {
						modified = true;
					}
				}

				len = pos;
			} else {
				// 替换
				for (int j = 0; j < len; j++) {
					if (chars[j] == searchChar) {
						chars[j] = replaceChars.charAt(i);
						modified = true;
					}
				}
			}
		}

		if (!modified) {
			return str;
		}

		return new String(chars, 0, len);
	}

	/**
	 * 将指定的子串用另一指定子串覆盖。
	 * 
	 * <p>
	 * 如果字符串为<code>null</code>，则返回<code>null</code>。 负的索引值将被看作<code>0</code>
	 * ，越界的索引值将被设置成字符串的长度相同的值。
	 * 
	 * <pre>
	 *                                 StringUtil.overlay(null, *, *, *)            = null
	 *                                 StringUtil.overlay(&quot;&quot;, &quot;abc&quot;, 0, 0)          = &quot;abc&quot;
	 *                                 StringUtil.overlay(&quot;abcdef&quot;, null, 2, 4)     = &quot;abef&quot;
	 *                                 StringUtil.overlay(&quot;abcdef&quot;, &quot;&quot;, 2, 4)       = &quot;abef&quot;
	 *                                 StringUtil.overlay(&quot;abcdef&quot;, &quot;&quot;, 4, 2)       = &quot;abef&quot;
	 *                                 StringUtil.overlay(&quot;abcdef&quot;, &quot;zzzz&quot;, 2, 4)   = &quot;abzzzzef&quot;
	 *                                 StringUtil.overlay(&quot;abcdef&quot;, &quot;zzzz&quot;, 4, 2)   = &quot;abzzzzef&quot;
	 *                                 StringUtil.overlay(&quot;abcdef&quot;, &quot;zzzz&quot;, -1, 4)  = &quot;zzzzef&quot;
	 *                                 StringUtil.overlay(&quot;abcdef&quot;, &quot;zzzz&quot;, 2, 8)   = &quot;abzzzz&quot;
	 *                                 StringUtil.overlay(&quot;abcdef&quot;, &quot;zzzz&quot;, -2, -3) = &quot;zzzzabcdef&quot;
	 *                                 StringUtil.overlay(&quot;abcdef&quot;, &quot;zzzz&quot;, 8, 10)  = &quot;abcdefzzzz&quot;
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param str
	 *            要扫描的字符串
	 * @param overlay
	 *            用来覆盖的字符串
	 * @param start
	 *            起始索引
	 * @param end
	 *            结束索引
	 * 
	 * @return 被覆盖后的字符串，如果原始字符串为<code>null</code>，则返回<code>null</code>
	 */
	public static String overlay(String str, String overlay, int start, int end) {
		if (str == null) {
			return null;
		}

		if (overlay == null) {
			overlay = EMPTY_STRING;
		}

		int len = str.length();

		if (start < 0) {
			start = 0;
		}

		if (start > len) {
			start = len;
		}

		if (end < 0) {
			end = 0;
		}

		if (end > len) {
			end = len;
		}

		if (start > end) {
			int temp = start;

			start = end;
			end = temp;
		}

		return new StringBuffer((len + start) - end + overlay.length() + 1)
				.append(str.substring(0, start)).append(overlay)
				.append(str.substring(end)).toString();
	}

	/*
	 * ==========================================================================
	 * ==
	 */
	/* 字符串查找函数 —— 字符或字符串。 */
	/*                                                                              */
	/* 在字符串中查找指定字符或字符串。 */
	/*
	 * ==========================================================================
	 * ==
	 */

	/**
	 * 在字符串中查找指定字符，并返回第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>。
	 * 
	 * <pre>
	 *                                 StringUtil.indexOf(null, *)         = -1
	 *                                 StringUtil.indexOf(&quot;&quot;, *)           = -1
	 *                                 StringUtil.indexOf(&quot;aabaabaa&quot;, 'a') = 0
	 *                                 StringUtil.indexOf(&quot;aabaabaa&quot;, 'b') = 2
	 * </pre>
	 * 
	 * @param str
	 *            要扫描的字符串
	 * @param searchChar
	 *            要查找的字符
	 * 
	 * @return 第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
	 */
	public static int indexOf(String str, char searchChar) {
		if ((str == null) || (str.length() == 0)) {
			return -1;
		}

		return str.indexOf(searchChar);
	}

	/**
	 * 在字符串中查找指定字符，并返回第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>。
	 * 
	 * <pre>
	 *                                 StringUtil.indexOf(null, *, *)          = -1
	 *                                 StringUtil.indexOf(&quot;&quot;, *, *)            = -1
	 *                                 StringUtil.indexOf(&quot;aabaabaa&quot;, 'b', 0)  = 2
	 *                                 StringUtil.indexOf(&quot;aabaabaa&quot;, 'b', 3)  = 5
	 *                                 StringUtil.indexOf(&quot;aabaabaa&quot;, 'b', 9)  = -1
	 *                                 StringUtil.indexOf(&quot;aabaabaa&quot;, 'b', -1) = 2
	 * </pre>
	 * 
	 * @param str
	 *            要扫描的字符串
	 * @param searchChar
	 *            要查找的字符
	 * @param startPos
	 *            开始搜索的索引值，如果小于0，则看作0
	 * 
	 * @return 第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
	 */
	public static int indexOf(String str, char searchChar, int startPos) {
		if ((str == null) || (str.length() == 0)) {
			return -1;
		}

		return str.indexOf(searchChar, startPos);
	}

	/**
	 * 在字符串中查找指定字符串，并返回第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>。
	 * 
	 * <pre>
	 *                                 StringUtil.indexOf(null, *)          = -1
	 *                                 StringUtil.indexOf(*, null)          = -1
	 *                                 StringUtil.indexOf(&quot;&quot;, &quot;&quot;)           = 0
	 *                                 StringUtil.indexOf(&quot;aabaabaa&quot;, &quot;a&quot;)  = 0
	 *                                 StringUtil.indexOf(&quot;aabaabaa&quot;, &quot;b&quot;)  = 2
	 *                                 StringUtil.indexOf(&quot;aabaabaa&quot;, &quot;ab&quot;) = 1
	 *                                 StringUtil.indexOf(&quot;aabaabaa&quot;, &quot;&quot;)   = 0
	 * </pre>
	 * 
	 * @param str
	 *            要扫描的字符串
	 * @param searchStr
	 *            要查找的字符串
	 * 
	 * @return 第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
	 */
	public static int indexOf(String str, String searchStr) {
		if ((str == null) || (searchStr == null)) {
			return -1;
		}

		return str.indexOf(searchStr);
	}

	/**
	 * 在字符串中查找指定字符串，并返回第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>。
	 * 
	 * <pre>
	 *                                 StringUtil.indexOf(null, *, *)          = -1
	 *                                 StringUtil.indexOf(*, null, *)          = -1
	 *                                 StringUtil.indexOf(&quot;&quot;, &quot;&quot;, 0)           = 0
	 *                                 StringUtil.indexOf(&quot;aabaabaa&quot;, &quot;a&quot;, 0)  = 0
	 *                                 StringUtil.indexOf(&quot;aabaabaa&quot;, &quot;b&quot;, 0)  = 2
	 *                                 StringUtil.indexOf(&quot;aabaabaa&quot;, &quot;ab&quot;, 0) = 1
	 *                                 StringUtil.indexOf(&quot;aabaabaa&quot;, &quot;b&quot;, 3)  = 5
	 *                                 StringUtil.indexOf(&quot;aabaabaa&quot;, &quot;b&quot;, 9)  = -1
	 *                                 StringUtil.indexOf(&quot;aabaabaa&quot;, &quot;b&quot;, -1) = 2
	 *                                 StringUtil.indexOf(&quot;aabaabaa&quot;, &quot;&quot;, 2)   = 2
	 *                                 StringUtil.indexOf(&quot;abc&quot;, &quot;&quot;, 9)        = 3
	 * </pre>
	 * 
	 * @param str
	 *            要扫描的字符串
	 * @param searchStr
	 *            要查找的字符串
	 * @param startPos
	 *            开始搜索的索引值，如果小于0，则看作0
	 * 
	 * @return 第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
	 */
	public static int indexOf(String str, String searchStr, int startPos) {
		if ((str == null) || (searchStr == null)) {
			return -1;
		}

		// JDK1.3及以下版本的bug：不能正确处理下面的情况
		if ((searchStr.length() == 0) && (startPos >= str.length())) {
			return str.length();
		}

		return str.indexOf(searchStr, startPos);
	}

	/**
	 * 在字符串中查找指定字符集合中的字符，并返回第一个匹配的起始索引。 如果字符串为<code>null</code>，则返回
	 * <code>-1</code>。 如果字符集合为<code>null</code>或空，也返回<code>-1</code>。
	 * 
	 * <pre>
	 *                                 StringUtil.indexOfAny(null, *)                = -1
	 *                                 StringUtil.indexOfAny(&quot;&quot;, *)                  = -1
	 *                                 StringUtil.indexOfAny(*, null)                = -1
	 *                                 StringUtil.indexOfAny(*, [])                  = -1
	 *                                 StringUtil.indexOfAny(&quot;zzabyycdxx&quot;,['z','a']) = 0
	 *                                 StringUtil.indexOfAny(&quot;zzabyycdxx&quot;,['b','y']) = 3
	 *                                 StringUtil.indexOfAny(&quot;aba&quot;, ['z'])           = -1
	 * </pre>
	 * 
	 * @param str
	 *            要扫描的字符串
	 * @param searchChars
	 *            要搜索的字符集合
	 * 
	 * @return 第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
	 */
	public static int indexOfAny(String str, char[] searchChars) {
		if ((str == null) || (str.length() == 0) || (searchChars == null)
				|| (searchChars.length == 0)) {
			return -1;
		}

		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);

			for (int j = 0; j < searchChars.length; j++) {
				if (searchChars[j] == ch) {
					return i;
				}
			}
		}

		return -1;
	}

	/**
	 * 在字符串中查找指定字符集合中的字符，并返回第一个匹配的起始索引。 如果字符串为<code>null</code>，则返回
	 * <code>-1</code>。 如果字符集合为<code>null</code>或空，也返回<code>-1</code>。
	 * 
	 * <pre>
	 *                                 StringUtil.indexOfAny(null, *)            = -1
	 *                                 StringUtil.indexOfAny(&quot;&quot;, *)              = -1
	 *                                 StringUtil.indexOfAny(*, null)            = -1
	 *                                 StringUtil.indexOfAny(*, &quot;&quot;)              = -1
	 *                                 StringUtil.indexOfAny(&quot;zzabyycdxx&quot;, &quot;za&quot;) = 0
	 *                                 StringUtil.indexOfAny(&quot;zzabyycdxx&quot;, &quot;by&quot;) = 3
	 *                                 StringUtil.indexOfAny(&quot;aba&quot;,&quot;z&quot;)          = -1
	 * </pre>
	 * 
	 * @param str
	 *            要扫描的字符串
	 * @param searchChars
	 *            要搜索的字符集合
	 * 
	 * @return 第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
	 */
	public static int indexOfAny(String str, String searchChars) {
		if ((str == null) || (str.length() == 0) || (searchChars == null)
				|| (searchChars.length() == 0)) {
			return -1;
		}

		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);

			for (int j = 0; j < searchChars.length(); j++) {
				if (searchChars.charAt(j) == ch) {
					return i;
				}
			}
		}

		return -1;
	}

	/**
	 * 在字符串中查找指定字符串集合中的字符串，并返回第一个匹配的起始索引。 如果字符串为<code>null</code>，则返回
	 * <code>-1</code>。 如果字符串集合为<code>null</code>或空，也返回<code>-1</code>。
	 * 如果字符串集合包括<code>""</code>，并且字符串不为<code>null</code>，则返回
	 * <code>str.length()</code>
	 * 
	 * <pre>
	 *                                 StringUtil.indexOfAny(null, *)                     = -1
	 *                                 StringUtil.indexOfAny(*, null)                     = -1
	 *                                 StringUtil.indexOfAny(*, [])                       = -1
	 *                                 StringUtil.indexOfAny(&quot;zzabyycdxx&quot;, [&quot;ab&quot;,&quot;cd&quot;])   = 2
	 *                                 StringUtil.indexOfAny(&quot;zzabyycdxx&quot;, [&quot;cd&quot;,&quot;ab&quot;])   = 2
	 *                                 StringUtil.indexOfAny(&quot;zzabyycdxx&quot;, [&quot;mn&quot;,&quot;op&quot;])   = -1
	 *                                 StringUtil.indexOfAny(&quot;zzabyycdxx&quot;, [&quot;zab&quot;,&quot;aby&quot;]) = 1
	 *                                 StringUtil.indexOfAny(&quot;zzabyycdxx&quot;, [&quot;&quot;])          = 0
	 *                                 StringUtil.indexOfAny(&quot;&quot;, [&quot;&quot;])                    = 0
	 *                                 StringUtil.indexOfAny(&quot;&quot;, [&quot;a&quot;])                   = -1
	 * </pre>
	 * 
	 * @param str
	 *            要扫描的字符串
	 * @param searchStrs
	 *            要搜索的字符串集合
	 * 
	 * @return 第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
	 */
	public static int indexOfAny(String str, String[] searchStrs) {
		if ((str == null) || (searchStrs == null)) {
			return -1;
		}

		int sz = searchStrs.length;

		// String's can't have a MAX_VALUEth index.
		int ret = Integer.MAX_VALUE;

		int tmp = 0;

		for (int i = 0; i < sz; i++) {
			String search = searchStrs[i];

			if (search == null) {
				continue;
			}

			tmp = str.indexOf(search);

			if (tmp == -1) {
				continue;
			}

			if (tmp < ret) {
				ret = tmp;
			}
		}

		return (ret == Integer.MAX_VALUE) ? (-1) : ret;
	}

	/**
	 * 在字符串中查找不在指定字符集合中的字符，并返回第一个匹配的起始索引。 如果字符串为<code>null</code>，则返回
	 * <code>-1</code>。 如果字符集合为<code>null</code>或空，也返回<code>-1</code>。
	 * 
	 * <pre>
	 *                                 StringUtil.indexOfAnyBut(null, *)             = -1
	 *                                 StringUtil.indexOfAnyBut(&quot;&quot;, *)               = -1
	 *                                 StringUtil.indexOfAnyBut(*, null)             = -1
	 *                                 StringUtil.indexOfAnyBut(*, [])               = -1
	 *                                 StringUtil.indexOfAnyBut(&quot;zzabyycdxx&quot;,'za')   = 3
	 *                                 StringUtil.indexOfAnyBut(&quot;zzabyycdxx&quot;, 'by')  = 0
	 *                                 StringUtil.indexOfAnyBut(&quot;aba&quot;, 'ab')         = -1
	 * </pre>
	 * 
	 * @param str
	 *            要扫描的字符串
	 * @param searchChars
	 *            要搜索的字符集合
	 * 
	 * @return 第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
	 */
	public static int indexOfAnyBut(String str, char[] searchChars) {
		if ((str == null) || (str.length() == 0) || (searchChars == null)
				|| (searchChars.length == 0)) {
			return -1;
		}

		outer: for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);

			for (int j = 0; j < searchChars.length; j++) {
				if (searchChars[j] == ch) {
					continue outer;
				}
			}

			return i;
		}

		return -1;
	}

	/**
	 * 在字符串中查找不在指定字符集合中的字符，并返回第一个匹配的起始索引。 如果字符串为<code>null</code>，则返回
	 * <code>-1</code>。 如果字符集合为<code>null</code>或空，也返回<code>-1</code>。
	 * 
	 * <pre>
	 *                                 StringUtil.indexOfAnyBut(null, *)            = -1
	 *                                 StringUtil.indexOfAnyBut(&quot;&quot;, *)              = -1
	 *                                 StringUtil.indexOfAnyBut(*, null)            = -1
	 *                                 StringUtil.indexOfAnyBut(*, &quot;&quot;)              = -1
	 *                                 StringUtil.indexOfAnyBut(&quot;zzabyycdxx&quot;, &quot;za&quot;) = 3
	 *                                 StringUtil.indexOfAnyBut(&quot;zzabyycdxx&quot;, &quot;by&quot;) = 0
	 *                                 StringUtil.indexOfAnyBut(&quot;aba&quot;,&quot;ab&quot;)         = -1
	 * </pre>
	 * 
	 * @param str
	 *            要扫描的字符串
	 * @param searchChars
	 *            要搜索的字符集合
	 * 
	 * @return 第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
	 */
	public static int indexOfAnyBut(String str, String searchChars) {
		if ((str == null) || (str.length() == 0) || (searchChars == null)
				|| (searchChars.length() == 0)) {
			return -1;
		}

		for (int i = 0; i < str.length(); i++) {
			if (searchChars.indexOf(str.charAt(i)) < 0) {
				return i;
			}
		}

		return -1;
	}

	/**
	 * 从字符串尾部开始查找指定字符，并返回第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回
	 * <code>-1</code>。
	 * 
	 * <pre>
	 *                                 StringUtil.lastIndexOf(null, *)         = -1
	 *                                 StringUtil.lastIndexOf(&quot;&quot;, *)           = -1
	 *                                 StringUtil.lastIndexOf(&quot;aabaabaa&quot;, 'a') = 7
	 *                                 StringUtil.lastIndexOf(&quot;aabaabaa&quot;, 'b') = 5
	 * </pre>
	 * 
	 * @param str
	 *            要扫描的字符串
	 * @param searchChar
	 *            要查找的字符
	 * 
	 * @return 第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
	 */
	public static int lastIndexOf(String str, char searchChar) {
		if ((str == null) || (str.length() == 0)) {
			return -1;
		}

		return str.lastIndexOf(searchChar);
	}

	/**
	 * 从字符串尾部开始查找指定字符，并返回第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回
	 * <code>-1</code>。
	 * 
	 * <pre>
	 *                                 StringUtil.lastIndexOf(null, *, *)          = -1
	 *                                 StringUtil.lastIndexOf(&quot;&quot;, *,  *)           = -1
	 *                                 StringUtil.lastIndexOf(&quot;aabaabaa&quot;, 'b', 8)  = 5
	 *                                 StringUtil.lastIndexOf(&quot;aabaabaa&quot;, 'b', 4)  = 2
	 *                                 StringUtil.lastIndexOf(&quot;aabaabaa&quot;, 'b', 0)  = -1
	 *                                 StringUtil.lastIndexOf(&quot;aabaabaa&quot;, 'b', 9)  = 5
	 *                                 StringUtil.lastIndexOf(&quot;aabaabaa&quot;, 'b', -1) = -1
	 *                                 StringUtil.lastIndexOf(&quot;aabaabaa&quot;, 'a', 0)  = 0
	 * </pre>
	 * 
	 * @param str
	 *            要扫描的字符串
	 * @param searchChar
	 *            要查找的字符
	 * @param startPos
	 *            从指定索引开始向前搜索
	 * 
	 * @return 第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
	 */
	public static int lastIndexOf(String str, char searchChar, int startPos) {
		if ((str == null) || (str.length() == 0)) {
			return -1;
		}

		return str.lastIndexOf(searchChar, startPos);
	}

	/**
	 * 从字符串尾部开始查找指定字符串，并返回第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回
	 * <code>-1</code>。
	 * 
	 * <pre>
	 *                                 StringUtil.lastIndexOf(null, *)         = -1
	 *                                 StringUtil.lastIndexOf(&quot;&quot;, *)           = -1
	 *                                 StringUtil.lastIndexOf(&quot;aabaabaa&quot;, 'a') = 7
	 *                                 StringUtil.lastIndexOf(&quot;aabaabaa&quot;, 'b') = 5
	 * </pre>
	 * 
	 * @param str
	 *            要扫描的字符串
	 * @param searchStr
	 *            要查找的字符串
	 * 
	 * @return 第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
	 */
	public static int lastIndexOf(String str, String searchStr) {
		if ((str == null) || (searchStr == null)) {
			return -1;
		}

		return str.lastIndexOf(searchStr);
	}

	/**
	 * 从字符串尾部开始查找指定字符串，并返回第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回
	 * <code>-1</code>。
	 * 
	 * <pre>
	 *                                 StringUtil.lastIndexOf(null, *, *)          = -1
	 *                                 StringUtil.lastIndexOf(*, null, *)          = -1
	 *                                 StringUtil.lastIndexOf(&quot;aabaabaa&quot;, &quot;a&quot;, 8)  = 7
	 *                                 StringUtil.lastIndexOf(&quot;aabaabaa&quot;, &quot;b&quot;, 8)  = 5
	 *                                 StringUtil.lastIndexOf(&quot;aabaabaa&quot;, &quot;ab&quot;, 8) = 4
	 *                                 StringUtil.lastIndexOf(&quot;aabaabaa&quot;, &quot;b&quot;, 9)  = 5
	 *                                 StringUtil.lastIndexOf(&quot;aabaabaa&quot;, &quot;b&quot;, -1) = -1
	 *                                 StringUtil.lastIndexOf(&quot;aabaabaa&quot;, &quot;a&quot;, 0)  = 0
	 *                                 StringUtil.lastIndexOf(&quot;aabaabaa&quot;, &quot;b&quot;, 0)  = -1
	 * </pre>
	 * 
	 * @param str
	 *            要扫描的字符串
	 * @param searchStr
	 *            要查找的字符串
	 * @param startPos
	 *            从指定索引开始向前搜索
	 * 
	 * @return 第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
	 */
	public static int lastIndexOf(String str, String searchStr, int startPos) {
		if ((str == null) || (searchStr == null)) {
			return -1;
		}

		return str.lastIndexOf(searchStr, startPos);
	}

	/**
	 * 从字符串尾部开始查找指定字符串集合中的字符串，并返回第一个匹配的起始索引。 如果字符串为<code>null</code>，则返回
	 * <code>-1</code>。 如果字符串集合为<code>null</code>或空，也返回<code>-1</code>。
	 * 如果字符串集合包括<code>""</code>，并且字符串不为<code>null</code>，则返回
	 * <code>str.length()</code>
	 * 
	 * <pre>
	 *                                 StringUtil.lastIndexOfAny(null, *)                   = -1
	 *                                 StringUtil.lastIndexOfAny(*, null)                   = -1
	 *                                 StringUtil.lastIndexOfAny(*, [])                     = -1
	 *                                 StringUtil.lastIndexOfAny(*, [null])                 = -1
	 *                                 StringUtil.lastIndexOfAny(&quot;zzabyycdxx&quot;, [&quot;ab&quot;,&quot;cd&quot;]) = 6
	 *                                 StringUtil.lastIndexOfAny(&quot;zzabyycdxx&quot;, [&quot;cd&quot;,&quot;ab&quot;]) = 6
	 *                                 StringUtil.lastIndexOfAny(&quot;zzabyycdxx&quot;, [&quot;mn&quot;,&quot;op&quot;]) = -1
	 *                                 StringUtil.lastIndexOfAny(&quot;zzabyycdxx&quot;, [&quot;mn&quot;,&quot;op&quot;]) = -1
	 *                                 StringUtil.lastIndexOfAny(&quot;zzabyycdxx&quot;, [&quot;mn&quot;,&quot;&quot;])   = 10
	 * </pre>
	 * 
	 * @param str
	 *            要扫描的字符串
	 * @param searchStrs
	 *            要搜索的字符串集合
	 * 
	 * @return 第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
	 */
	public static int lastIndexOfAny(String str, String[] searchStrs) {
		if ((str == null) || (searchStrs == null)) {
			return -1;
		}

		int searchStrsLength = searchStrs.length;
		int index = -1;
		int tmp = 0;

		for (int i = 0; i < searchStrsLength; i++) {
			String search = searchStrs[i];

			if (search == null) {
				continue;
			}

			tmp = str.lastIndexOf(search);

			if (tmp > index) {
				index = tmp;
			}
		}

		return index;
	}

	/*
	 * ==========================================================================
	 * ==
	 */
	/* 判空函数。 */
	/*                                                                              */
	/* 以下方法用来判定一个字符串是否为： */
	/* 1. null */
	/* 2. empty - "" */
	/* 3. blank - "全部是空白" - 空白由Character.isWhitespace所定义。 */
	/*
	 * ==========================================================================
	 * ==
	 */

	/**
	 * 检查字符串是否是空白：<code>null</code>、空字符串<code>""</code>或只有空白字符。
	 * 
	 * <pre>
	 *                                 StringUtil.isBlank(null)      = true
	 *                                 StringUtil.isBlank(&quot;&quot;)        = true
	 *                                 StringUtil.isBlank(&quot; &quot;)       = true
	 *                                 StringUtil.isBlank(&quot;bob&quot;)     = false
	 *                                 StringUtil.isBlank(&quot;  bob  &quot;) = false
	 * </pre>
	 * 
	 * @param str
	 *            要检查的字符串
	 * 
	 * @return 如果为空白, 则返回<code>true</code>
	 */
	public static boolean isBlank(String str) {
		int length;

		if ((str == null) || ((length = str.length()) == 0)) {
			return true;
		}

		for (int i = 0; i < length; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return false;
			}
		}

		return true;
	}

	/**
	 * 检查字符串是否为<code>null</code>或空字符串<code>""</code>。
	 * 
	 * <pre>
	 *                                 StringUtil.isEmpty(null)      = true
	 *                                 StringUtil.isEmpty(&quot;&quot;)        = true
	 *                                 StringUtil.isEmpty(&quot; &quot;)       = false
	 *                                 StringUtil.isEmpty(&quot;bob&quot;)     = false
	 *                                 StringUtil.isEmpty(&quot;  bob  &quot;) = false
	 * </pre>
	 * 
	 * @param str
	 *            要检查的字符串
	 * 
	 * @return 如果为空, 则返回<code>true</code>
	 */
	public static boolean isEmpty(String str) {
		return ((str == null) || (str.length() == 0));
	}

	/**
	 * 检查字符串是否不是<code>null</code>和空字符串<code>""</code>。
	 * 
	 * <pre>
	 *                                 StringUtil.isEmpty(null)      = false
	 *                                 StringUtil.isEmpty(&quot;&quot;)        = false
	 *                                 StringUtil.isEmpty(&quot; &quot;)       = true
	 *                                 StringUtil.isEmpty(&quot;bob&quot;)     = true
	 *                                 StringUtil.isEmpty(&quot;  bob  &quot;) = true
	 * </pre>
	 * 
	 * @param str
	 *            要检查的字符串
	 * 
	 * @return 如果不为空, 则返回<code>true</code>
	 */
	public static boolean isNotEmpty(String str) {
		return ((str != null) && (str.length() > 0));
	}

}
