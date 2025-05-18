package com.particle.data.domain.company;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 企业股东 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:46:01
 */
public class DataCompanyShareholderId extends Id {

	public DataCompanyShareholderId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 企业股东 领域模型id
	 * @param id
	 * @return
	 */
	public static DataCompanyShareholderId of(Long id){
		return new DataCompanyShareholderId(id);
	}
}
