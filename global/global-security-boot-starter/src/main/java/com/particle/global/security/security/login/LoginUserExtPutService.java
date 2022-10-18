package com.particle.global.security.security.login;

/**
 * 定义通用的用户登录时，添加当前登录用户额外的配置信息
 * 注意该类使用是在使用登录时，但还未登录成功或完成
 * @author yangwei
 * @since 2021/1/8 13:18
 */
public interface LoginUserExtPutService {

    /**
     * 是否支持该种类型
     * @param user 登录的用户
     * @param type 类型，以传入为准
     * @return
     */
    default boolean support(LoginUser user, String type){
        return true;
    }

    /**
     * 对登录信息添加额外参数
     * @param user
     */
    public void addExt(LoginUser user);
}
