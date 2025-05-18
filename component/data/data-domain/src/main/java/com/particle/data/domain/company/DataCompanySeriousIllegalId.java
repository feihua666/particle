package com.particle.data.domain.company;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 企业严重违法 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:45:45
 */
public class DataCompanySeriousIllegalId extends Id {

	public DataCompanySeriousIllegalId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 企业严重违法 领域模型id
	 * @param id
	 * @return
	 */
	public static DataCompanySeriousIllegalId of(Long id){
		return new DataCompanySeriousIllegalId(id);
	}
}
