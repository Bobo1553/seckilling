package com.example.seckilling.util;

import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Xiao Yijia
 * @create 2020/9/16 21:38
 */

public class ValidatorUtil {

    private static final Pattern MOBILE_PATTERN = Pattern.compile("1\\d{10}");

    public static boolean isMobile(String src) {
        if (StringUtils.isEmpty(src)) {
            return false;
        }

        Matcher matcher = MOBILE_PATTERN.matcher(src);
        return matcher.matches();
    }

    public static void main(String[] args) {
        System.out.println(isMobile("18512341234"));
        System.out.println(isMobile("1851234123a"));
    }

}
