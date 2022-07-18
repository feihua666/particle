package com.particle.common.domain;

import com.particle.global.dto.basic.DTO;
import lombok.Data;

/**
 * <p>
 * 聚合根基类
 * </p>
 *
 * @author yangwei
 * @since 2022-05-05 16:32
 */
@Data
public abstract class AggreateRoot extends DTO {

	/**
	 * 单表时可用，数据版本
	 */
	private Integer version;
}
