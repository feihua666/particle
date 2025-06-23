package com.particle.data.domain.company;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 企业送达公告 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:06
 */
public class DataCompanyDeliveryAnnouncementId extends Id {

	public DataCompanyDeliveryAnnouncementId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 企业送达公告 领域模型id
	 * @param id
	 * @return
	 */
	public static DataCompanyDeliveryAnnouncementId of(Long id){
		return new DataCompanyDeliveryAnnouncementId(id);
	}
}
