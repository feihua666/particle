package com.particle.global.crawler.runtime.session;

import com.particle.global.crawler.runtime.session.auth.SessionAuthInfo;

/**
 * <p>
 * session 认证信息存储
 * </p>
 * 认证信息只和 域名和账号有关
 *
 * @author yangwei
 * @since 2026/5/13 21:25
 */
public interface CrawlSessionAuthInfoRepository {

    /**
     * 保存会话认证信息
     * @param domain
     * @param account
     * @param sessionAuthInfo
     */
    void save(String domain,String account,SessionAuthInfo sessionAuthInfo);

    /**
     * 获取会话认证信息
     * @param domain
     * @param account
     * @return
     */
    SessionAuthInfo get(String domain,String account);

    /**
     * 删除会话认证信息
     * @param domain
     * @param account
     */
    void delete(String domain,String account);

    /**
     * 判断是否存在
     * @param domain
     * @param account
     * @return
     */
    boolean exists(String domain,String account);
}
