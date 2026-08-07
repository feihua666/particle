package com.particle.global.crawler.runtime.session;

import com.particle.global.crawler.common.enums.DriverType;
import com.particle.global.crawler.common.enums.SessionStatus;
import com.particle.global.crawler.driver.CrawlDriver;
import com.particle.global.crawler.runtime.session.auth.AuthConfig;
import com.particle.global.crawler.runtime.session.auth.SessionAuthInfo;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * <p>
 * 爬虫会话实现
 * </p>
 *
 * @author yangwei
 * @since 2026/5/13 19:25
 */
@Slf4j
@Data
public class DefaultCrawlSession implements CrawlSession{

    private String sessionId;

    private DriverType driverType;

    private CrawlDriver driver;

    private SessionStatus status;

    private boolean available;

    private CrawlSessionAuthInfoRepository crawlSessionAuthInfoRepository;

    public DefaultCrawlSession(String sessionId,
                               DriverType driverType,
                               CrawlDriver driver,
                               boolean available,
                               CrawlSessionAuthInfoRepository crawlSessionAuthInfoRepository) {
        this.sessionId = sessionId;
        this.driverType = driverType;
        this.driver = driver;
        this.available = available;
        this.crawlSessionAuthInfoRepository = crawlSessionAuthInfoRepository;
    }

    @Override
    public String getSessionId() {
        return sessionId;
    }

    @Override
    public DriverType getDriverType() {
        return driverType;
    }

    @Override
    public void setDriverType(DriverType driverType) {
        this.driverType = driverType;
    }

    @Override
    public CrawlDriver getDriver() {
        return driver;
    }

    @Override
    public void setDriver(CrawlDriver driver) {
        this.driver = driver;
    }

    @Override
    public SessionStatus getStatus() {
        return status;
    }

    @Override
    public void setStatus(SessionStatus status) {
        this.status = status;
    }

    @Override
    public boolean isAvailable() {
        return this.available;
    }

    @Override
    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public void loadAuth(List<AuthConfig> authConfigList) {
        if (authConfigList == null || authConfigList.isEmpty()) {
            return;
        }
        SessionAuthInfo sessionAuthInfo = null;
        for (AuthConfig authConfig : authConfigList) {
            SessionAuthInfo sessionAuthInfodDb = crawlSessionAuthInfoRepository.get(authConfig.getDomain(), authConfig.getUsername());
            if (sessionAuthInfo == null) {
                sessionAuthInfo = sessionAuthInfodDb;
            }else {
                sessionAuthInfo.merge(sessionAuthInfodDb);
            }
        }
        if (sessionAuthInfo != null && !sessionAuthInfo.isEmpty()) {
            try {
                driver.applyAuthInfo(sessionAuthInfo);
            } catch (Exception e) {
                // 抛异常后，认证deriver失效了，标记为不可用
                this.available = false;
            }
        }
    }

    @Override
    public void saveAuth(List<AuthConfig> authConfigList) {
        SessionAuthInfo authInfo = driver.getAuthInfo();
        if (authInfo != null && !authInfo.isEmpty()) {
            for (AuthConfig authConfig : authConfigList) {
                SessionAuthInfo sessionAuthInfo = authInfo.filterByDomain(authConfig.getDomain());
                if (sessionAuthInfo != null && !sessionAuthInfo.isEmpty()) {
                    crawlSessionAuthInfoRepository.save(authConfig.getDomain(), authConfig.getUsername(), sessionAuthInfo);
                }
            }
        }
    }

    @Override
    public void close() {
        if (driver != null) {
            try {
                driver.close();
            } catch (Exception e) {
                log.warn("关闭 Driver 失败", e);
            }
        }
    }
}
