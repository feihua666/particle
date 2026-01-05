package com.particle.common.domain;

import com.particle.common.domain.event.DomainEvent;
import com.particle.global.dto.basic.DTO;
import lombok.Data;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * <p>
 * 聚合根基类
 * </p>
 *
 * @author yangwei
 * @since 2022-05-05 16:32
 */
@Data
public abstract class AggreateRoot extends DTO {

	/**
	 * 数据版本
	 */
	private Integer version;


	/**
	 * 领域事件
	 */
	private List<DomainEvent> domainEvents;


	/**
	 * 修改版本
	 * @param version
	 */
	public void changeVersionTo(Integer version) {
		this.version = version;
	}
	/**
	 * 发布领域事件
	 * @param event
	 */
	public final void raiseEvent(DomainEvent event) {
		getDomainEvents().add(event);
	}

	/**
	 * 清空领域事件
	 */
	public final void clearEvents() {
		getDomainEvents().clear();
	}

	/**
	 * 获取所有领域事件
	 * @return
	 */
	public final List<DomainEvent> getDomainEvents() {
		if (domainEvents == null) {
			domainEvents = newArrayList();
		}
		return domainEvents;
	}

	/**
	 * 同 {@link com.particle.global.mybatis.plus.dto.BaseDO#updateControl} 透传
	 */
	private Object updateControl;
	/**
	 * 同 {@link com.particle.global.mybatis.plus.dto.BaseDO#addControl} 透传
	 */
	private Object addControl;
    /**
     * 在保存时，可能会主动添加id，如果添加了id，则强制添加，不会检查id是否存在
     */
    private Boolean isForceAdd;
    /**
     * 强制添加
     */
    public void changeForceAdd() {
        this.isForceAdd = true;
    }
}
