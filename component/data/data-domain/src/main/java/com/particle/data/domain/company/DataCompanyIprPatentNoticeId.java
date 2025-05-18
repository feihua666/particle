package com.particle.data.domain.company;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 企业知识产权专利通知书信息 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:41:13
 */
public class DataCompanyIprPatentNoticeId extends Id {

	public DataCompanyIprPatentNoticeId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 企业知识产权专利通知书信息 领域模型id
	 * @param id
	 * @return
	 */
	public static DataCompanyIprPatentNoticeId of(Long id){
		return new DataCompanyIprPatentNoticeId(id);
	}
}
