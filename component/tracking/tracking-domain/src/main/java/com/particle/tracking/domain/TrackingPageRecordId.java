package com.particle.tracking.domain;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 页面埋点记录 领域模型id
 * </p>
 *
 * @author yw
 * @since 2023-05-10 11:44:01
 */
public class TrackingPageRecordId extends Id {

	public TrackingPageRecordId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 页面埋点记录 领域模型id
	 * @param id
	 * @return
	 */
	public static TrackingPageRecordId of(Long id){
		return new TrackingPageRecordId(id);
	}
}
