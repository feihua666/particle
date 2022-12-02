package com.particle.common.client.dto.data;

import io.swagger.annotations.ApiModelProperty;
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
	@ApiModelProperty("主键id")
	private Long id;

	@ApiModelProperty("父级parentId")
	private Long parentId;
}
