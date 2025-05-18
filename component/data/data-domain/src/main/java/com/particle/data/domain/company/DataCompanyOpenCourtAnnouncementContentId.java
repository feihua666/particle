package com.particle.data.domain.company;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 企业开庭公告内容 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:44:18
 */
public class DataCompanyOpenCourtAnnouncementContentId extends Id {

	public DataCompanyOpenCourtAnnouncementContentId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 企业开庭公告内容 领域模型id
	 * @param id
	 * @return
	 */
	public static DataCompanyOpenCourtAnnouncementContentId of(Long id){
		return new DataCompanyOpenCourtAnnouncementContentId(id);
	}
}
