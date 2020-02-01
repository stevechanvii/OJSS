/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.regex.Pattern;

/**
 *
 * @author stevechanvii
 */
public class RegexUtil {
    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
    public static final String REGEX_PASSWORD = "^[a-zA-Z0-9]{6,20}$";
    public static final String REGEX_MOBILE = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
    public static final String REGEX_USERNAME = "^[a-zA-Z]\\w{5,20}$";
    public static final String REGEX_CATEGORY = "\\S{1,}";
    
    public boolean isEmail(String email) {
        return Pattern.matches(REGEX_EMAIL, email);
    }
    
    public boolean isPassword(String password) {
        return Pattern.matches(REGEX_PASSWORD, password);
    }
    
    public boolean isCategory(String category){
        return Pattern.matches(REGEX_CATEGORY, category);
    }
    
}
