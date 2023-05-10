package com.particle.tracking.domain;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 埋点页面 领域模型id
 * </p>
 *
 * @author yw
 * @since 2023-05-10 11:39:06
 */
public class TrackingPageId extends Id {

	public TrackingPageId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 埋点页面 领域模型id
	 * @param id
	 * @return
	 */
	public static TrackingPageId of(Long id){
		return new TrackingPageId(id);
	}
}
