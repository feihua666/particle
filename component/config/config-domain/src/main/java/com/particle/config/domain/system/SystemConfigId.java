package com.particle.config.domain.system;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 系统参数配置 领域模型id
 * </p>
 *
 * @author yw
 * @since 2024-05-30 10:29:04
 */
public class SystemConfigId extends Id {

	public SystemConfigId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 系统参数配置 领域模型id
	 * @param id
	 * @return
	 */
	public static SystemConfigId of(Long id){
		return new SystemConfigId(id);
	}
}
