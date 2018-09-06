package com.zhuoan.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 中文字符处理工具类
 * @author nanoha
 *
 */
public class GbkTransUtil {

	public static String getPinYinHeadChar(String str) throws BadHanyuPinyinOutputFormatCombination {

        String convert = "";
        for (int j = 0; j < str.length(); j++) {
            char word = str.charAt(j);
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word,new HanyuPinyinOutputFormat());
            if (pinyinArray != null) {
                convert += pinyinArray[0].charAt(0);
            } else {
                convert += word;
            }
        }
        return convert.substring(0, 1);
    }
}
