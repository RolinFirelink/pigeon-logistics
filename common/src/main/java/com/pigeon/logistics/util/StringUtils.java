package com.pigeon.logistics.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author dxy
 * @date 2023年3月28日
 */
public class StringUtils {

    /**
     * 判断字符串是不是数字类型
     *
     * @param str 字符串
     * @return 是否是数字相关类型
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]+[.]{0,1}[0-9]*[dD]{0,1}");
        Matcher isNum = pattern.matcher(str);
        return isNum.matches();
    }
}
