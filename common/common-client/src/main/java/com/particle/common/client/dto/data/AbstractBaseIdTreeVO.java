package com.particle.common.client.dto.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 基础VO，带id
 * </p>
 *
 * @author yangwei
 * @since 2022-05-17 16:57
 */
@Data
public class AbstractBaseIdTreeVO extends AbstractBaseVO{
	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	@Schema(description = "主键id")
	private Long id;

	@Schema(description = "父级parentId")
	private Long parentId;
}
