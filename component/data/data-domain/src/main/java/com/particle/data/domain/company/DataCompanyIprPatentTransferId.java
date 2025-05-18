package com.particle.data.domain.company;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 企业知识产权专利转让信息 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:42:51
 */
public class DataCompanyIprPatentTransferId extends Id {

	public DataCompanyIprPatentTransferId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 企业知识产权专利转让信息 领域模型id
	 * @param id
	 * @return
	 */
	public static DataCompanyIprPatentTransferId of(Long id){
		return new DataCompanyIprPatentTransferId(id);
	}
}
