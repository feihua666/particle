package com.particle.common.infrastructure.gateway;

import com.particle.common.domain.AggreateRoot;
import com.particle.common.domain.event.DomainEvent;
import com.particle.common.domain.gateway.IBaseGateway;
import com.particle.common.domain.id.Id;
import com.particle.global.messaging.event.api.MessageEventSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>
 * 防腐层基础实现类
 * </p>
 *
 * @author yangwei
 * @since 2022-04-20 11:55
 */
@Slf4j
public abstract class AbstractBaseGatewayImpl<ID extends Id,AR extends AggreateRoot> implements IBaseGateway<ID,AR> {

	/**
	 * 不强制依赖，在不启用全局消息组件时，可以正常使用
	 */
	@Autowired(required = false)
	protected MessageEventSender messageEventSender;

	@Override
	public boolean save(AR ar) {
		boolean b = doSave(ar);
		if (b) {
			if (messageEventSender != null) {
				log.info("send domain events size={}",ar.getDomainEvents().size());
				messageEventSender.sendBatch(((List) ar.getDomainEvents()));
			}else {
				log.warn("messageEventSender is null. enable global message first");
			}
			ar.clearEvents();
		}
		return b;
	}

	/**
	 * 保存
	 * @param ar
	 * @return
	 */
	public abstract boolean doSave(AR ar);

	@Override
	public void sendDomainEvents(List<DomainEvent> domainEventList) {
		if (messageEventSender != null) {
			log.info("send domain events only size={}",domainEventList.size());
			messageEventSender.sendBatch((List)domainEventList);
		}else {
			log.warn("messageEventSender is null. enable global message first");
		}
	}
}
