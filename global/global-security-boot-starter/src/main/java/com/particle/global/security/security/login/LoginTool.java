package com.particle.global.security.security.login;

import com.particle.global.tool.thread.ThreadContextTool;

/**
 * <p>
 * 登录相关工具，主要是控制一些登录逻辑
 * </p>
 *
 * @author yangwei
 * @since 2023/11/1 10:53
 */
public class LoginTool {

    private  static String ignoreTenantResourceAndClearTenantLocalKey = "ignoreTenantResourceAndClearTenantLocal";


    /**
     * 用来判断是否忽略租户解析和清空本地线程已经解析到的租户信息
     * @return
     */
    public static boolean checkIgnoreTenantResolveAndClearTenantLocal() {
        Boolean b = (Boolean)ThreadContextTool.get(ignoreTenantResourceAndClearTenantLocalKey);
        return b != null && b;
    }

    /**
     * 设置忽略租户解析和清空本地线程已经解析到的租户信息
     */
    public static void doIgnoreTenantResolveAndClearTenantLocal() {
        ThreadContextTool.put(ignoreTenantResourceAndClearTenantLocalKey,true);
    }
}
