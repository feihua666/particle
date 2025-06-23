package com.particle.data.domain.company;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 企业送达公告当事人 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:33
 */
public class DataCompanyDeliveryAnnouncementPartyId extends Id {

	public DataCompanyDeliveryAnnouncementPartyId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 企业送达公告当事人 领域模型id
	 * @param id
	 * @return
	 */
	public static DataCompanyDeliveryAnnouncementPartyId of(Long id){
		return new DataCompanyDeliveryAnnouncementPartyId(id);
	}
}
