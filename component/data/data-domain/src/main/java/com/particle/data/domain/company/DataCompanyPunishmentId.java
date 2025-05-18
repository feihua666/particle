package com.particle.data.domain.company;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 企业行政处罚 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:43:37
 */
public class DataCompanyPunishmentId extends Id {

	public DataCompanyPunishmentId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 企业行政处罚 领域模型id
	 * @param id
	 * @return
	 */
	public static DataCompanyPunishmentId of(Long id){
		return new DataCompanyPunishmentId(id);
	}
}
