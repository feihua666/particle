package com.particle.data.domain.company;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 企业法院公告当事人 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:38:44
 */
public class DataCompanyCourtAnnouncementPartyId extends Id {

	public DataCompanyCourtAnnouncementPartyId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 企业法院公告当事人 领域模型id
	 * @param id
	 * @return
	 */
	public static DataCompanyCourtAnnouncementPartyId of(Long id){
		return new DataCompanyCourtAnnouncementPartyId(id);
	}
}
