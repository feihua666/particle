package com.particle.global.tool.servlet;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * getContextPath = /context // 部署到ROOT为空""
 * getPathInfo = null
 * getQueryString = null
 * getRequestURI = /context/news/main/111.html
 * getServletPath = /news/main/111.html
 * getRemoteAddr = 0:0:0:0:0:0:0:1
 * getRemoteAddr1 = 0:0:0:0:0:0:0:1
 * getLocalAddr = 0:0:0:0:0:0:0:1
 * getRequestURL = http://localhost:8080/context/news/main/111.html
 * protocol = HTTP/1.1
 * getServerPort = 8080
 * getScheme = http
 * getServerName = localhost
 * getServletContext = org.apache.catalina.core.ApplicationContextFacade@4f43c2d3
 * <p>
 *     推荐使用 {@link ServletUtil}
 * <p>
 * @author yw on 2016/2/21.
 */
@Slf4j
public class RequestTool {

    /**
     * 获得用户远程地址,ip地址
     */
    public static String getClientIP(HttpServletRequest request, String... otherHeaderNames) {
        String remoteAddr = ServletUtil.getClientIP(request,otherHeaderNames);
        if (StrUtil.isEmpty(remoteAddr)) {
            // 下面是自定义的获取ip的方法暂保留
            remoteAddr = request.getHeader("X-Real-IP");
            if (StrUtil.isEmpty(remoteAddr) || "unknown".equalsIgnoreCase(remoteAddr)) {
                remoteAddr = request.getHeader("X-Forwarded-For");
            }
            if (StrUtil.isEmpty(remoteAddr)  || "unknown".equalsIgnoreCase(remoteAddr)) {
                remoteAddr = request.getHeader("Proxy-Client-IP");
            }
            if (StrUtil.isEmpty(remoteAddr)  || "unknown".equalsIgnoreCase(remoteAddr)) {
                remoteAddr = request.getHeader("WL-Proxy-Client-IP");
            }
            if(StrUtil.isEmpty(remoteAddr) || "unknown".equalsIgnoreCase(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }


        if(StrUtil.equalsAny(remoteAddr,"127.0.0.1","0:0:0:0:0:0:0:1")){
            //根据网卡取本机配置的IP
            InetAddress inet = null;
            try {
                inet = InetAddress.getLocalHost();
                remoteAddr= inet.getHostAddress();
            } catch (UnknownHostException e) {
            }
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if(remoteAddr != null && remoteAddr.length()>15){
            //"***.***.***.***".length() = 15
            if(remoteAddr.indexOf(",") > 0){
                remoteAddr = remoteAddr.substring(0,remoteAddr.indexOf(","));
            }
        }
        return remoteAddr;
    }

    /**
     * 获取取cookie
     *
     * @param request
     *
     * @return
     */
    public static Cookie[] getCookies(HttpServletRequest request) {
        return request.getCookies();
    }

    /**
     * 根据cookie名获取
     *
     * @param name
     * @param request
     *
     * @return
     */
    public static Cookie getCookieByName(String name, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return cookie;
                }
            }
        }
        return null;
    }

    /**
     * 是否是Ajax异步请求
     *
     * @param request
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {

        String accept = request.getHeader("accept");
        String xRequestedWith = request.getHeader("X-Requested-With");

        // 如果是异步请求或是手机端，则直接返回信息
        return ((accept != null && accept.indexOf("application/json") != -1
                || (xRequestedWith != null && xRequestedWith.indexOf("XMLHttpRequest") != -1)
        ));
    }


    /**
     * 获得站点url
     *
     * @return
     */
    public static String getWebUrl(HttpServletRequest request) {
        String url = request.getScheme() + "://" + request.getServerName();
        if (request.getServerPort() != 80) {
            url += ":" + request.getServerPort();
        }
        url += request.getContextPath();
        return url;
    }

    /**
     * 获取headers
     *
     * @param request
     *
     * @return
     */
    public static Map<String, String> getHeaderText(HttpServletRequest request) {
        Map<String, String> map = new HashMap<String, String>();
        Enumeration enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()) {
            String key = (String) enumeration.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }
    /**
     * 获取 请求参数
     *
     * @param request
     *
     * @return
     */
    public static Map<String, String> getRequestParameterText(HttpServletRequest request) {
        Map<String, String> map = new HashMap<String, String>();
        Enumeration enumeration = request.getParameterNames();
        while (enumeration.hasMoreElements()) {
            String key = (String) enumeration.nextElement();
            String value = request.getParameter(key);
            map.put(key, value);
        }
        return map;
    }
    /**
     * 获取当前访问的域名
     *
     * @param request
     * @param includeScheme 是否包含scheme
     * @param includePort   是否包含接口
     *
     * @return http://localhost:8080
     */
    public static String getDomain(HttpServletRequest request, boolean includeScheme, boolean includePort) {
        String scheme = request.getScheme();
        String serverName = request.getServerName();
        int serverPort = request.getServerPort();
        String r = serverName;
        if (includeScheme) {
            r = scheme + "://" + r;
        }
        if (includePort) {
            r = r + ":" + serverPort;
        }
        return r;
    }

    /**
     * @param request
     * @param includeContextPath
     *
     * @return
     */
    public static String[] resolveRequestURI(HttpServletRequest request, boolean includeContextPath) {
        String[] r = null;
        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();

        String siteRequestUri = requestURI;
        if (!includeContextPath && !StrUtil.isEmpty(contextPath)) {
            siteRequestUri = siteRequestUri.substring(contextPath.length());
        }
        r = siteRequestUri.split("/");
        return r;
    }

    /**
     * 获取当前项目的实际地址存放路径
     *
     * @param request
     *
     * @return
     */
    public static String getWebappRealPath(HttpServletRequest request) {

        return request.getServletContext().getRealPath("");
    }
}
