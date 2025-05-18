package com.particle.data.domain.company;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 企业开庭公告 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:44:31
 */
public class DataCompanyOpenCourtAnnouncementId extends Id {

	public DataCompanyOpenCourtAnnouncementId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 企业开庭公告 领域模型id
	 * @param id
	 * @return
	 */
	public static DataCompanyOpenCourtAnnouncementId of(Long id){
		return new DataCompanyOpenCourtAnnouncementId(id);
	}
}
