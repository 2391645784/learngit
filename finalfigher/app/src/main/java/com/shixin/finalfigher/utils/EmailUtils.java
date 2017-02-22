package com.shixin.finalfigher.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mio on 2017/2/21.
 */

public class EmailUtils {
    /**
     * 判断邮箱是否合法
     *
     * @param emailStr
     * @return
     */
    public static boolean isEmail(String emailStr) {
        boolean isMatched = false;
        String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(emailStr);
        isMatched = matcher.matches();
        return isMatched;
    }
}
