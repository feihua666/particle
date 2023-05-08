package com.particle.global.dataaudit.audit;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

/**
 * <p>
 * 获取 ApplicationEventPublisher 实例
 * </p>
 *
 * @author yangwei
 * @since 2023-05-07 21:46
 */
public class DataAuditApplicationEventPublisherAware implements ApplicationEventPublisherAware {

	private ApplicationEventPublisher applicationEventPublisher;

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		DataAuditCollectTool.applicationEventPublisher = applicationEventPublisher;
		this.applicationEventPublisher = applicationEventPublisher;
	}

	public ApplicationEventPublisher getApplicationEventPublisher() {
		return applicationEventPublisher;
	}
}
