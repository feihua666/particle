package com.particle.global.crawler.runtime.session;

import com.particle.global.crawler.runtime.session.auth.SessionAuthInfo;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 内存会话认证信息存储库
 * @author yangwei
 * @since 2026/05/13 21:35
 */
@Slf4j
public class MemoryCrawlSessionAuthInfoRepository implements CrawlSessionAuthInfoRepository {

    /**
     * 认证信息存储
     * Key 格式: "domain::account"
     * Value: SessionAuthInfo
     */
    private final Map<String, SessionAuthInfo> authInfoMap = new ConcurrentHashMap<>();

    /**
     * 生成存储键
     *
     * @param domain  域名
     * @param account 账号
     * @return 存储键
     */
    private String generateKey(String domain, String account) {
        if (domain == null || domain.isEmpty()) {
            throw new IllegalArgumentException("domain 不能为空");
        }
        if (account == null || account.isEmpty()) {
            throw new IllegalArgumentException("account 不能为空");
        }
        return domain + "::" + account;
    }

    @Override
    public void save(String domain, String account, SessionAuthInfo sessionAuthInfo) {
        if (sessionAuthInfo == null) {
            log.warn("尝试保存空的认证信息: domain={}, account={}", domain, account);
            return;
        }

        String key = generateKey(domain, account);
        authInfoMap.put(key, sessionAuthInfo);

        log.debug("保存认证信息成功: key={}, cookies={}, localStorage={}",
                key,
                sessionAuthInfo.getCookies() != null ? sessionAuthInfo.getCookies().size() : 0,
                sessionAuthInfo.getLocalStorage() != null ? sessionAuthInfo.getLocalStorage().size() : 0);
    }

    @Override
    public SessionAuthInfo get(String domain, String account) {
        String key = generateKey(domain, account);
        SessionAuthInfo authInfo = authInfoMap.get(key);

        if (authInfo != null) {
            log.debug("获取认证信息成功: key={}", key);

            // 过滤已过期的 Cookie
            authInfo.filterExpiredCookies();

            // 如果过滤后没有任何认证信息，返回 null
            if (authInfo.isEmpty()) {
                log.debug("认证信息已过期或为空，删除: key={}", key);
                delete(domain, account);
                return null;
            }
        } else {
            log.debug("未找到认证信息: key={}", key);
        }

        return authInfo;
    }

    @Override
    public void delete(String domain, String account) {
        String key = generateKey(domain, account);
        SessionAuthInfo removed = authInfoMap.remove(key);

        if (removed != null) {
            log.debug("删除认证信息成功: key={}", key);
        } else {
            log.debug("认证信息不存在，无需删除: key={}", key);
        }
    }

    @Override
    public boolean exists(String domain, String account) {
        String key = generateKey(domain, account);
        boolean exists = authInfoMap.containsKey(key);

        if (exists) {
            log.trace("认证信息存在: key={}", key);
        } else {
            log.trace("认证信息不存在: key={}", key);
        }

        return exists;
    }

    /**
     * 获取所有存储的认证信息数量
     *
     * @return 认证信息数量
     */
    public int size() {
        return authInfoMap.size();
    }
}

