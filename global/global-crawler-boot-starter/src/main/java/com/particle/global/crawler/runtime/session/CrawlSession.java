package com.particle.global.crawler.runtime.session;

import com.particle.global.crawler.common.enums.DriverType;
import com.particle.global.crawler.common.enums.SessionStatus;
import com.particle.global.crawler.driver.CrawlDriver;
import com.particle.global.crawler.runtime.session.auth.AuthConfig;

import java.util.List;

/**
 * 爬取会话接口
 * 核心概念：session 的定位是对一个驱动实例的持有者，因为驱动不能跨机器共享
 * session 使用 session 池管理，对用户透明
 *
 * @author yangwei
 * @since 2026/05/13 13:00
 */
public interface CrawlSession {

    /**
     * 获取会话 ID
     *
     * @return 会话唯一标识
     */
    String getSessionId();

    /**
     * 获取驱动类型
     *
     * @return 驱动类型
     */
    DriverType getDriverType();

    /**
     * 设置驱动类型
     *
     * @param driverType 驱动类型
     */
    void setDriverType(DriverType driverType);
    /**
     * 获取驱动
     *
     * @return 驱动
     */
    CrawlDriver getDriver();

    /**
     * 设置驱动
     *
     * @param driver 驱动
     */
    void setDriver(CrawlDriver driver);
    /**
     * 获取会话状态
     *
     * @return 会话状态
     */
    SessionStatus getStatus();
    /**
     * 设置会话状态
     *
     * @param status 会话状态
     */
    void setStatus(SessionStatus status);

    /**
     * 会话是否激活
     *
     * @return 是否激活
     */
    boolean isAvailable();
    /**
     * 设置会话是否激活
     *
     * @param available 是否激活
     */
    void setAvailable(boolean available);

    /**
     * 加载认证信息
     *
     * @param authConfigList 认证信息列表
     */
    void loadAuth(List<AuthConfig> authConfigList);

    /**
     * 保存认证信息
     *
     * @param authConfigList 认证信息列表
     */
    void saveAuth(List<AuthConfig> authConfigList);
    /**
     * 销毁会话
     * <p>
     * 清理会话资源，对于 DatabaseCrawlSession 可能需要从数据库中删除
     * </p>
     */
    void close();
}
