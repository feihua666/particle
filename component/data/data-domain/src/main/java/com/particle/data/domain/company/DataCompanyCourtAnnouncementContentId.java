package com.particle.data.domain.company;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 企业法院公告内容 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:38:28
 */
public class DataCompanyCourtAnnouncementContentId extends Id {

	public DataCompanyCourtAnnouncementContentId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 企业法院公告内容 领域模型id
	 * @param id
	 * @return
	 */
	public static DataCompanyCourtAnnouncementContentId of(Long id){
		return new DataCompanyCourtAnnouncementContentId(id);
	}
}
