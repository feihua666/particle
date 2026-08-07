package com.particle.global.crawler.runtime.session;

import com.particle.global.crawler.runtime.CrawlRuntimeOptions;
import com.particle.global.crawler.runtime.RuntimeContext;

/**
 * <p>
 * 会话管理器接口
 * </p>
 *
 * @author yangwei
 * @since 2026/5/13 19:30
 */
public interface CrawlSessionManager {


    /**
     * 获取会话
     * @param sessionId 会话ID
     * @return 会话
     */
    public CrawlSession getSession(String sessionId);

    /**
     * 创建会话
     * 新创建的会话，状态必须为空，供后续代码设置状态
     * @return 会话
     */
    public CrawlSession createSession(CrawlRuntimeOptions options);

    /**
     * 移除会话
     * @param sessionId 会话ID
     */
    public void removeSession(String sessionId);
}
