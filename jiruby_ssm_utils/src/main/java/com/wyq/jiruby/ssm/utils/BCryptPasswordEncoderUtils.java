package com.wyq.jiruby.ssm.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordEncoderUtils {
    static BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();

    public static void main(String[] args) {
        String pass="123";
        String encode = bCryptPasswordEncoder.encode(pass);
        System.out.println("encode = " + encode);
    }
}
