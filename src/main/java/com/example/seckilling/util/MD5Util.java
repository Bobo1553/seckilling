package com.example.seckilling.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author Xiao Yijia
 * @create 2020-09-15 22:00
 */

public class MD5Util {

    private static final String SALT = "1a2b3c4d";

    public static String md5(String src){
        return DigestUtils.md5Hex(src);
    }

    public static String inputPassToFormPass(String inputPass) {
        String string = "" + SALT.charAt(0) + SALT.charAt(3) + inputPass + SALT.charAt(4) + SALT.charAt(6);
        return md5(string);
    }

    public static String formPassToDbPass(String formPass, String salt){
        String string = "" + salt.charAt(0) + salt.charAt(3) + formPass + salt.charAt(4) + salt.charAt(6);
        return md5(string);
    }

    public static String inputPassToDbPass(String inputPass, String salt){
        String formPass = inputPassToFormPass(inputPass);
        return formPassToDbPass(formPass, salt);
    }

    public static void main(String[] args) {
        System.out.println(inputPassToFormPass("123456"));//a0a5918a88f8ba33361cd49cadc360df
        System.out.println(inputPassToDbPass("123456", "1a2b3c4d"));//504d9aa3ecb5c4305f675cab6a5e6eed
    }

}
