package com.particle.data.domain.company;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 企业知识产权地理标识核准公告 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:16:21
 */
public class DataCompanyIprGeograApproveAnnouncementId extends Id {

	public DataCompanyIprGeograApproveAnnouncementId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 企业知识产权地理标识核准公告 领域模型id
	 * @param id
	 * @return
	 */
	public static DataCompanyIprGeograApproveAnnouncementId of(Long id){
		return new DataCompanyIprGeograApproveAnnouncementId(id);
	}
}
