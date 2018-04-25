package com.yuzf.utils;

import java.util.Random;

public class KeyUtil {

    /**
     * 生成唯一的逐渐
     * 格式:时间+随机数
     * 多线程并发
     */
    public static synchronized String genUniqueKey(){
        Random random = new Random();
        //生成6位随机数
        Integer number = random.nextInt(900000)+100000;

        return System.currentTimeMillis() + String.valueOf(number);
    }
}
