package com.particle.lowcode.domain.generator;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 低代码数据源 领域模型id
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public class LowcodeDatasourceId extends Id {

	public LowcodeDatasourceId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 低代码数据源 领域模型id
	 * @param id
	 * @return
	 */
	public static LowcodeDatasourceId of(Long id){
		return new LowcodeDatasourceId(id);
	}
}
