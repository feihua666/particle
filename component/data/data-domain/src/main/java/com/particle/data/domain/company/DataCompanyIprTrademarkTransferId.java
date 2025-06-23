package com.particle.data.domain.company;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 企业知识产权商标转让信息 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:16:04
 */
public class DataCompanyIprTrademarkTransferId extends Id {

	public DataCompanyIprTrademarkTransferId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 企业知识产权商标转让信息 领域模型id
	 * @param id
	 * @return
	 */
	public static DataCompanyIprTrademarkTransferId of(Long id){
		return new DataCompanyIprTrademarkTransferId(id);
	}
}
