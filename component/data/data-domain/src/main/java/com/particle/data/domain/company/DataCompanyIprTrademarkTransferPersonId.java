package com.particle.data.domain.company;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 企业知识产权商标转让人 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:13
 */
public class DataCompanyIprTrademarkTransferPersonId extends Id {

	public DataCompanyIprTrademarkTransferPersonId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 企业知识产权商标转让人 领域模型id
	 * @param id
	 * @return
	 */
	public static DataCompanyIprTrademarkTransferPersonId of(Long id){
		return new DataCompanyIprTrademarkTransferPersonId(id);
	}
}
