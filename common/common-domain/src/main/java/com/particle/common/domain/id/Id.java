package com.particle.common.domain.id;

import com.particle.global.dto.basic.DTO;
import lombok.Data;

/**
 * <p>
 * 领域模型的id基类
 * </p>
 *
 * @author yangwei
 * @since 2022-04-30 17:44
 */
@Data
public class Id extends DTO {

	public Long id;

	public Id(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return String.valueOf(id);
	}
}
