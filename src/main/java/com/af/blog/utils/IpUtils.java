package com.af.blog.utils;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author Tanglinfeng
 * @date 2021/10/9 14:56
 */
public class IpUtils {

    public static String getIpAddress(HttpServletRequest request) {
        String ip = null;

        if (request == null) {
            return "";
         // Redis -> MySQL
        }
        ip = request.getHeader("x-forwarded-for");
        if (checkIp(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (checkIp(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (checkIp(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (checkIp(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (checkIp(ip)) {
            ip = request.getRemoteAddr();
            if ("127.0.0.1".equals(ip) || "0:0:0:0:0:0:0:1".equals(ip)) {
                // 根据网卡取本机配置的IP
                ip = getLocalAddr();
            }
        }


        // 使用代理，则获取第一个IP地址
        if (StringUtils.isEmpty(ip) && ip.length() > 15) {
            if (ip.indexOf(",") > 0) {
                ip = ip.substring(0, ip.indexOf(","));
            }
        }

        return ip;
    }

    private static boolean checkIp(String ip) {
        String unknown = "unknown";
        return StringUtils.isEmpty(ip) || ip.length() == 0 || unknown.equalsIgnoreCase(ip);
    }

    /**
     * 获取本机的IP地址
     *
     */
    private static String getLocalAddr() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException ignored) {

        }
        return "";
    }
}
