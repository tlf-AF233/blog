package com.af.blog.utils;

import java.util.UUID;

/**
 * UUID 工具类
 */
public class UUIDUtils {

    /**
     * 产生随机字符串
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString();
    }
}
