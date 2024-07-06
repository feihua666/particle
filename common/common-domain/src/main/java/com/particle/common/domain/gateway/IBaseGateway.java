package com.particle.common.domain.gateway;

import com.google.common.collect.Lists;
import com.particle.common.domain.AggreateRoot;
import com.particle.common.domain.event.DomainEvent;
import com.particle.common.domain.id.Id;
import com.particle.global.dto.basic.IdCommand;
import com.particle.global.dto.dataconstraint.DataConstraintContext;

import java.util.List;

/**
 * <p>
 * 防腐层基础接口类
 * </p>
 *
 * @author yangwei
 * @since 2022-04-20 11:49
 */
public interface IBaseGateway<ID extends Id,AR extends AggreateRoot> extends IGateway {

	/**
	 * 根据 id 获取  领域对象
	 * @param id
	 * @return 必须包括全部可用属性
	 */
	AR getById(ID id);

	/**
	 * 保存 领域对象
	 * 如果不存在应该新增,新增时需要将参数 areaId 设置成功，如果存在应该更新
	 * @param aggreateRoot  领域对象
	 * @return true=保存成功，false=保存失败，注意只要持久化了应该为 {@code true}
	 *         注意在有些 orm 或数据库下数据库中已经存在，更新时影响结果为0，但应该返回 {@code true}
	 */
	boolean save(AR aggreateRoot);


	/**
	 * 删除 领域对象
	 * @param id
	 * @return
	 */
	boolean delete(ID id);
	/**
	 * 删除 领域对象，增加对数据范围约束的支持
	 * 这里设置为默认不加数据权限，因为历史代码需要大量修改，在有需要的地方修改成该方法即可
	 * @param id
	 * @param idCommand
	 * @return
	 */
	default boolean delete(ID id, IdCommand idCommand){
		return delete(id);
	}

	/**
	 * 单独发送领城事件多条
	 * @param domainEventList
	 */
	void sendDomainEvents(List<DomainEvent> domainEventList);

	/**
	 * 单独发送领城事件单条
	 * @param domainEvent
	 */
	default void sendDomainEvent(DomainEvent domainEvent) {
		sendDomainEvents(Lists.newArrayList(domainEvent));
	}

	/**
	 * 异步单独发送领城事件多条
	 * @param domainEventList
	 */
	void sendDomainEventsAsync(List<DomainEvent> domainEventList);

	/**
	 * 异步单独发送领城事件单条
	 * @param domainEvent
	 */
	default void sendDomainEventAsync(DomainEvent domainEvent) {
		sendDomainEventsAsync(Lists.newArrayList(domainEvent));
	}
}
