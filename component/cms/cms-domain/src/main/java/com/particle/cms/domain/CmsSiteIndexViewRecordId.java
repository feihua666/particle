package com.particle.cms.domain;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 站点首页访问记录 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:15:10
 */
public class CmsSiteIndexViewRecordId extends Id {

	public CmsSiteIndexViewRecordId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 站点首页访问记录 领域模型id
	 * @param id
	 * @return
	 */
	public static CmsSiteIndexViewRecordId of(Long id){
		return new CmsSiteIndexViewRecordId(id);
	}
}
