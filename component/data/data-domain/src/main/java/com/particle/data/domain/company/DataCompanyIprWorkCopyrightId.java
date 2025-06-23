package com.particle.data.domain.company;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 企业知识产权作品著作 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:16:45
 */
public class DataCompanyIprWorkCopyrightId extends Id {

	public DataCompanyIprWorkCopyrightId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 企业知识产权作品著作 领域模型id
	 * @param id
	 * @return
	 */
	public static DataCompanyIprWorkCopyrightId of(Long id){
		return new DataCompanyIprWorkCopyrightId(id);
	}
}
