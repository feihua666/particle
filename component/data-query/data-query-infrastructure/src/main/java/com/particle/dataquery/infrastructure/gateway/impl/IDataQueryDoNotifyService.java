package com.particle.dataquery.infrastructure.gateway.impl;

/**
 * <p>
 * 这里包装一层，不强制依赖
 * </p>
 *
 * @author yangwei
 * @since 2024/1/19 16:17
 */
public interface IDataQueryDoNotifyService {

    public void notifySystem(String title, String contentType, String suggest, String content);
}
