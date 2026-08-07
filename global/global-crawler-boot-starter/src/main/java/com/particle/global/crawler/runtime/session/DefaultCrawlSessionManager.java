package com.particle.global.crawler.runtime.session;

import cn.hutool.core.lang.UUID;
import com.particle.global.crawler.common.enums.DriverType;
import com.particle.global.crawler.driver.CrawlDriver;
import com.particle.global.crawler.driver.DriverFactory;
import com.particle.global.crawler.runtime.CrawlRuntimeOptions;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2026/5/14 09:45
 */
@Slf4j
public class DefaultCrawlSessionManager implements CrawlSessionManager{
    /**
     * Session 池
     * key = sessionId
     */
    private final Map<String, CrawlSession> sessionPool = new ConcurrentHashMap<>();

    /**
     * 认证仓库
     */
    private final CrawlSessionAuthInfoRepository authRepository;

    /**
     * Driver 工厂
     */
    private final DriverFactory driverFactory;

    public DefaultCrawlSessionManager(CrawlSessionAuthInfoRepository authRepository, DriverFactory driverFactory) {
        this.authRepository = authRepository;
        this.driverFactory = driverFactory;
    }


    @Override
    public CrawlSession getSession(String sessionId) {
        if (sessionId == null || sessionId.isEmpty()) {
            return null;
        }
        CrawlSession session = sessionPool.get(sessionId);
        if (session != null && !session.isAvailable()) {
            // Session 已失效，移除并返回 null
            removeSession(sessionId);
            return null;
        }
        return session;
    }

    @Override
    public CrawlSession createSession(CrawlRuntimeOptions options) {
        // 1. 生成唯一 sessionId
        String sessionId = generateSessionId();

        // 2. 创建 Driver
        CrawlDriver driver = driverFactory.create(options);

        // 3. 创建 Session
        DriverType driverType = options.getDeriver().getDriverType();
        DefaultCrawlSession session = new DefaultCrawlSession(sessionId,driverType, driver,true,authRepository);
        // 4. 存入池中
        sessionPool.put(sessionId, session);

        log.info("创建 Session: {}", sessionId);
        return session;
    }

    @Override
    public void removeSession(String sessionId) {
        CrawlSession session = sessionPool.remove(sessionId);
        if (session != null) {
            try {
                session.close();
                log.info("关闭并移除 Session: {}", sessionId);
            } catch (Exception e) {
                log.error("关闭 Session 失败: {}", sessionId, e);
            }
        }
    }

    /**
     * 生成唯一 sessionId
     * @return sessionId
     */
    protected String generateSessionId() {
        return UUID.fastUUID().toString();
    }
}
